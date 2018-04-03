package application.controllers;

import application.Database;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
@RequestMapping("/account")
public class AccountController {

        @RequestMapping(value="", method = RequestMethod.GET)
        public ModelAndView showRegistrationPage(ModelAndView modelAndView){
            modelAndView.setViewName("account");
            return modelAndView;
        }

//    @RequestMapping("/account")
//    public String hostMeal() {
//        return "account";
//    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView registerUser(//@RequestParam("emailsignup") String email,
                                     @RequestParam("name") String name1,
                                     @RequestParam("email") String email1,
                                     @RequestParam("location") String location1,
                                     @RequestParam("ccnum") String ccnum1,
                                     @RequestParam("cctype") String cctype1,
                                     @RequestParam("ccdigits") String ccdigits1,

                                     HttpServletRequest request) throws SQLException, ClassNotFoundException {

        ModelAndView modelAndView = new ModelAndView();

        Connection newConnection = Database.connectDatabase();
        Statement statement = newConnection.createStatement();

//        //String fullName = name;
//        String update = "INSERT Users\n" +
//                //"set    email='" + email + "', pass = '" + password +"',\n" +
//                "	username='" + name + "', description=null, country=null, currency=null, ppicture=null,\n" +
//                "    dob='2012-01-01', gender=null, userlang=null, ccnum=null, cccvv=null, cccountry=null,\n" +
//                "    ccprovince=null, cccity=null, ccaddress=null, ccpostal=null, ccexp='2020-5-2'\n";
//
//        ResultSet rs=statement.executeQuery("select * from Users");


        String  email, pass, username, description, country, currency, ppicture;
        String dob, gender, userlang,  ccnum, cccvv, cccountry, ccprovince;
        String cccity, ccaddress, ccpostal, ccexp;
        int userid;

        // Search criteria
        /*
        The default value for each variable is set to null (or 0).  In the
        search query anything that is changed to be not null will be a
        limitation on the results from the select statement.  For example if
        the email is set to "123@email.com" than only users with that email
        will be returned from the query.  To be clear if the email is set it
        will return 1 or 0 users as that is a primary key for the table.

        Date format: year-month-day
         */
        userid = 0; // Int
        email = email1;
        pass = null;
        username = null;
        description = null;
        country = null;
        currency = null;
        ppicture = null;
        dob = null;
        gender = null;
        userlang = null;
        ccnum = ccnum1;
        cccvv = ccdigits1;
        cccountry = null;
        ccprovince = null;
        cccity = null;
        ccaddress = null;
        ccpostal = null;
        ccexp = null;

        // IGNORE - Create the search line
        String selectCriteria = "";
        boolean notFirst = false;

        if (userid > 0) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "userid = " + userid;
            notFirst = true;
        }
        if (email != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "email = '" + email + "'";
            notFirst = true;
        }
        if (pass != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "pass = '" + pass + "'";
            notFirst = true;
        }
        if (username != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "username = '" + username + "'";
            notFirst = true;
        }
        if (description != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "description = '" + description + "'";
            notFirst = true;
        }
        if (country != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "country = '" + country + "'";
            notFirst = true;
        }
        if (currency != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "currency = '" + currency + "'";
            notFirst = true;
        }
        if (ppicture != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "ppicture = '" + ppicture + "'";
            notFirst = true;
        }
        if (dob != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "dob = '" + dob + "'";
            notFirst = true;
        }
        if (gender != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "gender = '" + gender + "'";
            notFirst = true;
        }
        if (userlang != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "userlang = '" + userlang + "'";
            notFirst = true;
        }
        if (ccnum != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "ccnum = '" + ccnum + "'";
            notFirst = true;
        }
        if (cccvv != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "cccvv = '" + cccvv + "'";
            notFirst = true;
        }
        if (cccountry != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "cccountry = '" + cccountry + "'";
            notFirst = true;
        }
        if (ccprovince != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "ccprovince = '" + ccprovince + "'";
            notFirst = true;
        }
        if (cccity != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "cccity = '" + cccity + "'";
            notFirst = true;
        }
        if (ccaddress != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "ccaddress = '" + ccaddress + "'";
            notFirst = true;
        }
        if (ccpostal != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "ccpostal = '" + ccpostal + "'";
            notFirst = true;
        }
        if (ccexp != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "ccexp = '" + ccexp + "'";
            notFirst = true;
        }

        // IGNORE - Finalize and run the query
        if (!selectCriteria.equals("")) {
            selectCriteria = " where " + selectCriteria;
        }
        ResultSet rs = statement.executeQuery("select * from Users"
                + selectCriteria + ";");

        // Retrieve and print results from query or print "no results"
        /*
        Obviously for the website printing is not required.  Therefore, after
        retrival you can do whatever is necessary with the data.
         */
        if (rs.first()) {
            do {
                userid = rs.getInt("userid");
                email = rs.getString("email");
                pass = rs.getString("pass");
                username = rs.getString("username");
                description = rs.getString("description");
                country = rs.getString("country");
                currency = rs.getString("currency");
                ppicture = rs.getString("ppicture");
                dob = rs.getString("dob");
                gender = rs.getString("gender");
                userlang = rs.getString("userlang");
                ccnum = rs.getString("ccnum");
                cccvv = rs.getString("cccvv");
                cccountry = rs.getString("cccountry");
                ccprovince = rs.getString("ccprovince");
                cccity = rs.getString("cccity");
                ccaddress = rs.getString("ccaddress");
                ccpostal = rs.getString("ccpostal");
                ccexp = rs.getString("ccexp");

                System.out.println("User ID: " + userid);
                System.out.println("Email: " + email);
                System.out.println("Password: " + pass);
                System.out.println("Username: " + username);
                System.out.println("User Description: " + description);
                System.out.println("Country: " + country);
                System.out.println("Currency: " + currency);
                System.out.println("Profile Picture Location: " + ppicture);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Gender: " + gender);
                System.out.println("User Language: " + userlang);
                System.out.println("Credit Card Number: " + ccnum);
                System.out.println("Credit Card CVV: " + cccvv);
                System.out.println("Credit Card Country: " + cccountry);
                System.out.println("Credit Card Province: " + ccprovince);
                System.out.println("Credit Card City: " + cccity);
                System.out.println("Credit Card Address: " + ccaddress);
                System.out.println("Credit Card Postal: " + ccpostal);
                System.out.println("Credit Card Expirary Date: " + ccexp);
                System.out.println();
            } while (rs.next());
        } else {
            System.out.println("No Results");
        }

        // Insert criteria
        /*
        The default value for each variable is set to null.  In order to
        insert a user with the specified values simply change the value from
        null to the desired value.  Note that email and password are not
        allowed to be null for an insert.


        Date string format: year-month-day
         */

        String  email2, pass2, username2, description2, country2, currency2, ppicture2;
        String dob2, gender2, userlang2,  ccnum2, cccvv2, cccountry2, ccprovince2;
        String cccity2, ccaddress2, ccpostal2, ccexp2;

        email2 = email1;
        //pass2 = pass;
        //username2 = username;
        //description2 = description;
        //country2 = null;
        //currency2 = null;

        dob2 = null; // Date
        gender2 = null;
        userlang2 = null;
        ccnum2 = ccnum1;
        cccvv2 = ccdigits1;
        cccountry2 = null;
        ccprovince2 = null;
        cccity2 = null;
        ccaddress2 = null;
        ccpostal2 = null;
        ccexp2 = null; // Date

        // IGNORE - Create the insert line
        String insertLine = "";
        insertLine += "email = '" + email + "', ";
        insertLine += "pass = '" + pass + "', ";
        if (username != null) {
            insertLine += "username = '" + username + "', ";
        } else {
            insertLine += "username = null, ";
        }
        if (description != null) {
            insertLine += "description = '" + description + "', ";
        } else {
            insertLine += "description = null, ";
        }
        if (country != null) {
            insertLine += "country = '" + country + "', ";
        } else {
            insertLine += "country = null, ";
        }
        if (currency != null) {
            insertLine += "currency = '" + currency + "', ";
        } else {
            insertLine += "currency = null, ";
        }
        // For testing
