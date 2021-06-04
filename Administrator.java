package test394;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Administrator {
  private ArrayList<String> dbInfo;
  private String hashedID;
  private String username;
  private String fname;
  private String lname;
  private java.sql.Date dob;

  public Administrator() throws ClassNotFoundException, SQLException {
    this.hashedID = "777";
    this.username = "Seal Search Admin";
    this.fname = "King";
    this.lname = "Seal";
    Calendar cal = Calendar.getInstance();
    this.dob = new java.sql.Date(cal.getTimeInMillis());
    this.dbInfo = new ArrayList<String>();
    this.dbInfo.add("com.mysql.cj.jdbc.Driver");
    this.dbInfo.add(
        "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false");
    this.dbInfo.add("kingSeal@sealsearch");
    this.dbInfo.add("Password1");
  }

  public Administrator(String hid) throws ClassNotFoundException, SQLException {
    this.hashedID = hid;
    this.dbInfo = new ArrayList<String>();
    this.dbInfo.add("com.mysql.cj.jdbc.Driver");
    this.dbInfo.add(
        "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false");
    this.dbInfo.add("kingSeal@sealsearch");
    this.dbInfo.add("Password1");
  }

  public Administrator(String hid, String un, String first, String last, Calendar dobCal)
      throws ClassNotFoundException, SQLException {
    this.hashedID = hid;
    this.username = un;
    this.fname = first;
    this.lname = last;
    Calendar cal = dobCal;
    this.dob = new java.sql.Date(cal.getTimeInMillis());
    this.dbInfo = new ArrayList<String>();
    this.dbInfo.add("com.mysql.cj.jdbc.Driver");
    this.dbInfo.add(
        "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false");
    this.dbInfo.add("kingSeal@sealsearch");
    this.dbInfo.add("Password1");
  }

  public void createAdmin() throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con = DriverManager.getConnection(dbInfo.get(1), dbInfo.get(2), dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "INSERT INTO ADMINISTRATOR (admin_hashedid, admin_username, admin_fname, admin_lname, admin_dob) VALUES (?, ?, ?, ?, ?)");
    ps.setString(1, this.hashedID);
    ps.setString(2, this.username);
    ps.setString(3, this.fname);
    ps.setString(4, this.lname);
    ps.setDate(5, this.dob);
    int count = ps.executeUpdate();
    System.out.println("Rows Affected By createAdmin() Query = " + count);
    con.close();
    ps.close();
  }

  public void createApplicant(String a_hid, String a_un, String a_first, String a_last,
      Calendar a_dobCal) throws ClassNotFoundException, SQLException {
    Applicant newApp = new Applicant(a_hid, a_un, a_first, a_last, a_dobCal);
    newApp.createApplicant();
  }

  public void createEmployer(String e_hid, String e_compName, String e_un, String e_first,
      String e_last, Calendar e_dobCal) throws ClassNotFoundException, SQLException {
    Employer newEmp = new Employer(e_hid, e_compName, e_un, e_first, e_last, e_dobCal);
    newEmp.createEmployer();
  }


  public void retrieveResume(String a_hid)
      throws ClassNotFoundException, IOException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps =
        con.prepareStatement("SELECT a_resumePDF FROM APPLICANT WHERE a_hashedID = ?;");
    ps.setString(1, a_hid);
    ResultSet rs = ps.executeQuery();
    Random random = new Random();
    String ext = ".pdf";
    String name = String.format("%s%s", System.currentTimeMillis(), random.nextInt(100000) + ext);
    File file = new File(name);
    FileOutputStream output = new FileOutputStream(file);
    System.out.println("Writing to file...");
    while (rs.next()) {
      InputStream input = rs.getBlob("a_resumePDF").getBinaryStream();
      byte[] buffer = new byte[(int) rs.getBlob("a_resumePDF").length()];
      while (input.read(buffer) > 0) {
        output.write(buffer);
      }
    }
    System.out.println("File Saved Here: " + file.getAbsolutePath());
    output.close();
    ps.close();
    con.close();
  }

  public void retrieveJobListing(String e_hid)
      throws ClassNotFoundException, IOException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps =
        con.prepareStatement("SELECT e_jobListingPDF FROM EMPLOYER WHERE e_hashedID = ?;");
    ps.setString(1, e_hid);
    ResultSet rs = ps.executeQuery();
    Random random = new Random();
    String ext = ".pdf";
    String name = String.format("%s%s", System.currentTimeMillis(), random.nextInt(100000) + ext);
    File file = new File(name);
    FileOutputStream output = new FileOutputStream(file);
    System.out.println("Writing to file...");
    while (rs.next()) {
      InputStream input = rs.getBlob("e_jobListingPDF").getBinaryStream();
      byte[] buffer = new byte[(int) rs.getBlob("e_jobListingPDF").length()];
      while (input.read(buffer) > 0) {
        output.write(buffer);
      }
    }
    System.out.println("File Saved Here: " + file.getAbsolutePath());
    output.close();
    ps.close();
    con.close();
  }

  public ResultSet viewApplicant(String a_hid) throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "SELECT a_hashedid, a_username, a_fname, a_lname, a_dob, a_tech_yearsofexp, a_tech_problemsolving, a_tech_degree, a_busi_jobtype, a_busi_growthopp, a_busi_companysize, a_cult_consistency, a_cult_communication, a_cult_leadership FROM APPLICANT WHERE a_hashedID = ?;");
    ps.setString(1, a_hid);
    ResultSet rs = ps.executeQuery();
    return rs;
  }

  public ResultSet browseApplicants() throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "SELECT a_hashedid, a_username, a_fname, a_lname, a_dob, a_tech_yearsofexp, a_tech_problemsolving, a_tech_degree, a_busi_jobtype, a_busi_growthopp, a_busi_companysize, a_cult_consistency, a_cult_communication, a_cult_leadership FROM APPLICANT;");
    ResultSet rs = ps.executeQuery();
    return rs;
  }

  public ResultSet viewEmployer(String e_hid) throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "SELECT e_hashedID, e_companyName, e_username, e_fname, e_lname, e_dob, e_tech_yearsofexp, e_tech_problemsolving, e_tech_degree, e_busi_jobtype, e_busi_growthopp, e_busi_companysize, e_cult_consistency, e_cult_communication, e_cult_leadership FROM EMPLOYER WHERE e_hashedID = ?;");
    ps.setString(1, e_hid);
    ResultSet rs = ps.executeQuery();
    return rs;
  }

  public ResultSet browseEmployers() throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "SELECT e_hashedID, e_companyName, e_username, e_fname, e_lname, e_dob, e_tech_yearsofexp, e_tech_problemsolving, e_tech_degree, e_busi_jobtype, e_busi_growthopp, e_busi_companysize, e_cult_consistency, e_cult_communication, e_cult_leadership FROM EMPLOYER;");
    ResultSet rs = ps.executeQuery();
    return rs;
  }

  public void deleteApplicant(String a_hid) throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement("DELETE FROM applicant WHERE a_hashedID = ?;");
    ps.setString(1, a_hid);
    int count = ps.executeUpdate();
    System.out.println("Rows Affected By deleteApplicant() Query = " + count);
    con.close();
    ps.close();
  }

  public void deleteEmployer(String e_hid) throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement("DELETE FROM employer WHERE e_hashedID = ?;");
    ps.setString(1, e_hid);
    int count = ps.executeUpdate();
    System.out.println("Rows Affected By deleteEmployer() Query = " + count);
    con.close();
    ps.close();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sb.append("HashedID: " + this.hashedID + "\n" + "Username: " + this.username + "\n"
        + "First Name: " + this.fname + "\n" + "Last Name: " + this.lname + "\n" + "DOB: "
        + sdf.format(this.dob) + "\n");
    return sb.toString();
  }

  public void setHashedID(String hid) {
    this.hashedID = hid;
  }

  public void setUsername(String un) {
    this.username = un;
  }

  public void setFname(String first) {
    this.fname = first;
  }

  public void setLname(String last) {
    this.lname = last;
  }

  public void setDOB(Calendar cal) {
    this.dob = new java.sql.Date(cal.getTimeInMillis());
  }

  public ArrayList<String> getDBInfo() {
    return this.dbInfo;
  }

  public String getHashedID() {
    return this.hashedID;
  }

  public String getUsername() {
    return this.username;
  }

  public String getFirstName() {
    return this.fname;
  }

  public String getLastName() {
    return this.lname;
  }

  public java.sql.Date getDOB() {
    return this.dob;
  }

  public static void main(String[] args)
      throws ClassNotFoundException, IOException, ParseException, SQLException {
    Administrator admin = new Administrator();
    System.out.println(admin.toString());
    admin.createAdmin();
    Calendar cal = Calendar.getInstance();
    admin.createApplicant("aca1234", "Generico", "Bob", "Stevens", cal);
    ResultSet test1 = admin.viewApplicant("aca1234");
    ArrayList<String> applicants = new ArrayList<String>();
    while (test1.next()) {
      for (int i = 1; i <= test1.getMetaData().getColumnCount(); i++) {
        applicants.add(test1.getString(i));
      }
    }
    test1.close();
    System.out.println("Looping Thru Applicant Result Set Now!");
    for (int i = 0; i < applicants.size(); i++) {
      System.out.println(applicants.get(i));
    }
    admin.createEmployer("ace0001", "Overwatch", "Tigole", "Jeff", "Kaplan", cal);
    ResultSet test2 = admin.viewEmployer("ace0001");
    ArrayList<String> jobs = new ArrayList<String>();
    while (test2.next()) {
      for (int i = 1; i <= test2.getMetaData().getColumnCount(); i++) {
        jobs.add(test2.getString(i));
      }
    }
    test2.close();
    System.out.println("Looping Thru Employer Result Set Now!");
    for (int i = 0; i < jobs.size(); i++) {
      System.out.println(jobs.get(i));
    }
    admin.deleteApplicant("aca1234");
    admin.deleteEmployer("ace0001");
  }
}
