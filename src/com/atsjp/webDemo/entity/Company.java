package com.atsjp.webDemo.entity;

public class Company {
    private String id;
    private String companyname;
    private String linkman;
    private String linkphone;
    private String address;

    public Company() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Company(String id, String companyname, String linkman, String linkphone, String address) {
        super();
        this.id = id;
        this.companyname = companyname;
        this.linkman = linkman;
        this.linkphone = linkphone;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", companyname=" + companyname + ", linkman="
                + linkman + ", linkphone=" + linkphone + ", address=" + address + "]";
    }

}
