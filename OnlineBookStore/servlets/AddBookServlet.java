package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import constants.IOnlineBookStoreConstants;
import sql.IBookConstants;

public class AddBookServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
		rd.include(req, res);
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String username = req.getParameter("ownerid");
		String bCode = req.getParameter(IBookConstants.COLUMN_BARCODE);
		String bName = req.getParameter(IBookConstants.COLUMN_NAME);
		String bAuthor = req.getParameter(IBookConstants.COLUMN_AUTHOR);
		String bPrice =(req.getParameter(IBookConstants.COLUMN_PRICE));
		String bPublisher = req.getParameter(IBookConstants.COLUMN_PUBLISHER);
		String bEdition = req.getParameter(IBookConstants.COLUMN_EDITION);
		String bYear = req.getParameter(IBookConstants.COLUMN_YEAR);
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into " + IBookConstants.TABLE_BOOK + "  values(?,?,?,?,?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, bCode);
			ps.setString(3, bName);
			ps.setString(4, bAuthor);
			ps.setString(5, bPrice);
			ps.setString(6, bPublisher);
			ps.setString(7, bEdition);
			ps.setString(8, bYear);
			int k = ps.executeUpdate();
			if(k==1)
			{
//				RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
//				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>");
			}
			else
			{
//				RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
				pw.println("<div class=\"tab\">Failed to Add Books! Fill up CareFully</div>");
//				rd.include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
