package cc.occs.auth.service;

import cc.occs.auth.domain.SysRole;
import cc.occs.auth.domain.SysUser;
import cc.occs.auth.domain.UserDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
    List<SysRole> getRoleListByUsername(String username);

    UserDetail getUserDetailByUsername(String username);

    SysUser register(SysUser sysUser);
}
