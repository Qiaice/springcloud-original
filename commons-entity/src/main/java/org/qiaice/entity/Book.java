package org.qiaice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {

    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    private String title;

    @TableField(value = "`desc`")
    private String desc;

    private Integer hold;
}
