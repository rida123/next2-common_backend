package net.claims.express.next2.controllers;

import net.claims.express.next2.entities.CarsErrorlog;
import net.claims.express.next2.services.CarsErrorlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/error_log")
public class ErrorLogController {


    @Autowired
    CarsErrorlogService errorlogService;

    @GetMapping("/all")
    public List<CarsErrorlog> getAllErrors() {
        return this.errorlogService.findAll();
    }

    @GetMapping("/test") //TODO: WORKING EVEN ITS AUTHORIZATION RULE NOT DEFINED IN SECURITY CONFIG
    public String search() {//(MUST be AUTHENTICATED IN http section
        return "partial";
    }
}
