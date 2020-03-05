package life.mashuai.communtiy.model;

import lombok.Data;

@Data
public class Quesion {
    private Integer id; //唯一标识
    private  String title; //文章标题
    private String description;//文章内容
    private  String tag;//文章标签
    private  Long gmt_creat;
    private  Long gmt_modified;
    private  Integer creator;//
    private  Integer view_count;
    private  Integer like_count;
    private String  username;//用户
    private  String  avatar_url;//头像

}
