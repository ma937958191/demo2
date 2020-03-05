package life.mashuai.communtiy.dto;

import life.mashuai.communtiy.serivce.QuestionSerivce;
import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    private List<QuestionSerivce> questionSerivces;
    private  boolean showPrevious;
    private  boolean showFristpage;
    private  boolean showNext;
    private  boolean showEndpage;
    private  Integer page;//当前页
    private List<Integer> pages;
}
