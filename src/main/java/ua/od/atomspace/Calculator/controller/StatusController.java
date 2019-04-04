package ua.od.atomspace.Calculator.controller;


import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {
    @RequestMapping(value = "/healthcheck", produces = {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> healthCheck(@RequestHeader(value = "Content-Type") String contentType){
        if (contentType.equals("application/json")) {
            HashMap<String,String> status = new HashMap<>();
            status.put("status","ok");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return new ResponseEntity<>(new JSONObject(status).toString(),headers, HttpStatus.OK);
        }
        else if (contentType.equals("text/plain")) {
            return new ResponseEntity<>("status\tok",HttpStatus.OK);
        }
        return new ResponseEntity<>("No valid Content-Type",HttpStatus.BAD_REQUEST);
    }

}
