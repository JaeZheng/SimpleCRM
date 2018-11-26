package com.atsjp.webDemo.dao;

import com.atsjp.webDemo.daoInter.CustServiceDaoInter;
import com.atsjp.webDemo.entity.CustService;
import com.atsjp.webDemo.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustServiceDao implements CustServiceDaoInter {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet res = null;

    /*
     * 
     * 增加custService对象
     */
    @Override
    public boolean addCustService(CustService custService) {
        try {
            conn = JDBC.getConnection();
            System.out.println("custServiceDao...");
            String sql1 = "insert into custService values(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, 0);
            ps.setString(2, custService.getCustomername());
            ps.setString(3, custService.getLinkman());
            ps.setString(4, custService.getLinkphone());
            ps.setString(5, custService.getServicetype());
            ps.setString(6, custService.getServicedate());
            ps.setString(7, custService.getEstimatedcost());
            ps.setString(8, custService.getActualcost());
            ps.setString(9, custService.getSatisfaction());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     * 
     * 删除custService对象
     */
    @Override
    public boolean deleteCustService(CustService custService) {
        String sql = "delete from custService where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, custService.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     * 
     * 修改custService对象
     */
    @Override
    public boolean modifyCustService(CustService custService) {
        String sql = "update custService set customername=?, linkman=?, linkphone=?, servicetype=?, servicedate=?," +
                " estimatedcost=?, actualcost=?, satisfaction=? where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, custService.getCustomername());
            ps.setString(2, custService.getLinkman());
            ps.setString(3, custService.getLinkphone());
            ps.setString(4, custService.getServicetype());
            ps.setString(5, custService.getServicedate());
            ps.setString(6, custService.getEstimatedcost());
            ps.setString(7, custService.getActualcost());
            ps.setString(8, custService.getSatisfaction());
            ps.setInt(9, Integer.parseInt(custService.getId()));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     * 
     * 根据返回的custService对象的custServiceName或者linkPhone，查找custService对象
     */
    @Override
    public CustService getCustService(CustService custService) {
        CustService tempC = new CustService();
        String index = custService.getId();
        String sql = "select * from custService where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(index));
            res = ps.executeQuery();
            while (res.next()) {
                tempC = new CustService(Integer.toString(res.getInt(1)), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9));
            }
            return tempC;
        } catch (Exception e) {
            return null;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     *
     * 根据返回的index值，模糊查询custService对象
     */
    @Override
    public List<CustService> queryCustServiceList(String index, int page, int pageSize){
        List<CustService> tempc = new ArrayList<CustService>();
        try {
            String sql1 = "select * from custService where customername like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new CustService(Integer.toString(res.getInt(1)), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9)));
            }
            String sql2 = "select * from custService where id='"+index+"'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new CustService(Integer.toString(res.getInt(1)), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9)));
            }
            if (tempc.size() == 0){
                return tempc;
            } else{
                List<CustService> ppage= new ArrayList<CustService>();
                for (int i = (page)*pageSize; i < (page+1)*pageSize; i++) {
                    if(i < tempc.size()) {
                        ppage.add(tempc.get(i));
                    }
                }
                return ppage;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     *
     * 根据返回的index值，模糊查询custService对象个数
     */
    @Override
    public int queryCustServiceCount(String index){
        int count = 0;
        try {
            String sql1 = "select * from custService where customername like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while(res.next())
                count ++;
            String sql2 = "select * from custService where id='"+index+"'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while(res.next())
                count ++;
            return count;
        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     * 
     * 返回所有custService对象
     */
    @Override
    public List<CustService> page(int page, int pageSize) {
        List<CustService> tempc = new LinkedList<CustService>();
        try {
            String sql = "select * from custService limit ?,?";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, pageSize);
            // 3.执行SQL语句
            res = ps.executeQuery();
            // 4.访问结果集
            while (res.next()) {
                tempc.add(new CustService(res.getString(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6), res.getString(7),
                        res.getString(8), res.getString(9)));
            }
            return tempc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     * 
     * 返回custService总记录数
     */
    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) from custService";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                count = Integer.valueOf(res.getString(1));
            }
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }
}
