import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Assert that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */

    @Test
    public void countryTest() throws SQLException {
        // User connects to the database
        JdbcUtils.connectToDatabase("localhost","techproed","postgres","2580");
        Statement st=JdbcUtils.createStatement();

       // User sends the query to get the region ids from "countries" table
        String sql="select region_id from countries";
        ResultSet result= st.executeQuery(sql);

        List<Integer>ids=new ArrayList<>();

        while(result.next()){

           ids.add(result.getInt(1));
        }
        System.out.println(ids);

        // Assert that the number of region ids greater than 1 is 17.

      List<Integer>idsGraterThanOne=new ArrayList<>();
        for (int w:ids) {
            if(w>1){
                idsGraterThanOne.add(w);
            }
        }
        System.out.println(idsGraterThanOne);

        Assert.assertEquals(17,idsGraterThanOne.size());


        //User closes the connection

        JdbcUtils.closeConnectionAndStatement();

    }

}
