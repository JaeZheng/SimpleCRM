package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.Lost;

import java.util.List;

public interface LostDaoInter {
    // ����Lost����
    public boolean addLost(Lost lost);

    // ɾ��Lost����
    public boolean deleteLost(Lost lost);

    // �޸�Lost����
    public boolean modifyLost(Lost lost);

    // ���ݷ��ص�Lost�����cname����cphone������Lost����
    public Lost getLost(Lost lost);

    // ��������Lost����
    public List<Lost> page(int page, int pageSize);

    // ���ز�ѯ��Lost�б�
    public List<Lost> queryLostList(String index, int page, int pageSize);

    // ���ز�ѯ��Lost����
    public int queryLostCount(String index);

    // ����Lost�ܼ�¼��
    public int getCount();

    public boolean deleteLostAll();
}
