package com.gh.dao.impl;

import com.gh.base.BaseMapper;
import com.gh.base.BaseMybatisDAO;
import com.gh.pojo.base.BaseEntity;
import com.gh.utils.PagingResult;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository("baseuserMapper")
public class UserMapperImpl extends BaseMybatisDAO implements BaseMapper {
    private final static String USER_NAMESPACE = "com.gh.dao.UserMapper";
    private final static String ROLE_NAMESPACE = "com.gh.dao.RoleMapper";

    @Override
    public <T extends BaseEntity> PagingResult<T> findForPage(int page, int rows, Map<String, Object> params) throws SQLException {
        return super.findForPage(USER_NAMESPACE + ".cont", USER_NAMESPACE + ".findForPageUser",
                page, rows, params);
    }

    @Override
    public <T extends BaseEntity> PagingResult<T> findForPage(int page, int rows, BaseEntity baseEntity) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        System.out.print("asdasdasd>>>>>"+baseEntity);
        if(null!= baseEntity.getName() && !baseEntity.getName().trim().equals("")){
            params.put("name", baseEntity.getName());
        }

        return super.findForPage(ROLE_NAMESPACE + ".cont", ROLE_NAMESPACE + ".findForPageRole",
                page, rows, params);
    }

    @Override
    public <T extends BaseEntity> Integer save(T userVo) throws SQLException {
        return super.save(USER_NAMESPACE + ".saveUser", userVo);
    }

    @Override
    public <T extends BaseEntity> Integer editModfiy(T t) throws SQLException {
        return super.EditModfiy(USER_NAMESPACE + ".editModfityUserByid", t);
    }

    @Override
    public Integer deleteById(Integer id) throws SQLException {
        return super.deleteById(USER_NAMESPACE + ".deleteUserById", id);
    }

    @Override
    public String sql() {
        return null;
    }

    @Override
    public <T extends BaseEntity> T find(T userVo) throws SQLException {
        return super.find(USER_NAMESPACE + ".findUser", userVo);
    }

    @Override
    public <T extends BaseEntity> T find(Integer id) throws SQLException {

        return super.find(USER_NAMESPACE + ".findUserEdit", id);
    }

}
