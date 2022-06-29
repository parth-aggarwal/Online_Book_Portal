package servlets;

import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class AddMoney extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		RequestDispatcher rd = req.getRequestDispatcher("AddMoney.html");
		rd.include(req, res);
		String bkid = req.getParameter("pwd1");
		int value = Integer.parseInt(req.getParameter("user1"));
		int walletFirst=0;
		try {
			Connection con = DBConnection.getCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Users where password = \'"+bkid+"\'");
			
			while(rs.next())
			{
				walletFirst = rs.getInt("wallet");
				
			}
			value= value+walletFirst;
			PreparedStatement ps = con.prepareStatement(
					"update " + IUserContants.TABLE_USERS +" set "+ IUserContants.COLUMN_WALLET + " = \'"+value+"\'"+" where " + IUserContants.COLUMN_PASSWORD + " =\'"+bkid+"\' ");
			//ps.setString(2, uName);
			//ps.setString(1, bkid);
			int k = ps.executeUpdate();
			if (k == 1) {
				
				pw.println("<div class=\"tab\">Wallet Updated</div>");
			}else {
				pw.println("<div class=\"tab\">Too Much Money</div>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}