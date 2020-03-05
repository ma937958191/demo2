package life.mashuai.communtiy.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//自定义异常页面
@ControllerAdvice
public class CustomizeException {
    @ExceptionHandler(Exception.class)
    public ModelAndView handel(HttpServletRequest request, Throwable throwable, Model model){
        HttpStatus status=getStatus(request);
        model.addAttribute("messs","服务器异常");
        return  new ModelAndView("error");//返回的一个页面
    }

    private HttpStatus getStatus(HttpServletRequest request){
       Integer statues=(Integer)  request.getAttribute("javax.servle.error.staus_code");
       if(statues==null){
           return  HttpStatus.INTERNAL_SERVER_ERROR;
       }
       return HttpStatus.valueOf(statues);
    }
}
