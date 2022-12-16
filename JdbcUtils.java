import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

    private static Connection con;
    private static Statement st;

    private static ResultSet resultSet;


    //1.Adım: Driver a kaydol ve 2.adım Database baglan
    public static Connection connectToDatabase(String hostName,String dbName,String userName,String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
             con = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(con!=null){
            System.out.println("Connection success");
        }else {
            System.out.println("Connection fail");
        }

    return con;
    }


    //3.adım statement olustur.
    public static Statement createStatement(){
        try {
             st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return st;
    }

    //4.Adım: Query i calıstır.

    public static Boolean execute(String sql){
        boolean isExecute;
        try {
           isExecute= st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }
    //ExecuteQuery ve ExecuteUpdate methodları odev...

    //ExecuteQuery
    public static ResultSet ExecuteQuery(String tableName) {
        String str = "select count(*) from information_schema.columns where table_name = '" + tableName + "'";//column sayisi
        int count =0;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(str);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                count= rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        String sql1 = "select * from " + tableName;
        ResultSet resultSet2 = null;
        try {
            resultSet2 = st.executeQuery(sql1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!resultSet2.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i <= count; i++) {
                try {
                    System.out.print(resultSet2.getString(i) + "       ");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println();
        }
        return resultSet2;

    }



    //5.Adım: Baglantı ve statement kapat.

    public static void closeConnectionAndStatement() {
        try {
            con.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (con.isClosed() && st.isClosed()) {
                System.out.println("Connection and Statement closed" );
            } else {
                System.out.println("Connection and Statement not closed" );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        //Table olusturan method

    //Table oluşturan method
    public static void createTable(String tableName, String... columnName_dataType ){
        StringBuilder columnName_dataValue = new StringBuilder("");

        for(String w : columnName_dataType){

            columnName_dataValue.append(w).append(",");

        }

        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);

        try {
            st.execute( "CREATE TABLE "+tableName+"("+columnName_dataValue+")");
            System.out.println("Table "+tableName+" successfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ExecuteQuery methodu
    public static ResultSet executeQuery(String query) {

        try {
            resultSet = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    //ExecuteUpdate methodu
    public static int executeUpdate(String query) {
        int guncellenenSatirSayisi;

        try {
            guncellenenSatirSayisi = st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guncellenenSatirSayisi;
    }

    //Table'a data girme methodu
    public static void insertDataIntoTable(String tableName, String... columnName_Value) {

        StringBuilder columnNames = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        for (String w : columnName_Value) {
            columnNames.append(w.split(" ")[0]).append(",");//Bir String değeri ikiye bölüp birinciyi sütun adı, ikinciyi sütun değeri olarak alıyorum.
            values.append(w.split(" ")[1]).append(",");
        }

        columnNames.deleteCharAt(columnNames.lastIndexOf(","));//En son virgülü siliyor.
        values.deleteCharAt(values.lastIndexOf(","));

        //"INSERT INTO      members     ( id, name, address ) VALUES(123, 'john', 'new york')"
        String query = "INSERT INTO " + tableName + "(" + columnNames + ") VALUES(" + values + ")";

        execute(query);//execute methodu üstte oluşturuldu, query'yi çalıştırıyor.
        System.out.println("Data " + tableName + " tablosuna girildi.");

    }

    //Sütun Değerlerini List içerisine alan method
    public static List<Object> getColumnList(String columnName, String tableName) {

        List<Object> columnData = new ArrayList<>();//ResultSet'ten alınan datanın koyulacağı List.

        //SELECT        id          FROM      students
        String query = "SELECT " + columnName + " FROM " + tableName;

        executeQuery(query);// => Bu method üstte oluşturuldu. Query'yi çalıştırıp alınan datayı 'resultSet' container'ı içine atama yapıyor.

        try {
            while (resultSet.next()) {
                columnData.add(resultSet.getObject(columnName));//add methodu ile alınan sütun değerlerini List'e ekliyor.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnData;
    }

}

