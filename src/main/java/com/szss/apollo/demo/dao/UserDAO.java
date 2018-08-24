package com.szss.apollo.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szss.apollo.demo.domain.UserDO;

/**
 * @author 鼠笑天
 * @date 2018/8/14
 */
@Mapper
public interface UserDAO extends BaseMapper<UserDO> {

}
