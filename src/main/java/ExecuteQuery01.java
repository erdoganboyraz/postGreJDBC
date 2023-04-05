import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Eb224287");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean sql1b = st.execute(sql1);
        System.out.println("sql1 = " + sql1b);
        //Recordlari gormek icin ExecuteQuery() methodunu kullanmaliyiz.
       ResultSet resultSet1=st.executeQuery(sql1);

        while (resultSet1.next()){


            System.out.println(resultSet1.getString(1));

    }
        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 ="SELECT country_name, country_id FROM countries WHERE region_id>2";
        ResultSet resultSet2 = st.executeQuery(sql2);

        System.out.println("-----------------------");

        while (resultSet2.next()){

            System.out.println(resultSet2.getString("country_name")+"--"+resultSet2.getString("country_id"));

        }
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = st.executeQuery(sql3);
        System.out.println("------------------");
        while (resultSet3.next()) {
            System.out.println(resultSet3.getInt(1) + "--" + resultSet3.getString(2)+"--"+resultSet3.getInt(3));
        }
        con.close();
        st.close();
        resultSet1.close();



    }
}
