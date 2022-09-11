package cc.occs.auth.service;

import cc.occs.auth.domain.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {

    List<SysResource> getResourceListByUsername(String username);

}
