package org.qiaice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
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
}
