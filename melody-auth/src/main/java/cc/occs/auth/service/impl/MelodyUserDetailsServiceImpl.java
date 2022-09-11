package cc.occs.auth.service.impl;

import cc.occs.auth.domain.SysResource;
import cc.occs.auth.domain.SysRole;
import cc.occs.auth.domain.UserDetail;
import cc.occs.auth.mapper.SysResourceMapper;
import cc.occs.auth.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MelodyUserDetailsServiceImpl implements UserDetailsService {

    private final SysResourceMapper sysResourceMapper;

    private final SysUserMapper sysUserMapper;

    /**
     * Constructor Function
     * @param sysResourceMapper
     * @param sysUserMapper
     */
    public MelodyUserDetailsServiceImpl(SysResourceMapper sysResourceMapper, SysUserMapper sysUserMapper) {
        this.sysResourceMapper = sysResourceMapper;
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * Load User By Username
     * @param username Username
     * @return UserDetails
     * @throws UsernameNotFoundException Username Not Found Exception
     */
    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetail = sysUserMapper.getUserDetailByUsername(username);
        System.out.println(username);
        if(userDetail == null) {
            System.out.println("NULL--");
            throw new UsernameNotFoundException("-- " + username + " -- : Cannot Be Found!");
        }
        System.out.println("?");
        List<SysResource> sysResourceList = sysResourceMapper.getResourceListByUsername(username);
        List<SysRole> sysRoleList = sysUserMapper.getRoleListByUsername(username);
        System.out.println("??");
        userDetail.setRoleList(sysRoleList);
        userDetail.setResourceList(sysResourceList);
        System.err.println(userDetail.toString());
        System.out.println("LOAD END");
        return userDetail;
    }
}
