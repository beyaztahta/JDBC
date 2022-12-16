package JdbcCalismasi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute001 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adım: Driver a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st = con.createStatement();

     //1.Örnek: "student" adında bir table oluşturup "id,isim,adres,sınav_notu" sütunlarını ekleyin.

        boolean sql1=st.execute("create table student(id int,isim varchar(40),adres varchar(100),sinav_notu int)");
        System.out.println("sql1 = " + sql1);

     //2.Örnek: tabloya student_no ssutnu ekleyiniz.
        boolean sql2=st.execute("alter table student add student_no int");
        System.out.println("sql2 = " + sql2);

     //3.Ornek: student tablosunu siliniz.
        boolean sql3=st.execute("DROP TABLE student");
        System.out.println("sql3 = " + sql3);



        con.close();
        st.close();








    }
}
