package com.gh.dao;


import com.gh.pojo.TRole;
import com.gh.pojo.TUserRole;
import com.gh.pojo.extendes.RoleVo;
import com.gh.pojo.extendes.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    Integer deleteByPrimaryKey(Integer id);


    Integer insertSelective(RoleVo record);

    RoleVo selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(RoleVo record);

    Integer updateByPrimaryKey(RoleVo record);

    List<RoleVo> findAllRole();

    List<UserRoleVo> findAllByRoleId(Integer id);

    Integer deleteUserRoleRelationship(@Param("userid") Integer userid,@Param("roleids")  List<Integer> roleids);

    Integer saveUserRoleRelationship(@Param("userid") Integer userid, @Param("roleids")List<Integer> roleids);
}