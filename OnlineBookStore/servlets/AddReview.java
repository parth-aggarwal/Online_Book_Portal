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
import sql.IReviewConstants;

public class AddReview extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		
		res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		String bName = req.getParameter(IReviewConstants.COLUMN_NAME);
		String bAuthor = req.getParameter(IReviewConstants.COLUMN_AUTHOR);
		String bEdition = req.getParameter(IReviewConstants.COLUMN_EDITION);
		String bYear = req.getParameter(IReviewConstants.COLUMN_YEAR);
		String review = req.getParameter(IReviewConstants.COLUMN_REVIEW);
		RequestDispatcher rd = req.getRequestDispatcher("Review.html");
		rd.include(req, res);
		try {
			if (bName != null)
			{
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into " + IReviewConstants.TABLE_REVIEW + "  values(?,?,?,?,?)");
			ps.setString(1, bName);
			ps.setString(2, bAuthor);
			ps.setString(3, bEdition);
			ps.setString(4, bYear);
			ps.setString(5, review);
			int k = ps.executeUpdate();
			if(k==1)
			{
//				RequestDispatcher rdd = req.getRequestDispatcher("Review.html");
//				rdd.include(req, res);
				pw.println("<div class=\"tab\">Review Added</div>");
			}
			else
			{
//				RequestDispatcher rdd = req.getRequestDispatcher("Sampl.html");
//				rdd.include(req, res);
				pw.println("<div class=\"tab\">Failed to Add Review! Fill up CareFully</div>");
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
