package ua.od.atomspace.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.od.atomspace.Calculator.service.FractionOperations.calculate.Calculator;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    private final Calculator calculator;

    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping(path = "/calc",produces = "application/json")
    public Map<String,String> calculateExpression(@RequestBody Map<String,String> equation) {
        equation.put("result",calculator.calculate(equation.get("equation")));
        return equation;
    }

    @RequestMapping(value = "/calculate", produces = MediaType.TEXT_PLAIN_VALUE)
    public String plainTextAnnotation(@RequestBody String text) {
        String[] requestText = text.split("=");
        StringBuilder responseText = new StringBuilder();
        responseText.append(text);
        responseText.append("&");
        responseText.append("result=");
        responseText.append(calculator.calculate(requestText[1]));
        return responseText.toString();
    }

}

