package cc.occs.auth.mapper;

import cc.occs.auth.domain.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}
