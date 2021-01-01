package com.gk.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gk.login.entity.User;

/**
 * ���ݷ��ʲ�(ʵ����)
 * @Repository(�־û�):���ݷ��ʲ�Ӧ��ʹ�ø�ע���������ɨ��
 * @author LENOVO
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	//ע�����ӳ�
	/*
	 * @Resource(name = "dataSource")
	 * ��ȡid = "dataSource"��bean
	 */
	@Resource(name = "dataSource")
	private DataSource ds;

	@Override
	public User findUserByName(String name) {
		Connection conn = null;
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from user where user_name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
