package test394;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Applicant {
	
	private String hashedID;
	private String username;
	private String fname;
	private String lname;
	private java.sql.Date dob;
	
	public Applicant() {
		this.hashedID = "123456";
		this.username = "Test";
		this.fname = "John";
		this.lname = "Doe";
		Calendar cal = Calendar.getInstance();
		this.dob = new java.sql.Date(cal.getTimeInMillis());
	}
	
	public Applicant(String hid, String un, String first, String last, Calendar dobCal) {
		this.hashedID = hid;
		this.username = un;
		this.fname = first;
		this.lname = last;
		Calendar cal = dobCal;
		this.dob = new java.sql.Date(cal.getTimeInMillis());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sb.append("HashedID: " + this.hashedID + "\n" + "Username: " + this.username + "\n" + "First Name: " + this.fname + "\n" + "Last Name: " + this.lname + "\n" + "DOB: " + sdf.format(this.dob));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Applicant app = new Applicant();
		System.out.println(app.toString());
	}
}