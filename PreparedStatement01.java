import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      /*
        PreparedStatement bir interface dir. Birden cok kez calıstırılabilen, onceden derlenmis bir sql kodunu temsil eder.
        Parametrelendirilmis sql sorguları(query)ile calisir.Bu sorguya 0 veya daha fazla parametre ile kullanabiliriz.
       */

        //1.Adım: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st = con.createStatement();


        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

       //1.adım prepared statement query sini olustur.
        String sql1="Update companies set  number_of_employees= ? where company= ?";//soru isareti koydugumuz yere artık farklı parametrelelre de kullanmıs olacagız.

       //.2.adım prepared statement objesini olustur.
       PreparedStatement pst1=con.prepareStatement(sql1);

       //3.adım soru isaretleri yerlerine deger ataması yap.setInt(),setString() ,.....methodlarını kulllanarak soru isaretleri yerlerine deger ata.
      pst1.setInt(1,9999);
      pst1.setString(2,"IBM");

      //4.adım  Query i calistir.
        int guncellenenSatırSayısı=pst1.executeUpdate();
        System.out.println("guncellenenSatırSayısı = " + guncellenenSatırSayısı);

      String sql2="SELECT * FROM companies";
       ResultSet result=st.executeQuery(sql2);
        while(result.next()){
            System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getInt(3));
        }
    //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        int guncellenenSatırSayısı2=pst1.executeUpdate();
        System.out.println("guncellenenSatırSayısı = " + guncellenenSatırSayısı2);

        ResultSet result1=st.executeQuery(sql2);
        while(result1.next()){
            System.out.println(result1.getInt(1) + " " + result1.getString(2) + " " + result1.getInt(3));
        }
        con.close();
        st.close();
        result.close();
        result1.close();
        pst1.close();







    }
}
