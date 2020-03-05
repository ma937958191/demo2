package life.mashuai.communtiy.controller;

import life.mashuai.communtiy.dto.QuesionDto;
import life.mashuai.communtiy.mapper.QuesionMapper;
import life.mashuai.communtiy.mapper.UserMapper;
import life.mashuai.communtiy.model.Quesion;
import life.mashuai.communtiy.model.User;
import life.mashuai.communtiy.serivce.QuestionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class helloController {
    @Autowired
    UserMapper userMapper;

    @Autowired
   private QuestionSerivce quesionList;

    @Autowired
    private  QuesionMapper quesionMapper;

    @Autowired
    private QuestionSerivce questionSerivce;
    //拦截请求
    @GetMapping("/")
    public String hello(HttpServletRequest request,Model model){


        return  "hellospringboot";

    }

    //分页的请求 ;
    @GetMapping("/pageHello")
    public String pageHello(HttpServletRequest request,Model model
                            ,@RequestParam("page") Integer page
    ){
        Integer size=4;//每页显示4
        Integer countPage=quesionMapper.pageCount();//总条数
        Integer count=0;//总页数
        if(countPage%size==0){
            count=countPage%size;
        }else if (countPage%size!=0){
            count=countPage%size+1;
        }
        //判断拥有连续显示的条数
        List<Integer> lianXuPAge=new ArrayList<>();
        for (int i=1;i<=countPage;i++){
            lianXuPAge.add(i);
        }

        Integer offset=(page-1)*size; //需要从第几条开始显示
        List<QuesionDto> quesionDtos=quesionList.quesionDtos(offset,4);
        model.addAttribute("count",count);//总页
        model.addAttribute("lianXuPAge",lianXuPAge);//总页
        model.addAttribute("quesionLists",quesionDtos);
        return  "hellospringboot";

    }
    //测试类
    @GetMapping("/getlist")
    public String getlist(){
        System.out.println("准备查询");
        System.out.println(userMapper.listuser().get(0).toString());
        return  "hellospringboot";
    }



}
