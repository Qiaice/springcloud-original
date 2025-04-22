package org.qiaice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.qiaice.entity.Book;
import org.qiaice.entity.Borrow;
import org.qiaice.entity.User;
import org.qiaice.mapper.BorrowMapper;
import org.qiaice.service.BorrowService;
import org.qiaice.vo.UserBorrowVO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    private final RestTemplate template;

    @Override
    public UserBorrowVO findByUid(Integer uid) {
        List<Borrow> borrows = lambdaQuery().eq(Borrow::getUid, uid).list();
        User user = template.getForObject("http://user-service/api/user/" + uid, User.class);
        List<Book> books = borrows.stream()
                .map(borrow -> template.getForObject("http://book-service/api/book/" + borrow.getBid(), Book.class))
                .toList();
        return new UserBorrowVO(user, books);
    }
}
