package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.IUserContants;

import java.io.*;

public class AdminDeleteUser extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bkid = req.getParameter("userid");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + IUserContants.TABLE_USERS + "  where " + IUserContants.COLUMN_USERID + "=?");
			ps.setString(1, bkid);
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Removed Successfully</div>");
				pw.println("<div class=\"tab\"><br/><a href=\"AddBook.html\">ADD BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"RemoveBooks.html\">REMOVE BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"viewbook\">VIEW BOOKS</a></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"DeleteUser.html\">DELETE USER</a></div>");
				
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">User Not Present</div>");
				pw.println("<div class=\"tab\"><br/><a href=\"AddBook.html\">ADD BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"RemoveBooks.html\">REMOVE BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"viewbook\">VIEW BOOKS</a></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"DeleteUser.html\">DELETE USER</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
