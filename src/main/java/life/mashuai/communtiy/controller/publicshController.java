package life.mashuai.communtiy.controller;

import life.mashuai.communtiy.mapper.QuesionMapper;
import life.mashuai.communtiy.mapper.UserMapper;
import life.mashuai.communtiy.model.Quesion;
import life.mashuai.communtiy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publicshController {
    @Autowired
    private UserMapper userMapper;//用户信息的接口
    @Autowired
    private QuesionMapper quesionMapper;//发帖人的接口

    @GetMapping("/publish")
    public  String publicsh(){
        return "publish";
    }

    @PostMapping("/publish")
    public  String doPublicsh(
            @RequestParam("title") String titile,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        if(titile.equals("")||description.equals("")||tag.equals("")){
            model.addAttribute("error","发送内容不为空");
            System.err.println("不能为空");
            return "publish";
        }

        //在调回页面的时候保存当前用户所填写的信息，。后期ajax回显
        model.addAttribute("title",titile);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //在提交发帖的时候先判断用户是否已经登录
        System.out.println("title="+titile+"-description="+description+"--tag="+tag );
        User user=(User) request.getSession().getAttribute("user");
       /* Cookie[] cookies=request.getCookies();
        //注意一定要加上这个条件，不然没登陆就开始发帖会报错，。后期可以使用拦截器
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("username")){
                    String name=cookie.getValue();
                    user=userMapper.findCokieUser(name);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

      */
        //说明用户没有登录
        if(user ==null){
            model.addAttribute("error","请先去登录");
            return "publish";
        }
        //已经登录的时候，就给用户的发帖信息i插入到数据库
        Quesion quesion=new Quesion();
        quesion.setTitle(titile);
        quesion.setDescription(description);
        quesion.setTag(tag);
        quesion.setGmt_creat(System.currentTimeMillis());
        //这是一种时间格式，在前端直接取出来就行th:text="${#dates.format(quesionList.getGmt_creat(),'yyyy-MM-dd HH:mm')
        quesion.setGmt_modified(quesion.getGmt_creat());
        //田间用户名
        quesion.setUsername(user.getName());
        quesionMapper.ceaate(quesion);
        return "hellospringboot";//发布成功调回首页
    }
}
