package com.atsjp.webDemo.dao;

import com.atsjp.webDemo.daoInter.LostDaoInter;
import com.atsjp.webDemo.entity.Lost;
import com.atsjp.webDemo.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LostDao implements LostDaoInter {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet res = null;

    /*
     * 
     * ����lost����
     */
    @Override
    public boolean addLost(Lost lost) {
        try {
            conn = JDBC.getConnection();
            String sql = "insert into lost values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, lost.getId());
            ps.setString(2, lost.getJoindate());
            ps.setString(3, lost.getLossdate());
            ps.setString(4, lost.getCompanyname());
            ps.setString(5, lost.getReason());
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
     * ɾ��lost����
     */
    @Override
    public boolean deleteLost(Lost lost) {
        String sql = "delete from lost where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, lost.getId());
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
     * �޸�lost����
     */
    @Override
    public boolean modifyLost(Lost lost) {
        String sql = "update lost set joindate=?, lossdate=?, companyname=?, reason=? where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, lost.getJoindate());
            ps.setString(2, lost.getLossdate());
            ps.setString(3, lost.getCompanyname());
            ps.setString(4, lost.getReason());
            ps.setString(5, lost.getId());
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
     * ���ݷ��ص�lost�����lostName����linkPhone������lost����
     */
    @Override
    public Lost getLost(Lost lost) {
        Lost tempC = new Lost();
        String index = "";
        String sql = "";
        boolean flag = false;
        if (lost.getId() != null && !lost.getId().equals("")) {// ������߶�������cname����
            index = lost.getId();
            flag = true; // ���ڱ��index��������cname
        } else if (lost.getCompanyname() != null
                && !lost.getCompanyname().equals("")) {
            index = lost.getCompanyname();
            flag = false;// ���ڱ��index��������cphone
        }
        if (!index.equals("") && flag) {
            sql = "select * from lost where id=?";
        } else if (!index.equals("") && !flag) {
            sql = "select * from lost where companyname=?";
        } else {
            return null;
        }
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, index);
            res = ps.executeQuery();
            while (res.next()) {
                tempC = new Lost(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5));
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
     * ���ݷ��ص�indexֵ��ģ����ѯlost����
     */
    @Override
    public List<Lost> queryLostList(String index, int page, int pageSize){
        List<Lost> tempc = new ArrayList<Lost>();
        try {
            String sql1 = "select * from lost where companyname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new Lost(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5)));
            }
            String sql2 = "select * from lost where lossdate like '%"+index+"%'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new Lost(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5)));
            }
            if (tempc.size() == 0){
                return tempc;
            } else{
                List<Lost> ppage= new ArrayList<Lost>();
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
     * ���ݷ��ص�indexֵ��ģ����ѯlost�������
     */
    @Override
    public int queryLostCount(String index){
        int count = 0;
        try {
            String sql1 = "select * from lost where companyname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while(res.next())
                count ++;
            String sql2 = "select * from lost where lossdate like '%"+index+"%'";
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
     * ��������lost����
     */
    @Override
    public List<Lost> page(int page, int pageSize) {
        List<Lost> tempc = new LinkedList<Lost>();
        try {
            String sql = "select * from lost limit ?,?";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, pageSize);
            // 3.ִ��SQL���
            res = ps.executeQuery();
            // 4.���ʽ����
            while (res.next()) {
                tempc.add(new Lost(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5)));
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
     * ����lost�ܼ�¼��
     */
    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) from lost";
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
