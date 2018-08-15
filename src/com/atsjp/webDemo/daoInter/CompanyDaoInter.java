package com.atsjp.webDemo.daoInter;

import java.util.List;

import com.atsjp.webDemo.entity.Company;

public interface CompanyDaoInter {
    // ����Company����
    public boolean addCompany(Company Company);

    // ɾ��Company����
    public boolean deleteCompany(Company Company);

    // �޸�Company����
    public boolean modifyCompany(Company Company);

    // ���ݷ��ص�Company�����cname����cphone������Company����
    public Company getCompany(Company Company);

    // ��������Company����
    public List<Company> page(int page, int pageSize);

    // ����Company�ܼ�¼��
    public int getCount();
}
