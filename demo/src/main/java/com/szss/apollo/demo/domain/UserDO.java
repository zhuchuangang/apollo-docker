package com.szss.apollo.demo.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * @author 鼠笑天
 * @date 2018/8/14
 */
@Data
@TableName("t_user")
public class UserDO {
    @TableId
    private Long id;

    private String username;
}
