package kime.api.user;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kime.help.Conn;
import kime.model.RESULTModel;
import kime.model.USERModel;

public class getUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		RESULTModel<USERModel> result=new RESULTModel<>();
		Gson gson=new Gson();
		List<USERModel> luser=new ArrayList<>();
		
		String sql=String.format("select *  from T_USER where ID<>'0' ");
		try {
			ResultSet rs=Conn.executeSql(sql);
			int rowCount=0;
			while(rs.next())    
			{   
				rowCount++;
				USERModel u=new USERModel();
				u.setPhone(rs.getString("PHONE"));
			    u.setBalance(rs.getString("BALANCE"));
			    u.setPass_word(rs.getString("PASS_WORD"));
			    u.setTelephone(rs.getString("telephone"));
			    luser.add(u);
			}  
			if (rowCount==0) {
				result.setCode("2011");
				result.setMessage("无数据");
				result.setSuccess("false");
			}else{
				result.setCode("2010");
				result.setMessage("获取成功");
				result.setSuccess("true");
			}
		} catch (Exception e) {
			result.setCode("2012");
			result.setMessage(e.getMessage());
			result.setSuccess("error");
			e.printStackTrace();
		}
		
		result.setSystemTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		result.setData(luser);
		response.getWriter().write(gson.toJson(result));
	
	}

}
