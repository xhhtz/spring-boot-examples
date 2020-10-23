package cn.xhhtz.sbe.controller;

import cn.xhhtz.sbe.common.ServerResponse;
import cn.xhhtz.sbe.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/api/sync")
public class AsyncController {
    @Resource
    AsyncService asyncService;

    @GetMapping("/test")
    public ServerResponse test(@RequestParam String data) {
        System.out.println("请求->" + data);
        asyncService.asyncWork(data);
        System.out.println("返回->" + data);
        return ServerResponse.createBySuccess(data);
    }

}
