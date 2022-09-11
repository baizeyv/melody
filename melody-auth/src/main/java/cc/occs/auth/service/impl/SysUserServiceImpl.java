package cc.occs.auth.service.impl;

import cc.occs.auth.domain.SysRole;
import cc.occs.auth.domain.SysUser;
import cc.occs.auth.domain.UserDetail;
import cc.occs.auth.mapper.SysUserMapper;
import cc.occs.auth.service.SysUserService;
import cc.occs.common.exception.CustomException;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public List<SysRole> getRoleListByUsername(String username) {
        return this.baseMapper.getRoleListByUsername(username);
    }

    @Override
    public UserDetail getUserDetailByUsername(String username) {
        return this.baseMapper.getUserDetailByUsername(username);
    }

    @Override
    public SysUser register(SysUser sysUser) {
        String username = sysUser.getUsername();
        if(this.baseMapper.getUserDetailByUsername(username) != null) {
            throw new CustomException(ResJson.failure(ResCode.USER_EXIST));
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = sysUser.getPassword();
        sysUser.setAvailable(true);
        sysUser.setPassword(bCryptPasswordEncoder.encode(rawPassword));
        this.baseMapper.insert(sysUser);
        return sysUser;
    }
}
