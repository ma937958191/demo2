package life.mashuai.communtiy.dto;

import lombok.Data;

@Data
//登陆成功之后，获取当前用户的一些相关的信息
public class GithubUser {
    private  String name;
    private  Long id;
    private  String bio;
    private  String  avatar_url;//头像

}
