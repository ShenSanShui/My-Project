package db;

import java.sql.*;

public class ContactDao {
	
	//验证登陆帐户是否合法
	public static boolean checkUser(String name, String pw) {
		boolean flag = false;
		String sql = "select * from user where " +
				"username=? and password=?";
		Connection conn = DBConnection.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// 添加联系人
	public static int insertContact(String sql, Contact aFriend) {
		int result = 0;
		/*
		 * 取连接
		 * 预处理语句对象各IN参数赋值 
		 * 执行传入的insert语句 
		 * 返回结果
		 */
		Connection conn = DBConnection.getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aFriend.getName());
			pstmt.setString(2, aFriend.getSex());
			pstmt.setInt(3, aFriend.getAge());
			pstmt.setString(4, aFriend.getPhone());
			pstmt.setString(5, aFriend.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 查询所有联系人
	public static ResultSet getAllContacts() {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from contact";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}

	// 按姓名模糊检索联系人
	public static ResultSet getContact(String name) {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from contact " +
				"where name LIKE '%" + name + "%'";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}

	// 按姓名精确检索联系人
	public static ResultSet getContactByName(String name) {
		ResultSet rt = null;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		String sql = "select * from contact " +
				"where name ='" + name + "'";
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}

	// 更新联系人：包括修改联系人资料和删除联系人
	public static int updateContact(String sql) {
		int result = 0;
		Connection conn = DBConnection.getConn();
		Statement stmt = null;
		try {
			stmt = conn.createStatement
			(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
