package com.example.HealthInsurance.service;

import com.example.HealthInsurance.Common.Constants;
import com.example.HealthInsurance.Repository.PremiumRepository;
import com.example.HealthInsurance.Util.HealthUtil;
import com.example.HealthInsurance.model.Premium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumService {
    @Autowired
    private HealthUtil util;

    @Autowired
    private PremiumRepository premiumRepository;

    public double getPremiumAmount(Premium premium) {
        double amountToBePaid = 0;
        // condition based on age
        amountToBePaid = totalPercentByAgeLimit(premium.getAge());
        // condition based on gender
        amountToBePaid = amountByGender(premium.getGender(), amountToBePaid);
        // condition based on Health and Habits
        amountToBePaid = getCostForHealthStatus(premium, amountToBePaid);
        return amountToBePaid;
    }

    private double totalPercentByAgeLimit(int age) {
        double amount = 0;
        amount = age <= 40 ? util.calculateAmount(age) : util
                .calculateAmount(age) + (util.calculateAmount(age) * 0.2);
        return amount;
    }

    private double amountByGender(String gender, double amount) {
        double amountByAge = 0;
        if (gender.equalsIgnoreCase(Constants.MALE)
                || gender.equalsIgnoreCase(Constants.FEMALE)) {
            amountByAge = amount + (amount * 0.02);
        }
        return amountByAge;
    }

    private double getCostForHealthStatus(Premium premium, double amount) {
        if (premium.getBloodPressure().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.01);
        }
        if (premium.getBloodSugar().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.01);
        }
        if (premium.getHypertension().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.01);
        }
        if (premium.getOverWeight().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.01);
        }
        if (premium.getAlcohol().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.03);
        }
        if (premium.getDrugs().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.03);
        }
        if (premium.getSmoking().equalsIgnoreCase(Constants.YES)) {
            amount = amount + (amount * 0.03);
        }
        if (premium.getDailyExercise().equalsIgnoreCase(Constants.YES)) {
            amount = amount - (amount * 0.03);
        }
        return amount;
    }


    public void addPremium(Premium premium){

       premiumRepository.save(premium);

}
    public Premium findByName(String name){
        Premium premium=premiumRepository.findByName(name);
         return premium;
    }



}
