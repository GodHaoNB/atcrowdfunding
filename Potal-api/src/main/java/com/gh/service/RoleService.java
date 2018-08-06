package com.gh.service;


import com.gh.pojo.TRole;
import com.gh.pojo.TUserRole;
import com.gh.pojo.extendes.RoleVo;
import com.gh.pojo.extendes.UserRoleVo;
import com.gh.utils.Data;
import com.gh.utils.PagingResult;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RoleService {

    Integer deleteByPrimaryKey(Integer id)throws SQLException;

    Integer insertSelective(RoleVo record)throws SQLException;

    RoleVo selectByPrimaryKey(Integer id)throws SQLException;

    Integer updateByPrimaryKeySelective(RoleVo record)throws SQLException;


    /**
     * 分页查询角色
     * @param page
     * @param rows
     * @param roleVo
     * @return
     * @throws SQLException
     */
    PagingResult<RoleVo> findForPageRole(int page, int rows,RoleVo roleVo) throws SQLException;

    Integer deleteRoleByIds(Integer[] id) throws SQLException;

    List<RoleVo> findAllRole()throws SQLException;

    List<Integer> findAllByRoleId(Integer id)throws SQLException;

    boolean deleteUserRoleRelationship(Integer userid, Data data);

    boolean saveUserRoleRelationship(Integer userid, Data data);
}
