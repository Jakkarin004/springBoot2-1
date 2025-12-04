package pccth.sp.pccthspseedservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class MyClass {

	public static void main(String[] args) {
		
		Connection connect = null;
		Statement s = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect =  DriverManager.getConnection("jdbc:mysql://10.1.233.16:3336/etaxinvoicedb?user=root&password=password");
			
			s = connect.createStatement();
			
			String CusID = "C002";
			
			String sql = "select table_schema as database_name,table_name from information_schema.tables "
					+ "where table_type = 'BASE TABLE' and table_schema='etaxinvoicedb' and table_name in ('data_entry') "
					+ "order by table_schema,table_name;";
			
			ResultSet rec = s.executeQuery(sql);
			
			while((rec!=null) && (rec.next()))
            {
                System.out.println(rec.getString("table_name"));
                
                String sql2 = "SELECT column_name, column_type FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '"+ rec.getString("table_name") +"'";
                //String sql2 = "SELECT column_name, column_type FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'data_entry'";
                
                ResultSet rec2 = s.executeQuery(sql2);
                while((rec2!=null) && (rec2.next()))
                {
                    System.out.print("	>"+rec2.getString("column_name"));
                    System.out.println(" - "+rec2.getString("column_type"));
                }
            }
			
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Close
		try {
			if(connect != null){
				s.close();
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}