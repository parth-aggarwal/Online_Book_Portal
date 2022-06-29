package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IUserContants;

public class AdminAllUsers extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("Select * from " + IUserContants.TABLE_USERS);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewBooks2.html");
			rd.include(req, res);
			pw.println("<div class=\"brown tab\">All Users</div>");
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>UserID</th>\r\n" + 
					"				<th>User Name</th>\r\n" + 
					"				<th>First Name</th>\r\n" +  
					"				<th>Last Name</th>\r\n" + 
					"				<th>Mail ID</th>\r\n" + 
					"			</tr>");
			while(rs.next())
			{
				//String bCode = rs.getString(2);
				String userid = rs.getString(10);
				String username = rs.getString(1);
				String firstname = rs.getString(3);
				String lastname = rs.getString(4);
				String mailid = rs.getString(7);
				//pw.println("<tr><td>"+bCode+"</td>");
				pw.println("<tr><td>"+userid+"</td>");
				pw.println("<td>"+username+"</td>");
				pw.println("<td>"+firstname+"</td>");
				pw.println("<td>"+lastname+"</td>");
				pw.println("<td>"+mailid+"</td>");
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
