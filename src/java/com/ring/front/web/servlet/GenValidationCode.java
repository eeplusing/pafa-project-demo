package com.ring.front.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码图片.
 * @author luohuan
 * 2014/10/20
 */
public class GenValidationCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static int HEIGHT = 38; // 图片的高度(px)
	private final static int WIDTH = 100; // 图片的宽度(px)
	
	public GenValidationCode() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应报头的格式
		response.setContentType("imeg/jpeg");
		request.getSession().setAttribute("ranNum", null);
		// 浏览器不缓存图片
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0);
		// 创建内存图像并获得其图形上下文
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 产生随机的验证码
		char[] rands = generateCheckCode();
		// 产生图像
		drawBackground(g);
		drawRands(g, rands);
		// 结束图像的绘制过程,完成图像
		g.dispose();
		// 将图像输出至客户端
		ServletOutputStream sos = response.getOutputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		sos.println(new String(rands));
		bos.close();
		sos.close();
		// 将当前验证码存入到request中
		request.getSession().setAttribute("randNum", new String(rands));
	}
	
	public char[] generateCheckCode() {
    	// 定义验证码
    	String charArr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	int charLength = charArr.length();
    	// 生成4位随机码
    	char[] rands = new char[4];
    	for (int i = 0;i <4;i++) {
    		int rand = (int)(Math.random() * charLength);
    		rands[i] = charArr.charAt(rand);
    	}
    	return rands;
	}
	
	public void drawBackground(Graphics g) {
    	// 画背景
    	g.setColor(Color.white);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	// 随机产生120个干扰点
    	for (int i = 0;i <120;i++) {
    		int x = (int)(Math.random() * WIDTH);
    		int y = (int)(Math.random() * HEIGHT);
    		int red = (int)(Math.random() * 256);
    		int green = (int)(Math.random() * 256);
    		int blue = (int)(Math.random() * 256);
    		g.setColor(new Color(red, green, blue));
    		g.drawOval(x, y, 1, 0);
//    		g.drawLine(x, y, 1, 2);//放射线
    	}
    }
	
   public void drawRands(Graphics g, char[] rands) {
    	g.setColor(Color.RED);
    	g.setFont(new Font(null, Font.ITALIC | Font.CENTER_BASELINE, 21));
    	// 在不同的高度上输出验证码的每个字符
    	g.drawString(" " + rands[0], 11, 29);
    	g.drawString(" " + rands[1], 29, 24);
    	g.drawString(" " + rands[2], 52, 25);
    	g.drawString(" " + rands[3], 67, 31);
    }
}
