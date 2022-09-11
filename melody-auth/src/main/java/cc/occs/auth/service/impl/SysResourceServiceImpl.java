package cc.occs.auth.service.impl;

import cc.occs.auth.domain.SysResource;
import cc.occs.auth.mapper.SysResourceMapper;
import cc.occs.auth.service.SysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    @Override
    public List<SysResource> getResourceListByUsername(String username) {
        return this.baseMapper.getResourceListByUsername(username);
    }
}
