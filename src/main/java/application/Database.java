package application;
import application.models.Meals;
import application.models.User;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Database {

    public static Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://eatngreetdb.ctuwvkfyzjbp.us-east-2.rds.amazonaws.com:3306/engdb?user=EatnGreet&password=Eatngreet6");
        return connection;
    }

    public static void disconnectDatabase(Connection conn) throws SQLException {
        conn.close();
    }

    // TODO: Test
    public static boolean deleteAttending(int mealid, String gemail) throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        boolean deleteSuccess;

        // IGNORE - Create the delete line
        String deleteCriteria = "";
        deleteCriteria += "mealid = " + mealid;
        deleteCriteria += " and ";
        deleteCriteria += "gemail = '" + gemail + "'";

        // IGNORE - Finalize and run the query
        int results = 0;
        if (!deleteCriteria.equals("")) {
            deleteCriteria = " where " + deleteCriteria;
            results = statement.executeUpdate("delete from Attending" + deleteCriteria + ";");
        }

        // Display a message about how the delete went
        /*
        Obviously for the website printing is not required.  Therefore, after
        delete you can display a message to the user.
         */
        if (results > 0) {
//            System.out.println(results + " entrie(s) deleted");
            deleteSuccess = true;
        } else {
            if (deleteCriteria.equals("")) {
//                System.out.println("No delete criteria entered");
            } else {
//                System.out.println("No entries found with specified criteria");
            }
            deleteSuccess = false;
        }
        disconnectDatabase(newConnection);
        return deleteSuccess;
    }

    // TODO: Test
    public static boolean deleteMeal(int mealid) throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        boolean deleteSuccess;

        // IGNORE - Create the delete line
        String deleteCriteria = "";
        deleteCriteria += "mealid = " + mealid;

        // IGNORE - Finalize and run the query
        int results = 0;
        if (!deleteCriteria.equals("")) {
            deleteCriteria = " where " + deleteCriteria;
            results = statement.executeUpdate("delete from Meals" + deleteCriteria + ";");
        }

        // Display a message about how the delete went
        /*
        Obviously for the website printing is not required.  Therefore, after
        delete you can display a message to the user.
         */
        if (results > 0) {
//            System.out.println(results + " entrie(s) deleted");
            deleteSuccess = true;
        } else {
            if (deleteCriteria.equals("")) {
//                System.out.println("No delete criteria entered");
            } else {
//                System.out.println("No entries found with specified criteria");
            }
            deleteSuccess = false;
        }
        disconnectDatabase(newConnection);
        return deleteSuccess;
    }

    // TODO: Test
    public static boolean insertAttending(int mealid, String gemail) throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        boolean insertSuccess;

        // IGNORE - Create the insert line
        String insertLine = "";
        insertLine += "mealid = " + mealid + ", ";
        insertLine += "gemail = '" + gemail + "'";

        // Run the insert and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an insert to the
        database.
         */
        if (mealid != 0 && gemail != null) {
            try {
                statement.executeUpdate("insert into Attending set " + insertLine + ";");
                insertSuccess = true;
//                System.out.println("Following Attending Relationship Added");
//                System.out.println("Meal ID: " + mealid);
//                System.out.println("Guest's Email: " + gemail);
            } catch (com.mysql.jdbc.MysqlDataTruncation e) {
//                System.out.println("Error in length/format of input");
                insertSuccess = false;
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
//                System.out.println("Attending relationship already existed");
                insertSuccess = false;
            }
        } else {
//            System.out.println("An entry was left blank");
            insertSuccess = false;
        }
        disconnectDatabase(newConnection);
        return insertSuccess;
    }

    // TODO: Test
    public static boolean insertHRating(int mealid, String gemail, int rating, String comments)
            throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        String hemail;
        boolean insertSuccess;

        // IGNORE - Create the insert line
        String insertLine = "";
        insertLine += "mealid = " + mealid + ", ";
        insertLine += "gemail = '" + gemail + "', ";
        insertLine += "rating = " + rating + ", ";
        if (comments != null) {
            insertLine += "comments = '" + comments + "'";
        } else {
            insertLine += "comments = null";
        }

        // Run the insert and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an insert to the
        database.
         */
        if (mealid > 0 && gemail != null && rating > 0) {
            try {
                statement.executeUpdate("insert into HRating set " + insertLine + ";");
                ResultSet rs = statement.executeQuery("select * from Meals " + "where mealid = " + mealid + ";");
                rs.first();
                hemail = rs.getString("hemail");
                statement.executeUpdate("update HRating set hemail = '" + hemail + "' where mealid = " + mealid
                        + " and gemail ='" + gemail + "';");
                insertSuccess = true;
//                System.out.println("Following Host Rating Added");
//                System.out.println("Meal ID: " + mealid);
//                System.out.println("Guest's Email: " + gemail);
//                System.out.println("Host's Email: " + hemail);
//                System.out.println("Rating: " + rating);
//                System.out.println("Comments: " + comments);
            } catch (com.mysql.jdbc.MysqlDataTruncation e) {
//                System.out.println("Error in length/format of input");
                insertSuccess = false;
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
//                System.out.println("This rating already exists");
                insertSuccess = false;
            }
        } else {
//            System.out.println("A mandatory entry was left blank");
            insertSuccess = false;
        }
        disconnectDatabase(newConnection);
        return insertSuccess;
    }

    // TODO: Test
    public static boolean insertMeal(String hemail, String dom, String mtitle, File mealPicture, int capacity,
                                  double pricepp, String category, String description, String cancelationtime,
                                  double cancelationfee, String country, String city, String saddress, String postal)
            throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        String ACCESS_KEY = "AKIAIGHO2KWNFTEHE5TQ";
        String SECRET_ACCESS_KEY = "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";
        String BUCKET = "elasticbeanstalk-us-east-2-855393191779";
        String mpicture;
        int mealid;
        boolean insertSuccess;

        // IGNORE - Create the insert line
        String insertLine = "";
        mpicture = null;
        insertLine += "hemail = '" + hemail + "', ";
        insertLine += "dom = '" + dom + "', ";
        insertLine += "mtitle = '" + mtitle + "', ";
        insertLine += "mpicture = '" + "TEMP" + "', ";
        // For testing
