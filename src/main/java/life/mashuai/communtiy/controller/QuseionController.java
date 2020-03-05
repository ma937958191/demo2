package life.mashuai.communtiy.controller;

import life.mashuai.communtiy.dto.QuesionDto;
import life.mashuai.communtiy.mapper.QuesionMapper;
import life.mashuai.communtiy.serivce.QuestionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuseionController {


    @Autowired
    private QuesionMapper quesionMapper;

    @Autowired
    private QuestionSerivce questionSerivce;
    //根据id查找出这个文章是否存在
    @GetMapping("/quesion/{id}")
    public String qeusion(@PathVariable(name = "id") Integer id
    , Model model){
        //给文章数添加阅读量
        int updateViewCount=quesionMapper.updateViewCount(id);
        //更具当前文章的住建查到对应的用户信息
        //先取出creator
        int cerator=quesionMapper.slectCreator(id);
       QuesionDto quesionDto=questionSerivce.getUserMes(cerator,id);
       System.out.println(quesionDto.toString());
        //将这个
        model.addAttribute("quesionUser",quesionDto);

        return "quesion";
    }

}
