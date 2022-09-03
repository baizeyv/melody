package cc.occs.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("sys_role")
public class Role {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Boolean available;

}
