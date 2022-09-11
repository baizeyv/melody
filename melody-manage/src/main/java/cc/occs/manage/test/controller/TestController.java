package cc.occs.manage.test.controller;

import cc.occs.common.model.ResJson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public ResJson test() {
        return ResJson.success("arst");
    }

    @PostMapping("/test1")
    public ResJson test1() {
        return ResJson.success("neio");
    }

}
