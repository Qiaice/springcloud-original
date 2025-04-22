package org.qiaice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.qiaice.entity.Book;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
