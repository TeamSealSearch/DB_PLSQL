public class applicantQueries {

/*
These Strings are the basis for our Applicant queries
The intention would be to substitute relevant fields (e.g. a_hashedid) for a specific user
Intended usage would be as follows
*
String stringWithPlaceHolder = "test String with placeholders {0} {1} {2} {3}";
String formattedString = java.text.MessageFormat.format(stringWithPlaceHolder, "place-holder-1", "place-holder-2", "place-holder-3", "place-holder-4");
*/

static final String createApplicant = "INSERT INTO APPLICANT (a_hashedid, a_username, a_fname, a_lname, a_dob) VALUES (\'?D(G+KbPeShVmYq3\', \'Rockstar Programmer\', \'Test\', \'Testerson\', \'2020-01-29\')";

static final String updateAppProfile = "UPDATE APPLICANT SET a_tech_yearsofexp = 4, a_tech_problemsolving = 7, a_tech_degree = 2, a_busi_jobtype = 4, a_busi_growthopp = 8, a_busi_companysize = 10, a_cult_consistency = 8, a_cult_communication = 9, a_cult_leadership = 7 WHERE a_hashedid = '?D(G+KbPeShVmYq3';";

static final String updateAppInfo = "UPDATE APPLICANT SET a_username = \'Dark Lord\', a_fname = \'Darth\', a_lname = \'Vader\', a_dob = \'2020-01-29\' WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String updateAppUsername = "UPDATE APPLICANT SET a_username = \'Enlightened One\' WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String updateAppName = "UPDATE APPLICANT SET a_fname = \'Luke\', a_lname = \'Skywalker\' WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String viewEntireAppRow = "SELECT * FROM APPLICANT WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String viewAppInfo = "SELECT a_hashedid, a_username, a_fname, a_lname, a_dob FROM APPLICANT WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String viewAppProfile = "SELECT a_tech_yearsofexp, a_tech_problemsolving, a_tech_degree, a_busi_jobtype, a_busi_growthopp, a_busi_companysize, a_cult_consistency, a_cult_communication, a_cult_leadership FROM APPLICANT WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String viewAppResume = "SELECT a_resumepdf FROM APPLICANT WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

static final String deleteAppAccount = "DELETE FROM APPLICANT WHERE a_hashedid = \'?D(G+KbPeShVmYq3\';";

}