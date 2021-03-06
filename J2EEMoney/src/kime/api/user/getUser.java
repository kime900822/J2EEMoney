package kime.api.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kime.db.DBUser;
import kime.model.RESULTModel;
import kime.model.USERModel;

public class GetUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		RESULTModel<USERModel> result=new RESULTModel<>();
		Gson gson=new Gson();
		List<USERModel> luser=new ArrayList<>();
		
		try {
			luser=DBUser.getUser();
			if (luser.size()==0) {
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
