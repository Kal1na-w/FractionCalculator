package ua.od.atomspace.Calculator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.od.atomspace.calculate.Calculator;

import java.util.Map;

@RestController
public class CalculatorController {
    private Calculator calculator = new Calculator();

    @PostMapping(path = "/calc",produces = "application/json")
    public Map<String,String> calculateExpression(@RequestBody Map<String,String> equation) {
        equation.put("result",calculator.calculate(equation.get("equation")));
        return equation;
    }
}

