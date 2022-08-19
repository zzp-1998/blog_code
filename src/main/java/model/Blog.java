package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * model.User: Administrator
 * Date: 2022-05-30
 * Time: 下午 5:27
 * 描述：每个Blog对象都对应blog表中的一条记录
 * 创建类之后再根据表结构（字段）来创建类里的属性
 */
public class Blog {
    private int blogId;
    private String title;
    private String content;
    private int userId;
    private Timestamp postTime;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public Timestamp getPostTime() {
//        return postTime;
//    }

    //把这里的getter方法改了，不是返回一个时间戳对象，而是一个String（已经格式好的日期）
    public String getPostTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return simpleDateFormat.format(postTime);
    }
    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }
}
