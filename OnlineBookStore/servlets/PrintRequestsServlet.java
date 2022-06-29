package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IRequestConstants;

public class PrintRequestsServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{   String uname = req.getParameter("username");
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		try {
			Connection con = DBConnection.getCon();
			//ArrayList<Books> al = new ArrayList<Books>();
			PreparedStatement ps = con.prepareStatement("Select * from " + IRequestConstants.TABLE_REQUEST + " where " + IRequestConstants.COLUMN_NAME + " IS NOT NULL and username=\'"+uname+"\'");        
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewBooks.html");
			rd.include(req, res);
			pw.println("<div class=\"tab hd brown \">Books Requests</div>");
     		pw.println("<div class=\"tab\"><form action=\"buys\" method=\"post\">");
			pw.println("<table>\r\n" + 
					"			<tr>\r\n" + 
					"				<th>User Name</th>\r\n" + 
					"				<th>Request ID</th>\r\n" + 
					"				<th>Code</th>\r\n" + 
					"				<th>Name</th>\r\n" + 
					"				<th>Author</th>\r\n" +
					"				<th>Price</th>\r\n" +  
					"				<th>Publisher</th>\r\n" + 
					"				<th>Edition</th>\r\n" + 
					"				<th>Year</th>\r\n" + 
					"				<th>Duration</th>\r\n" +  
					"			</tr>");
			while(rs.next())
			{
				String uId = rs.getString(2);
				String ReqId = rs.getString(3);
				String bCode = rs.getString(4);
				String bName = rs.getString(5);
				String bAuthor = rs.getString(6);
				String bPrice = rs.getString(7);
				String bPublisher = rs.getString(8);
				String bEdition= rs.getString(9);
				String bYear = rs.getString(10);
				String bDuration = rs.getString(11);
				pw.println(""
						+ "<tr><td>"+uId+"</td>");
				pw.println("<td>"+ReqId+"</td>");
				pw.println("<td>"+bCode+"</td>");
				pw.println("<td>"+bName+"</td>");
				pw.println("<td>"+bAuthor+"</td>");
				pw.println("<td>"+bPrice+"</td>");
				pw.println("<td>"+bPublisher+"</td>");
				pw.println("<td>"+bEdition+"</td>");
				pw.println("<td>"+bYear+"</td>");
				pw.println("<td>"+bDuration+"</td></tr>");
				
			}
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
