package life.mashuai.communtiy.mapper;

import life.mashuai.communtiy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//这个注解一定要加，不然会报错（简单说就是为了后面调佣UserMapperd接口的方法
public interface UserMapper {
    //查找cokkie是否已经存在这个用户
    @Select("select *from user where name=#{name}")
    User findCokieUser(String name);

    //查找所有用户
    @Select("select *from user")
    List<User> listuser();

//查找一个用户
    @Select("select *from user  where creator=#{creator}")
    User findOneUser(Integer creator);

    //根据授权登陆的结果插入当前用户
    @Insert("insert into user(name,token,accountId,avatar_url) values(#{name},#{token},#{accountId},#{avatar_url})")
      void  insertUser(User user);


}
