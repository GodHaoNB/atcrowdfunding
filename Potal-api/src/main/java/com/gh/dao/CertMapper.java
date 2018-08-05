package com.gh.dao;


import com.gh.pojo.extendes.CertVo;

import java.sql.SQLException;

public interface CertMapper {
    int deleteByPrimaryKey(Integer id) throws SQLException;


    int insertSelective(CertVo record) throws SQLException;

    CertVo selectByPrimaryKey(Integer id) throws SQLException;

    int updateByPrimaryKeySelective(CertVo record) throws SQLException;

}
