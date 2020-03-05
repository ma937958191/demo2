package life.mashuai.communtiy.dto;

import lombok.Data;

@Data
//授权登陆的必备一些变量
public class AccessTokenDto {
    private String client_id;//注册应用的钥匙id
    private  String client_secret;//注册应用的密码
    private  String  code;//
    private  String  redirect_uri;//回调网址，就是登陆成功之后需要把网页转发到哪个请求的意思
    private  String  state;//状态码，默认是1

}
