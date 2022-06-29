package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.IBookConstants;
import sql.IRequestConstants;
import sql.IUserContants;

import java.io.*;

public class RemoveBookRequestServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String name = req.getParameter("name123");
		String book = req.getParameter("book123");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"delete from " + IRequestConstants.TABLE_REQUEST + "  where " + IUserContants.COLUMN_USERNAME + " = \'"+name+"\'"+" and "+IBookConstants.COLUMN_NAME+" = \'"+book+"\'");
//			ps.setString(1, name);
//			ps.setString(2, book);
			int k = ps.executeUpdate();
			if (k == 1) {
				System.out.println("p");
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Request Removed Successfully</div>");
//				pw.println("<div class=\"tab\">Book Request Deleted</div>");
//				pw.println("<div class=\"tab\"><a href=\"RemoveBooks.html\">Remove more Book Requests</a></div>");

			} else {
				System.out.println("q");
				RequestDispatcher rd = req.getRequestDispatcher("RequestRemove.html");
				rd.include(req, res);
//				pw.println("<div class=\"tab\">Book Request Not Deleted</div>");
//				pw.println("<div class=\"tab\"><a href=\"RemoveBooks.html\">Remove more Books Requests</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
