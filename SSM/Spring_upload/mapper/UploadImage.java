package mapper;

import org.apache.ibatis.annotations.Param;

/**
 * ӳ����
 * @author LENOVO
 *
 */
public interface UploadImage {
	boolean updateImageById(@Param("id")int id,@Param("image")String image);
	
	String findImageById(int id);
}
