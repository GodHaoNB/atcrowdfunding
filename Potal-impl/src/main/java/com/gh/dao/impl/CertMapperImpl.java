package com.gh.dao.impl;

import com.gh.base.BaseMapper;
import com.gh.base.BaseMybatisDAO;
import com.gh.pojo.base.BaseEntity;
import com.gh.utils.PagingResult;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Repository("CertMapperImpl")
public class CertMapperImpl extends BaseMybatisDAO implements BaseMapper {
    private final static String CERT_NAMESPACE = "com.gh.dao.CertMapper";
    @Override
    public String sql() {
        return null;
    }

    @Override
    public <T extends BaseEntity> T find(T t) throws SQLException {
        return null;
    }

    @Override
    public <T extends BaseEntity> T find(Integer id) throws SQLException {
        return null;
    }

    @Override
    public <T extends BaseEntity> PagingResult<T> findForPage(int page, int rows, Map<String, Object> params) throws SQLException {
        return null;
    }

    @Override
    public <T extends BaseEntity> PagingResult<T> findForPage(int page, int rows, BaseEntity baseEntity) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        if(null!= baseEntity.getName() && !baseEntity.getName().trim().equals("")){
            params.put("name", baseEntity.getName());
        }
        return super.findForPage(CERT_NAMESPACE + ".cont", CERT_NAMESPACE + ".findForPageRole",
                page, rows, params);
    }

    @Override
    public <T extends BaseEntity> Integer save(T t) throws SQLException {
        return null;
    }

    @Override
    public <T extends BaseEntity> Integer editModfiy(T t) throws SQLException {
        return null;
    }

    @Override
    public Integer deleteById(Integer id) throws SQLException {
        return null;
    }
}
