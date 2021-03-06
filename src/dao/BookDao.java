package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import beans.BookBean;
import beans.IssueBookBean;
public class BookDao {
	
	public static int save(BookBean bean)
	{
		int status=0;
		try {
			Connection con=(Connection) DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into E_BOOK values(?,?,?,?,?,?)");
			ps.setString(1, bean.getCallno());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getAuthor());
			ps.setString(4, bean.getPublisher());
			ps.setInt(5, bean.getQuantity());
			ps.setInt(6, bean.getIssued());
			status=ps.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}
	
	public static List<BookBean> view()
	{
		List<BookBean> list=new ArrayList<BookBean>();
		try {
			Connection con=(Connection) DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from E_BOOK");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				BookBean bean=new BookBean();
				bean.setCallno(rs.getString("callno"));
				bean.setName(rs.getString("name"));
				bean.setAuthor(rs.getString("author"));
				bean.setPublisher(rs.getString("publisher"));
				bean.setQuantity(rs.getInt("quantity"));
				bean.setIssued(rs.getInt("issued"));
				
				list.add(bean);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static boolean checkIssue(String callno){
		boolean status=false;
		try{
			Connection con=(Connection) DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from E_BOOK where callno=? and quantity>issued");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int getIssued(String callno){
		int issued=0;
		try{
			Connection con=(Connection) DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from E_BOOK where callno=?");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				issued=rs.getInt("issued");
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return issued;
	}
	public static int issueBook(IssueBookBean bean){
		String callno=bean.getCallno();
		boolean checkstatus=checkIssue(callno);
		System.out.println("Check status: "+checkstatus);
		if(checkstatus){
			int status=0;
			try{
				Connection con=(Connection) DB.getCon();
				PreparedStatement ps=con.prepareStatement("insert into E_ISSUEBOOK values(?,?,?,?,?,?)");
				ps.setString(1,bean.getCallno());
				ps.setString(2,bean.getStudentid());
				ps.setString(3,bean.getStudentname());
				ps.setLong(4,bean.getStudentmobile());
				java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
				ps.setDate(5,currentDate);
				ps.setString(6,"no");
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update E_BOOK set issued=? where callno=?");
					ps2.setInt(1,getIssued(callno)+1);
					ps2.setString(2,callno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}else{
			return 0;
		}
	}
	public static List<IssueBookBean> viewIssuedBooks(){
		List<IssueBookBean> list=new ArrayList<IssueBookBean>();
		try{
			Connection con=(Connection) DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from E_ISSUEBOOK order by issueddate desc");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				IssueBookBean bean=new IssueBookBean();
				bean.setCallno(rs.getString("callno"));
				bean.setStudentid(rs.getString("studentid"));
				bean.setStudentname(rs.getString("studentname"));
				bean.setStudentmobile(rs.getLong("studentmobile"));
				bean.setIssuedate(rs.getDate("issueddate"));
				bean.setReturnstatus(rs.getString("returnstatus"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	
	public static int returnBook(String callno,int studentid){
		int status=0;
			try{
				Connection con=(Connection) DB.getCon();
				PreparedStatement ps=con.prepareStatement("update E_ISSUEBOOK set returnstatus='yes' where callno=? and studentid=?");
				ps.setString(1,callno);
				ps.setInt(2,studentid);
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update E_BOOK set issued=? where callno=?");
					ps2.setInt(1,getIssued(callno)-1);
					ps2.setString(2,callno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
	}
}
