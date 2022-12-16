import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adım: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st = con.createStatement();

//1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
       String sql1="Select company,number_of_employees from companies order by number_of_employees desc offset 1 limit 1";
       //Select company,number_of_employees from companies order by number_of_employees desc offset 1 row fetch next 1 only
       boolean r1=st.execute(sql1);
        System.out.println("r1 = " + r1);

        ResultSet result1=st.executeQuery(sql1);
        while(result1.next()){
            System.out.println(result1.getString(1) +"---"+ result1.getInt(2));
        }
    //2.Yol subquery i kullanarak:

        String sql2="Select company,number_of_employees from companies \n" +
                "where number_of_employees=\n" +
                "(select max(number_of_employees)from companies where number_of_employees <(select max(number_of_employees)from companies))";

       ResultSet result2=st.executeQuery(sql2);
       while(result2.next()){
           System.out.println(result2.getString("company") + "  " + result2.getInt("number_of_employees"));
       }

       con.close();
       st.close();
       result1.close();
       result2.close();


    }
}
