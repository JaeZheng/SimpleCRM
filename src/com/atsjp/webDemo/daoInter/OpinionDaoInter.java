package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.Opinion;

import java.util.List;

public interface OpinionDaoInter {
    // 增加Opinion对象
    public boolean addOpinion(Opinion opinion);

    // 删除Opinion对象
    public boolean deleteOpinion(Opinion opinion);

    // 修改Opinion对象
    public boolean modifyOpinion(Opinion opinion);

    // 根据返回的Opinion对象的cname或者cphone，查找Opinion对象
    public Opinion getOpinion(Opinion opinion);

    // 返回所有Opinion对象
    public List<Opinion> page(int page, int pageSize);

    // 返回查询的Opinion列表
    public List<Opinion> queryOpinionList(String index, int page, int pageSize);

    // 返回查询的Opinion个数
    public int queryOpinionCount(String index);

    // 返回Opinion总记录数
    public int getCount();

    public boolean deleteOpinionAll();
}
