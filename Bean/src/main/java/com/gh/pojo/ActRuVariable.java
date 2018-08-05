package com.gh.pojo;

public class ActRuVariable {
    private String id;

    private Integer rev;

    private String type;

    private String name;

    private String executionId;

    private String procInstId;

    private String taskId;

    private String bytearrayId;

    private Double doubles;

    private Long longs;

    private String text;

    private String text2;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId == null ? null : executionId.trim();
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId == null ? null : procInstId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getBytearrayId() {
        return bytearrayId;
    }

    public void setBytearrayId(String bytearrayId) {
        this.bytearrayId = bytearrayId == null ? null : bytearrayId.trim();
    }

    public Double getDouble() {
        return doubles;
    }

    public void setDouble(Double doubles) {
        this.doubles = doubles;
    }

    public Long getLong() {
        return longs;
    }

    public void setLong(Long longs) {
        this.longs = longs;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2 == null ? null : text2.trim();
    }
}