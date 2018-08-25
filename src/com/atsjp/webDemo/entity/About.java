package com.atsjp.webDemo.entity;

/**
 * Created by G40 on 2018/8/25.
 */
public class About {

    private String software;
    private String banquan;
    private String address;
    private String linkman;
    private String linkphone;

    public About() {
        super();
    }

    public About(String software, String banquan, String address, String linkman, String linkphone) {
        this.software = software;
        this.banquan = banquan;
        this.address = address;
        this.linkman = linkman;
        this.linkphone = linkphone;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getBanquan() {
        return banquan;
    }

    public void setBanquan(String banquan) {
        this.banquan = banquan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    @Override
    public String toString() {
        return "About{" +
                "software='" + software + '\'' +
                ", banquan='" + banquan + '\'' +
                ", address='" + address + '\'' +
                ", linkman='" + linkman + '\'' +
                ", linkphone='" + linkphone + '\'' +
                '}';
    }
}
