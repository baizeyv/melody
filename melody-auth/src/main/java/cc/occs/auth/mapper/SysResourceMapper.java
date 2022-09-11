package cc.occs.auth.mapper;

import cc.occs.auth.domain.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> getResourceListByUsername(String username);

}
