package com.gh.pojo;

public class ActRuExecution {
    private String id;

    private Integer rev;

    private String procInstId;

    private String businessKey;

    private String parentId;

    private String procDefId;

    private String superExec;

    private String actId;

    private Byte isActive;

    private Byte isConcurrent;

    private Byte isScope;

    private Byte isEventScope;

    private Integer suspensionState;

    private Integer cachedEntState;

    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId == null ? null : procInstId.trim();
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey == null ? null : businessKey.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId == null ? null : procDefId.trim();
    }

    public String getSuperExec() {
        return superExec;
    }

    public void setSuperExec(String superExec) {
        this.superExec = superExec == null ? null : superExec.trim();
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId == null ? null : actId.trim();
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Byte getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(Byte isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public Byte getIsScope() {
        return isScope;
    }

    public void setIsScope(Byte isScope) {
        this.isScope = isScope;
    }

    public Byte getIsEventScope() {
        return isEventScope;
    }

    public void setIsEventScope(Byte isEventScope) {
        this.isEventScope = isEventScope;
    }

    public Integer getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    public Integer getCachedEntState() {
        return cachedEntState;
    }

    public void setCachedEntState(Integer cachedEntState) {
        this.cachedEntState = cachedEntState;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}