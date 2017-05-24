

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

public class Login extends HttpServlet {

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
		RESULTModel<USERModel> result=new RESULTModel<>();
		Gson gson=new Gson();
		USERModel u=new USERModel(); 
		try {
			u=DBUser.login(telephone, pass_word);
			if (u.getId()!=null) {
				result.setCode("2000");
				result.setMessage("登录成功");
				result.setSuccess("true");

			}else{
				result.setCode("2001");
				result.setMessage("用户名或者密码错误");
				result.setSuccess("false");
			}
		} catch (Exception e) {
			result.setCode("2002");
			result.setMessage(e.getMessage());
			result.setSuccess("error");
			e.printStackTrace();
		}
		
		result.setSystemTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		List<USERModel> lu=new ArrayList<>();
		lu.add(u);
		result.setData(lu);
		response.getWriter().write(gson.toJson(result));
	}
	
	
}
