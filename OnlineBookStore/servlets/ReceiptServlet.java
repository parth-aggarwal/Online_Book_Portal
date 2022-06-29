package servlets;

import java.sql.*;
import java.io.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IRequestConstants;
import sql.IUserContants;

public class ReceiptServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		try {
			String uname = req.getParameter("username");
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from " + IBookConstants.TABLE_BOOK);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			RequestDispatcher rd = req.getRequestDispatcher("buybook");
			rd.include(req, res);
			
//			pw.println(
//					"<div class=\"tab\">\r\n" + "		<table>\r\n" + "			<tr>\r\n" + "				\r\n"
//							+ "				<th>Book Code</th>\r\n" + "				<th>Book Name</th>\r\n"
//							+ "				<th>Book Author</th>\r\n" + "				<th>Book Price</th>\r\n"
//							+  "				<th>Book Publisher</th>\r\n" + "	    <th>Book Edition</th>\r\n"
//							+  "			  <th>Book Year</th>\r\n </tr>");
			while (rs.next()) {
				String bPrice = rs.getString(IBookConstants.COLUMN_PRICE);
				String bCode = rs.getString(IBookConstants.COLUMN_BARCODE);
				String bName = rs.getString(IBookConstants.COLUMN_NAME);
				String bAuthor = rs.getString(IBookConstants.COLUMN_AUTHOR);
				//String userName = rs.getString(IUserContants.COLUMN_USERNAME);
				String bPublisher = rs.getString(IBookConstants.COLUMN_PUBLISHER);
				String bEdition = rs.getString(IBookConstants.COLUMN_EDITION);
				String bYear = rs.getString(IBookConstants.COLUMN_YEAR);
				String ownrid = rs.getString("ownerid");
				//String bDuration = req.getParameter("pay");
				i = i + 1;
				String qt = "qty" + Integer.toString(i);
				String quantity = req.getParameter(qt);
				try {
					String check1 = "checked" + Integer.toString(i);
					String getChecked = req.getParameter(check1);

					if (getChecked.equals("pay")) {
						pw.println("<tr><td>" + bCode + "</td>");
						pw.println("<td>" + bName + "</td>");
						pw.println("<td>" + bAuthor + "</td>");
						pw.println("<td>" + bPrice + "</td>");
						pw.println("<td>" + bPublisher + "</td>");
						pw.println("<td>" + bEdition + "</td>");
						pw.println("<td>" + bYear + "</td>");
//						bQty = bQty - quantity;
//						System.out.println(bQty);
						PreparedStatement ps1 = con.prepareStatement(" insert into " + IRequestConstants.TABLE_REQUEST +" (ownerid,username,barcode,name,author,price,publisher,edition,year,duration) "+" values(?,\'"+uname+"\'"+",?,?,?,?,?,?,?,?) ");
						ps1.setString(1, ownrid);
						ps1.setString(2, bCode);
						ps1.setString(3, bName);
						ps1.setString(4, bAuthor);
						ps1.setString(5, bPrice);
						ps1.setString(6, bPublisher);
						ps1.setString(7, bEdition);
						ps1.setString(8, bYear);
						ps1.setString(9, quantity);
						ps1.executeUpdate();
						pw.println("<div class=\"tab\">You Successfully Requested a Book</div>");
					}
				} catch (Exception e) {
				}
			}
			pw.println("</table><br/><div class='tab'>Book Issued\"</div>");
			//String fPay = req.getParameter("f_pay");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
