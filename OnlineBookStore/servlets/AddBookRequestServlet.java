package servlets;
import java.lang.Integer;
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
import sql.IRequestConstants;
import sql.IUserContants;

public class AddBookRequestServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		RequestDispatcher rd = req.getRequestDispatcher("BookRequest.html");
		rd.include(req, res);
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String userName =req.getParameter("name1");
//		String uId = req.getParameter(IUserContants.COLUMN_USERNAME);
		//String bCode = req.getParameter(IRequestConstants.COLUMN_BARCODE);
		String bName = req.getParameter(IRequestConstants.COLUMN_NAME);
		String bAuthor = req.getParameter(IRequestConstants.COLUMN_AUTHOR);
		String bPublisher = req.getParameter(IRequestConstants.COLUMN_PUBLISHER);
		String bEdition =req.getParameter(IRequestConstants.COLUMN_EDITION);
		String bYear = req.getParameter(IRequestConstants.COLUMN_YEAR);
		String bPrice =req.getParameter(IRequestConstants.COLUMN_PRICE);
		String bDuration = req.getParameter(IRequestConstants.COLUMN_DURATION);
		System.out.println("Abhinav");
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(" insert into " + IRequestConstants.TABLE_REQUEST +" (username,name,author,price,publisher,edition,year,duration) "+" values("+"\'"+userName+"\'"+",?,?,?,?,?,?,?) ");
//			ps.setInt(1, uId);
			//ps.setString(1, bCode);
			ps.setString(1, bName);
			ps.setString(2, bAuthor);
			ps.setString(3, bPrice);
			ps.setString(4, bPublisher);
			ps.setString(5, bEdition);
			ps.setString(6, bYear);
			ps.setString(7, bDuration);
			
			System.out.println("Talesra");
//			System.out.println(uId);
			int k = ps.executeUpdate();
			if(k==1)
			{
				pw.println("Thik Bhai.");
			}
			else
			{
//				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				pw.println("<div class=\"tab\">Failed to Add Book Request! Fill up CareFully</div>");
				rd.include(req, res);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
}
