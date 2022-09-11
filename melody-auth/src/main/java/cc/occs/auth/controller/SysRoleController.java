package cc.occs.auth.controller;

import cc.occs.auth.domain.SysRole;
import cc.occs.auth.service.SysRoleService;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/list/{currentPage}/{pageSize}")
    public ResJson getRoleList(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody SysRole sysRole) {
        QueryWrapper<SysRole> roleQueryWrapper = new QueryWrapper<>();
        if(!sysRole.getName().equals("")) {
            roleQueryWrapper.like("name", sysRole.getName());
        }
        if(sysRole.getAvailable() != null) {
            roleQueryWrapper.eq("available", sysRole.getAvailable());
        }
        IPage pageList = sysRoleService.page(new Page<>(currentPage, pageSize), roleQueryWrapper);
        return ResJson.success(pageList);
    }

    @PostMapping("/modify")
    public ResJson saveOrUpdate(@RequestBody SysRole sysRole) {
        /* ID 为空，则为插入新数据 */
        if(sysRole.getId() == null) {
            /* 判断是否存在当前用户名的角色 */
            QueryWrapper<SysRole> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("name", sysRole.getName());
            long count = sysRoleService.count(roleQueryWrapper);
            if(count > 0) {
                return ResJson.failure(ResCode.ROLE_DUPLICATION);
            }
        }
        boolean flag = sysRoleService.saveOrUpdate(sysRole);
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
