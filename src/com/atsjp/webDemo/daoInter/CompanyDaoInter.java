package com.atsjp.webDemo.daoInter;

import java.util.List;

import com.atsjp.webDemo.entity.Company;

public interface CompanyDaoInter {
    // 增加Company对象
    public boolean addCompany(Company company);

    // 删除Company对象
    public boolean deleteCompany(Company company);

    // 修改Company对象
    public boolean modifyCompany(Company company);

    // 根据返回的Company对象的cname或者cphone，查找Company对象
    public Company getCompany(Company company);

    // 返回所有Company对象
    public List<Company> page(int page, int pageSize);

    // 返回查询的Company列表
    public List<Company> queryCompanyList(String index, int page, int pageSize);

    // 返回查询的Company个数
    public int queryCompanyCount(String index);

    // 返回Company总记录数
    public int getCount();

    // 删除全部Company
    public boolean deleteCompanyAll();
}
