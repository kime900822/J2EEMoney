package kime.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kime.help.Conn;
import kime.model.USERModel;

public class DBUser {

	/**
	 * 用户登录
	 * @param telephone
	 * @param pass_word
	 * @return
	 * @throws Exception
	 */
	public static USERModel login(String telephone,String pass_word) throws Exception{
		USERModel u=new USERModel();
		String sql=String.format("select * from T_USER where TELEPHONE=%S AND PASS_WORD=%S", telephone,pass_word);
		ResultSet rs=Conn.executeSql(sql);
		if(rs.next())    
		{    
			u.setPhone(rs.getString("PHONE"));
			u.setId(rs.getString("ID"));
		    u.setBalance(rs.getString("BALANCE"));
		    u.setPass_word(rs.getString("PASS_WORD"));
		    u.setTelephone(rs.getString("TELEPHONE"));
		}  

		return u;
	}
	
	
	public static List<USERModel> getUser() throws Exception{
		List<USERModel> luser=new ArrayList<>();
		String sql=String.format("select *  from T_USER where ID<>'0' ");
		ResultSet rs=Conn.executeSql(sql);
		while(rs.next())    
		{   
			USERModel u=new USERModel();
			u.setPhone(rs.getString("PHONE"));
			u.setId(rs.getString("ID"));
		    u.setBalance(rs.getString("BALANCE"));
		    u.setPass_word(rs.getString("PASS_WORD"));
		    u.setTelephone(rs.getString("telephone"));
		    luser.add(u);
		}  
		return luser;
		
	}
	
}
