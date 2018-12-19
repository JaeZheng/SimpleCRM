package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.Opinion;

import java.util.List;

public interface OpinionDaoInter {
    // ����Opinion����
    public boolean addOpinion(Opinion opinion);

    // ɾ��Opinion����
    public boolean deleteOpinion(Opinion opinion);

    // �޸�Opinion����
    public boolean modifyOpinion(Opinion opinion);

    // ���ݷ��ص�Opinion�����cname����cphone������Opinion����
    public Opinion getOpinion(Opinion opinion);

    // ��������Opinion����
    public List<Opinion> page(int page, int pageSize);

    // ���ز�ѯ��Opinion�б�
    public List<Opinion> queryOpinionList(String index, int page, int pageSize);

    // ���ز�ѯ��Opinion����
    public int queryOpinionCount(String index);

    // ����Opinion�ܼ�¼��
    public int getCount();

    public boolean deleteOpinionAll();
}
