package life.mashuai.communtiy.serivce;

import life.mashuai.communtiy.dto.QuesionDto;
import life.mashuai.communtiy.mapper.QuesionMapper;
import life.mashuai.communtiy.mapper.UserMapper;
import life.mashuai.communtiy.model.Quesion;
import life.mashuai.communtiy.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionSerivce {

    @Autowired
    private QuesionMapper quesionMapper;
    @Autowired
    private UserMapper userMapper;

    //根据帖子的id确定作者的信息
    public  QuesionDto getUserMes(Integer creator,Integer quesion_id){
        Quesion quesion=quesionMapper.quesionMes(quesion_id);//定位到问题
        User user=userMapper.findOneUser(creator);//查找出当前的用户

        QuesionDto quesionDto=new QuesionDto();
        BeanUtils.copyProperties(quesion,quesionDto);//直接将queison的属性复制给quesionDto相同字段的属性
        quesionDto.setUser(user);
        return quesionDto;
    }


    //这个类就是查询两个表的数据，然后封装起来，起到关联表达作用(这里确认每个文章对应每个用户的信息)
    public List<QuesionDto> quesionDtos(Integer offset,Integer size){
        List<Quesion> quesions=quesionMapper.quesionList(offset,size);//查找所有哦文章(根据分页确定)
        List<QuesionDto> quesionDtos=new ArrayList<>();
        for (Quesion quesion:quesions){
            User user=userMapper.findOneUser(quesion.getCreator());//根据cretor这个地段去获取对应的用户信息
            QuesionDto quesionDto=new QuesionDto();
            BeanUtils.copyProperties(quesion,quesionDto);
            quesionDto.setUser(user);
            quesionDtos.add(quesionDto);
        }
        return quesionDtos;
    }

//这里已经确认文章对应的idu也就确认作者
    public List<QuesionDto> getQuesionId(Integer id) {
        //将两个表查询的结果关联起来
       User userList=userMapper.findOneUser(id);//查找到的用户
        List<Quesion> quesionList=quesionMapper.quesion(id);
        //开始赋值
        List<QuesionDto> quesionDtos=new ArrayList<>();
        for(Quesion quesion:quesionList){
           QuesionDto quesionDto=new QuesionDto();
           BeanUtils.copyProperties(quesion,quesionDto);//将Quesion的结果赋值给qeusionDato对应的字段
           quesionDto.setUser(userList);

           quesionDtos.add(quesionDto);
        }
     return  quesionDtos;
    }
}
