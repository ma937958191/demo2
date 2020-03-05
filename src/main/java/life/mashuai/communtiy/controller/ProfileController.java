package life.mashuai.communtiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @GetMapping("/profile/{action}")
    //@PathVariable意思是说省去了profile/name=question
    public String profile(@PathVariable(name = "action")String action, Model model){
        System.err.println("进入");
        if("quesion".equals(action)){

            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("message".equals(action)){
            model.addAttribute("section","message");
            model.addAttribute("sectionName","个人资料");
        }

        return "profile";
    }
}
