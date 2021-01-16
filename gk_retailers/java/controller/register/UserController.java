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
 * 控制层
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("user")
public class UserController extends HandlerException {
	//调用用户业务层
	@Autowired
	private UserService service;
	
	//调用商品分类业务层
	@Autowired
	private GoodsCategoryService cateService;
	
	//调用商品类业务层
	@Autowired
	private GoodsService goodsService;
	
	/**用户注册页面*/
	@RequestMapping("/registerHtml.do")
	public String registerHtml(){
		return "register";
	}
	
	/**校验注册用户名*/
	@RequestMapping("checkName.do")
	@ResponseBody
	public JsonResult<Integer> checkName(String userName){
		int count = service.findCountByNameService(userName);
		return new JsonResult<Integer>(count);
	}
	
	/**校验用户注册*/
	@RequestMapping("toRegister.do")
	@ResponseBody
	public JsonResult<Boolean> toRegister(User user){
		boolean b = service.addUserService(user);
		return new JsonResult<Boolean>(b);
	}
	
	/**用户登录页面*/
	@RequestMapping("toLoginHtml.do")
	public String toLoginHtml(){
		return "login";
	}
	
	/**用户登录*/
	@RequestMapping("toLogin.do")
	@ResponseBody
	public JsonResult<User> toLogin(HttpSession session, String code, @RequestParam("lname")String name, @RequestParam("lwd")String password){
		//获取code
		String c = (String)session.getAttribute("code");
		
		//校验验证码
		//equalsIgnoreCase()忽略大小写
		if(!c.equalsIgnoreCase(code)){
			throw new CodeErrorException("验证码不正确!");
		}
		
		User user = service.findUserByPwdAndNameService(name, password);
		
		//利用session绑定user对象
		session.setAttribute("user", user);
		//用session绑定用户的的id
		session.setAttribute("uId", user.getId());
		return new JsonResult<User>(user);
	}
	
	/**首页面*/
	@RequestMapping("toIndexHtml.do")
	public String toIndexHtml(HttpSession session, ModelMap m){
		//获取用户登录成功后用户数据
		User user = (User)session.getAttribute("user");
		m.addAttribute("user", user);
		//用session绑定用户id
		//session.setAttribute("uId", user.getId());
		
		//获取分类商品  电脑办公
		Map<String, Object> map = cateService.findGoodsCategoryByParentIdService(161, 0, 3);
		m.addAttribute("map", map);
		
		//根据商品分类id获取商品 笔记本
		List<Goods> gl = goodsService.findGoodsByCategoryIdService(163, 0, 3);
		m.addAttribute("gl", gl);
		
		//获取分类商品  办公文具
		Map<String, Object> map2 = cateService.findGoodsCategoryByParentIdService(161, 4, 3);
		m.addAttribute("map2", map2);
		
		//根据商品分类id获取商品 计算器、圆珠笔
		List<Goods> gl2 = goodsService.findGoodsByCategoryIdService(241, 0, 3);
		m.addAttribute("gl2", gl2);
		
		return "index";
	}
	
	/**生成验证码
	 * @throws IOException */
	@RequestMapping(value = "code.do", produces = "image/png")
	@ResponseBody
	public byte[] code(HttpSession session) throws IOException{
		String code = getCode(4);
		//利用Session保存code进行数据共享(用户登陆时获取该数据)
		session.setAttribute("code", code);
		byte[] png = createImage(code);
		return png;
	}
	
	public byte[] createImage(String code) throws IOException{
		//创建BufferedImage
		BufferedImage img = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		//创建画笔(在图片上画)
		Graphics2D g = img.createGraphics();
		//生成随机颜色
		Random random = new Random();
		
		//通过RGB来设置颜色
		//Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		//通过16进制来设置颜色
		Color color = new Color(random.nextInt(0xffffff));
		//设置画笔颜色
		g.setColor(color);
		//填充矩形区域(填充图片背景)
		g.fillRect(0, 0, 100, 40);
		
		//绘制300个随机点
		for(int i = 0; i < 500; ++i){
			img.setRGB(random.nextInt(100), random.nextInt(40), random.nextInt(0xffffff));
		}
		//设置字体大小Font.SANS_SERIF就是一种通用字体,Font.PLAIN标准,30大小
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		
		//干扰线10条
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//设置线条宽度,加粗
		g.setStroke(new BasicStroke(1.5f));
		for(int i = 0; i < 10; ++i){
			g.setColor(new Color(random.nextInt(0xffffff)));
			//起点
			int x1 = random.nextInt(100);
			int y1 = random.nextInt(40);
			
			//终点
			int x2 = random.nextInt(100);
			int y2 = random.nextInt(40);
			g.drawLine(x1, y1, x2, y2);
		}
		
		g.setColor(new Color(random.nextInt(0xffffff)));
		//画字符串
		g.drawString(code, 13, 30);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		//把图片以png形式字节放在out里
		ImageIO.write(img, "png", out);
		//把out中的png图片拿出来放在byte[]数组中
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
			//随机生成一个字符在flag数组中对应的下标
			int index = random.nextInt(ch.length);
			if(!flag[index]){//判断该字符是否被使用
				//该位置为false则表示未使用
				code.append(ch[index]);
				flag[index] = true;
			}
		}while(code.length() <= codeLength);
		
		return code.toString();
	}*/
	
	/*private String getCode3(){
		//判断该字符是否被使用,默认为false,即未被使用
		boolean[] flag = new boolean[75];
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		do{
			//随机生成一个字符在flag数组中对应的下标
			int index = ((random.nextInt(10) + 48) | (random.nextInt(26) + 65) | (random.nextInt(26) + 97)) - 48;
			System.out.println(index);
			if(!flag[index]){//判断该字符是否被使用
				//该位置为false则表示未使用
				
				//将该数字转换成对应的字符串
				char ch = (char)(index + 48);
				code.append(ch);
			}
		}while(code.length() < 5);
		
		System.out.println(code.toString());
		return code.toString();
	}*/
	
}
