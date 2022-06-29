package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;

public class ViewBookServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + IBookConstants.TABLE_BOOK);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewBooks2.html");
			rd.include(req, res);
			pw.println("<div class=\"brown tab\">Books Available In Our Store</div>");
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>Book Name</th>\r\n" + 
					"				<th>Book Author</th>\r\n" + 
					"				<th>Book Price</th>\r\n" +  
					"				<th>Book Publisher</th>\r\n" + 
					"				<th>Book Edition</th>\r\n" + 
					"				<th>Book Year</th>\r\n" + 
					"			</tr>");
			while(rs.next())
			{
				//String bCode = rs.getString(2);
				String bName = rs.getString(3);
				String bAuthor = rs.getString(4);
				String bPrice = rs.getString(5);
				String bPublisher = rs.getString(6);
				String bEdition = rs.getString(7);
				String bYear = rs.getString(8);
				//pw.println("<tr><td>"+bCode+"</td>");
				pw.println("<tr><td>"+bName+"</td>");
				pw.println("<td>"+bAuthor+"</td>");
				pw.println("<td>"+bPrice+"</td>");
				pw.println("<td>"+bPublisher+"</td>");
				pw.println("<td>"+bEdition+"</td>");
				pw.println("<td>"+bYear+"</td>\n");
			}
			pw.println("</table>\r\n" + 
					"	</div>");
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
