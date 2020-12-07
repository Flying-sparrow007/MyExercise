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
	public int register(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//��������
			conn = DButil.getConnection();
			//����sql���
			String sql = "insert into usertable_2 values (null, ? , ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			//����SQL����ֵ
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getUserPhone());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ע��ʧ��!");
		} finally {
			//�黹��Դ
			DButil.close(conn, ps, null);
		}
	}

	@Override
	public boolean selectUserByName(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = DButil.getConnection();
			String sql = "select id from usertable_2 where username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			//ִ�в�ѯ���
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯʧ��!");
		} finally {
			DButil.close(conn, ps, rs);
		}
	}

	@Override
	public List<User> selectUserList() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<User> list = new ArrayList<User>();
			conn = DButil.getConnection();
			String sql = "select id,username,useremail,userphone from usertable_2";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserEmail(rs.getString("useremail"));
				user.setUserPhone(rs.getString("userphone"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�û��б�ʧ��!");
		} finally {
			DButil.close(conn, ps, rs);
		}
	}

	@Override
	public User loginByNameAndPwd(String name, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			User user = null;
			conn = DButil.getConnection();
			String sql = "select username,password from usertable_2 where username = ? AND password = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�û�ʧ��!");
		} finally {
			DButil.close(conn, ps, rs);
		}
	}

	@Override
	public int deleteUserById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			String sql = "delete from usertable_2 where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ɾ���û�ʧ��!");
		} finally {
			DButil.close(conn, ps, null);
		}
	}

	@Override
	public User selectUserById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DButil.getConnection();
			String sql = "select id,username,useremail,userphone from usertable_2 where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserEmail(rs.getString("useremail"));
				user.setUserPhone(rs.getString("userphone"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����ID��ѯ�û�ʧ��!");
		} finally {
			DButil.close(conn, ps, rs);
		}
	}

	@Override
	public int updateUserMegById(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			String sql = "update usertable_2 set username = ?,useremail = ?,userphone = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPhone());
			ps.setInt(4, user.getId());
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�޸��û���Ϣʧ��!");
		} finally {
			DButil.close(conn, ps, null);
		}
	}

	@Override
	public int selectPageCountByUserCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			int count = 0;//�û�����
			
			/*
			 * ��ҳ��ѯÿһҳ��ҳ��,�������С������Ϊ�����������ȡ����Ҫ�õ�double����
			 * ��������ȡ����ԭ���ǵ�ҳ�����ֲ���һҳʱҲ��һ��ҳ
			 */
			double pageSize = 3.0;
			
			conn = DButil.getConnection();
			String sql = "select count(id) user_c from usertable_2";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("user_c");
			}
			
			
			int pageCount = (int)Math.ceil(count / pageSize);
			
			return pageCount;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ�û������쳣");
		} finally {
			DButil.close(conn, ps, rs);
		}
	}
	
	@Override
	public List<User> selectUserByPage(int page) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int pageSize = 3;//ÿһҳ���û�����
			
			List<User> list = new ArrayList<User>();
			conn = DButil.getConnection();
			String sql = "select id,username,useremail,userphone from usertable_2 limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1) * pageSize);//����������
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setUserEmail(rs.getString("useremail"));
				user.setUserPhone(rs.getString("userphone"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ҳ��ѯ��ʧ��!");
		} finally {
			DButil.close(conn, ps, rs);
		}
	}

}
