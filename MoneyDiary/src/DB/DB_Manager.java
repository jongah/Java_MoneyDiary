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
	//���������� DB�� �����ؼ� ���� �� ���� Ŭ����
	static {
		//����̹� �ε�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			System.out.println("���� : " + e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		//Ŀ�ؼ� �� ��ȯ�ϴ� �޼���
		String url="jdbc:mysql://localhost:3306/table_test?useTimezone=true&serverTimezone=UTC";
		String user="root", pwd="mysql1103";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}
	
	public void insertData(Dto_all dto) throws SQLException {
		//dto��ü�� �޾Ƽ� DB�� ���� insert �ϴ� �޼���
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
		      System.out.println("���� : " + e);
	    }
	}
	
	public Vector select_PanelDiy(int year, int month) {
		//�⵵�� ���� �Է� �޾� select�޼��忡 sql������ �Ѱ��ָ鼭 ȣ��(�⵵�� 0 �� ��� ��� �����͸� ������)
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
		//�˻�Ű���� ���� �Է� �޾� if������ sql������ ���� �� select�޼��忡 sql������ �Ѱ��ָ鼭 ȣ��
		int count = 0;
		String sql = "SELECT * FROM table_test.allowance WHERE ";
		
		if(col.equals("��")) {
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
			//sql�� �Ѱ��ָ鼭 ȣ��
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Vector selectAllowance(String sql) throws SQLException{
		//sql���� �޾� DB���� ���� select�ؿ��� �޼��� ����Ÿ������ �����͸� ������ ���� �Ã���.
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
			System.out.println("����" + e);
		}
		return data;
	}
	public void deleteTable() {
		//���̺��� ��� �����͸� ����� Delete�޼���
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
		//sql ������ ���� sumselect�޼��忡 sql������ �Ѱ��ָ鼭 ȣ��
		return selectSum("SELECT sum(amount) FROM allowance WHERE " + col + " = \"" + content + "\" and date like  \"" + year + "%%-" + month + "-%\"");
	}
	public String selectSum_Balance() {
		//���ݱ����� ��� �ݾ��� �� ���ؼ� �ܾ��� ���ϴ� �޼��� sumselect�޼��忡 sql������ �Ѱ��ָ鼭 ȣ��
		return selectSum("SELECT sum(amount) From allowance");
	}
	public String selectSum(String sql) {
		//���� �ϳ��� ������ select�޼��� ������ String���� �Ѵ�.
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
			System.out.println("���� : " + e);
		}
		if(result == null)
			result = "0";
		return result;
	}
	public static void db_Close(PreparedStatement ps, Connection conn) throws SQLException{
		//DB������ ��� �ڿ� ����
		if(ps != null)ps.close();
		if(conn != null)conn.close();
	}
	public static void db_Close(ResultSet rs, Statement stmt, Connection conn) throws SQLException{
		//�ڿ� ����
		if(rs != null)rs.close();
		if(stmt != null)stmt.close();
		if(conn != null)conn.close();
	}
}
