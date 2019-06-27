<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.util.Random"%>
<%@page import="java.awt.Graphics"%>
<%@page import="java.awt.image.BufferedImage, java.awt.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		final int width = 150;
		final int height = 30;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics g = bi.getGraphics();
	    g.setColor(Color.WHITE);
	    g.drawRect(0, 0, width, height);
	    g.fillRect(1, 1, width-1, height-1);
	    g.setColor(Color.RED);
	    for(int i = 1; i <= 6; i++){
	    	int x1 = new Random().nextInt(width-1);
	    	int x2 = new Random().nextInt(width-1);
	    	int y1 = new Random().nextInt(height-1);
	    	int y2 = new Random().nextInt(height-1);
	    	g.drawLine(x1, y1, x2, y2);
	    }
	    g.setColor(Color.BLACK);
	    g.setFont(new Font("consolas",Font.BOLD,20));
	    int x = 25;
	    int y = 15;
	    String num = "";
	    for(int i =1 ; i <= 3; i++){
	    	int s = new Random().nextInt(9);
	    	x += new Random().nextInt(50);
	    	y += new Random().nextInt(5);
	    	g.drawString(s+"", x, y);
	    	num += s+"";
	    }
	    ServletOutputStream output = response.getOutputStream();
	    ImageIO.write(bi, "jpeg", output);
	    output.close();
	    session.setAttribute("num", num);
	    response.sendRedirect("login.jsp");
	%>
</body>
</html>