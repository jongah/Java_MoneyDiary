package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Other.Date_ymd;

public class DB_Manager {
	//실질적으로 DB에 연결해서 값을 빼 오는 클래스
	static {
		//드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			System.out.println("오류 : " + e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		//커넥션 값 반환하는 메서드
		String url="jdbc:mysql://localhost:3306/table_test?useTimezone=true&serverTimezone=UTC";
		String user="root", pwd="mysql1103";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}
	
	public void insertData(Dto_all dto) throws SQLException {
		//dto객체를 받아서 DB에 값을 insert 하는 메서드
		PreparedStatement ps;
		Connection conn;
		try {
			conn = getConnection();
			String sql = "insert into table_test.allowance values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setDate(1, dto.getDate());
		    ps.setString(2, dto.getForm());
		    ps.setString(3, dto.getCategory());
		    ps.setString(4, dto.getContent());
		    ps.setInt(5, dto.getAmount());
		    ps.executeUpdate();
		    db_Close(ps, conn);
		}catch (Exception e) {
		      System.out.println("오류 : " + e);
	    }
	}
	
	public Vector select_PanelDiy(int year, int month) {
		//년도와 월을 입력 받아 select메서드에 sql구문을 넘겨주면서 호출(년도가 0 일 경우 모든 데이터를 가져옴)
			try {
				if(year == 0)
					return selectAllowance("SELECT * FROM allowance ORDER BY date");
				else
					return selectAllowance("SELECT * FROM allowance WHERE date like \"" + year + "%%-" + month + "-%\" ORDER BY date");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public Vector select_Search(String col, String content, String word, int year, int month) {
		//검색키워드 등을 입력 받아 if문으로 sql구문을 만든 뒤 select메서드에 sql구문을 넘겨주면서 호출
		int count = 0;
		String sql = "SELECT * FROM table_test.allowance WHERE ";
		
		if(col.equals("최")) {
			sql += "amount = (select " + content + "(amount) from allowance)";
			count ++;
		}else if(!col.equals("all")) {
			sql += col + " = \"" + content + "\"";
			count ++;
		}
		if(!word.equals("no")){
			if(count != 0)
				sql += " and ";
			 sql += "(date LIKE '%"+ word +"%' or form LIKE '%"+ word +"%' or category LIKE '%"+ word +"%'or content LIKE '%"+ word +"%' or amount LIKE '%"+ word +"%')";
		}
		sql += " and date like \"" + year + "%%-" + month + "-%\" ORDER BY date";
		try {
			return selectAllowance(sql);
			//sql문 넘겨주면서 호출
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Vector selectAllowance(String sql) throws SQLException{
		//sql문을 받아 DB에서 값을 select해오는 메서드 벡터타입으로 데이터를 저장해 리턴 시깉다.
		Vector data = new Vector();
		Statement stmt;
		ResultSet rs;
		Connection conn;
		try
		{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
			    String date = rs.getString(1);
			    String form = rs.getString(2);
			    String category = rs.getString(3);
			    String content = rs.getString(4);
			    String amount = rs.getString(5);
			    
			    Vector row = new Vector();
			    row.add(date);
			    row.add(form);
			    row.add(category);
			    row.add(content);
			    row.add(amount);
			    data.add(row);
			}
			db_Close(rs, stmt, conn);
		}
		catch (Exception e)
		{
			System.out.println("오류" + e);
		}
		return data;
	}
	public void deleteTable() {
		//테이블의 모든 데이터를 지우는 Delete메서드
		Connection conn;
		Statement stmt;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE allowance");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String selectSum_Where(int year, int month, String col, String content) {
		//sql 구문을 만들어서 sumselect메서드에 sql구문을 넘겨주면서 호출
		return selectSum("SELECT sum(amount) FROM allowance WHERE " + col + " = \"" + content + "\" and date like  \"" + year + "%%-" + month + "-%\"");
	}
	public String selectSum_Balance() {
		//지금까지의 모든 금액을 다 더해서 잔액을 구하는 메서드 sumselect메서드에 sql구문을 넘겨주면서 호출
		return selectSum("SELECT sum(amount) From allowance");
	}
	public String selectSum(String sql) {
		//값을 하나만 빼오는 select메서드 리턴은 String으로 한다.
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String result = "";
		try 
		{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result = rs.getString(1);
				break;
			}
			db_Close(rs, stmt, conn);
		}catch (Exception e) {
			System.out.println("오류 : " + e);
		}
		if(result == null)
			result = "0";
		return result;
	}
	public static void db_Close(PreparedStatement ps, Connection conn) throws SQLException{
		//DB연동에 썼던 자원 해제
		if(ps != null)ps.close();
		if(conn != null)conn.close();
	}
	public static void db_Close(ResultSet rs, Statement stmt, Connection conn) throws SQLException{
		//자원 해제
		if(rs != null)rs.close();
		if(stmt != null)stmt.close();
		if(conn != null)conn.close();
	}
}
