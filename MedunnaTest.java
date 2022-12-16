import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaTest {

    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db, Usename: medunna_user, Password: medunna_pass_987))
   When
     User sends the query to get the names of created_by column from "room" table
   Then
     Assert that there are some rooms created by "john_doe".
   And
     User closes the connection
  */


    @Test
    public void medunnaTest() throws SQLException {
        // User connects to the database
        JdbcUtils.connectToDatabase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement st=JdbcUtils.createStatement();

        //User sends the query to get the names of created_by column from "room" table
       String sql1="select created_by from room";
        ResultSet result= st.executeQuery(sql1);

        List<String> names=new ArrayList<>();

        while(result.next()){

            names.add(result.getString(1));
        }
        System.out.println(names);

     // Assert that there are some rooms created by "john_doe".

        Assert.assertTrue(names.contains("john_doe"));

     //User closes the connection
        JdbcUtils.closeConnectionAndStatement();













    }

}
