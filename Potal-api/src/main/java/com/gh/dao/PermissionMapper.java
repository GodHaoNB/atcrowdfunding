package com.gh.dao;

import com.gh.pojo.extendes.PermissionVo;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PermissionVo record);

    PermissionVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionVo record);

    List<PermissionVo> findAllPrimary();
}
