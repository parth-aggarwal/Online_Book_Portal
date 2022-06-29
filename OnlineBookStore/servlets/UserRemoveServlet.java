package servlets;

import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IUserContants;

import java.io.*;

public class UserRemoveServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String password = req.getParameter("pwd2");
		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + IUserContants.TABLE_USERS + "  where " + IUserContants.COLUMN_PASSWORD +" =\'"+password+"\' ");
			//ps.setString(1, uName);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Removed Successfully</div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UserRemove.html");
				rd.include(req, res);
//				pw.println("<div class=\"home hd brown\">User Not Removed </div><br/>");
//				pw.println("<div class=\"tab\"><a href=\"viewbook\">VIEW BOOKS</a></div>");
//				pw.println("<div class='tab'><a href=\"buybook\">BUY BOOKS</a></div>");
//				pw.println("<div class='tab'><a href=\"delself\">DELETE ACCOUNT</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
