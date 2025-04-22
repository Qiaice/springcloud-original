package org.qiaice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.qiaice.entity.Borrow;
import org.qiaice.vo.UserBorrowVO;

public interface BorrowService extends IService<Borrow> {

    UserBorrowVO findByUid(Integer uid);
}
