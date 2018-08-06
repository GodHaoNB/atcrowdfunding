package com.gh.service.impl;

import com.gh.Exception.DelException;
import com.gh.Exception.LoingRunTime;

import com.gh.base.BaseMapper;
import com.gh.dao.RoleMapper;
import com.gh.dao.UserMapper;
import com.gh.pojo.TRole;
import com.gh.pojo.TUser;
import com.gh.pojo.TUserRole;
import com.gh.pojo.extendes.RoleVo;
import com.gh.pojo.extendes.UserRoleVo;
import com.gh.pojo.extendes.UserVo;
import com.gh.service.RoleService;
import com.gh.service.UserService;
import com.gh.utils.Data;
import com.gh.utils.MD5Util;
import com.gh.utils.PagingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService, RoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    @Qualifier("baseuserMapper")
    BaseMapper baseMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;


    @Override
    @Transactional(readOnly = true)
    public TUser findLoignByUser(UserVo userVo) throws SQLException {
        logger.info("Service -------------->Login----------->Start");

        TUser user = baseMapper.find(userVo);
        if (user == null) {
            logger.info("Service -------------->Login----------->Exception");

            throw new LoingRunTime("登录失败，查不到用户");
        }
        logger.info("Service -------------->Login----------->End");
        return user;
    }


    @Override
    @Transactional(readOnly = true)
    public PagingResult<UserVo> findForPageUser(int page, int rows, Map<String, Object> params) throws SQLException {
        return baseMapper.findForPage(page, rows, params);
    }

    @Override
    public Integer saveUser(UserVo userVo) throws SQLException {

        /*for (int i = 1; i <= 100; i++) {
            UserVo user = new UserVo();
            user.setLoginacct("test" + i);
            user.setUserpswd(MD5Util.digest("root"));
            user.setUsername("test" + i);
            user.setEmail("test" + i + "@godhao.com");
            user.setCreatetime("2017-09-23 14:17:00");
            userMapper.saveUser(user);
        }*/
        return userMapper.saveUser(userVo);
    }

    @Override
    @Transactional(readOnly = true)
    public UserVo findEidt(Integer id) throws SQLException {
        return baseMapper.find(id);
    }

    @Override
    public Integer editUser(UserVo user) throws SQLException {

        return baseMapper.editModfiy(user);
    }

    @Override
    public Integer deleteUserById(Integer id) throws SQLException {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer deleteUserById(Integer[] ids) throws SQLException {
        Integer delLength = 0;
        if (ids != null && ids.length != 0) {
            for (Integer id : ids) {

                delLength += userMapper.deleteUserById(id);
            }
            if (delLength != ids.length) {
                throw new DelException("用户删除失败");
            } else {
                delLength = 1;
            }
        } else {
            delLength = -1;
            throw new NullPointerException("删除的用户为空");
        }
        return delLength;
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) throws SQLException {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertSelective(RoleVo record) throws SQLException {
        return roleMapper.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleVo selectByPrimaryKey(Integer id) throws SQLException {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(RoleVo record) throws SQLException {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PagingResult<RoleVo> findForPageRole(int page, int rows, RoleVo roleVo) throws SQLException {
        return baseMapper.findForPage(page, rows, roleVo);
    }

    @Override
    public Integer deleteRoleByIds(Integer[] ids) throws SQLException {
        Integer delLength = 0;
        if (ids != null && ids.length != 0) {
            for (Integer id : ids) {

                delLength += roleMapper.deleteByPrimaryKey(id);
            }
            if (delLength != ids.length) {
                throw new DelException("角色删除失败");
            } else {
                delLength = 1;
            }
        } else {
            delLength = -1;
            throw new NullPointerException("删除的角色为空");
        }
        return delLength;
    }

    @Override
    public List<RoleVo> findAllRole() throws SQLException {
        return roleMapper.findAllRole();
    }

    @Override
    public List<Integer> findAllByRoleId(Integer id) throws SQLException {
        List<Integer> listRoleid = new ArrayList<>();
        roleMapper.findAllByRoleId(id).forEach(
                listroleid -> {
                    listRoleid.add(listroleid.getRoleid());
                }
        );
        return listRoleid;
    }

    @Override
    public boolean deleteUserRoleRelationship(Integer userid, Data data) {
        Integer cont = roleMapper.deleteUserRoleRelationship(userid,data.getRoleids());
        return cont==data.getRoleids().size();
    }

    @Override
    public boolean saveUserRoleRelationship(Integer userid, Data data) {
        Integer cont = roleMapper.saveUserRoleRelationship(userid,data.getRoleids());
        return cont==data.getRoleids().size();
    }
}
