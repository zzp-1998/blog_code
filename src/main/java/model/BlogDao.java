package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * model.User: Administrator
 * Date: 2022-05-30
 * Time: 下午 5:37
 * 描述：这个类用于封装 博客表 的基本操作（增删改查）
 * 这些操作中是增删查，还没有改的操作
 * JDBC 基本代码
 */
public class BlogDao {
    //1、往博客表里插入一篇博客：
    public void insert(Blog blog){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1）和数据库建立连接：
            connection = DBUtil.getConnection();
            //2）构造SQl语句：
            String sql = "insert into blog values(null,?,?,?,now())";
            statement = connection.prepareStatement(sql);
            statement.setString(1,blog.getTitle());
            statement.setString(2, blog.getContent());
            statement.setInt(3,blog.getUserId());
            //3）执行SQL：
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4）释放资源，关闭连接：
            DBUtil.close(connection,statement,null);
        }
    }

    //2、能够获取到博客表里所有的博客信息（用于博客列表页，每篇博客不一定是显示全部信息）
    public List<Blog> selectAll(){
        List<Blog> blogs = new ArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1）和数据库建立连接：
            connection = DBUtil.getConnection();
            //2）构造SQl语句：
            String sql = "select * from blog order by postTime desc";
            statement = connection.prepareStatement(sql);
            //3）执行SQL：
            resultSet = statement.executeQuery();
            //4）遍历结果集（将查询到的结果resultSet变为blog对象放到blogs数组里）
            while (resultSet.next()){
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                //这里针对博客列表页的“正文”content进行截取，如果其长度大于50，那么只取前50：
                String content = resultSet.getString("content");
                if (content.length() > 50){
                    content = content.substring(0,50);
                }
                blog.setContent(content);
                blog.setUserId(resultSet.getShort("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5）释放资源，关闭连接：
            DBUtil.close(connection,statement,resultSet);
        }
        return blogs;
    }

    //3、能够根据博客id获取到指定的博客内容（用于博客详情页）
    public Blog selectOne(int blogId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1）和数据库建立连接：
            connection = DBUtil.getConnection();
            //2）构造SQl语句：
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            //3）执行SQL：
            resultSet = statement.executeQuery();
            //4）遍历结果集:
            //因为此处是blogId（使用了主键约束）作为查询依据的，结果要么为1，要么为0，所以不用where，而用if
            //如果resultSet里没有数据，就不会执行if，直接返回null
            if (resultSet.next()){
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setUserId(resultSet.getShort("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5）释放资源，关闭连接：
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    //4、从博客表中，根据博客id，删除某篇博客
    public void delete(int blogId){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1）和数据库建立连接：
            connection = DBUtil.getConnection();
            //2）构造SQl语句：
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            //3）执行SQL：
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4）释放资源，关闭连接：
            DBUtil.close(connection,statement,null);
        }
    }

}
