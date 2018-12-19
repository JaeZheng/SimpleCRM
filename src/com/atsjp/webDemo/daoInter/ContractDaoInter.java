package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.Contract;

import java.util.List;

public interface ContractDaoInter {
    // ����Contract����
    public boolean addContract(Contract Contract);

    // ɾ��Contract����
    public boolean deleteContract(Contract Contract);

    // �޸�Contract����
    public boolean modifyContract(Contract Contract);

    // ���ݷ��ص�Contract�����cname����cphone������Contract����
    public Contract getContract(Contract Contract);

    // ��������Contract����
    public List<Contract> page(int page, int pageSize);

    // ���ز�ѯ��Contract��¼��
    public int queryContractCount(String index);

    // ���ز�ѯ��Contract�б�
    public List<Contract> queryContractList(String index, int page, int pageSize);

    // ����Contract�ܼ�¼��
    public int getCount();

    public boolean deleteContractAll();
}
