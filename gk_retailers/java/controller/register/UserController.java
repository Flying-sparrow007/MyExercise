package com.gk.retailers.controller.register;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gk.retailers.commons.CodeErrorException;
import com.gk.retailers.commons.HandlerException;
import com.gk.retailers.commons.JsonResult;
import com.gk.retailers.entity.Goods;
import com.gk.retailers.entity.User;
import com.gk.retailers.service.goods.GoodsService;
import com.gk.retailers.service.goodsCategory.GoodsCategoryService;
import com.gk.retailers.service.register.UserService;

/**
 * ���Ʋ�
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("user")
public class UserController extends HandlerException {
	//�����û�ҵ���
	@Autowired
	private UserService service;
	
	//������Ʒ����ҵ���
	@Autowired
	private GoodsCategoryService cateService;
	
	//������Ʒ��ҵ���
	@Autowired
	private GoodsService goodsService;
	
	/**�û�ע��ҳ��*/
	@RequestMapping("/registerHtml.do")
	public String registerHtml(){
		return "register";
	}
	
	/**У��ע���û���*/
	@RequestMapping("checkName.do")
	@ResponseBody
	public JsonResult<Integer> checkName(String userName){
		int count = service.findCountByNameService(userName);
		return new JsonResult<Integer>(count);
	}
	
	/**У���û�ע��*/
	@RequestMapping("toRegister.do")
	@ResponseBody
	public JsonResult<Boolean> toRegister(User user){
		boolean b = service.addUserService(user);
		return new JsonResult<Boolean>(b);
	}
	
	/**�û���¼ҳ��*/
	@RequestMapping("toLoginHtml.do")
	public String toLoginHtml(){
		return "login";
	}
	
	/**�û���¼*/
	@RequestMapping("toLogin.do")
	@ResponseBody
	public JsonResult<User> toLogin(HttpSession session, String code, @RequestParam("lname")String name, @RequestParam("lwd")String password){
		//��ȡcode
		String c = (String)session.getAttribute("code");
		
		//У����֤��
		//equalsIgnoreCase()���Դ�Сд
		if(!c.equalsIgnoreCase(code)){
			throw new CodeErrorException("��֤�벻��ȷ!");
		}
		
		User user = service.findUserByPwdAndNameService(name, password);
		
		//����session��user����
		session.setAttribute("user", user);
		//��session���û��ĵ�id
		session.setAttribute("uId", user.getId());
		return new JsonResult<User>(user);
	}
	
	/**��ҳ��*/
	@RequestMapping("toIndexHtml.do")
	public String toIndexHtml(HttpSession session, ModelMap m){
		//��ȡ�û���¼�ɹ����û�����
		User user = (User)session.getAttribute("user");
		m.addAttribute("user", user);
		//��session���û�id
		//session.setAttribute("uId", user.getId());
		
		//��ȡ������Ʒ  ���԰칫
		Map<String, Object> map = cateService.findGoodsCategoryByParentIdService(161, 0, 3);
		m.addAttribute("map", map);
		
		//������Ʒ����id��ȡ��Ʒ �ʼǱ�
		List<Goods> gl = goodsService.findGoodsByCategoryIdService(163, 0, 3);
		m.addAttribute("gl", gl);
		
		//��ȡ������Ʒ  �칫�ľ�
		Map<String, Object> map2 = cateService.findGoodsCategoryByParentIdService(161, 4, 3);
		m.addAttribute("map2", map2);
		
		//������Ʒ����id��ȡ��Ʒ ��������Բ���
		List<Goods> gl2 = goodsService.findGoodsByCategoryIdService(241, 0, 3);
		m.addAttribute("gl2", gl2);
		
		return "index";
	}
	
	/**������֤��
	 * @throws IOException */
	@RequestMapping(value = "code.do", produces = "image/png")
	@ResponseBody
	public byte[] code(HttpSession session) throws IOException{
		String code = getCode(4);
		//����Session����code�������ݹ���(�û���½ʱ��ȡ������)
		session.setAttribute("code", code);
		byte[] png = createImage(code);
		return png;
	}
	
	public byte[] createImage(String code) throws IOException{
		//����BufferedImage
		BufferedImage img = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		//��������(��ͼƬ�ϻ�)
		Graphics2D g = img.createGraphics();
		//���������ɫ
		Random random = new Random();
		
		//ͨ��RGB��������ɫ
		//Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		//ͨ��16������������ɫ
		Color color = new Color(random.nextInt(0xffffff));
		//���û�����ɫ
		g.setColor(color);
		//����������(���ͼƬ����)
		g.fillRect(0, 0, 100, 40);
		
		//����300�������
		for(int i = 0; i < 500; ++i){
			img.setRGB(random.nextInt(100), random.nextInt(40), random.nextInt(0xffffff));
		}
		//���������СFont.SANS_SERIF����һ��ͨ������,Font.PLAIN��׼,30��С
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		
		//������10��
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//�����������,�Ӵ�
		g.setStroke(new BasicStroke(1.5f));
		for(int i = 0; i < 10; ++i){
			g.setColor(new Color(random.nextInt(0xffffff)));
			//���
			int x1 = random.nextInt(100);
			int y1 = random.nextInt(40);
			
			//�յ�
			int x2 = random.nextInt(100);
			int y2 = random.nextInt(40);
			g.drawLine(x1, y1, x2, y2);
		}
		
		g.setColor(new Color(random.nextInt(0xffffff)));
		//���ַ���
		g.drawString(code, 13, 30);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		//��ͼƬ��png��ʽ�ֽڷ���out��
		ImageIO.write(img, "png", out);
		//��out�е�pngͼƬ�ó�������byte[]������
		byte[] bytes = out.toByteArray();
		return bytes;
	}
	
	private String getCode(int code) {
		char[] arry = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };
		boolean[] flag = new boolean[arry.length];
		char[] newArry = new char[code];
		for (int i = 0; i < newArry.length; i++) {
			int index;
			do {
				index = (int) (Math.random() * arry.length);
			} while (flag[index] == true);
			flag[index] = true;
			newArry[i] = arry[index];
		}
		//System.out.println(Arrays.toString(newArry));
		return new String(newArry);
	}
	
	/*private String getCode2(int codeLength){
		
		char[] ch={'a','b','c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
	              'A','B','C','D','E','F','G','H','I','G','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
	              '0','1','2','3','4','5','6','7','8','9'};
		boolean[] flag = new boolean[ch.length];
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		do{
			//�������һ���ַ���flag�����ж�Ӧ���±�
			int index = random.nextInt(ch.length);
			if(!flag[index]){//�жϸ��ַ��Ƿ�ʹ��
				//��λ��Ϊfalse���ʾδʹ��
				code.append(ch[index]);
				flag[index] = true;
			}
		}while(code.length() <= codeLength);
		
		return code.toString();
	}*/
	
	/*private String getCode3(){
		//�жϸ��ַ��Ƿ�ʹ��,Ĭ��Ϊfalse,��δ��ʹ��
		boolean[] flag = new boolean[75];
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		do{
			//�������һ���ַ���flag�����ж�Ӧ���±�
			int index = ((random.nextInt(10) + 48) | (random.nextInt(26) + 65) | (random.nextInt(26) + 97)) - 48;
			System.out.println(index);
			if(!flag[index]){//�жϸ��ַ��Ƿ�ʹ��
				//��λ��Ϊfalse���ʾδʹ��
				
				//��������ת���ɶ�Ӧ���ַ���
				char ch = (char)(index + 48);
				code.append(ch);
			}
		}while(code.length() < 5);
		
		System.out.println(code.toString());
		return code.toString();
	}*/
	
}
