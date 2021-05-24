package test394;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class jdbcRetrieveApplicantPDF {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String murl = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false";
		String user = "kingSeal@sealsearch";
		String pw = "Password1";
		Class.forName(driverClassName);
    	Connection con = DriverManager.getConnection(murl, user, pw);
    	String selectPDF = "SELECT a_resumePDF FROM APPLICANT WHERE a_hashedID = ?;";
    	PreparedStatement ps = con.prepareStatement(selectPDF);
    	ps.setString(1, "?D(G+KbPeShVmYq3");
    	ResultSet rs = ps.executeQuery();
    	File file = new File("retrievePDFTest.png");
    	FileOutputStream output = new FileOutputStream(file);
    	System.out.println("Writing to file " + file.getAbsolutePath());
    	while (rs.next()) {
    	    InputStream input = rs.getBlob("a_resumePDF").getBinaryStream();
    	    byte[] buffer = new byte[999999999];
    	    while (input.read(buffer) > 0) {
    	        output.write(buffer);
    	    }
    	}
    	output.close();
    	ps.close();
    	con.close();
    }
}