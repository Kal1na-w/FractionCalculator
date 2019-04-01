package ua.od.atomspace.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.od.atomspace.Calculator.service.FractionOperations.calculate.Calculator;

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
}

