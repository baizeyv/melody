package cc.occs.auth.controller;

import cc.occs.auth.domain.Role;
import cc.occs.auth.service.RoleService;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/list/{currentPage}/{pageSize}")
    public ResJson getRoleList(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody Role role) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if(!role.getName().equals("")) {
            roleQueryWrapper.like("name", role.getName());
        }
        if(role.getAvailable() != null) {
            roleQueryWrapper.eq("available", role.getAvailable());
        }
        IPage pageList = roleService.page(new Page<>(currentPage, pageSize), roleQueryWrapper);
        return ResJson.success(pageList);
    }

    @PostMapping("/modify")
    public ResJson saveOrUpdate(@RequestBody Role role) {
        /* ID 为空，则为插入新数据 */
        if(role.getId() == null) {
            /* 判断是否存在当前用户名的角色 */
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("name", role.getName());
            Long count = roleService.count(roleQueryWrapper);
            if(count > 0) {
                return ResJson.failure(ResCode.ROLE_DUPLICATION);
            }
        }
        boolean flag = roleService.saveOrUpdate(role);
        if(flag) {
            return ResJson.success();
        }
        return ResJson.failure(ResCode.FATAL_ERROR);
    }

    @DeleteMapping("/delete")
    public ResJson deleteRole(Long[] ids) {
        return null;
    }

}