//        String fileToUpload = "words.jpg";
//        profilePicture = new File(fileToUpload);

        if (dob != null) {
            insertLine += "dob = '" + dob + "', ";
        } else {
            insertLine += "dob = null, ";
        }
        if (gender != null) {
            insertLine += "gender = '" + gender + "', ";
        } else {
            insertLine += "gender = null, ";
        }
        if (userlang != null) {
            insertLine += "userlang = '" + userlang + "', ";
        } else {
            insertLine += "userlang = null, ";
        }
        if (ccnum != null) {
            insertLine += "ccnum = '" + ccnum + "', ";
        } else {
            insertLine += "ccnum = null, ";
        }
        if (cccvv != null) {
            insertLine += "cccvv = '" + cccvv + "', ";
        } else {
            insertLine += "cccvv = null, ";
        }
        if (cccountry != null) {
            insertLine += "cccountry = '" + cccountry + "', ";
        } else {
            insertLine += "cccountry = null, ";
        }
        if (ccprovince != null) {
            insertLine += "ccprovince = '" + ccprovince + "', ";
        } else {
            insertLine += "ccprovince = null, ";
        }
        if (cccity != null) {
            insertLine += "cccity = '" + cccity + "', ";
        } else {
            insertLine += "cccity = null, ";
        }
        if (ccaddress != null) {
            insertLine += "ccaddress = '" + ccaddress + "', ";
        } else {
            insertLine += "ccaddress = null, ";
        }
        if (ccpostal != null) {
            insertLine += "ccpostal = '" + ccpostal + "', ";
        } else {
            insertLine += "ccpostal = null, ";
        }
        if (ccexp != null) {
            insertLine += "ccexp = '" + ccexp + "'";
        } else {
            insertLine += "ccexp = null";
        }

        // Run the insert and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an insert to the
        database.
         */
        if (email != null && pass != null) {
            try {
                statement.executeUpdate("insert into Users set " + insertLine + ";");
                System.out.println("Following User Added");
                System.out.println("Email: " + email);
                System.out.println("Password: " + pass);
                System.out.println("Username: " + username);
                System.out.println("User Description: " + description);
                System.out.println("Country: " + country);
                System.out.println("Currency: " + currency);
                System.out.println("Profile Picture Location: " + ppicture);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Gender: " + gender);
                System.out.println("User Language: " + userlang);
                System.out.println("Credit Card Number: " + ccnum);
                System.out.println("Credit Card CVV: " + cccvv);
                System.out.println("Credit Card Country: " + cccountry);
                System.out.println("Credit Card Province: " + ccprovince);
                System.out.println("Credit Card City: " + cccity);
                System.out.println("Credit Card Address: " + ccaddress);
                System.out.println("Credit Card Postal: " + ccpostal);
                System.out.println("Credit Card Expirary Date: " + ccexp);
            } catch (com.mysql.jdbc.MysqlDataTruncation e) {
                System.out.println("Error in length/format of input");
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
                System.out.println("This user already exists");
            }
        } else {
            System.out.println("Email and password not entered");
        }
        Database.disconnectDatabase(newConnection);
        return modelAndView;
    }


}
