package dao;

import java.util.List;

import entity.User;

/**
 * �û������ӿ�
 * @author LENOVO
 *
 */
public interface UserDao {
	/**�û�ע��*/
	int register(User user);
	
	/**�����û�����ѯ�û�*/
	boolean selectUserByName(String username);
	
	/**��ѯ�û��б�*/
	List<User> selectUserList();
	
	/**�����û��˺ź������¼*/
	User loginByNameAndPwd(String name, String pwd);
	
	/**�����û�idɾ���û�*/
	int deleteUserById(int id);
	
	/**�����û�id��ѯ�û���Ϣ*/
	User selectUserById(int id);
	
	/**�����û�id�޸��û���Ϣ*/
	int updateUserMegById(User user);
	
	/**����ÿһҳ�����ݸ�����ѯ��ҳ��*/
	int selectPageCountByUserCount();
	
	/**��ѯĳһҳ������*/
	List<User> selectUserByPage(int page);
}
