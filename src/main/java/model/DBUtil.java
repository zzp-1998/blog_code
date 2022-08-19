package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * model.User: Administrator
 * Date: 2022-05-30
 * Time: 下午 5:18
 * 描述：使用这个类，来和数据库建立连接。封装数据库
 */
public class DBUtil {
    //这是连接数据库的地址
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java102_blog?characterEncoding=utf8&useSSL=false";
    //用户名
    private static final String USERNAME = "root";
    //密码
    private static final String PASSWORD = "517718";

    //创建一个数据源对象：
    private volatile static DataSource dataSource = null;
    private static DataSource getDataSource(){
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource) dataSource).setURL(URL);
                    ((MysqlDataSource) dataSource).setUser(USERNAME);
                    ((MysqlDataSource) dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    //关闭资源：
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
