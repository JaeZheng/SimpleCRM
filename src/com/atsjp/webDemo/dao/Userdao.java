package com.atsjp.webDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.atsjp.webDemo.daoInter.UserDaoInter;
import com.atsjp.webDemo.entity.About;
import com.atsjp.webDemo.entity.User;
import com.atsjp.webDemo.utils.JDBC;

/*
 * 
 * 对于user的增删改操作定义
 */
public class Userdao implements UserDaoInter {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet res = null;

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
		    conn = JDBC.getConnection();
			String sql = "select * from users where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			res = ps.executeQuery();
			if (res.next()) { // 存在相同用户名，
				return false;
			} else {
				String sql1 = "insert into users values(?,?,?,?)";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				ps.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
		    JDBC.closeAll(conn, ps, res);
        }
    }

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		try {
		    conn = JDBC.getConnection();
			String sql = "delete * from users where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
            JDBC.closeAll(conn, ps, res);
        }
		return true;
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		try {
		    conn = JDBC.getConnection();
			String sql = "update users set password =? where username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
            JDBC.closeAll(conn, ps, res);
        }
		return true;
	}

	@Override
	public User getUser(User user) { // 根据service层传回的User对象的name值查找管理员信息
		// TODO Auto-generated method stub
		User tempUser = new User();
		try {
		    conn = JDBC.getConnection();
			String sql = "select * from users where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            res = ps.executeQuery();
            if (res.next()) {
                String username = res.getString("username");
                String password = res.getString("password");
                tempUser.setName(username);
                tempUser.setPassword(password);
                return tempUser;
            } else {
                return null;
            }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e1) {

			return null;
		} finally {
            JDBC.closeAll(conn, ps, res);
        }
	}

    public About getAbout(){
		About about = new About();
		try {
			conn = JDBC.getConnection();
			String sql = "select * from about";
			ps = conn.prepareStatement(sql);
			res = ps.executeQuery();
			if (res.next()) {
				String software = res.getString("software");
				String banquan = res.getString("banquan");
				String address = res.getString("address");
				String linkman = res.getString("linkman");
				String linkphone = res.getString("linkphone");
				about.setSoftware(software);
				about.setBanquan(banquan);
				about.setAddress(address);
				about.setLinkman(linkman);
				about.setLinkphone(linkphone);
				return about;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e1) {

			return null;
		} finally {
			JDBC.closeAll(conn, ps, res);
		}
	}

	public boolean modifyAboutUs(About about, String oldSoftware){
		try {
			conn = JDBC.getConnection();
			String sql = "update about set software=?,banquan=?,address=?,linkman=?,linkphone=? where software=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, about.getSoftware());
            ps.setString(2, about.getBanquan());
            ps.setString(3, about.getAddress());
            ps.setString(4, about.getLinkman());
            ps.setString(5, about.getLinkphone());
            ps.setString(6, oldSoftware);
            ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e1) {
            e1.printStackTrace();
			return false;
		} finally {
			JDBC.closeAll(conn, ps, res);
		}
		return true;
	}
}
