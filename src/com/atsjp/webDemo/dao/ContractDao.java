package com.atsjp.webDemo.dao;

import com.atsjp.webDemo.daoInter.ContractDaoInter;
import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContractDao implements ContractDaoInter {
	private Connection conn = JDBC.getConnection();
	private PreparedStatement ps = null;
	private ResultSet res = null;

	/*
	 * 
	 * 增加contract对象
	 */
	@Override
	public boolean addContract(Contract contract) {
		try {
			String sql = "insert into contract values(?,?,?,?,?,?,?,?,?,?)";
			conn =JDBC.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, contract.getId());
			ps.setString(2, contract.getContracttime());
			ps.setString(3, contract.getContractname());
			ps.setString(4, contract.getInvoicetitle());
			ps.setString(5, contract.getAddress());
			ps.setString(6, contract.getContractcontent());
			ps.setString(7, contract.getInvoicedetail());
			ps.setString(8, contract.getInvoicetime());
			ps.setString(9, contract.getInvoicenumber());
			ps.setString(10, contract.getInvoiceamount());
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
	 * 删除contract对象
	 */
	@Override
	public boolean deleteContract(Contract contract) {
		String sql = "delete from contract where id=?";
		try {
		    conn = JDBC.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, contract.getId());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
            JDBC.closeAll(conn, ps, res);
        }
	}

    @Override
    public boolean deleteContractAll() {
        String sql = "delete from contract";
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
	 * 修改contract对象
	 */
	@Override
	public boolean modifyContract(Contract contract) {
		String sql = "update contract set contracttime=?,contractname=?,invoicetitle=?,address=?,contractcontent=?," +
                "invoicedetail=?,invoicetime=?,invoicenumber=?,invoiceamount=? where id=?";
		try {
		    conn = JDBC.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, contract.getContracttime());
			ps.setString(2, contract.getContractname());
			ps.setString(3, contract.getInvoicetitle());
			ps.setString(4, contract.getAddress());
			ps.setString(5, contract.getContractcontent());
			ps.setString(6, contract.getInvoicedetail());
			ps.setString(7, contract.getInvoicetime());
			ps.setString(8, contract.getInvoicenumber());
			ps.setString(9, contract.getInvoiceamount());
			ps.setString(10, contract.getId());
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
	 * 根据返回的contract对象的invoicetitle或者invoicenumber，查找contract对象
	 */
	@Override
	public Contract getContract(Contract contract) {
		Contract tempC = new Contract();
		String index = "";
		String sql = "";

		if (contract.getInvoicenumber() != null
				&& !contract.getInvoicenumber().equals("")) {
			index = contract.getInvoicenumber();
		}
		if (!index.equals("")) {
			sql = "select * from contract where invoicenumber=?";
		} else {
			return null;
		}

		try {
		    conn = JDBC.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, index);
			res = ps.executeQuery();
			while (res.next()) {
				tempC = new Contract(res.getString(1), res.getString(2),
						res.getString(3), res.getString(4), res.getString(5),
						res.getString(6), res.getString(7), res.getString(8),
						res.getString(9), res.getString(10));
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
     * 根据返回的index值，模糊查询company对象个数
     */
    @Override
    public int queryContractCount(String index){
        int count = 0;
        try {
            String sql1 = "select * from contract where contractname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            List<String> invoices = new ArrayList<String>();
            while(res.next()){
                String tmp = res.getString("invoicenumber");
                invoices.add(tmp);
                count ++;
            }
            String sql2 = "select * from contract where invoicetitle like '%"+index+"%'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while(res.next()){
                String tmp = res.getString("invoicenumber");
                if(!invoices.contains(tmp)){
                    invoices.add(tmp);
                    count ++;
                }
            }
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
     * 根据返回的index值，模糊查询company对象
     */
    @Override
    public List<Contract> queryContractList(String index, int page, int pageSize){
        List<Contract> tempc = new ArrayList<Contract>();
        try {
            String sql1 = "select * from contract where contractname like '%"+index+"%'";
            conn = JDBC.getConnection();
            ps = conn.prepareStatement(sql1);
            res = ps.executeQuery();
            List<String> invoices = new ArrayList<String>();
            while (res.next()) {
                String tmp = res.getString("invoicenumber");
                invoices.add(tmp);
                tempc.add(new Contract(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8),
                        res.getString(9), res.getString(10)));
            }
            String sql2 = "select * from contract where invoicetitle like '%"+index+"%'";
            ps = conn.prepareStatement(sql2);
            res = ps.executeQuery();
            while (res.next()) {
                String tmp = res.getString("invoicenumber");
                if(!invoices.contains(tmp)){
                    invoices.add(tmp);
                    tempc.add(new Contract(res.getString(1), res.getString(2),
                            res.getString(3), res.getString(4), res.getString(5),
                            res.getString(6), res.getString(7), res.getString(8),
                            res.getString(9), res.getString(10)));
                }
            }
            if(tempc.size() == 0){
                return tempc;
            } else{
                List<Contract> ppage= new ArrayList<Contract>();
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
	 * 返回所有contract对象
	 */
	@Override
	public List<Contract> page(int page, int pageSize) {
		List<Contract> tempc = new LinkedList<Contract>();
		try {
			String sql = "select * from contract limit ?,?";
			conn = JDBC.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, page);
			ps.setInt(2, pageSize);
			// 3.执行SQL语句
			res = ps.executeQuery();
			// 4.访问结果集
			while (res.next()) {
				tempc.add(new Contract(res.getString(1), res.getString(2), res
						.getString(3), res.getString(4), res.getString(5), res
						.getString(6), res.getString(7), res.getString(8), res
						.getString(9), res.getString(10)));
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
	 * 返回contract总记录数
	 */
	@Override
	public int getCount() {
		int count = 0;
		String sql = "select count(*) from contract";
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
