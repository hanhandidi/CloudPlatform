package com.neu.authority.entity;


import java.util.List;

public class TPermit {

    private long id;
    private long flag;
    private long status;
    private String permitName;
    private String permitPath;
    private String permitIcon;
    private String permitDesc;
    private long parentId;
    private List<TPermit> permitSubs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getFlag() {
        return flag;
    }

    public void setFlag(long flag) {
        this.flag = flag;
    }


    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }


    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }


    public String getPermitPath() {
        return permitPath;
    }

    public void setPermitPath(String permitPath) {
        this.permitPath = permitPath;
    }


    public String getPermitIcon() {
        return permitIcon;
    }

    public void setPermitIcon(String permitIcon) {
        this.permitIcon = permitIcon;
    }


    public String getPermitDesc() {
        return permitDesc;
    }

    public void setPermitDesc(String permitDesc) {
        this.permitDesc = permitDesc;
    }


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<TPermit> getPermitSubs() {
        return permitSubs;
    }

    public void setPermitSubs(List<TPermit> permitSubs) {
        this.permitSubs = permitSubs;
    }
}
