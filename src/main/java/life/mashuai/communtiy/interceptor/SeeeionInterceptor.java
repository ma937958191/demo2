package life.mashuai.communtiy.interceptor;

import life.mashuai.communtiy.mapper.UserMapper;
import life.mashuai.communtiy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SeeeionInterceptor implements HandlerInterceptor
{

    @Autowired
    private UserMapper userMapper;
    @Override
    //拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //直接从cookie取，看用户是否·已经登录
        User user=null;
        Cookie[] cookies=request.getCookies();
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



        return true;//true会继续执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
