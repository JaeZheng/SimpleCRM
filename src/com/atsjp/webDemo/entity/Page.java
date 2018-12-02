package com.atsjp.webDemo.entity;

import com.atsjp.webDemo.dao.*;

public class Page {
	private int Page = 0;// ��ҳ��
	private int TotalCount = 0;// �ܼ�¼��
	private int CurrentPage = 0;// ��ǰҳ
	private int PageSize = 7; // ÿҳ��ʾ����Ĭ��7
	CompanyDao companyDao = new CompanyDao();
	ContractDao contractDao = new ContractDao();
	CustServiceDao custServiceDao = new CustServiceDao();
	OpinionDao opinionDao = new OpinionDao();
	LostDao lostDao = new LostDao();

	public Page() {// ������ʱ�򣬳�ʼ��Page��TotalCount
		super();
	}

	public Page(int page, int currentPage, int pageSize, int totalCount) {
		super();
		Page = page;
		CurrentPage = currentPage;
		PageSize = pageSize;
		TotalCount = totalCount;
	}

	// ��ȡcustomer���µ����ݿ���Ϣ
	public boolean getNew() {
		try {
			this.TotalCount = new CustomerDao().getCount();
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ��ѯ����������company����
	public boolean queryCompanyCount(String index){
		try {
			this.TotalCount = companyDao.queryCompanyCount(index);
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e){
			return false;
		}
	}

    // ��ѯ����������contract����
    public boolean queryContractCount(String index){
        try {
            this.TotalCount = contractDao.queryContractCount(index);
            double dtc = (double) TotalCount;
            double dps = (double) PageSize;
            this.Page = (int) Math.ceil(dtc / dps);
            return true;
        } catch (Exception e){
            return false;
        }
    }

	// ��ѯ����������custService����
	public boolean queryCustServiceCount(String index){
		try {
			this.TotalCount = custServiceDao.queryCustServiceCount(index);
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	// ��ѯ����������opinion����
	public boolean queryOpinionCount(String index){
		try {
			this.TotalCount = opinionDao.queryOpinionCount(index);
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public boolean queryLostCount(String index){
		try {
			this.TotalCount = lostDao.queryLostCount(index);
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	// ��ȡcompany���µ����ݿ���Ϣ
	public boolean getCompanyNew() {
		try {
			this.TotalCount = companyDao.getCount();
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    // ��ȡcontract���µ����ݿ���Ϣ
    public boolean getContractNew() {
        try {
            this.TotalCount = contractDao.getCount();
            double dtc = (double) TotalCount;
            double dps = (double) PageSize;
            this.Page = (int) Math.ceil(dtc / dps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	// ��ȡcustService���µ����ݿ���Ϣ
	public boolean getCustServiceNew() {
		try {
			this.TotalCount = custServiceDao.getCount();
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ��ȡopinion���µ����ݿ���Ϣ
	public boolean getOpinionNew() {
		try {
			this.TotalCount = opinionDao.getCount();
			double dtc = (double) TotalCount;
			double dps = (double) PageSize;
			this.Page = (int) Math.ceil(dtc / dps);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    public boolean getLostNew() {
        try {
            this.TotalCount = lostDao.getCount();
            double dtc = (double) TotalCount;
            double dps = (double) PageSize;
            this.Page = (int) Math.ceil(dtc / dps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public int getPage() {
		return Page;
	}

	public void setPage(int page) {
		Page = page;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	@Override
	public String toString() {
		return "Page [Page=" + Page + ", CurrentPage=" + CurrentPage
				+ ", PageSize=" + PageSize + ", TotalCount=" + TotalCount + "]";
	}

}
