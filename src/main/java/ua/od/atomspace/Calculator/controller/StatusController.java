package ua.od.atomspace.Calculator.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {
    @GetMapping(value = "/healthcheck",produces = "application/json")
    public Map<String,String> healthCheck(){
        HashMap<String,String> status = new HashMap<>();
        status.put("status","ok");
        return status;
    }

}
