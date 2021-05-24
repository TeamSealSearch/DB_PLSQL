public class administratorQueries {

/*
These Strings are the basis for our Administrator queries
The intention would be to substitute relevant fields (e.g. e_hashedid) for a specific user
Intended usage would be as follows
*
String stringWithPlaceHolder = "test String with placeholders {0} {1} {2} {3}";
String formattedString = java.text.MessageFormat.format(stringWithPlaceHolder, "place-holder-1", "place-holder-2", "place-holder-3", "place-holder-4");
*/

static final String createAdmin = "INSERT INTO ADMINISTRATOR (admin_hashedid, admin_username, admin_fname, admin_lname, admin_dob) VALUES (\'9z$Cw)J@NcRfUjX\', \'Seal Search Admin\', \'Sammy\', \'Seal\', \'2021-01-29\')"; 

static final String updateAdminInfo = "UPDATE ADMINISTRATOR SET admin_username = \'The Creator\', admin_fname = \'Spaghetti\', admin_lname = \'Monster\', admin_dob = \'2021-01-29\' WHERE admin_hashedid = \'9z$Cw)J@NcRfUjX\';";

static final String updateAdminUsername = "UPDATE ADMINISTRATOR SET admin_username = \'Supreme Being\' WHERE admin_hashedid = \'9z$Cw)J@NcRfUjX\';";

static final String updateAdminName = "UPDATE ADMINISTRATOR SET admin_fname = \'All\', admin_lname = \'Father\' WHERE admin_hashedid = \'9z$Cw)J@NcRfUjX\';";

static final String viewAdminEmpRow = "SELECT * FROM ADMINISTRATOR WHERE admin_hashedid = \'9z$Cw)J@NcRfUjX\';";

static final String deleteAdminAccount = "DELETE FROM ADMINISTRATOR WHERE admin_hashedid = \'9z$Cw)J@NcRfUjX\';";

}