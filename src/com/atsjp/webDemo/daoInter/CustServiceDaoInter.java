package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.CustService;

import java.util.List;

public interface CustServiceDaoInter {
    // 增加CustService对象
    public boolean addCustService(CustService custService);

    // 删除CustService对象
    public boolean deleteCustService(CustService custService);

    // 修改CustService对象
    public boolean modifyCustService(CustService custService);

    // 根据返回的CustService对象的cname或者cphone，查找CustService对象
    public CustService getCustService(CustService custService);

    // 返回所有CustService对象
    public List<CustService> page(int page, int pageSize);

    // 返回查询的CustService列表
    public List<CustService> queryCustServiceList(String index, int page, int pageSize);

    // 返回查询的CustService个数
    public int queryCustServiceCount(String index);

    // 返回CustService总记录数
    public int getCount();

    public boolean deleteCustServiceAll();
}
