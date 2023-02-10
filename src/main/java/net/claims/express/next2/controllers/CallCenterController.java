package net.claims.express.next2.controllers;

import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import net.claims.express.next2.services.CallCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/call_center")
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
    public ApiResponse getNotificationSearch (@RequestParam(name = "type",required = true)String type,
                                                                               @RequestParam(name = "cmp",required = true)String cmp,
                                                                               @RequestParam(name = "admin", required = true)String admin,
                                                                               @RequestParam(name = "value",required = true)String value) {
        ApiResponse notifications= callCenterService.getNotificationSearch(type,value,cmp,admin);

       return  notifications;


    }

    @GetMapping("/getPolicy")

    public ApiResponse getPolicy (@RequestParam(name = "iSearchBy",required = true)String iSearchBy,
                                              @RequestParam(name = "iSearchValue",required = true)String iSearchValue,
                                              @RequestParam(name = "iPolicyType", required = true)String iPolicyType,
                                              @RequestParam(name = "iAsOfDate",required = true) java.util.Date iAsOfDate,
                                  @RequestParam(name = "iInsurance",required = true) String iInsurance,
                                  @RequestParam(name = "productType") String productType

                                  )  {
        ApiResponse policySearch= callCenterService.getPolicySearch(iSearchBy, iSearchValue,iPolicyType,iAsOfDate,iInsurance,productType);

        return  policySearch;


    }



    @PostMapping("/saveNotfication")
    public ApiResponse createNotification () {
     //   ApiResponse notifications= callCenterService.getNotificationSearch(type,value,cmp,admin);

        return  null;


    }





}
