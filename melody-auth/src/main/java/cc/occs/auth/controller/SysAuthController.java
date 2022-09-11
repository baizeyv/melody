package cc.occs.auth.controller;

import cc.occs.auth.domain.SysUser;
import cc.occs.auth.model.UserInfoResponse;
import cc.occs.auth.service.SysAuthService;
import cc.occs.auth.service.SysUserService;
import cc.occs.common.exception.CustomException;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import cc.occs.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/cert")
public class SysAuthController {

    @Autowired
    private SysAuthService sysAuthService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public ResJson login(@RequestBody SysUser sysUser) throws CustomException {
        UserInfoResponse userInfoResponse = sysAuthService.login(sysUser.getUsername(), sysUser.getPassword());
        return ResJson.success(userInfoResponse);
    }

    @GetMapping("/tmp")
    public ResJson tmp() {
        return ResJson.success("TMP");
    }

    @PostMapping("/signup")
    public ResJson signup(@RequestBody SysUser user) {
        if (StringUtils.isAnyBlank(user.getPassword(), user.getUsername())) {
            return ResJson.failure(ResCode.ARGS_ERROR);
        }
        return ResJson.success(sysUserService.register(user));
    }

}
