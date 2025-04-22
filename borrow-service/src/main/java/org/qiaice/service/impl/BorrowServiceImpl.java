package org.qiaice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    private static final RestTemplate template = new RestTemplate();

    @Override
    public UserBorrowVO findByUid(Integer uid) {
        List<Borrow> borrows = lambdaQuery().eq(Borrow::getUid, uid).list();
        User user = template.getForObject("http://localhost:8101/api/user/" + uid, User.class);
        List<Book> books = borrows.stream()
                .map(borrow -> template.getForObject("http://localhost:8201/api/book/" + borrow.getBid(), Book.class))
                .toList();
        return new UserBorrowVO(user, books);
    }
}
