package cc.occs.auth.service.impl;

import cc.occs.auth.domain.Resource;
import cc.occs.auth.mapper.ResourceMapper;
import cc.occs.auth.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
}
