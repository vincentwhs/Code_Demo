package cn.demo.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GenerateRandomPic {

	private static int WIDTH = 120;
	private static int HEIGHT = 35;
	public static BufferedImage getImage(){
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics graphic = image.getGraphics();
		
		//1设置背景�?
		setBackColor(graphic);
		//2设置边看
		setBorder(graphic);
		//3设置干扰�?
		setRandomLine(graphic);
		//4设置随机�?
		setRandomNum((Graphics2D)graphic);
		
		//5返回用于网页显示
		return image;
	}
	private static void setRandomNum(Graphics2D graphic) {
		
		graphic.setColor(Color.RED);
		
		String testString = "\u4e01\u4e00\u4e02\u4e03\u4e04\u4e05\u4e06\u4e07\u4e08";	
		int x=10;
		//[\u4e00~\u9fa5]汉字范围
		for (int i = 0; i < 4; i++) {
			System.out.println(testString.length());
			String ch = testString.charAt(new Random().nextInt(testString.length())) + "";
			
			int degree = new Random().nextInt()%30;//�?大转30�?
			double theta = degree*Math.PI/180;//弧度
			graphic.rotate(theta, x, 20);
			graphic.drawString(ch, x, 20);
			graphic.rotate(-theta, x, 20);
			x+= 30;
		}
		
	}
	private static void setRandomLine(Graphics graphic) {
		graphic.setColor(Color.GREEN);
		
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
		
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			
			graphic.drawLine(x1, y1, x2, y2);
			
		}
	}
	private static void setBorder(Graphics graphic) {
		graphic.setColor(Color.BLUE);
		graphic.drawRect(0, 0, WIDTH, HEIGHT);
	}
	private static void setBackColor(Graphics graphic) {
		graphic.setColor(Color.white);
		graphic.fillRect(1, 1, WIDTH-2, HEIGHT-2);
	}
}
