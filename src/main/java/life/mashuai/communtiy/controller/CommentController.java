package life.mashuai.communtiy.controller;


import life.mashuai.communtiy.dto.CommentDto;
import life.mashuai.communtiy.dto.ResultDto;
import life.mashuai.communtiy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentController {

    @ResponseBody
    @RequestMapping(value = "/commentTest",method = RequestMethod.GET)
    //@RequestBody CommentDto commentDto//直接将前端的信息转换对象
    public Object  posts(CommentDto commentDto, HttpServletRequest request){
        //如果用户没登录为空就不可以回复
        User user= (User) request.getSession().getAttribute("username");
        if(user==null){
            return ResultDto.errorCode(2002,"没有进行登录，不能评论");
        }
                

        return commentDto;
    }
}
