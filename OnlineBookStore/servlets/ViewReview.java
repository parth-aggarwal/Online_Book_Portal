package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IReviewConstants;

public class ViewReview extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + IReviewConstants.TABLE_REVIEW);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewReview.html");
			rd.include(req, res);
			pw.println("<div class=\"brown tab\">Book Review</div>");
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>Book Name</th>\r\n" + 
					"				<th>Book Author</th>\r\n" + 
					"				<th>Book Edition</th>\r\n" + 
					"				<th>Book Year</th>\r\n" + 
					"				<th>Book Review</th>\r\n" + 
					"			</tr>");
			while(rs.next())
			{
				String bName = rs.getString(1);
				String bAuthor = rs.getString(2);
				String bEdition = rs.getString(3);
				String bYear = rs.getString(4);
				String review = rs.getString(5);
				pw.println("<tr><td>"+bName+"</td>");
				pw.println("<td>"+bAuthor+"</td>");
				pw.println("<td>"+bEdition+"</td>");
				pw.println("<td>"+bYear+"</td>");
				pw.println("<td>"+review+"</td>");
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
