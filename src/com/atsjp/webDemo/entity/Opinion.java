package com.atsjp.webDemo.entity;

public class Opinion {
    private String id;
    private String companyname;
    private String linkman;
    private String linkphone;
    private String opiniondetail;
    private String opinionstate;

    public Opinion() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Opinion(String id, String companyname, String linkman, String linkphone, String opiniondetail, String opinionstate) {
        super();
        this.id = id;
        this.companyname = companyname;
        this.linkman = linkman;
        this.linkphone = linkphone;
        this.opiniondetail = opiniondetail;
        this.opinionstate = opinionstate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkMan(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getOpiniondetail() {
        return opiniondetail;
    }

    public void setOpiniondetail(String opiniondetail) {
        this.opiniondetail = opiniondetail;
    }

    public String getOpinionstate() {
        return opinionstate;
    }

    public void setOpinionstate(String opinionstate) {
        this.opinionstate = opinionstate;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", companyname=" + companyname + ", linkman="
                + linkman + ", linkphone=" + linkphone + ", opiniondetail=" + opiniondetail + "]";
    }

}
