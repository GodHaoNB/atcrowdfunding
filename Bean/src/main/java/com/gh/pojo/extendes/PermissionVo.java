package com.gh.pojo.extendes;

import com.gh.pojo.TPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PermissionVo extends TPermission {
    @Setter
    @Getter
    private List<PermissionVo> children;
    @Setter
    @Getter
    private boolean open;

}
