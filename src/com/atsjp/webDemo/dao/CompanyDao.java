package com.atsjp.webDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.atsjp.webDemo.daoInter.CompanyDaoInter;
import com.atsjp.webDemo.entity.Company;
import com.atsjp.webDemo.utils.JDBC;

public class CompanyDao implements CompanyDaoInter {
    private Connection conn = JDBC.getConnection();
    private PreparedStatement ps = null;
    private ResultSet res = null;

    /*
     * 
     * ����company����
     */
    @Override
    public boolean addCompany(Company company) {
        try {
            String sql = "select * from company where companyname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getCompanyname());
            res = ps.executeQuery();
            if (res.next()) { // ������ͬ��˾����
                return false;
            }
            sql = "select * from company where linkphone = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getLinkphone());
            res = ps.executeQuery();
            if (res.next()) {// ������ͬ�ֻ����룬
                return false;
            } else {
                String sql1 = "insert into company values(?,?,?,?,?,?,?,?,?)";
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
        }
    }

    /*
     * 
     * ɾ��company����
     */
    @Override
    public boolean deleteCompany(Company company) {
        String sql = "delete from company where companyname=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getCompanyname());
//			System.out.println(company.getCname());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * 
     * �޸�company����
     */
    @Override
    public boolean modifyCompany(Company company) {
        String sql = "update company set companyname=?, linkman=?, linkphone=?, address=? where id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getId());
            ps.setString(2, company.getCompanyname());
            ps.setString(3, company.getLinkman());
            ps.setString(4, company.getLinkphone());
            ps.setString(5, company.getAddress());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * 
     * ���ݷ��ص�company�����companyName����linkPhone������company����
     */
    @Override
    public Company getCompany(Company company) {
        Company tempC = new Company();
        String index = "";
        String sql = "";
        boolean flag = false;
        if (company.getCompanyname() != null && !company.getCompanyname().equals("")) {// ������߶�������cname����
            index = company.getCompanyname();
            flag = true; // ���ڱ��index��������cname
        } else if (company.getLinkphone() != null
                && !company.getLinkphone().equals("")) {
            index = company.getLinkphone();
            flag = false;// ���ڱ��index��������cphone
        }
        if (!index.equals("") && flag) {
            sql = "select * from company where companyname=?";
        } else if (!index.equals("") && !flag) {
            sql = "select * from company where linkphone=?";
        } else {
            return null;
        }
        try {
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
        }
    }

    /*
     * 
     * ��������company����
     */
    @Override
    public List<Company> page(int page, int pageSize) {
        List<Company> tempc = new LinkedList<Company>();
        try {
            String sql = "select * from company limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, pageSize);
            // 3.ִ��SQL���
            res = ps.executeQuery();
            // 4.���ʽ����
            while (res.next()) {
                tempc.add(new Company(res.getString(1), res.getString(2), res
                        .getString(3), res.getString(4), res.getString(5)));
            }
            return tempc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 
     * ����company�ܼ�¼��
     */
    @Override
    public int getCount() {
        int count = 0;
        String sql = "select count(*) from company";
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                count = Integer.valueOf(res.getString(1));
            }
            System.out.println("��ѯcount���: "+ Integer.toString(count));
            return count;
        } catch (Exception e) {
            return 0;
        }
    }
}
