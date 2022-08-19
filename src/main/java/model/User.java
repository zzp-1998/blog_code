package model;

/**
 * model.User: Administrator
 * Date: 2022-05-30
 * Time: 下午 5:27
 * 描述：每个User对象都对应User表中的一条记录
* 创建类之后再根据表结构（字段）来创建类里的属性
 */
public class User {
    private int userId = 0;
    private String username = "";
    private String password = "";

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
