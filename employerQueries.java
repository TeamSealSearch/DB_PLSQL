public class employerQueries {

/*
These Strings are the basis for our Employer queries
The intention would be to substitute relevant fields (e.g. e_hashedid) for a specific user
Intended usage would be as follows
*
String stringWithPlaceHolder = "test String with placeholders {0} {1} {2} {3}";
String formattedString = java.text.MessageFormat.format(stringWithPlaceHolder, "place-holder-1", "place-holder-2", "place-holder-3", "place-holder-4");
*/

static final String createEmployer = "INSERT INTO EMPLOYER (e_hashedid, e_companyname, e_username, e_fname, e_lname, e_dob) VALUES ('Xp2s5v8y/A?D(G+K', 'Google', 'The Founder', 'Larry', 'Page', SYSDATE)";

static final String updateEmpProfile = "UPDATE EMPLOYER SET e_tech_yearsofexp = 4, e_tech_problemsolving = 7, e_tech_degree = 2, e_busi_jobtype = 4, e_busi_growthopp = 8, e_busi_companysize = 10, e_cult_consistency = 8, e_cult_communication = 9, e_cult_leadership = 7 WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String updateEmpInfo = "UPDATE EMPLOYER SET e_username = 'The Creator', e_fname = 'Sergey', e_lname = 'Brin', e_dob = SYSDATE WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String updateEmpUsername = "UPDATE EMPLOYER SET e_username = 'Alphabet' WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String updateEmpName = "UPDATE EMPLOYER SET e_fname = 'Sundar', e_lname = 'Pichai' WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String viewEntireEmpRow = "SELECT * FROM EMPLOYER WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String viewEmpInfo = "SELECT e_hashedid, e_companyname, e_username, e_fname, e_lname, e_dob FROM EMPLOYER WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String viewEmpProfile = "SELECT e_tech_yearsofexp, e_tech_problemsolving, e_tech_degree, e_busi_jobtype, e_busi_growthopp, e_busi_companysize, e_cult_consistency, e_cult_communication, e_cult_leadership FROM EMPLOYER WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String viewEmpJobListing = "SELECT e_joblistingpdf FROM EMPLOYER WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

static final String deleteEmpAccount = "DELETE FROM EMPLOYER WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';";

}