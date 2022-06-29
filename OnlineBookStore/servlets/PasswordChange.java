package servlets;

import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class PasswordChange extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("user1");
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		String bkid = req.getParameter("pwd1");
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"update " + IUserContants.TABLE_USERS +" set "+ IUserContants.COLUMN_PASSWORD +" =\'"+bkid+"\' " + "where " + IUserContants.COLUMN_USERNAME + " =\'"+uName+"\'");
			//ps.setString(2, uName);
			//ps.setString(1, bkid);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Pwd Changed Successfully</div>");
			}else {

				RequestDispatcher rd = req.getRequestDispatcher("PwdChange.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName</div>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}