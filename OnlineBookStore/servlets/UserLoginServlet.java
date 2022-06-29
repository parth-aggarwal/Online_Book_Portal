package servlets;

import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class UserLoginServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String uName = req.getParameter(IUserContants.COLUMN_USERNAME);
		String pWord = req.getParameter(IUserContants.COLUMN_PASSWORD);
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + IUserContants.TABLE_USERS + " WHERE "
					+ IUserContants.COLUMN_USERNAME + "=? AND " + IUserContants.COLUMN_PASSWORD + "=? ");
//			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + IUserContants.TABLE_USERS + " WHERE "
//					+ IUserContants.COLUMN_USERNAME + "=? AND " + IUserContants.COLUMN_PASSWORD + "=? AND " + IUserContants.COLUMN_USERTYPE + "=2");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"home brown\">Welcome " + uName + "</div><br/>");
				pw.println("<div class=\"tab hd brown\">User Login Successful !</div><br/>");
				pw.println("<div class=\"tab\"><br/><a href=\"AddBook.html\">ADD BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><a href=\"viewbook\">VIEW BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"search\">SEARCH BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"buybook\">REQUEST FOR AVAILABLE BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"reqbooks\">REQUEST FOR OTHER BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"ViewReq.html\">VIEW BOOK REQUESTS TO OTHERS</a></div>");
				pw.println("<div class='tab'><a href=\"ViewOtherReq.html\">VIEW BOOK REQUESTS FROM OTHERS</a></div>");
				pw.println("<div class='tab'><a href=\"remove\">DELETE BOOK</a></div>");
				pw.println("<div class='tab'><a href=\"reqdelete\">DELETE BOOK REQUESTS</a></div>");
				pw.println("<div class='tab'><a href=\"PwdChange.html\">CHANGE PASSWORD</a></div>");
				pw.println("<div class='tab'><a href=\"returnbook\">RETURN BOOK</a></div>");
				pw.println("<div class='tab'><a href=\"addmoney\">ADD MONEY</a></div>");
				pw.println("<div class='tab'><a href=\"addreview\">ADD REVIEW</a></div>");
				pw.println("<div class='tab'><a href=\"viewreview\">VIEW REVIEW</a></div>");
				pw.println("<div class='tab'><a href=\"delself\">DELETE ACCOUNT</a></div>");
				
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
			}

		} catch (Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class=\"tab\">Incorrect Details Entered</div>");
			e.printStackTrace();
		}
	}
}