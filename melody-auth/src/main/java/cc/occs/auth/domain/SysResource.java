package cc.occs.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * System Resource Entity
 */
@Data
@Builder
@TableName("sys_resource")
public class SysResource {

    /* Resource ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /* Resource Name */
    private String name;

    /* Resource ID Of Parent-Level Resource */
    private Long parentId;

    /* Resource Jump Router */
    private String router;

    /* Resource Right Field */
    private String auth;

    /* Icon Of Resource */
    private String icon;

    /* Resource Type - 1:Menu 2:Button */
    private Integer type;

    /* Vue Page Resource Path */
    private String path;

    /* Order Number */
    private Integer orderNum;

    /* Resource External Like, Default:Null */
    private String externalLink;

    /* Resource Sidebar Menu Hidden Mark */
    private Boolean menuHidden;

    /* Redirect Path */
    private String redirect;

    /* Group Name Of Resource */
    private String groupName;

    /* Resource Usability */
    private Boolean available;

}
