package com.atsjp.webDemo.entity;

public class Lost {
    private String id;
    private String joindate;
    private String lossdate;
    private String companyname;
    private String reason;

    public Lost() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Lost(String id, String joindate, String lossdate, String companyname, String reason) {
        super();
        this.id = id;
        this.joindate = joindate;
        this.lossdate = lossdate;
        this.companyname = companyname;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getLossdate() {
        return lossdate;
    }

    public void setLinkMan(String lossdate) {
        this.lossdate = lossdate;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Lost [id=" + id + ", joindate=" + joindate + ", lossdate="
                + lossdate + ", companyname=" + companyname + ", reason=" + reason + "]";
    }

}
