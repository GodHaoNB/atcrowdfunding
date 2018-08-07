package com.gh.service.impl;

import com.alibaba.fastjson.JSON;
import com.gh.dao.PermissionMapper;
import com.gh.pojo.extendes.PermissionVo;
import com.gh.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Autowired
    @Qualifier("permissionMapper")
    PermissionMapper permissionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PermissionVo> findAllPrimaryZTree() throws SQLException {
        List<PermissionVo> allpermissions = permissionMapper.findAllPrimary();
        //区分根节点
        List<PermissionVo> rootpermissions = allpermissions.stream().filter(permissionVo -> (permissionVo.getPid() == 0)).collect(Collectors.toList());
        //区分非根节点
        List<PermissionVo> subpermissions = allpermissions.stream().filter(permissionVo -> (permissionVo.getPid() != 0)).collect(Collectors.toList());
        rootpermissions.forEach(permission ->
                bindSubs(permission, subpermissions)
        );
        return rootpermissions;
    }

    @Override
    public List<PermissionVo> findAllPrimarys() throws SQLException {
        return permissionMapper.findAllPrimary();
    }

    @Override
    public Boolean savePermission(PermissionVo permissionVo)throws SQLException {
        return permissionMapper.insertSelective(permissionVo)>=1;
    }

    /** List<PermissionVo> findAllPrimary()throws SQLException;
     * 使用递归构建菜单树
     *
     * @param rootpermission
     * @param subpermissions
     */
    private void bindSubs(PermissionVo rootpermission, List<PermissionVo> subpermissions) {
        List<PermissionVo> childrens = subpermissions.stream().filter(subpermission ->
                (subpermission.getPid() == rootpermission.getId()))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(childrens)) {

            rootpermission.setOpen(true);
            //子节点集合不为空
            rootpermission.setChildren(childrens);
            rootpermission.setOpen(true);
            logger.info("子节点-->>>>>>>>>>>>>>>>" + rootpermission.getName() +
                    "+*****+" + rootpermission.getOpen() + "->>>>>>>>>>" + JSON.toJSONString(rootpermission.getChildren()));
            //再次循环
            childrens.forEach(children -> bindSubs(children, subpermissions));
        }
    }

}
