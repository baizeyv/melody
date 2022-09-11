package cc.occs.auth.controller;

import cc.occs.auth.domain.SysResource;
import cc.occs.auth.service.SysResourceService;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 获取所有资源
     * @return
     */
    @GetMapping("/getAllResource")
    public ResJson getAllResource() {
        List<SysResource> sysResourceList = sysResourceService.list();
        return ResJson.success(sysResourceList);
    }

    /**
     * 获取侧边栏菜单项
     * @return
     */
    @GetMapping("/getMenu")
    public ResJson getMenu() {
        QueryWrapper<SysResource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.eq("type", 1);
        List<SysResource> menuList = sysResourceService.list(resourceQueryWrapper);
        return ResJson.success(menuList);
    }

    /**
     * 添加单独资源
     * @param sysResource
     * @return
     */
    @PostMapping("/addSingleResource")
    public ResJson addSingleResource(@RequestBody SysResource sysResource) {
        boolean flag = sysResourceService.save(sysResource);
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
        QueryWrapper<SysResource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.in("parent_id", Arrays.asList(ids));
        long count = sysResourceService.count(resourceQueryWrapper);
        if(count != 0) {
            return ResJson.failure(ResCode.HAS_CHILD_RESOURCE);
        }
        boolean flag = sysResourceService.removeByIds(Arrays.asList(ids));
        if(flag) {
            return ResJson.success();
        }
        return ResJson.failure(ResCode.FATAL_ERROR);
    }

}
