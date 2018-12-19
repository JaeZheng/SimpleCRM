package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.Contract;

import java.util.List;

public interface ContractDaoInter {
    // 增加Contract对象
    public boolean addContract(Contract Contract);

    // 删除Contract对象
    public boolean deleteContract(Contract Contract);

    // 修改Contract对象
    public boolean modifyContract(Contract Contract);

    // 根据返回的Contract对象的cname或者cphone，查找Contract对象
    public Contract getContract(Contract Contract);

    // 返回所有Contract对象
    public List<Contract> page(int page, int pageSize);

    // 返回查询的Contract记录数
    public int queryContractCount(String index);

    // 返回查询的Contract列表
    public List<Contract> queryContractList(String index, int page, int pageSize);

    // 返回Contract总记录数
    public int getCount();

    public boolean deleteContractAll();
}
