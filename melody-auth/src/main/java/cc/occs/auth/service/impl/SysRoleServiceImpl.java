package cc.occs.auth.service.impl;

import cc.occs.auth.domain.SysRole;
import cc.occs.auth.mapper.SysRoleMapper;
import cc.occs.auth.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
