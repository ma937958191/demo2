package life.mashuai.communtiy.model;

import lombok.Data;
import sun.rmi.runtime.Log;

@Data

//lombok依赖自动生成set。get，tostring
public class User {
    private  Integer id;
    private  String  name;
    private  String  accountId;
    private  String  avatar_url;//头像
    private String token;
    private  Integer creator;



}
