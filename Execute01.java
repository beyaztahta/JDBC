import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       //1.Adım: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st = con.createStatement();
        System.out.println("Connection Success");

        //4.Adım: Query i calıstır.
        /*
        execute() methodu DDL(cerate,drop,alter table)ve DQL(select) icin kullanılabilir.
        1.Eger bu execute methodu DDL icin kullanılırsa false return eder.
        2.Eger execute methodu DQL icin kullanılırasa data alırsayani result set alınırsa true, almazsa false verir.
         */

    //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.

  boolean sql1= st.execute("CREATE TABLE workers(worker_id varchar(20),worker_name varchar(20),worker_salary int)");
        System.out.println("sql1 = " + sql1);// false return eder. Cunku data cagırmıyoruz.

       //execute methodu sadece bize data olusturur.
      //Bir data cagırabiliyorsak true, cagıramıyorsak yani data alamıyorsak false alırız.


     //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
      String sql2="ALTER TABLE workers ADD worker_adress varchar(80)";
      boolean sql4=st.execute(sql2);
      System.out.println("sql4 = " + sql4);

     //3.Ornek: workers table ını silin.
      String sql3="DROP TABLE workers";
      boolean sql5=st.execute(sql3);
      System.out.println("sql5 = " + sql5);


        //5.Adım: Baglantı ve statement kapat.
        con.close();
        st.close();

    }
}
