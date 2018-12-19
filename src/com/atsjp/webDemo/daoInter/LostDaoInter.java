package com.atsjp.webDemo.daoInter;

import com.atsjp.webDemo.entity.Lost;

import java.util.List;

public interface LostDaoInter {
    // 增加Lost对象
    public boolean addLost(Lost lost);

    // 删除Lost对象
    public boolean deleteLost(Lost lost);

    // 修改Lost对象
    public boolean modifyLost(Lost lost);

    // 根据返回的Lost对象的cname或者cphone，查找Lost对象
    public Lost getLost(Lost lost);

    // 返回所有Lost对象
    public List<Lost> page(int page, int pageSize);

    // 返回查询的Lost列表
    public List<Lost> queryLostList(String index, int page, int pageSize);

    // 返回查询的Lost个数
    public int queryLostCount(String index);

    // 返回Lost总记录数
    public int getCount();

    public boolean deleteLostAll();
}
