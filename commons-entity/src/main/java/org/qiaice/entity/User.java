package org.qiaice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private String uname;

    private Integer age;

    private String gender;

    private Integer hold;
}
