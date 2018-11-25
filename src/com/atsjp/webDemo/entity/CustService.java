package com.atsjp.webDemo.entity;

public class CustService {
    private String id;
    private String customername;
    private String linkman;
    private String linkphone;
    private String servicetype;
    private String servicedate;
    private String estimatedcost;
    private String actualcost;
    private String satisfaction;

    public CustService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CustService(String id, String customername, String linkman, String linkphone, String servicetype,
                       String servicedate, String estimatedcost, String actualcost, String satisfaction) {
        super();
        this.id = id;
        this.customername = customername;
        this.linkman = linkman;
        this.linkphone = linkphone;
        this.servicetype = servicetype;
        this.servicedate = servicedate;
        this.estimatedcost = estimatedcost;
        this.actualcost = actualcost;
        this.satisfaction = satisfaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
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

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getServicedate() {
        return servicedate;
    }

    public void setServicedate(String servicedate) {
        this.servicedate = servicedate;
    }

    public String getEstimatedcost() {
        return estimatedcost;
    }

    public void setEstimatedcost(String estimatedcost) {
        this.estimatedcost = estimatedcost;
    }

    public String getActualcost() {
        return actualcost;
    }

    public void setActualcost(String actualcost) {
        this.actualcost = actualcost;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", customername=" + customername + ", linkman="
                + linkman + ", linkphone=" + linkphone + ", servicetype=" + servicetype + "]";
    }

}