//        String fileToUpload = "words.jpg";
//        mealPicture = new File(fileToUpload);
        insertLine += "capacity = " + capacity + ", ";
        insertLine += "pricepp = " + pricepp + ", ";
        insertLine += "category = '" + category + "', ";
        insertLine += "description = '" + description + "', ";
        insertLine += "cancelationtime = '" + cancelationtime + "', ";
        insertLine += "cancelationfee = " + cancelationfee + ", ";
        insertLine += "country = '" + country + "', ";
        insertLine += "city = '" + city + "', ";
        insertLine += "saddress = '" + saddress + "', ";
        insertLine += "postal = '" + postal + "'";

        // Run the insert and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an insert to the
        database.
         */
        if (hemail != null && dom != null && capacity > 0 && pricepp > 0.0 && category != null && description != null
                && mealPicture != null && cancelationtime != null && cancelationfee > 0.0 && country != null
                && city != null && saddress != null && postal != null && mtitle != null) {
            try {
                statement.executeUpdate("insert into Meals set " + insertLine
                        + " on duplicate key update " + insertLine + ";");
                ResultSet rs = statement.executeQuery("select * from Meals "
                        + "where hemail = '" + hemail + "' and dom = '" + dom + "';");
                rs.first();
                mealid = rs.getInt("mealid");
                mpicture = "https://s3.us-east-2.amazonaws.com/elasticbeanstalk"
                        + "-us-east-2-855393191779/Meal/" + mealid + ".jpg";
                BasicAWSCredentials creds = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);
                AmazonS3 S3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials
                                (new AWSStaticCredentialsProvider(creds)).build();
                S3Client.putObject(new PutObjectRequest(BUCKET, "Meal/" + mealid + ".jpg", mealPicture));
                statement.executeUpdate("update Meals set mpicture ='" + mpicture + "' where hemail = '" + hemail
                        + "' and dom = '" + dom + "' limit 1;");
                insertSuccess = true;
//                System.out.println("Following Meal Added");
//                System.out.println("Capacity: " + capacity);
//                System.out.println("Host's Email: " + hemail);
//                System.out.println("Date of Meal: " + dom);
//                System.out.println("Meal Picture Location: " + mpicture);
//                System.out.println("Price Per Person: $" + pricepp);
//                System.out.println("Meal Category: " + category);
//                System.out.println("Meal Description: " + description);
//                System.out.println("Cancel By: " + cancelationtime);
//                System.out.println("Cencelation Fee: $" + cancelationfee);
//                System.out.println("Country: " + country);
//                System.out.println("City: " + city);
//                System.out.println("Street Address: " + saddress);
//                System.out.println("Postal Code: " + postal);
            } catch (com.mysql.jdbc.MysqlDataTruncation e) {
                System.out.println("Error in length/format of input");
                insertSuccess = false;
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
                System.out.println("This meal already exists");
                insertSuccess = false;
            }
        } else {
            System.out.println("An entry was left blank");
            insertSuccess = false;
        }
        disconnectDatabase(newConnection);
        return insertSuccess;
    }

    // TODO: Test
    public static boolean insertUser(String email, String pass, String username, String description,
                                  String country, String currency, File profilePicture, String dob,
                                  String gender, String userlang, String ccnum, String cccvv, String cccountry,
                                  String ccprovince, String ccaddress, String cccity, String ccpostal, String ccexp)
            throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        String ACCESS_KEY = "AKIAIGHO2KWNFTEHE5TQ";
        String SECRET_ACCESS_KEY = "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";
        String BUCKET = "elasticbeanstalk-us-east-2-855393191779";
        String ppicture;
        boolean insertSuccess;

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
        if (profilePicture != null) {
            ppicture = "https://s3.us-east-2.amazonaws.com/elasticbeanstalk"
                    + "-us-east-2-855393191779/Profile/" + email + ".jpg";
            insertLine += "ppicture = '" + ppicture + "', ";
        } else {
            insertLine += "ppicture = null, ";
        }
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
                if (profilePicture != null) {
                    BasicAWSCredentials creds = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);
                    AmazonS3 S3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials
                                    (new AWSStaticCredentialsProvider(creds)).build();
                    S3Client.putObject(new PutObjectRequest(BUCKET,"Profile/" + email + ".jpg", profilePicture));
                }
                insertSuccess = true;
