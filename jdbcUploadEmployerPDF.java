package test394;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class jdbcUploadEmployerPDF {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String murl = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false";
		String user = "kingSeal@sealsearch";
		String pw = "Password1";
		Class.forName(driverClassName);
    	String filepath = "C:/Users/Jeremy/Desktop/seal.pdf";
    	File pdfFile = new File(filepath);
    	byte[] pdfData = new byte[(int) pdfFile.length()];
    	DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
    	dis.readFully(pdfData);  // read from file into byte[] array
    	dis.close();
    	Connection con = DriverManager.getConnection(murl, user, pw);
    	PreparedStatement ps = con.prepareStatement("UPDATE EMPLOYER SET e_jobListingPDF = ? WHERE e_hashedID = \'Xp2s5v8y/A?D(G+K\';");
    	ps.setBytes(1, pdfData);  // byte[] array
    	ps.executeUpdate();
    	con.close();
    }
}