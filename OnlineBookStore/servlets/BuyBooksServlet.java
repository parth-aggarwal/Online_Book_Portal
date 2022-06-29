package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;
import sql.IUserContants;
public class BuyBooksServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		try {
			Connection con = DBConnection.getCon();
			//ArrayList<Books> al = new ArrayList<Books>();
			PreparedStatement ps = con.prepareStatement("Select barcode,name,author,price,publisher,edition,year  from " + IBookConstants.TABLE_BOOK);
			ResultSet rs = ps.executeQuery();
			RequestDispatcher rd = req.getRequestDispatcher("ViewBooks.html");
			rd.include(req, res);
			pw.println("<div class=\"tab hd brown \">Books Available In Our Store</div>");
			pw.println("<div class=\"tab\"><form action=\"buys\" method=\"post\">");
			pw.println("<table>\r\n" + 
					"			<tr>\r\n" + 
					"				<th>Books</th>\r\n" + 
//					"				<th>Code</th>\r\n" + 
					"				<th>Name</th>\r\n" + 
					"				<th>Author</th>\r\n" + 
					"				<th>Price</th>\r\n" + 
					"				<th>Publisher</th>\r\n" + 
					"				<th>Edition</th>\r\n" + 
					"				<th>Year</th>\r\n" + 
					"               <th>Duration</th>\r\n" + 
					"			</tr>");
			int i=0;
			while(rs.next())
			{
				//String bCode = rs.getString(1);
				String bName = rs.getString(2);
				String bAuthor = rs.getString(3);
				String bPrice = rs.getString(4);
				String bPublisher = rs.getString(5);
				String bEdition = rs.getString(6);
				String bYear = rs.getString(7);
				i=i+1;
				String n = "checked"+ Integer.toString(i);
				String q = "qty"+Integer.toString(i);
				pw.println("<tr>\r\n" + 
						"				<td>\r\n" + 
						"					<input type=\"checkbox\" name="+n+" value=\"pay\">\r\n" + //Value is made equal to bcode
						"				</td>");
				//pw.println("<td>"+bCode+"</td>");
				pw.println("<td>"+bName+"</td>");
				pw.println("<td>"+bAuthor+"</td>");
				pw.println("<td>"+bPrice+"</td>");
				pw.println("<td>"+bPublisher+"</td>");
				pw.println("<td>"+bEdition+"</td>");
				pw.println("<td>"+bYear+"</td>");
				pw.println("<td><input type=\"text\" name="+q+" size=5 value=\"0\" text-align=\"center\"></td></tr>");
				
			}
			pw.println("</table>\r\n");
			pw.println("Enter Username : <input class=\"form-control\" type=\"text\" name=\"username\"/>");
			pw.println("<input class=\"btn btn-primary\"type=\"submit\" value=\" REQUEST NOW \">"+"<br/>"+
					"	</form>\r\n" + 
					"	</div>");
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
			pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
		rd.include(req, res);
		pw.println("<div class=\"tab\">Incorrect Details Entered</div>");
			e.printStackTrace();
		}
	}

}
