package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * model.User: Administrator
 * Date: 2022-05-30
 * Time: 下午 5:38
 * 描述：这个类用于封装 用户表 的基本操作（增删改查）
 */
public class UserDao {
    //需要实现的操作：
    //针对这个类来说，简化写就行了，不考虑注册/注销 这样的功能。

    //1、根据用户名来查找用户信息（在登录逻辑中出现）：
    public User selectByName(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1）和数据库建立连接：
            connection = DBUtil.getConnection();
            //2）构造SQl语句：
            String sql = "select * from user where username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            //3）执行SQL：
            resultSet = statement.executeQuery();
            //4）遍历结果集:
            //因为此处username使用的是unique约束，所以要么查到的结果为1要么为0，所以用if
            if (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5）释放资源，关闭连接：
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    //2、根据用户ID来查找用户信息（在博客详情页，根据用户ID查找作者名字，并显示作者名字）：
    public User selectById(int userId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1）和数据库建立连接：
            connection = DBUtil.getConnection();
            //2）构造SQl语句：
            String sql = "select * from user where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            //3）执行SQL：
            resultSet = statement.executeQuery();
            //4）遍历结果集:
            //因为此处username使用的是unique约束，所以要么查到的结果为1要么为0，所以用if
            if (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5）释放资源，关闭连接：
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
}
