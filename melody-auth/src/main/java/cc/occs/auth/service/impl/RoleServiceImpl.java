package cc.occs.auth.service.impl;

import cc.occs.auth.domain.Role;
import cc.occs.auth.mapper.RoleMapper;
import cc.occs.auth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
