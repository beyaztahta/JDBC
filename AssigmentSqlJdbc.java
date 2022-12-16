package JdbcCalismasi;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AssigmentSqlJdbc {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "2580");
        Statement st = con.createStatement();

      /*
        - Kullanıcıdan int öğelerini girmesini ve listeye öğe eklemesini istemek için kod yazın
        - Kullanıcıdan kaldırılacak öğeleri girmesini isteyin, ardından bu öğeyi listeden kaldırın.
        - Kullanıcıdan güncellemek için öğeyi girmesini isteyin, ardından güncelleyin.
       */

        //table olusturdum.
        String sql = "create table workers(ad_soyadı varchar(30),yas int,salary int)";
        st.execute(sql);
        menu(st,con);
    }
     public static void menu(Statement st,Connection con)throws SQLException{
           boolean secim=true;
           while(secim) {
               System.out.println("Yapacagınız islemi seciniz.");
               System.out.println("1.ADD\n" +
                       "2.DELETE\n" +
                       "3.UPDATE\n" +
                       "4.QUİT");
               String workers = input.next();
               switch (workers) {
                   case "1":
                       add(st);
                       menu(st, con);
                       secim=false;
                       break;
                   case "2":
                       delete(st);
                       menu(st, con);
                       secim=false;
                       break;
                   case "3":
                       update(st,con);
                       menu(st, con);
                       secim=false;
                       break;
                   case "4":
                       st.execute("drop table workers");
                       System.out.println("Tesekkurler");
                       st.close();
                       con.close();
                       secim=false;
                       break;
                   default:
                       menu(st,con);
               }
           }
        }
       public static void add(Statement st) throws SQLException {
           try {
               System.out.println("lutfen adınızı soyadınızı giriniz. ");
               String name = input.next().toLowerCase();

               System.out.println("Yasınızı giriniz.");
               int yas = input.nextInt();

               System.out.println("Maasınızı giriniz.");
               int salary = input.nextInt();

               st.execute("insert into workers values ('" + name + "', " + yas + ", " + salary + ")");
           } catch (InputMismatchException e) {
               System.out.println("Gecersiz bir giris yaptınız,tekrar deneyiniz.");
               input.nextLine();
               add(st);
           }catch (SQLException e) {
               System.out.println("Database ile ilgili bir hata olustu " + e.getMessage());
               input.nextLine();
               delete(st);
           }

        }


        public static void delete(Statement st) throws SQLException{
        try {
            System.out.println("Silmek istediginiz yası giriniz.");
            int removeYas = input.nextInt();
            st.execute("delete from workers where yas=" + removeYas);
        }catch(InputMismatchException e){
            System.out.println("Gecersiz yas formatı hatalıdır.");
            input.nextLine();
            delete(st);
        }catch(SQLException e){
            System.out.println("Database ile ilgili bir hata olustu " + e.getMessage());
            input.nextLine();
            delete(st);
        }

        }

       public static void update(Statement st,Connection con) throws SQLException{
           try{System.out.println("Guncellemek istediginiz maası gırınız.");
               int updateSalary=input.nextInt();

               System.out.println("Guncellemek istediginiz kolon adini giriniz");
               String kolonAdi = input.next().toLowerCase();

               System.out.println("Guncel veriyi giriniz");
               String guncelVeri = input.next();
               st.executeUpdate("update workers set " + kolonAdi + " = '" + guncelVeri + "' where salary = " + updateSalary);
           }catch(SQLException e){
               System.out.println("Database ilgili bir hata olustu"+ e.getMessage());
               input.nextLine();
               menu(st,con);
           }catch(InputMismatchException e){
               System.out.println("Gecersiz veri girisi yaptınız,tektat deneyiniz.");
               input.nextLine();
               update(st,con);
           }

        }


     }



