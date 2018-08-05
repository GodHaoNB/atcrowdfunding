package com.gh.dao;


import com.gh.pojo.extendes.UserVo;
import com.gh.utils.PagingResult;

import java.sql.SQLException;

import java.util.Map;

public interface UserMapper {

    String sql();

    UserVo  findUser(UserVo userVo) throws SQLException;


    UserVo findUserEdit(Integer id)throws SQLException;


    PagingResult<UserVo> findForPageUser(int page, int rows, Map<String, Object> params) throws SQLException;

    Integer saveUser( UserVo t) throws SQLException;

    Integer deleteUserById(Integer id)throws SQLException;
}
