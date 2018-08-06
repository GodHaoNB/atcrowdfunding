package com.gh.service.impl;

import com.gh.dao.PermissionMapper;
import com.gh.pojo.extendes.PermissionVo;
import com.gh.service.PermissionService;
import org.activiti.engine.impl.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    @Qualifier("permissionMapper")
    PermissionMapper permissionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PermissionVo> findAllPrimary() throws SQLException {
        List<PermissionVo> allpermissions = permissionMapper.findAllPrimary();
        //区分根节点
        List<PermissionVo> rootpermissions = allpermissions.stream().filter(permissionVo -> (permissionVo.getPid() == 0)).collect(Collectors.toList());
        //区分非根节点
        List<PermissionVo> subpermissions = allpermissions.stream().filter(permissionVo -> (permissionVo.getPid() != 0)).collect(Collectors.toList());
        rootpermissions.forEach(permission -> bindSubs(permission, subpermissions));
        return rootpermissions;
    }

    /**
     * 使用递归构建菜单树
     * @param rootpermission
     * @param subpermissions
     */
    private void bindSubs(PermissionVo rootpermission, List<PermissionVo> subpermissions) {
        List<PermissionVo> childrens = subpermissions.stream().filter(subpermission ->
                (subpermission.getPid() == rootpermission.getId()))
                .collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(childrens)){//子节点集合不为空
            rootpermission.setChildren(childrens);
            //再次循环
            childrens.forEach(children->bindSubs(children,subpermissions));
        }
    }
}
