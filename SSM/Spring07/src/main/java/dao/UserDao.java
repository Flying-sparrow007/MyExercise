package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jt;
	
	//�����û�����
	public void saveUser(User user){
		String sql = "insert into user values (null, ?, ?, ?, ?)";
		//��ȡ����
		Object[] args = {user.getUserName(), user.getUserPassword(), user.getUserSal(), user.getUserAge()};
		//ִ�в�������
		jt.update(sql, args);
	}
	
	//��ѯ�����û�����
	public List<User> findUserAll(){
		String sql = "select * from user";
		List<User> list = jt.query(sql, new UserMapper());
		return list;
	}
	
	//ͨ��id��ѯ�û�����
	public User findUserById(int id){
		String sql = "select * from user where id = ?";
		Object[] args = {id};
		User user = jt.queryForObject(sql, args, new UserMapper());
		return user;
	}
	
	//�����û�idɾ���û�����
	public void deleteUserById(int id){
		String sql = "delete from user where id = ?";
		Object[] args = {id};
		jt.update(sql, args);
	}
	
	//�����û�id�޸��û�����
	public void updateUserById(String pwd, int id){
		String sql = "update user set user_password = ? where id = ?";
		Object[] args = {pwd, id};
		jt.update(sql, args);
	}
	
}

class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserAge(rs.getInt("user_age"));
		user.setUserName(rs.getString("user_name"));
		user.setUserPassword(rs.getString("user_password"));
		user.setUserSal(rs.getDouble("sal"));
		return user;
	}
	
}