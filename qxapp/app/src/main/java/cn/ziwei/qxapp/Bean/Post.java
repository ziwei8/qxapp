package cn.ziwei.qxapp.Bean;

import cn.bmob.v3.BmobObject;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.11  17:01:00
 * @Description 对应Bomb中的表
 */
public class Post extends BmobObject {

    private User author;     // 上传的对应用户
    private String title;    // 帖子标题
    private String content;  // 帖子内容
    private String nickName; // 帖子昵称

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
