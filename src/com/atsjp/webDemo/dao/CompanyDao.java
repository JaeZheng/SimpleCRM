package com.atsjp.webDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.atsjp.webDemo.daoInter.CompanyDaoInter;
import com.atsjp.webDemo.entity.Company;
import com.atsjp.webDemo.utils.JDBC;

public class CompanyDao implements CompanyDaoInter {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet res = null;

    /*
     * 
     * 增加company对象
     */
    @Override
    public boolean addCompany(Company company) {
        try {
            conn = JDBC.getConnection();
            String sql = "select * from company where companyname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getCompanyname());
            res = ps.executeQuery();
            if (res.next()) { // 存在相同公司名，
                return false;
            }
            sql = "select * from company where linkphone = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getLinkphone());
            res = ps.executeQuery();
            if (res.next()) {// 存在相同手机号码，
                return false;
            } else {
                String sql1 = "insert into company values(?,?,?,?,?)";
                ps = conn.prepareStatement(sql1);
                ps.setString(1, company.getId());
                ps.setString(2, company.getCompanyname());
                ps.setString(3, company.getLinkman());
                ps.setString(4, company.getLinkphone());
                ps.setString(5, company.getAddress());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    /*
     * 
     * 删除company对象
     */
    @Override
    public boolean deleteCompany(Company company) {
        String sql = "delete from company where companyname=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getCompanyname());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            JDBC.closeAll(conn, ps, res);
        }
    }

    @Override
    public boolean deleteCompanyAll() {
        String sql = "delete from company";
        try{
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
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
     * 修改company对象
     */
    @Override
    public boolean modifyCompany(Company company) {
        String sql = "update company set companyname=?, linkman=?, linkphone=?, address=? where id=?";
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getCompanyname());
            ps.setString(2, company.getLinkman());
            ps.setString(3, company.getLinkphone());
            ps.setString(4, company.getAddress());
            ps.setString(5, company.getId());
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
     * 根据返回的company对象的companyName或者linkPhone，查找company对象
     */
    @Override
    public Company getCompany(Company company) {
        Company tempC = new Company();
        String index = "";
        String sql = "";
        boolean flag = false;
        if (company.getCompanyname() != null && !company.getCompanyname().equals("")) {// 如果两者都有优先cname查找
            index = company.getCompanyname();
            flag = true; // 用于标记index被赋予了cname
        } else if (company.getLinkphone() != null
                && !company.getLinkphone().equals("")) {
            index = company.getLinkphone();
            flag = false;// 用于标记index被赋予了cphone
        }
        if (!index.equals("") && flag) {
            sql = "select * from company where companyname=?";
        } else if (!index.equals("") && !flag) {
            sql = "select * from company where linkphone=?";
        } else {
            return null;
        }
        try {
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, index);
            res = ps.executeQuery();
            while (res.next()) {
                tempC = new Company(res.getString(1), res.getString(2),
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
     * 根据返回的index值，模糊查询company对象
     */
    @Override
    public List<Company> queryCompanyList(String index, int page, int pageSize){
        List<Company> tempc = new ArrayList<Company>();
        try {
            String sql1 = "select * from company where companyname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new Company(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5)));
            }
            String sql2 = "select * from company where linkman like '%"+index+"%'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while (res.next()) {
                tempc.add(new Company(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5)));
            }
            if (tempc.size() == 0){
                return tempc;
            } else{
                List<Company> ppage= new ArrayList<Company>();
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
     * 根据返回的index值，模糊查询company对象个数
     */
    @Override
    public int queryCompanyCount(String index){
        int count = 0;
        try {
            String sql1 = "select * from company where companyname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            while(res.next())
                count ++;
            String sql2 = "select * from company where linkman like '%"+index+"%'";
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
     * 返回所有company对象
     */
    @Override
    public List<Company> page(int page, int pageSize) {
        List<Company> tempc = new LinkedList<Company>();
        try {
            String sql = "select * from company limit ?,?";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, pageSize);
            // 3.执行SQL语句
            res = ps.executeQuery();
            // 4.访问结果集
            while (res.next()) {
                tempc.add(new Company(res.getString(1), res.getString(2), res
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
     * 返回company总记录数
     */
    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) from company";
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
