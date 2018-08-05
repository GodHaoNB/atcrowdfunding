package com.gh.dao;


import com.gh.pojo.extendes.RoleVo;

public interface RoleMapper {
    Integer deleteByPrimaryKey(Integer id);


    Integer insertSelective(RoleVo record);

    RoleVo selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(RoleVo record);

    Integer updateByPrimaryKey(RoleVo record);
}