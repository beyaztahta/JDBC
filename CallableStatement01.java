import java.sql.*;

public class CallableStatement01 {
        /*
         Java da methodlar return type sahibi olsa da olmasa da method olarak adlandırılır.
         SQL DE ise data return ediyorsa "function"denir.Return yapmıyorsa "procedure"olarak adlandırılır.
         */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","2580");
        Statement st = con.createStatement();

        //CallabelStatement ile function cagırmayı parametrelendirecegiz.

       //1.adım: foncition kodunu yaz.
        String sql1="CREATE OR REPLACE FUNCTION  toplamaF(x Numeric,y Numeric)\n" +
                "RETURNS Numeric\n" +
                "LANGUAGE plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$\n";
        //2.adım: function ı calıstır.
        st.execute(sql1);

        //3.adım: function ı cagır.
        CallableStatement cst1=con.prepareCall("{?=call toplamaF(?,?)}");//ilk parametre return type

        //4.adım:Return icin registerOurParameter() methodunu,parametreler icin set().....methodlarını uygula.
         cst1.registerOutParameter(1,Types.NUMERIC);
         cst1.setInt(2,6);
         cst1.setInt(3,4);

        //5.adım: execute() methodu ile CallableStatement ı calıstırn
         cst1.execute();

        //6.adım: Sonucu cagırmak icin return dta type tipine gore cagır.
        System.out.println(cst1.getBigDecimal(1));


   //2. Örnek: Koninin hacmini hesaplayan bir function yazın.(V=1/3hπr²)

        //1.adım: foncition kodunu yaz.
        String sql2="CREATE OR REPLACE FUNCTION  konininhacmiF(r Numeric,h Numeric)\n" +
                "RETURNS Numeric\n" +
                "LANGUAGE plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$\n";
        //2.adım: function ı calıstır.
        st.execute(sql2);

       //3.adım: function ı cagır.
        CallableStatement cst2=con.prepareCall("{?=call konininhacmiF(?,?)}");//ilk parametre return type

        //4.adım:Return icin registerOurParameter() methodunu,parametreler icin set().....methodlarını uygula.
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        //5.adım: execute() methodu ile CallableStatement ı calıstırn
        cst2.execute();

        //6.adım: Sonucu cagırmak icin return dta type tipine gore cagır.
        System.out.printf("%.2f",cst2.getBigDecimal(1));

        System.out.println();

        System.out.printf("%.2f",123654.3265);//sayılarımızı formatlyabiliyoruz.

    }
}
