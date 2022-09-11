package cc.occs.auth.mapper;

import cc.occs.auth.domain.SysRole;
import cc.occs.auth.domain.SysUser;
import cc.occs.auth.domain.UserDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysRole> getRoleListByUsername(String username);

    UserDetail getUserDetailByUsername(String username);

}
