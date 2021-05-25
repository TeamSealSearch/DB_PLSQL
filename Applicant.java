package test394;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.Date;
import java.util.Random;

public class Applicant {
  private ArrayList<String> dbInfo;
  private ArrayList<String> profileRankings;
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
    this.dbInfo = new ArrayList<String>();
    this.dbInfo.add("com.mysql.cj.jdbc.Driver");
    this.dbInfo.add(
        "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false");
    this.dbInfo.add("kingSeal@sealsearch");
    this.dbInfo.add("Password1");
  }

  public Applicant(String hid) {
    this.hashedID = hid;
    this.dbInfo = new ArrayList<String>();
    this.dbInfo.add("com.mysql.cj.jdbc.Driver");
    this.dbInfo.add(
        "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false");
    this.dbInfo.add("kingSeal@sealsearch");
    this.dbInfo.add("Password1");
  }

  public Applicant(String hid, String un, String first, String last, Calendar dobCal) {
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

  public void createApplicant() throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con = DriverManager.getConnection(dbInfo.get(1), dbInfo.get(2), dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "INSERT INTO APPLICANT (a_hashedid, a_username, a_fname, a_lname, a_dob) VALUES (?, ?, ?, ?, ?)");
    ps.setString(1, this.hashedID);
    ps.setString(2, this.username);
    ps.setString(3, this.fname);
    ps.setString(4, this.lname);
    ps.setDate(5, this.dob);
    int count = ps.executeUpdate();
    System.out.println("Rows Affected By createApplicant() Query = " + count);
    con.close();
    ps.close();
  }

  public void uploadResume(String filepath)
      throws ClassNotFoundException, IOException, SQLException {
    Class.forName(dbInfo.get(0));
    String fp = filepath;
    File pdfFile = new File(fp);
    byte[] pdfData = new byte[(int) pdfFile.length()];
    DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
    dis.readFully(pdfData);
    dis.close();
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps =
        con.prepareStatement("UPDATE APPLICANT SET a_resumePDF = ? WHERE a_hashedID = ?;");
    ps.setBytes(1, pdfData);
    ps.setString(2, this.hashedID);
    int count = ps.executeUpdate();
    System.out.println("Rows Affected By uploadResume() Query = " + count);
    con.close();
    ps.close();
  }

  public void retrieveResume() throws ClassNotFoundException, IOException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps =
        con.prepareStatement("SELECT a_resumePDF FROM APPLICANT WHERE a_hashedID = ?;");
    ps.setString(1, this.hashedID);
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

  public void updateProfile(String[] rankings) throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    this.profileRankings = new ArrayList<String>();
    for (int i = 0; i < rankings.length; i++) {
      this.profileRankings.add(rankings[i]);
    }
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "UPDATE APPLICANT SET a_tech_yearsofexp = ?, a_tech_problemsolving = ?, a_tech_degree = ?, a_busi_jobtype = ?, a_busi_growthopp = ?, a_busi_companysize = ?, a_cult_consistency = ?, a_cult_communication = ?, a_cult_leadership = ? WHERE a_hashedid = ?;");
    for (int i = 0; i < this.profileRankings.size(); i++) {
      ps.setString(i + 1, this.profileRankings.get(i));
    }
    ps.setString(10, this.hashedID);
    int count = ps.executeUpdate();
    System.out.println("Rows Affected By updateProfile() Query = " + count);
    con.close();
    ps.close();
  }

  public void updateApplicant() throws ClassNotFoundException, ParseException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement("SELECT * FROM APPLICANT WHERE a_hashedID = ?;");
    ps.setString(1, this.hashedID);
    ResultSet rs = ps.executeQuery();
    ArrayList<String> appInfo = new ArrayList<String>();
    while (rs.next()) {
      for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        appInfo.add(rs.getString(i));
      }
    }
    this.hashedID = appInfo.get(0);
    this.username = appInfo.get(1);
    this.fname = appInfo.get(2);
    this.lname = appInfo.get(3);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date parsed = sdf.parse(appInfo.get(4));
    java.sql.Date sql = new java.sql.Date(parsed.getTime());
    this.dob = sql;
    this.profileRankings = new ArrayList<String>();
    for (int i = 7; i < appInfo.size(); i++) {
      this.profileRankings.add(appInfo.get(i));
    }
  }

  public void updateRankings() throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    this.profileRankings = new ArrayList<String>();
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps = con.prepareStatement(
        "SELECT a_tech_yearsofexp, a_tech_problemsolving, a_tech_degree, a_busi_jobtype, a_busi_growthopp, a_busi_companysize, a_cult_consistency, a_cult_communication, a_cult_leadership FROM APPLICANT WHERE a_hashedid = ?;");
    ps.setString(1, this.hashedID);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
        this.profileRankings.add(rs.getString(i));
      }
    }
    System.out.println("Retrieved Rankings: " + this.profileRankings.toString());
    con.close();
    ps.close();
    rs.close();
  }

  public ResultSet browseJobs() throws ClassNotFoundException, SQLException {
    Class.forName(dbInfo.get(0));
    Connection con =
        DriverManager.getConnection(this.dbInfo.get(1), this.dbInfo.get(2), this.dbInfo.get(3));
    PreparedStatement ps =
        con.prepareStatement("SELECT * FROM employer WHERE e_jobListingPDF IS NOT NULL;");
    ResultSet rs = ps.executeQuery();
    return rs;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sb.append("HashedID: " + this.hashedID + "\n" + "Username: " + this.username + "\n"
        + "First Name: " + this.fname + "\n" + "Last Name: " + this.lname + "\n" + "DOB: "
        + sdf.format(this.dob) + "\n");
    sb.append("Years Of Experience: " + this.profileRankings.get(0) + "\n" + "Problem Solving: "
        + this.profileRankings.get(1) + "\n" + "Degree: " + this.profileRankings.get(2) + "\n");
    sb.append("Job Type: " + this.profileRankings.get(3) + "\n" + "Growth Opportunity: "
        + this.profileRankings.get(4) + "\n" + "Company Size: " + this.profileRankings.get(5)
        + "\n");
    sb.append("Consistency: " + this.profileRankings.get(6) + "\n" + "Communication: "
        + this.profileRankings.get(7) + "\n" + "Leadership: " + this.profileRankings.get(8));
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

  public ArrayList<String> getProfileRankings() {
    return this.profileRankings;
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
    Applicant app = new Applicant();
    app.updateRankings();
    System.out.println(app.getHashedID() + "\n" + app.getUsername() + "\n" + app.getFirstName()
        + " " + app.getLastName() + "\n" + app.getDOB().toString());
    System.out.println(app.getDBInfo().toString() + "\n" + app.getProfileRankings().toString());
    app = new Applicant("2129704133");
    System.out.println("Before: " + app.hashedID + " " + app.username + " " + app.fname + " "
        + app.lname + " " + app.profileRankings);
    app.updateApplicant();
    System.out.println("After: " + app.hashedID + " " + app.username + " " + app.fname + " "
        + app.lname + " " + app.dob + " " + app.getProfileRankings().toString());
    app.setHashedID("987654320");
    app.setUsername("Captain America");
    app.setFname("Steve");
    app.setLname("Rogers");
    Calendar cal = Calendar.getInstance();
    cal.set(1918, 06, 04);
    app.setDOB(cal);
    String[] rankings = {"100", "101", "102", "103", "104", "105", "106", "107", "108"};
    app.createApplicant();
    app.uploadResume("C:/Users/Jeremy/Desktop/captain.pdf");
    app.updateProfile(rankings);
    app.retrieveResume();
    System.out.println(app.toString());
    ResultSet test = app.browseJobs();
    ArrayList<String> jobs = new ArrayList<String>();
    while (test.next()) {
      for (int i = 1; i <= test.getMetaData().getColumnCount(); i++) {
        jobs.add(test.getString(i));
      }
    }
    test.close();
    System.out.println("Looping Thru Result Set Now");
    for (int i = 0; i < jobs.size(); i++) {
      if (i != 6) {
        System.out.println(jobs.get(i));
      }
    }
  }
}
