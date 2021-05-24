package test394;

import java.sql.*;
import java.text.ParseException;

public class jdbcCreateEmployer {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String murl = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false";
		String user = "kingSeal@sealsearch";
		String pw = "Password1";
    	String query = "INSERT INTO EMPLOYER (e_hashedid, e_companyname, e_username, e_fname, e_lname, e_dob) VALUES ({0}, {1}, {2}, {3}, {4}, {5})";
    	String qf = java.text.MessageFormat.format(query, "\'Xp2s5v8y/A?D(G+K\'", "\'Google\'", "\'The Founder\'", "\'Larry\'", "\'Page\'", "\'1973-03-26\'");
    	System.out.println(qf);
    	Class.forName(driverClassName);
    	Connection con = DriverManager.getConnection(murl, user, pw);
    	Statement st = con.createStatement();
    	int count = st.executeUpdate(qf);
    	System.out.println("Rows Affected By This Query = " + count);
    	con.close();
    }
}