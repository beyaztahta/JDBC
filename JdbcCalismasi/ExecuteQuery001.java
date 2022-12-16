package JdbcCalismasi;

import java.sql.*;

public class ExecuteQuery001 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adım: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st = con.createStatement();

        //1. Örnek:  calisan_kimligi  123 olan  isimlerin değerlerini çağırın.

            String sql1="select adı_soyadı from calisan where calisan_kimligi=123";
            boolean r1=st.execute(sql1);
            System.out.println("r1"+r1);  // datadan veri geldigi için true verdi.

           ResultSet result1= st.executeQuery(sql1);
           while( result1.next()){

               System.out.println(result1.getString("adı_soyadı"));

           }
        System.out.println("************************************************************");

         //2.Ornek: id si 102 den buyuk olanların adı_soyadı ve sehir adını alan kodu yazınız.
         String sql2="select adı_soyadı,sehir from calisan where calisan_kimligi>102";
         boolean r2=st.execute(sql2);
         System.out.println("r2 = " + r2);// true

        ResultSet result2=st.executeQuery(sql2);

        while(result2.next()){

            System.out.println( result2.getString(1) + "----" + result2.getString(2));
        }

        System.out.println("************************************************************");


        //3.Ornek: calisan_kimligi no su değeri en düşük olan satırın tüm değerlerini çağırın.

        String sql3="select * from calisan where calisan_kimligi=(select min(calisan_kimligi)from calisan)";
        boolean r3=st.execute(sql3);
        System.out.println("r3 = " + r3);

        ResultSet result3=st.executeQuery(sql3);
        while(result3.next()){
       System.out.println(result3.getInt("calisan_kimligi") +"--"+result3.getString("adı_soyadı")+"--"+result3.getString("sehir")+"--"+result3.getString("eyalet"));
        }

         con.close();
         st.close();
    }
}
