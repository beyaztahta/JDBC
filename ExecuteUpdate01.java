import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adım: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st = con.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.
      String sql1="UPDATE companies Set number_of_employees=16000\n" +
              "where number_of_employees<(select avg(number_of_employees)from companies)";

      int  updateEdilenSatırSayısı=st.executeUpdate(sql1);
      System.out.println("updateEdilenSatırSayısı = " + updateEdilenSatırSayısı);

        System.out.println();

      ResultSet result=st.executeQuery("select * from companies");
       while(result.next()){
           System.out.println(result.getInt(1)+"--"+result.getString(2)+"--"+result.getInt(3));
       }

       con.close();
       st.close();
       result.close();

    }
}
