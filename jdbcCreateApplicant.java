package test394;

import java.sql.*;
import java.text.ParseException;

public class jdbcCreateApplicant {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String murl = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false";
		String user = "kingSeal@sealsearch";
		String pw = "Password1";
    	String query = "INSERT INTO APPLICANT (a_hashedid, a_username, a_fname, a_lname, a_dob) VALUES ({0}, {1}, {2}, {3}, {4})";
    	String qf = java.text.MessageFormat.format(query, "\'ffffff\'", "\'Mr Query\'", "\'Query\'", "\'Format\'", "\'2021-05-24\'");
    	System.out.println(qf);
    	Class.forName(driverClassName);
    	Connection con = DriverManager.getConnection(murl, user, pw);
    	Statement st = con.createStatement();
    	int count = st.executeUpdate(qf);
    	System.out.println("Rows Affected By This Query = " + count);
    	con.close();
    }
}