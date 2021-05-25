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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
    File file = new File("retrievedAppResume.png");
    FileOutputStream output = new FileOutputStream(file);
    System.out.println("Writing to file...");
    while (rs.next()) {
      InputStream input = rs.getBlob("a_resumePDF").getBinaryStream();
      byte[] buffer = new byte[999999999];
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

  public void getProfileRankings() throws ClassNotFoundException, SQLException {
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

  public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
    Applicant app = new Applicant();
    app.setHashedID("987654");
    app.setUsername("Method Man");
    app.setFname("Clifford");
    app.setLname("Smith");
    Calendar cal = Calendar.getInstance();
    cal.set(1971, 02, 02);
    app.setDOB(cal);
    String[] rankings = {"9", "8", "7", "6", "5", "4", "3", "2", "1"};
    app.createApplicant();
    app.uploadResume("C:/Users/Jeremy/Desktop/seal.pdf");
    app.updateProfile(rankings);
    app.retrieveResume();
    app.getProfileRankings();
    System.out.println(app.toString());
  }
}
