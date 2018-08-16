package com.atsjp.webDemo.dao;

import com.atsjp.webDemo.daoInter.ContractDaoInter;
import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContractDao implements ContractDaoInter {
	private Connection conn = JDBC.getConnection();
	private PreparedStatement ps = null;
	private ResultSet res = null;

	/*
	 * 
	 * ����contract����
	 */
	@Override
	public boolean addContract(Contract contract) {
		try {
			String sql = "insert into contract values(?,?,?,?,?,?,?,?,?,?)";
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
		}
	}

	/*
	 * 
	 * ɾ��contract����
	 */
	@Override
	public boolean deleteContract(Contract contract) {
		String sql = "delete from contract where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, contract.getId());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * 
	 * �޸�contract����
	 */
	@Override
	public boolean modifyContract(Contract contract) {
		String sql = "update contract set contracttime=?,contractname=?,invoicetitle=?,address=?,contractcontent=?," +
                "invoicedetail=?,invoicetime=?,invoicenumber=?,invoiceamount=? where id=?";
		try {
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
		}
	}

	/*
	 * 
	 * ���ݷ��ص�contract�����invoicetitle����invoicenumber������contract����
	 */
	@Override
	public Contract getContract(Contract contract) {
		Contract tempC = new Contract();
		String index = "";
		String sql = "";
		boolean flag = false;
		if (contract.getInvoicetitle() != null && !contract.getInvoicetitle().equals("")) {// ������߶�������invoicetitle����
			index = contract.getInvoicetitle();
			flag = true; // ���ڱ��index��������invoicetitle
		} else if (contract.getInvoicenumber() != null
				&& !contract.getInvoicenumber().equals("")) {
			index = contract.getInvoicenumber();
			flag = false;// ���ڱ��index��������invoicenumber
		}
		if (!index.equals("") && flag) {
			sql = "select * from contract where invoicetitle=?";
		} else if (!index.equals("") && !flag) {
			sql = "select * from contract where invoicenumber=?";
		} else {
			return null;
		}
		try {
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
		}
	}

	/*
	 * 
	 * ��������contract����
	 */
	@Override
	public List<Contract> page(int page, int pageSize) {
		List<Contract> tempc = new LinkedList<Contract>();
		try {
			String sql = "select * from contract limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, page);
			ps.setInt(2, pageSize);
			// 3.ִ��SQL���
			res = ps.executeQuery();
			// 4.���ʽ����
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
		}
	}

	/*
	 * 
	 * ����contract�ܼ�¼��
	 */
	@Override
	public int getCount() {
		int count = 0;
		String sql = "select count(*) from contract";
		try {
			ps = conn.prepareStatement(sql);
			res = ps.executeQuery();
			while (res.next()) {
				count = Integer.valueOf(res.getString(1));
			}
			return count;
		} catch (Exception e) {
			return 0;
		}
	}
}