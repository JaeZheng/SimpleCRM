package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.CustService;

import java.util.List;

public interface CustServiceDaoInter {
    // ����CustService����
    public boolean addCustService(CustService custService);

    // ɾ��CustService����
    public boolean deleteCustService(CustService custService);

    // �޸�CustService����
    public boolean modifyCustService(CustService custService);

    // ���ݷ��ص�CustService�����cname����cphone������CustService����
    public CustService getCustService(CustService custService);

    // ��������CustService����
    public List<CustService> page(int page, int pageSize);

    // ���ز�ѯ��CustService�б�
    public List<CustService> queryCustServiceList(String index, int page, int pageSize);

    // ���ز�ѯ��CustService����
    public int queryCustServiceCount(String index);

    // ����CustService�ܼ�¼��
    public int getCount();

    public boolean deleteCustServiceAll();
}
