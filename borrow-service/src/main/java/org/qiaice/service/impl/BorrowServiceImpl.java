package org.qiaice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.qiaice.entity.Book;
import org.qiaice.entity.Borrow;
import org.qiaice.entity.User;
import org.qiaice.mapper.BorrowMapper;
import org.qiaice.service.BorrowService;
import org.qiaice.service.client.BookClient;
import org.qiaice.service.client.UserClient;
import org.qiaice.vo.UserBorrowVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    private final UserClient userClient;
    private final BookClient bookClient;

    @Override
    public UserBorrowVO findByUid(Integer uid) {
        List<Borrow> borrows = lambdaQuery().eq(Borrow::getUid, uid).list();
        User user = userClient.findByUid(uid);
        List<Book> books = borrows.stream()
                .map(borrow -> bookClient.findByBid(borrow.getBid()))
                .toList();
        return new UserBorrowVO(user, books);
    }

    @Override
    @GlobalTransactional
    public void borrow(Integer uid, Integer bid) {
        if (bookClient.getCountByBid(bid) < 1) {
            throw new RuntimeException("图书数量不足");
        }
        if (userClient.getHoldByUid(uid) > 2) {
            throw new RuntimeException("用户借阅量已达上限");
        }
        if (!bookClient.borrow(bid)) {
            throw new RuntimeException("在借阅图书时发生错误");
        }
        Borrow borrow = lambdaQuery().eq(Borrow::getUid, uid).eq(Borrow::getBid, bid).one();
        if (borrow != null) {
            throw new RuntimeException("此书籍已被此用户借阅");
        }
        if (!save(new Borrow(null, uid, bid))) {
            throw new RuntimeException("在录入借阅信息是发生错误");
        }
        if (!userClient.borrow(uid)) {
            throw new RuntimeException("在用户借阅图书时发生错误");
        }
    }
}
