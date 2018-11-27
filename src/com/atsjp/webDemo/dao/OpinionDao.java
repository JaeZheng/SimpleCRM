package com.atsjp.webDemo.dao;

import com.atsjp.webDemo.daoInter.OpinionDaoInter;
import com.atsjp.webDemo.entity.Opinion;
import com.atsjp.webDemo.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OpinionDao implements OpinionDaoInter {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet res = null;

    /*
     * 
     * 增加opinion对象
     */
    @Override
    public boolean addOpinion(Opinion opinion) {
        try {
            conn = JDBC.getConnection();
            String sql1 = "insert into opinion values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql1);
            ps.setInt(1, 0);
            ps.setString(2, opinion.getCompanyname());
            ps.setString(3, opinion.getLinkman());
            ps.setString(4, opinion.getLinkphone());
            ps.setString(5, opinion.getOpiniondetail());
            ps.setString(6, opinion.getOpinionstate());
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
     * 删除opinion对象
     */
    @Override
    public boolean deleteOpinion(Opinion opinion) {
        String sql = "delete from opinion where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(opinion.getId()));
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
     * 修改opinion对象
     */
    @Override
    public boolean modifyOpinion(Opinion opinion) {
        String sql = "update opinion set companyname=?, linkman=?, linkphone=?, opiniondetail=?, opinionstate=? where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, opinion.getCompanyname());
            ps.setString(2, opinion.getLinkman());
            ps.setString(3, opinion.getLinkphone());
            ps.setString(4, opinion.getOpiniondetail());
            ps.setString(5, opinion.getOpinionstate());
            ps.setInt(6, Integer.parseInt(opinion.getId()));
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
     * 根据返回的opinion对象的opinionName或者linkPhone，查找opinion对象
     */
    @Override
    public Opinion getOpinion(Opinion opinion) {
        Opinion tempC = new Opinion();
        String index = opinion.getId();
        String sql = "select * from opinion where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(index));
            res = ps.executeQuery();
            while (res.next()) {
                tempC = new Opinion(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6));
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
     * 根据返回的index值，模糊查询opinion对象
     */
    @Override
    public List<Opinion> queryOpinionList(String index, int page, int pageSize){
        List<Opinion> tempc = new ArrayList<Opinion>();
        try {
            String sql1 = "select * from opinion where companyname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new Opinion(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5),
                        res.getString(6)));
            }
            String sql2 = "select * from opinion where id='"+index+"'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new Opinion(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5),
                        res.getString(6)));
            }
            if (tempc.size() == 0){
                return tempc;
            } else{
                List<Opinion> ppage= new ArrayList<Opinion>();
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
     * 根据返回的index值，模糊查询opinion对象个数
     */
    @Override
    public int queryOpinionCount(String index){
        int count = 0;
        try {
            String sql1 = "select * from opinion where companyname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while(res.next())
                count ++;
            String sql2 = "select * from opinion where id='"+index+"'";
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
     * 返回所有opinion对象
     */
    @Override
    public List<Opinion> page(int page, int pageSize) {
        List<Opinion> tempc = new LinkedList<Opinion>();
        try {
            String sql = "select * from opinion limit ?,?";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, pageSize);
            // 3.执行SQL语句
            res = ps.executeQuery();
            // 4.访问结果集
            while (res.next()) {
                tempc.add(new Opinion(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5),
                        res.getString(6)));
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
     * 返回opinion总记录数
     */
    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) from opinion";
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
