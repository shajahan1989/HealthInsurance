package com.example.HealthInsurance.controller;

import com.example.HealthInsurance.model.Premium;
import com.example.HealthInsurance.model.ResponseAmount;
import com.example.HealthInsurance.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InsuranceController {

    private ResponseAmount response;

    @Autowired
    private PremiumService premiumService;

    @GetMapping("/welcome")
    public String Welcome(){
        return "Welcome1";
    }

    @PostMapping("/addPremium")
    public void addPremium(@RequestBody Premium premium){
        premiumService.addPremium(premium);
    }
     @GetMapping("/premium/{name}")
     public ResponseAmount getPremium(@PathVariable("name") String name){
        double totalPremiumAmount=0;
         Premium premium=premiumService.findByName(name);
         totalPremiumAmount = premiumService.getPremiumAmount(premium);
         response.setAmount(totalPremiumAmount);
        // response.setName(premium.getName());
         return response;





     }



}
