package com.gh.base;

import com.gh.pojo.base.BaseEntity;

import com.gh.utils.PagingResult;


import java.sql.SQLException;
import java.util.Map;

public interface BaseMapper {

    String sql();

    /**
     * 根据多个字段查找
     *
     * @param t
     * @return
     * @throws SQLException
     */
    <T extends BaseEntity> T find(T t) throws SQLException;

    /**
     * 根据id查找
     * @param id
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T extends BaseEntity> T find(Integer id)throws SQLException;

    /**
     * 分页查询
     * @param page
     * @param rows
     * @param params
     * @return
     * @throws SQLException
     */
    <T extends BaseEntity> PagingResult<T> findForPage(int page, int rows, Map<String, Object> params) throws SQLException;
    <T extends BaseEntity> PagingResult<T> findForPage(int page, int rows, BaseEntity baseEntity) throws SQLException;
    <T extends BaseEntity> Integer save(T t) throws SQLException;

    /**
     * 修改
     * @param t
     * @param <T>
     * @return
     * @throws SQLException
     */
    <T extends BaseEntity>  Integer editModfiy(T t) throws SQLException;
    Integer deleteById(Integer id)throws SQLException;
}
