package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IUserContants;

public class SearchServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String bname = req.getParameter("bookname");
		try {
			Connection con = DBConnection.getCon();
			//ArrayList<Books> al = new ArrayList<Books>();
			PreparedStatement ps = con.prepareStatement("Select * from " + IBookConstants.TABLE_BOOK+ " where " + IBookConstants.COLUMN_NAME +" = \'"+bname+"\'");
			//int k = ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			//if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Search.html");
				rd.include(req, res);
				while(rs.next())
				{
				pw.println("<div class=\"tab hd brown \">          Book Searched          </div>");
	     		pw.println("<div class=\"tab\"><form action=\"buys\" method=\"post\">");
				pw.println("<table>\r\n" + 
						"			<tr>\r\n" + 
						"				<th>Code</th>\r\n" + 
						"				<th>Name</th>\r\n" + 
						"				<th>Author</th>\r\n" +
						"				<th>Price</th>\r\n" +  
						"				<th>Publisher</th>\r\n" + 
						"				<th>Edition</th>\r\n" + 
						"				<th>Year</th>\r\n" + 
						"			</tr>");

					String bCode = rs.getString(2);
					String bName = rs.getString(3);
					String bAuthor = rs.getString(4);
					String bPrice = rs.getString(5);
					String bPublisher = rs.getString(6);
					String bEdition= rs.getString(7);
					String bYear = rs.getString(8);
					pw.println("<td>"+bCode+"</td>");
					pw.println("<td>"+bName+"</td>");
					pw.println("<td>"+bAuthor+"</td>");
					pw.println("<td>"+bPrice+"</td>");
					pw.println("<td>"+bPublisher+"</td>");
					pw.println("<td>"+bEdition+"</td>");
					pw.println("<td>"+bYear+"</td>");
					
				//}
//			} else {
//				RequestDispatcher rd = req.getRequestDispatcher("Search.html");
//				rd.include(req, res);
//			}
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}		}
			catch (Exception e) {
			e.printStackTrace();
		}
	}

}
