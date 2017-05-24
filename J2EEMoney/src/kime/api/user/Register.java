package kime.api.user;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kime.help.Conn;
import kime.model.RESULTModel;

public class Register extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		String telephone=request.getParameter("telephone");
		String pass_word=request.getParameter("pass_word");
		RESULTModel<String> result=new RESULTModel<>();
		Gson gson=new Gson();
		
		String sql=String.format("select count(1) count from T_USER where TELEPHONE=%S AND PASS_WORD=%S", telephone,pass_word);
		try {
			ResultSet rs=Conn.executeSql(sql);
			int rowCount=0;
			if(rs.next())    
			{    
			    rowCount=rs.getInt("count");    
			}  
			if (rowCount==0) {
				result.setCode("2001");
				result.setMessage("用户名或者密码错误");
				result.setSuccess("false");
			}else{
				result.setCode("2000");
				result.setMessage("登录成功");
				result.setSuccess("true");
			}
		} catch (Exception e) {
			result.setCode("2002");
			result.setMessage(e.getMessage());
			result.setSuccess("error");
			e.printStackTrace();
		}
		
		result.setSystemTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		result.setData(null);
		response.getWriter().write(gson.toJson(result));
	
	}
	
	
	
	

}
