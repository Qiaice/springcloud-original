package org.qiaice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.qiaice.entity.Book;
import org.qiaice.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
public class UserBorrowVO {

    private User user;

    private List<Book> books;
}
