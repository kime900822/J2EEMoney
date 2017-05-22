/**
* FileName: Conn.java
* 数据库连接类
*
* @author 赵舟浩
    * @Date    2017-05-22
* @version 1.00
*/

package kime.help;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Conn {
	public static PreparedStatement getConn(String sql) throws Exception{
		javax.naming.Context ctx=new javax.naming.InitialContext();
		javax.sql.DataSource ds=(javax.sql.DataSource) ctx.lookup("java:/comp/env/jdbc/money");
		Connection conn=ds.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		return pstmt;			
	}
}
