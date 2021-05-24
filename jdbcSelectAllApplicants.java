package test394;

import java.sql.*;

public class jdbcSelectAllApplicants {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String murl = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false";
		String user = "kingSeal@sealsearch";
		String pw = "Password1";
    	String query = "SELECT * FROM APPLICANT";
    	System.out.println(query);
    	Class.forName(driverClassName);
    	Connection con = DriverManager.getConnection(murl, user, pw);
    	Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery(query);
    	int count = 0;
    	while (rs.next()) {
    		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
    			if (rs.getMetaData().getColumnName(i).equals("a_hashedID")) {
    				count += 1;
    				if (count > 1) {
        	        	System.out.println("");
        	        }
    			}
    	        String columnValue = rs.getString(i);
    	        System.out.print(rs.getMetaData().getColumnName(i) + ": " + columnValue + "\n");
    		}
    	}
    	con.close();
    }
}