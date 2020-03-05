package life.mashuai.communtiy.dto;

import life.mashuai.communtiy.model.User;
import lombok.Data;

//这个类相当于关联表@Data
@Data
public class QuesionDto {
    private Integer id; //唯一标识
    private  String title; //文章标题
    private String description;//文章内容
    private  String tag;//文章标签
    private  Long gmt_creat;
    private  Long gmt_modified;
    private  Integer creator;//
    private  Integer like_count;
    private String  username;//用户
    private  String  avatar_url;//头像
    private  Integer view_count;
    private User user;



}
