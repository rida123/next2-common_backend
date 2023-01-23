package net.claims.express.next2.controllers;

import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import net.claims.express.next2.services.CallCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CallCenterController {
@Autowired
    CallCenterService callCenterService;
    @GetMapping("/demo")
    public String test() {
        return "abc";
    }

    //second endpoint:
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }


    @GetMapping("/getNotificationSearch")
    public List<NotificationSearchResponse> getNotificationSearch (@RequestParam(name = "type",required = true)String type,
                                                                   @RequestParam(name = "cmp",required = true)String cmp,
                                                                   @RequestParam(name = "admin", required = true)String admin,
                                                                   @RequestParam(name = "value",required = true)String value) {
        List<NotificationSearchResponse> notifications= callCenterService.getNotificationSearch(type,value,cmp,admin);

       return  notifications;


    }
}
