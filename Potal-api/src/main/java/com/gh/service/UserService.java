package com.gh.service;

import com.gh.pojo.TUser;

import com.gh.pojo.extendes.UserVo;
import com.gh.utils.PagingResult;

import java.sql.SQLException;

import java.util.Map;

public interface UserService {
    /**
     * 用户登录
     *
     * @param userVo
     * @return
     * @throws SQLException
     */
    TUser findLoignByUser(UserVo userVo) throws SQLException;


    /**
     * @param page   当前页码
     * @param rows   显示条数
     * @param params 条件参数 集合
     * @return 条件查询结果
     * @throws SQLException
     */
    PagingResult<UserVo> findForPageUser(int page, int rows, Map<String, Object> params)
            throws SQLException;

    /**
     * 保存用户信息
     *
     * @param userVo
     * @return
     * @throws SQLException
     */
    Integer saveUser(UserVo userVo) throws SQLException;

    /**
     * 根据用户id 查找用户
     *
     * @param id
     * @return
     * @throws SQLException
     */
    UserVo findEidt(Integer id) throws SQLException;

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    Integer editUser(UserVo user) throws SQLException;

    Integer deleteUserById(Integer id) throws SQLException;

    Integer deleteUserById(Integer[] id) throws SQLException;
}
