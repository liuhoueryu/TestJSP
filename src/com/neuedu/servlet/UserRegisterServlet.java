package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//���������������
		response.setContentType("text/html;charset=utf-8");	

		//��ȡout�������
		PrintWriter out = response.getWriter();		

		//��ȡsession����
	    HttpSession session = request.getSession();	

	    //��ȡapplication����
		ServletContext application = this.getServletContext();

		//�������ļ��ж�ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");
		request.setCharacterEncoding(charSet); 
		
		//��������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String photo = request.getParameter("photo");
		
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		String[] interests = request.getParameterValues("interest");
		
		//�ַ�������ƴ���ַ���
		String interest = "";
		if(interests!=null){
			for(String s : interests){
				interest += s + " ";
			}
			interest = interest.trim();
		}
		
		//�������ļ��ж�ȡ���û�����
		int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));
		
		//��������ݼ���
		if(username.length()<5 || username.length()>10){
			out.print("<script>alert('server:�û����ĳ��ȱ�����5-10���ַ�֮��');history.back()</script>");
			return;
		}
		
		DBManager dbManager = DBManager.getInstance();
		
		String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		boolean flag = dbManager.execUpdate(sql, username, password, registerScore, photo, gender, job, interest, new Date());
		
		if(flag){   //����ɹ�
			
			sql = "select * from user where username = ? and password = ?";
			ResultSet rs = dbManager.execQuery(sql, username, password);
			
			try {
				if(rs.next()){   //��¼�ɹ�
						
					//ȡ���û����
					int userid = rs.getInt(1);

					//���������user����
					User user = new User();
					user.setUserid(userid);
					user.setUsername(username);
					user.setScore(registerScore);
					user.setPhoto(photo);
					
					//��user���󱣴浽session���Է�Χ��
					session.setAttribute("user", user);
					
					//��application���Է�Χ�и��������û����� +1
					if(application.getAttribute("onlineCount")==null){   //��һ�η���
						application.setAttribute("onlineCount", 1);
					}else{
						int onlineCount = (Integer)application.getAttribute("onlineCount");
						application.setAttribute("onlineCount", onlineCount + 1);
					}
					
					//�ӳ���ת����ҳ
					//response.sendRedirect("index.jsp");
					response.sendRedirect("user_register_result.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				dbManager.closeConnection();
			}
			
		}else{     //����ʧ��
			
			//ʹ��JSʵ�ֿͻ����ض���
			out.print("<script>alert('ע��ʧ�ܣ�����������');history.back()</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
