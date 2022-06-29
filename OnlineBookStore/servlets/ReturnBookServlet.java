package servlets;

import java.sql.*;
import javax.servlet.*;

import sql.IBookConstants;
import sql.IUserContants;

import java.io.*;

public class ReturnBookServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		RequestDispatcher rd1 = req.getRequestDispatcher("ReturnBook.html");
		rd1.include(req, res);
		String uName = req.getParameter("username01");
//		System.out.print("hello"+uName);
//		String book = req.getParameter("bookname");
		int wallet = Integer.parseInt(req.getParameter("delay01"));
//		System.out.println(wallet);
		int walletFirst=0;
		try {
			Connection con = DBConnection.getCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Users where username = \'"+uName+"\'");
			
			while(rs.next())
			{
				walletFirst = rs.getInt("wallet");
				
			}
			System.out.println(walletFirst);
			wallet = walletFirst-wallet;
			if(wallet>0)
			{
				PreparedStatement ps = con.prepareStatement(
						"update " + IUserContants.TABLE_USERS + " set " + IUserContants.COLUMN_WALLET + " = \'"+wallet+" \'"+ "  where username " +" = \'"+uName+"\'");
//				ps.setString(1, bCode);
				int k = ps.executeUpdate();
				if (k == 1) {
					pw.println("<div class=\"tab\">Book Returned Successfully</div>");
					pw.println("<div class=\"tab\"><a href=\"returnbook\">Return more Books</a></div>");
					pw.println("<div class=\"tab\"><a href=\"addmoney\">Add Money</a></div>");

				} else {
					pw.println("<div class=\"tab\">Book Not Returned</div>");
					pw.println("<div class=\"tab\"><a href=\"returnbook\">Return more Books</a></div>");
					pw.println("<div class=\"tab\"><a href=\"addmoney\">Add Money</a></div>");
				}
			}
			else pw.println("<div class=\"tab\">Nahi ho rha kya huehue</div>");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
