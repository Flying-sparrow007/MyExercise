package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;

/**
 * ӳ����(Mapper):����ӳ���ļ�Ҫ��Ľӿ�
 * 1.������������sql��Ψһidһ��
 * 2.�������ͱ�����parameterTypeһ��
 * 3.�������ͱ�����resultTypeһ��
 * 4.ӳ���ļ��е�namespace������Mapperӳ����ȫ��(����.����)һ��
 * �ýӿ��еķ�������Ӧ����ӳ���ļ��е�Ψһ���Ǹ�id
 * @author LENOVO
 *
 */
public interface UserDao {
	/**��ѯ�����û�����*/
	List<User> findAllUser();
	
	/**�����û�id��ѯ�û�����*/
	User findUserById(int id);
	
	/**����û�*/
	int addUser(User user);
	
	/**�����û�idɾ�����û�*/
	int deleteUserById(int id);
	
	/**�����û�ID�޸��û��˺ź�����*/
	int updateUserById(User user);
	
	/**
	 * ���������ʱ��Ҫ���@Paramע��,д���βζ�Ӧӳ���ļ��Ļ�ȡ����
	 * @param password
	 * @param id
	 * @return
	 */
	int updateUserById2(@Param("pwd")String password, @Param("userId")int id);
}
