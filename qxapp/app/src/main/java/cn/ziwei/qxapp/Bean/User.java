package cn.ziwei.qxapp.Bean;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * @author ziwei
 * @version 1.0
 * @createTime 2022.07.05  14:25:00
 * @Description
 */
public class User extends BmobUser {
    /**
     * 昵称
     */
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
