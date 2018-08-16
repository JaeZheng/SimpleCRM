package com.atsjp.webDemo.entity;

public class Contract {
    private String id;
    private String contracttime;
    private String contractname;
    private String invoicetitle;
    private String address;
    private String contractcontent;
    private String invoicedetail;
    private String invoicetime;
    private String invoicenumber;
    private String invoiceamount;

    public Contract() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Contract(String id, String contracttime, String contractname, String invoicetitle, String address,
                    String contractcontent, String invoicedetail, String invoicetime, String invoicenumber,
                    String invoiceamount) {
        this.id = id;
        this.contracttime = contracttime;
        this.contractname = contractname;
        this.invoicetitle = invoicetitle;
        this.address = address;
        this.contractcontent = contractcontent;
        this.invoicedetail = invoicedetail;
        this.invoicetime = invoicetime;
        this.invoicenumber = invoicenumber;
        this.invoiceamount = invoiceamount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContracttime() {
        return contracttime;
    }

    public void setContracttime(String contracttime) {
        this.contracttime = contracttime;
    }

    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getInvoicetitle() {
        return invoicetitle;
    }

    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractcontent() {
        return contractcontent;
    }

    public void setContractcontent(String contractcontent) {
        this.contractcontent = contractcontent;
    }

    public String getInvoicedetail() {
        return invoicedetail;
    }

    public void setInvoicedetail(String invoicedetail) {
        this.invoicedetail = invoicedetail;
    }

    public String getInvoicetime() {
        return invoicetime;
    }

    public void setInvoicetime(String invoicetime) {
        this.invoicetime = invoicetime;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public String getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(String invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    @Override
    public String toString() {
        return "Contract [" +
                "id='" + id +
                ", contracttime='" + contracttime +
                ", contractname='" + contractname +
                ", invoicetitle='" + invoicetitle +
                ", address='" + address +
                ", contractcontent='" + contractcontent +
                ", invoicedetail='" + invoicedetail +
                ", invoicetime='" + invoicetime +
                ", invoicenumber='" + invoicenumber +
                ", invoiceamount='" + invoiceamount +
                ']';
    }
}
