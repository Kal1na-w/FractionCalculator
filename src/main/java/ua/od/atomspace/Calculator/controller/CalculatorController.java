package ua.od.atomspace.Calculator.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.od.atomspace.Calculator.service.FractionOperations.calculate.Calculator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    private final Calculator calculator;

    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @RequestMapping(value = "/calc", produces = {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> calculateExpression(@RequestHeader(value = "Content-Type") String contentType, @RequestBody String text) {
        if(contentType.equals("text/plain")) {
            String[] requestText = text.split("\t");
            StringBuilder responseText = new StringBuilder(text);
            responseText.append("\n");
            responseText.append("result\t");
            responseText.append(calculator.calculate(requestText[1]));
            return new ResponseEntity<>(responseText,HttpStatus.OK);
        }
        else if(contentType.equals("application/json")) {
            try {

                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> map;
                map = mapper.readValue(text, new TypeReference<Map<String, String>>(){});
                map.put("result",calculator.calculate(map.get("equation")));
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json");
                return new ResponseEntity<>(new JSONObject(map).toString(),headers,HttpStatus.OK);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>("No valid Content-Type",HttpStatus.BAD_REQUEST);
    }

}

