package com.gh.service;

import com.gh.pojo.extendes.PermissionVo;

import java.sql.SQLException;
import java.util.List;

public interface PermissionService {
    /**
     * 查找所有的菜单 并实现分类
     * @return
     * @throws SQLException
     */
    List<PermissionVo> findAllPrimaryZTree()throws SQLException;
    List<PermissionVo> findAllPrimarys()throws SQLException;

    Boolean savePermission(PermissionVo permissionVo)throws SQLException;
}
