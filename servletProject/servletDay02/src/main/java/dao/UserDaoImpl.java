package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DButil;

public class UserDaoImpl implements UserDao {

	@Override
	/**用户注册*/
	public int addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			String sql = "insert into usertable values (null, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getUserPhone());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器故障,请稍后再试!");
		} finally {
			DButil.closeConnection(conn, null, ps);
		}
	}

	@Override
	/**查询所有用户数据*/
	public List<User> findAllUser() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = null;
		try {
			list = new ArrayList<User>();
			conn = DButil.getConnection();
			String sql = "select id,username,password,useremail,userphone from usertable";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPwd(rs.getString("password"));
				user.setUserEmail(rs.getString("useremail"));
				user.setUserPhone(rs.getString("userphone"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, ps);
		}
		return null;
	}

	@Override
	public int deleteUserById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			String sql = "delete from usertable where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, null, ps);
		}
		return 0;
	}

	@Override
	public int updateUserPwdById(int id, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();
			String sql = "update usertable set password = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, id);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, ps);
		}
		return 0;
	}
	
}
