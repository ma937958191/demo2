package life.mashuai.communtiy.dto;

import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

@Data
public class CommentDto {
    private Integer id;
    private  Long parent_id;
    private  Integer type;
    private INACTIVE commentator;
    private Long gmt_Create;
    private  Long gmt_modified;
    private  Integer like_count;
    private  String  context;
}
