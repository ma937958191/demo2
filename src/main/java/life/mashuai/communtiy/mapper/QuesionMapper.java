package life.mashuai.communtiy.mapper;

import life.mashuai.communtiy.model.Quesion;
import life.mashuai.communtiy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuesionMapper {
    //
    @Select("select *from quseion where id=#{id}")
    Quesion quesionMes(Integer id);

    //文章点击量+1；
    @Update("UPDATE quseion set view_count=view_count+1 where id=#{id}")
    int updateViewCount(@Param(value = "id") Integer id);
    //根据文章的住建查找到对应的cretor
    @Select("select creator from quseion where id=#{id}")
    int slectCreator(Integer id);

    @Insert("insert into quseion(username,title,description,creator,tag,gmt_creat,gmt_modified) " +
            "values(#{username},#{title},#{description},#{creator},#{tag},#{gmt_creat},#{gmt_modified})")
    void ceaate(Quesion quesion);

    @Select("select *from quseion limit #{offset},#{size} ")
    List<Quesion> quesionList(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    //映射的注解，。对应查询语句的值

    @Select("select count(1) from  quseion")
    Integer pageCount();//查找总条数
    @Select("select *from quseion where creator=#{creator}")
    List<Quesion> quesion(Integer creator);

}
