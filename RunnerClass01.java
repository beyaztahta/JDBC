import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class RunnerClass01 {
    public static void main(String[] args) {

        //1.Adım: Driver a kaydol
        //2.Adım: Database baglan
        Connection con= JdbcUtils.connectToDatabase("localhost","techproed","postgres","2580");

        //3.Adım: Statement olustur.
        Statement st=JdbcUtils.createStatement();

        //4.Adım: Query i calıstır.

       // JdbcUtils.execute("CREATE TABLE students(name varchar(20),id int,adres varchar(80))");

        //JdbcUtils.createTable("def","classes VARCHAR(20)","teacher_name VARCHAR(20)","id INT");

       //Table'a data girme methodu
        JdbcUtils.insertDataIntoTable("countries","country_name 'Turkey'","country_id 'TR'","region_id 1");//VARARG değerleri ' ' içine alınız.

        //Sütun Değerlerini List içerisine alan method
        List<Object> list = JdbcUtils.getColumnList("country_id","countries");
        System.out.println("list = " + list);

        //5.Adım: Baglantı ve statement kapat.

        JdbcUtils.closeConnectionAndStatement();


    }
}
