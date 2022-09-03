package cc.occs.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("sys_resource")
public class Resource {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long parentId;

    private String router;

    private String auth;

    private String icon;

    private Integer type;

    private String path;

    private Integer orderNum;

    private String externalLink;

    private Boolean menuHidden;

    private String redirect;

    private String groupName;

}
