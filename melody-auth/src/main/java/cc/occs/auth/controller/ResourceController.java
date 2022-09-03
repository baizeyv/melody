package cc.occs.auth.controller;

import cc.occs.auth.domain.Resource;
import cc.occs.auth.service.ResourceService;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 获取所有资源
     * @return
     */
    @GetMapping("/getAllResource")
    public ResJson getAllResource() {
        List<Resource> resourceList = resourceService.list();
        return ResJson.success(resourceList);
    }

    /**
     * 获取侧边栏菜单项
     * @return
     */
    @GetMapping("/getMenu")
    public ResJson getMenu() {
        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.eq("type", 1);
        List<Resource> menuList = resourceService.list(resourceQueryWrapper);
        return ResJson.success(menuList);
    }

    /**
     * 添加单独资源
     * @param resource
     * @return
     */
    @PostMapping("/addSingleResource")
    public ResJson addSingleResource(@RequestBody Resource resource) {
        boolean flag = resourceService.save(resource);
        if(flag) {
            return ResJson.success();
        }
        return ResJson.failure(ResCode.FATAL_ERROR);
    }

    /**
     * 批量删除资源
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteResource")
    public ResJson deleteResource(Long[] ids) {
        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.in("parent_id", Arrays.asList(ids));
        Long count = resourceService.count(resourceQueryWrapper);
        if(count != 0) {
            return ResJson.failure(ResCode.HAS_CHILD_RESOURCE);
        }
        boolean flag = resourceService.removeBatchByIds(Arrays.asList(ids));
        if(flag) {
            return ResJson.success();
        }
        return ResJson.failure(ResCode.FATAL_ERROR);
    }

}
