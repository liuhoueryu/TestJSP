package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.dao.UserDAO;
import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class UserDAOImpl implements UserDAO {

	@Override
	public User findUser(String username, String password) {
		
		DBManager dbManager = DBManager.getInstance();
		
		String sql = "select * from user where username = ? and password = ?";
		
		ResultSet rs = dbManager.execQuery(sql, username, password);
		
		try {
		
			if(rs.next()){    //��¼�ɹ�
					
				//ȡ���û����
				int userid = rs.getInt(1);
			
				//ȡ������
				int score = rs.getInt(4);
			
				//ȡ��ͷ��
				String photo = rs.getString(5);
				
				//���������user����
				User user = new User();
				
				user.setUserid(userid);
				user.setUsername(username);
				user.setScore(score);
				user.setPhoto(photo);
				
				return user;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally{
			dbManager.closeConnection();
		}
		
		return null;
	}

}
