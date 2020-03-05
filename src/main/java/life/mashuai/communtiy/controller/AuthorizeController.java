package life.mashuai.communtiy.controller;

import life.mashuai.communtiy.dto.AccessTokenDto;
import life.mashuai.communtiy.dto.GithubUser;
import life.mashuai.communtiy.dto.QuesionDto;
import life.mashuai.communtiy.mapper.UserMapper;
import life.mashuai.communtiy.model.User;
import life.mashuai.communtiy.provider.GithubProvider;
import life.mashuai.communtiy.serivce.QuestionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
   @Autowired
    private UserMapper userMapper;


    @Autowired
    private QuestionSerivce quesionList;


    //获取配置文件的常量
    @Value("${github.client.id}")
    private  String clintId;
    @Value("${github.clinet.secret}")
    private  String clientSecrect;
    @Value("${github.redirect.uri}")
    private  String redirectUri;

    //腿出登录
    @GetMapping("/loginout")
    public  String loginout(HttpServletRequest request,HttpServletResponse response){

        request.getSession().removeAttribute("user");//移除session
        //移除cookie  就是设置一个一样的cookie，把他赋值重新覆盖
        Cookie cookie=new Cookie("username",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "hellospringboot";
    }

    //授权登陆成功之后就会跳到这个请求中，获取到他的access_token的码解析出用户的信息
    //这里的callback请求，在github上创建项目的时候就已经设置过登录成功之后直接进入这个请求
    @GetMapping("/callback")
    public String  callback(@RequestParam(name = "code") String code
                            , @RequestParam(name = "state") String state
                            , HttpServletRequest request
                            , HttpServletResponse response, Model model){
        //授权登陆成功之后返回的是很多的信息。我们要只取其中access_token的值解析出用户的信息，所以封桩一页房费
        AccessTokenDto accessTokenDto =new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(clintId);
        accessTokenDto.setClient_secret(clientSecrect);


        //将登录授权的状态的对象存放到GithubProvider githubProvider;的对象
        //获取accessToken
        String access_token=githubProvider.getAccesoken(accessTokenDto);
        System.err.println("accestoken的值是："+access_token);
        GithubUser user=githubProvider.getUser(access_token);


        //如果user不为空说明登录成功
        if(user!=null){
            //登录成功

            response.addCookie(new Cookie("username",user.getName()));//将信息放进自定义的cookies

             request.getSession().setAttribute("user",user);//将用户信息放进session。
            //先查询当前用户是否已经存在
            boolean isName=false;//默认用户第一次登录
            List<User> userList=userMapper.listuser();

            for (User user1:userList){
                if(user1.getName().equals(user.getName())){
                    //说明用户已经存在
                    System.out.println("用户已经存在了");
                    isName=true;
                    break;//z直接跳出
                }else {
                    isName=false;
                }
            }


            if(!isName){
                //利用mybatis持久化到数据库
                User user1=new User();
                user1.setAccountId(String.valueOf(user.getId()));
                user1.setName(user.getName());
                user1.setToken(access_token);
                user1.setAvatar_url(user.getAvatar_url());
                System.out.println("头像的地址："+user.getAvatar_url());
                userMapper.insertUser(user1);
            }


        } else {
            //登录失败

        }
        /*    Integer offset=
        List<QuesionDto> quesionDtos=quesionList.quesionDtos();
        model.addAttribute("quesionLists",quesionDtos);*/
        return "hellospringboot";

    }
}