//                System.out.println("Following User Added");
//                System.out.println("Email: " + email);
//                System.out.println("Password: " + pass);
//                System.out.println("Username: " + username);
//                System.out.println("User Description: " + description);
//                System.out.println("Country: " + country);
//                System.out.println("Currency: " + currency);
//                System.out.println("Profile Picture Location: " + ppicture);
//                System.out.println("Date of Birth: " + dob);
//                System.out.println("Gender: " + gender);
//                System.out.println("User Language: " + userlang);
//                System.out.println("Credit Card Number: " + ccnum);
//                System.out.println("Credit Card CVV: " + cccvv);
//                System.out.println("Credit Card Country: " + cccountry);
//                System.out.println("Credit Card Province: " + ccprovince);
//                System.out.println("Credit Card City: " + cccity);
//                System.out.println("Credit Card Address: " + ccaddress);
//                System.out.println("Credit Card Postal: " + ccpostal);
//                System.out.println("Credit Card Expirary Date: " + ccexp);
            } catch (com.mysql.jdbc.MysqlDataTruncation e) {
//                System.out.println("Error in length/format of input");
                insertSuccess = false;
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
//                System.out.println("This user already exists");
                insertSuccess = false;
            }
        } else {
//            System.out.println("Email and password not entered");
            insertSuccess = false;
        }
        disconnectDatabase(newConnection);
        return insertSuccess;
    }

    public static boolean login(String email, String pass) throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        boolean loginSuccessful;

        // Run the login and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an insert to the
        database.
         */
        if (email != null && pass != null) {
            try {
                ResultSet rs = statement.executeQuery("select * from Users " + "where email = '" + email + "';");
                if (!rs.first()){
//                    System.out.println("Email not in the database");
                    loginSuccessful = false;
                } else {
                    String test = rs.getString("pass");
                    if (test.equals(pass)){
//                        System.out.println("Logged in");
                        loginSuccessful = true;
                    } else {
//                        System.out.println("Incorrect password");
                        loginSuccessful = false;
                    }
                }
            } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
//                System.out.println("This user already exists");
                loginSuccessful = false;
            }
        } else {
//            System.out.println("Email and/or password not entered");
            loginSuccessful = false;
        }
        disconnectDatabase(newConnection);
        return loginSuccessful;
    }

    // TODO: Test
    public static ResultSet selectAttending(Connection conn, int mealid, String gemail)
            throws SQLException, ClassNotFoundException {
        Statement statement = conn.createStatement();

        // IGNORE - Create the select line
        String selectCriteria = "";
        boolean notFirst = false;
        if (mealid > 0) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "mealid = " + mealid;
            notFirst = true;
        }
        if (gemail != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "gemail = '" + gemail + "'";
        }

        // IGNORE - Finalize and run the query
        if (!selectCriteria.equals("")) {
            selectCriteria = " where " + selectCriteria;
        }
        ResultSet rs = statement.executeQuery("select * from Attending" + selectCriteria + ";");

        // Display a message about how the select went
        /*
        Obviously for the website printing is not required.  Therefore, after
        select you can display a message to the user.
         */
        if (rs.first()) {
            return rs;
//            System.out.println("The following attending relationships were found matching your criteria:");
//            do {
//                mealid = rs.getInt("mealid");
//                gemail = rs.getString("gemail");
//                System.out.println("Meal ID: " + mealid);
//                System.out.println("Guest's Email: " + gemail);
//                System.out.println();
//            } while (rs.next());
        } else {
//            System.out.println("No Results");
            return null;
        }
    }

    public static ResultSet selectMeal(Connection conn, int mealid, String hemail, String dom, String mtitle,
                                       String mpicture, int capacity, double pricepp, String category,
                                       String description, String cancelationtime, double cancelationfee,
                                       String country, String city, String saddress, String postal)
            throws SQLException, ClassNotFoundException {
        Statement statement = conn.createStatement();

        // IGNORE - Create the search line
        String selectCriteria = "";
        boolean notFirst = false;
        if (mealid > 0) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "mealid = " + mealid;
            notFirst = true;
        }
        if (hemail != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "hemail = '" + hemail + "'";
            notFirst = true;
        }
        if (dom != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "dom = '" + dom + "'";
            notFirst = true;
        }
        if (mtitle != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "mtitle = '" + mtitle + "'";
            notFirst = true;
        }
        if (mpicture != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "mpicture = '" + mpicture + "'";
            notFirst = true;
        }
        if (capacity > 0) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "capacity = " + capacity;
            notFirst = true;
        }
        if (pricepp > 0) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "pricepp = " + pricepp;
            notFirst = true;
        }
        if (category != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "category = '" + category + "'";
            notFirst = true;
        }
        if (description != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "description = '" + description + "'";
            notFirst = true;
        }
        if (cancelationtime != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "cancelationtime = '" + cancelationtime + "'";
            notFirst = true;
        }
        if (cancelationfee != 0.0) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "cancelationfee = " + cancelationfee;
            notFirst = true;
        }
        if (country != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "country = '" + country + "'";
            notFirst = true;
        }
        if (city != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "city = '" + city + "'";
            notFirst = true;
        }
        if (saddress != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "saddress = '" + saddress + "'";
            notFirst = true;
        }
        if (postal != null) {
            if (notFirst) {
                selectCriteria += " and ";
            }
            selectCriteria += "postal = '" + postal + "'";
        }

        // IGNORE - Finalize and run the query
        if (!selectCriteria.equals("")) {
            selectCriteria = " where " + selectCriteria;
        }
        ResultSet rs = statement.executeQuery("select * from Meals" + selectCriteria + ";");

        // Retrieve and print results from query or print "no results"
        /*
        Obviously for the website printing is not required.  Therefore, after
        retrival you can do whatever is necessary with the data.
         */
        if (rs.first()) {
            return rs;
//            System.out.println("The following meals were found matching your criteria:");
//            do {
//                mealid = rs.getInt("mealid");
//                capacity = rs.getInt("capacity");
//                hemail = rs.getString("hemail");
//                dom = rs.getString("dom");
//                mtitle = rs.getString("mtitle");
//                mpicture = rs.getString("mpicture");
//                pricepp = rs.getDouble("pricepp");
//                category = rs.getString("category");
//                description = rs.getString("description");
//                cancelationtime = rs.getString("cancelationtime");
//                cancelationfee = rs.getDouble("cancelationfee");
//                country = rs.getString("country");
//                city = rs.getString("city");
//                saddress = rs.getString("saddress");
//                postal = rs.getString("postal");
//
//                System.out.println("Meal ID: " + mealid);
//                System.out.println("Capacity: " + capacity);
//                System.out.println("Host's Email: " + hemail);
//                System.out.println("Date of Meal: " + dom);
//                System.out.println("Meal Title: " + mtitle);
//                System.out.println("Meal Picture Location: " + mpicture);
//                System.out.println("Price Per Person: $" + pricepp);
//                System.out.println("Meal Category: " + category);
//                System.out.println("Meal Description: " + description);
//                System.out.println("Cancel By: " + cancelationtime);
//                System.out.println("Cencelation Fee: $" + cancelationfee);
//                System.out.println("Country: " + country);
//                System.out.println("City: " + city);
//                System.out.println("Street Address: " + saddress);
//                System.out.println("Postal Code: " + postal);
//                System.out.println();
//            } while (rs.next());
        } else {
//            System.out.println("No Results");
            return null;
        }
    }

    // TODO: Test
    public static ResultSet selectMeal(Connection conn, int mealid, String hemail, String dom)
            throws SQLException, ClassNotFoundException {
        return selectMeal(conn, mealid, hemail, dom, null, null, 0, 0.0, null,
                null, null, 0.0, null, null, null,
                null);
    }

    public static ResultSet selectSimilarMeal(Connection conn, String searchQuery) throws SQLException, ClassNotFoundException {
        Statement statement = conn.createStatement();
        ResultSet rs;
        if (searchQuery != null && !searchQuery.equals("")){
            rs = statement.executeQuery("select * from Meals where mtitle like '%" + searchQuery
                    + "%' or category like '%" + searchQuery + "%' or description like '%" + searchQuery
                    + "%' or country like '%" + searchQuery + "%' or city like '%" + searchQuery + "%';");
        } else {
            rs = selectAllMeals(conn);
        }
        return rs;
    }

    public static ResultSet selectAllMeals(Connection conn) throws SQLException, ClassNotFoundException {
        return selectMeal(conn,0, null, null, null, null, 0, 0.0,
                null, null, null, 0.0, null, null,
                null, null);
    }

    public static ResultSet selectUser(Connection conn, int userid, String email, String pass, String username, String description,
                                       String country, String currency, String ppicture, String dob, String gender,
                                       String userlang, String ccnum, String cccvv, String cccountry, String ccprovince,
                                       String ccaddress, String cccity, String ccpostal, String ccexp)
            throws SQLException, ClassNotFoundException {
        Statement statement = conn.createStatement();

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
        }

        // IGNORE - Finalize and run the query
        if (!selectCriteria.equals("")) {
            selectCriteria = " where " + selectCriteria;
        }
        ResultSet rs = statement.executeQuery("select * from Users" + selectCriteria + ";");

        // Retrieve and print results from query or print "no results"
        /*
        Obviously for the website printing is not required.  Therefore, after
        retrival you can do whatever is necessary with the data.
         */
        if (rs.first()) {
            return rs;
//            do {
//                userid = rs.getInt("userid");
//                email = rs.getString("email");
//                pass = rs.getString("pass");
//                username = rs.getString("username");
//                description = rs.getString("description");
//                country = rs.getString("country");
//                currency = rs.getString("currency");
//                ppicture = rs.getString("ppicture");
//                dob = rs.getString("dob");
//                gender = rs.getString("gender");
//                userlang = rs.getString("userlang");
//                ccnum = rs.getString("ccnum");
//                cccvv = rs.getString("cccvv");
//                cccountry = rs.getString("cccountry");
//                ccprovince = rs.getString("ccprovince");
//                cccity = rs.getString("cccity");
//                ccaddress = rs.getString("ccaddress");
//                ccpostal = rs.getString("ccpostal");
//                ccexp = rs.getString("ccexp");
//
//                System.out.println("User ID: " + userid);
//                System.out.println("Email: " + email);
//                System.out.println("Password: " + pass);
//                System.out.println("Username: " + username);
//                System.out.println("User Description: " + description);
//                System.out.println("Country: " + country);
//                System.out.println("Currency: " + currency);
//                System.out.println("Profile Picture Location: " + ppicture);
//                System.out.println("Date of Birth: " + dob);
//                System.out.println("Gender: " + gender);
//                System.out.println("User Language: " + userlang);
//                System.out.println("Credit Card Number: " + ccnum);
//                System.out.println("Credit Card CVV: " + cccvv);
//                System.out.println("Credit Card Country: " + cccountry);
//                System.out.println("Credit Card Province: " + ccprovince);
//                System.out.println("Credit Card City: " + cccity);
//                System.out.println("Credit Card Address: " + ccaddress);
//                System.out.println("Credit Card Postal: " + ccpostal);
//                System.out.println("Credit Card Expirary Date: " + ccexp);
//                System.out.println();
//            } while (rs.next());
        } else {
//            System.out.println("No Results");
            return null;
        }
    }

    public static ResultSet selectUser(Connection conn, String email) throws SQLException, ClassNotFoundException {
        return selectUser(conn, 0, email, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null);
    }

    // TODO: Test
    public static ResultSet selectUser(Connection conn, int userid) throws SQLException, ClassNotFoundException {
        return selectUser(conn, userid, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null);
    }

    // TODO: Test
    public static boolean updateMeal(int mealid, String hemail, String dom, String mtitle, File mealPicture,
                                     int capacity, double pricepp, String category, String description,
                                     String cancelationtime, double cancelationfee, String country, String city,
                                     String saddress, String postal)
            throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        String mpicture;
        String ACCESS_KEY = "AKIAIGHO2KWNFTEHE5TQ";
        String SECRET_ACCESS_KEY = "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";
        String BUCKET = "elasticbeanstalk-us-east-2-855393191779";
        boolean updateSuccess;

        // IGNORE - Create the update line
        String updateLine = "";
        boolean notFirst = false;
        mpicture = null;
        if (hemail != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "hemail = '" + hemail + "'";
            notFirst = true;
        }
        if (dom != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "dom = '" + dom + "'";
            notFirst = true;
        }
        if (mtitle != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "mtitle = '" + mtitle + "'";
            notFirst = true;
        }
        // For Testing
//        String fileToUpload = "thing.jpg";
//        mealPicture = new File(fileToUpload);
        if (mealPicture != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            mpicture = "https://s3.us-east-2.amazonaws.com/elasticbeanstalk"
                    + "-us-east-2-855393191779/Meal/" + mealid + ".jpg";
            updateLine += "mpicture = '" + mpicture + "'";
            notFirst = true;
        }
        if (capacity > 0) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "capacity = " + capacity;
            notFirst = true;
        }
        if (pricepp > 0.0) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "pricepp = " + pricepp;
            notFirst = true;
        }
        if (category != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "category = '" + category + "'";
            notFirst = true;
        }
        if (description != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "description = '" + description + "'";
            notFirst = true;
        }
        if (cancelationtime != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "cancelationtime = '" + cancelationtime + "'";
            notFirst = true;
        }
        if (cancelationfee > 0.0) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "cancelationfee = " + cancelationfee;
            notFirst = true;
        }
        if (country != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "country = '" + country + "'";
            notFirst = true;
        }
        if (city != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "city = '" + city + "'";
            notFirst = true;
        }
        if (saddress != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "saddress = '" + saddress + "'";
            notFirst = true;
        }
        if (postal != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "postal = '" + postal + "'";
        }

        // Run the update and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an insert to the
        database.
         */
        if (mealid > 0) {
            if (!updateLine.equals("")) { // Something to update
                try {
                    if (statement.executeUpdate("update Meals set " + updateLine + " where mealid = " + mealid
                            + ";") == 0) {
                        // Update failed
                        updateSuccess = false;
//                        System.out.println("No meal found with given Meal ID");
                    } else { // Update success
                        updateSuccess = true;
                        if (mealPicture != null) {
                            // Upload the photo
                            BasicAWSCredentials creds = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);
                            AmazonS3 S3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2")
                                    .withCredentials(new AWSStaticCredentialsProvider(creds)).build();
                            S3Client.putObject(new PutObjectRequest(BUCKET, "Meal/" + mealid + ".jpg",
                                    mealPicture));
                        }
//                        System.out.println("Meal with Following Meal " + "ID Updated");
//                        System.out.println("Meal ID: " + mealid);
                    }
                } catch (com.mysql.jdbc.MysqlDataTruncation e) {
//                    System.out.println("Error in length/format of input");
                    updateSuccess = false;
                }
            } else {
                System.out.println("Nothing set to update");
                updateSuccess = false;
            }
        } else {
            System.out.println("MealID field not filled in");
            updateSuccess = false;
        }
        disconnectDatabase(newConnection);
        return updateSuccess;
    }

    // TODO: Test
    public static boolean updateUser(String email, String pass, String username, String description,
                                     String country, String currency, File profilePicture, String dob,
                                     String gender, String userlang, String ccnum, String cccvv, String cccountry,
                                     String ccprovince, String ccaddress, String cccity, String ccpostal, String ccexp)
            throws SQLException, ClassNotFoundException {
        Connection newConnection = connectDatabase();
        Statement statement = newConnection.createStatement();
        String ppicture;
        String ACCESS_KEY = "AKIAIGHO2KWNFTEHE5TQ";
        String SECRET_ACCESS_KEY = "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";
        String BUCKET = "elasticbeanstalk-us-east-2-855393191779";
        boolean updateSuccess;

        // IGNORE - Create the update line
        String updateLine = "";
        boolean notFirst = false;
        if (pass != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "pass = '" + pass + "'";
            notFirst = true;
        }
        if (username != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "username = '" + username + "'";
            notFirst = true;
        }
        if (description != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "description = '" + description + "'";
            notFirst = true;
        }
        if (country != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "country = '" + country + "'";
            notFirst = true;
        }
        if (currency != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "currency = '" + currency + "'";
            notFirst = true;
        }
        // For testing
//        String fileToUpload = "words.jpg";
//        profilePicture = new File(fileToUpload);
        if (profilePicture != null) {
            ppicture = "https://s3.us-east-2.amazonaws.com/elasticbeanstalk"
                    + "-us-east-2-855393191779/Profile/" + email + ".jpg";
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "ppicture = '" + ppicture + "'";
            notFirst = true;
        }
        if (dob != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "dob = '" + dob + "'";
            notFirst = true;
        }
        if (gender != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "gender = '" + gender + "'";
            notFirst = true;
        }
        if (userlang != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "userlang = '" + userlang + "'";
            notFirst = true;
        }
        if (ccnum != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "ccnum = '" + ccnum + "'";
            notFirst = true;
        }
        if (cccvv != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "cccvv = '" + cccvv + "'";
            notFirst = true;
        }
        if (cccountry != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "cccountry = '" + cccountry + "'";
            notFirst = true;
        }
        if (ccprovince != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "ccprovince = '" + ccprovince + "'";
            notFirst = true;
        }
        if (cccity != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "cccity = '" + cccity + "'";
            notFirst = true;
        }
        if (ccaddress != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "ccaddress = '" + ccaddress + "'";
            notFirst = true;
        }
        if (ccpostal != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "ccpostal = '" + ccpostal + "'";
            notFirst = true;
        }
        if (ccexp != null) {
            if (notFirst) {
                updateLine += ", ";
            }
            updateLine += "ccexp = '" + ccexp + "'";
        }

        // Run the update and explain outcome
        /*
        Obviously for the website printing is not required.  Therefore, after
        a success you can just display a success message to user and after a
        failure you can display an error message to the user.  Although,
        these constraints are best tested before sending an update to the
        database.
         */
        if (email != null) {
            if (!updateLine.equals("")) { // Something to update
                try {
                    if (statement.executeUpdate("update Users set " + updateLine + " where " + "email = '"
                            + email + "' limit 1;") == 0) { // Failed update
//                        System.out.println("No user found with given email");
                        updateSuccess = false;
                    } else { // Successful update
                        updateSuccess = true;
                        if (profilePicture != null) {
                            // Upload the photo
                            BasicAWSCredentials creds = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);
                            AmazonS3 S3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2")
                                    .withCredentials(new AWSStaticCredentialsProvider(creds)).build();
                            S3Client.putObject(new PutObjectRequest(BUCKET,"Profile/" + email + ".jpg",
                                    profilePicture));
                        }
//                        System.out.println("User with Following Email Updated");
//                        System.out.println("Email: " + email);
                    }
                } catch (com.mysql.jdbc.MysqlDataTruncation e) {
//                    System.out.println("Error in length/format of input");
                    updateSuccess = false;
                }
            } else {
//                System.out.println("Nothing set to update");
                updateSuccess = false;
            }
        } else {
//            System.out.println("Email field empty");
            updateSuccess = false;
        }
        disconnectDatabase(newConnection);
        return updateSuccess;
    }

    public static ArrayList<Meals> createMealsList (ResultSet rs) throws SQLException {
        ArrayList<Meals> meals = new ArrayList<>();
        Meals meal;
        do {
            meal = createMeal(rs);
            meals.add(meal);
        } while (rs.next());
        return meals;
    }

    public static Meals createMeal(ResultSet rs) throws SQLException {
        Meals newMeal;
        newMeal = new Meals();
        newMeal.setMealID(rs.getInt("mealid"));
        newMeal.setWithHost(rs.getString("hemail"));
        newMeal.setDate(rs.getString("dom"));
        newMeal.setMealTitle(rs.getString("mtitle"));
        newMeal.setImage(rs.getString("mpicture"));
        newMeal.setCapacity(rs.getInt("capacity"));
        newMeal.setPrice(rs.getDouble("pricepp"));
        newMeal.setCategory(rs.getString("category"));
        newMeal.setDescription(rs.getString("description"));
        newMeal.setCancelBy(rs.getString("cancelationtime"));
        newMeal.setCancelationFee(rs.getDouble("cancelationfee"));
        newMeal.setCountry(rs.getString("country"));
        newMeal.setCity(rs.getString("city"));
        newMeal.setAddress(rs.getString("saddress"));
        newMeal.setPostal(rs.getString("postal"));
        return newMeal;
    }

    public static User createUser(ResultSet rs) throws SQLException {
        User newUser;
        newUser = new User();
        newUser.setUserID(rs.getInt("userid"));
        newUser.setEmail(rs.getString("email"));
        newUser.setPassword(rs.getString("pass"));
        newUser.setName(rs.getString("username"));
        newUser.setUserDescription(rs.getString("description"));
        newUser.setCountry(rs.getString("country"));
        newUser.setCurrency(rs.getString("currency"));
        newUser.setProfilePicture(rs.getString("ppicture"));
        newUser.setDateOfBirth(rs.getString("dob"));
        newUser.setGender(rs.getString("gender"));
        newUser.setLanguage(rs.getString("userlang"));
        newUser.setCcnumber(rs.getString("ccnum"));
        newUser.setCccvv(rs.getString("cccvv"));
        newUser.setCccountry(rs.getString("cccountry"));
        newUser.setCcprovince(rs.getString("ccprovince"));
        newUser.setCccity(rs.getString("cccity"));
        newUser.setCcadress(rs.getString("ccaddress"));
        newUser.setCcpostal(rs.getString("ccpostal"));
        newUser.setCcexp(rs.getString("ccexp"));
        return newUser;
    }
}
