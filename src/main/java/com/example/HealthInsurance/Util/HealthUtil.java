package com.example.HealthInsurance.Util;

import com.example.HealthInsurance.Common.Constants;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class HealthUtil {

      private static Map<String,Integer> healthmap=new TreeMap<>();

       static {
           healthmap.put("18-25", 10);
           healthmap.put("25-30", 10);
           healthmap.put("30-35", 10);
           healthmap.put("35-40", 10);

       }

    public double calculateAmount(int enterAge) {
        double amount = Constants.MIN_PREMIUM_AMOUNT;
        int startAge = 0;
        int endAge = 0;
        double perc = 0;
        for (String age : healthmap.keySet()) {
            startAge = splitAgeLimit(age)[0];
            endAge = splitAgeLimit(age)[1];
            if ((enterAge >= startAge && enterAge <= endAge)
                    || (enterAge >= startAge && enterAge >= endAge)) {
                perc = (double) healthmap.get(age) / 100;
                amount = amount + (amount * perc);
            }
        }
        return amount;
    }

    private static int[] splitAgeLimit(String age) {
        String[] s = age.split("-");
        return new int[] { Integer.valueOf(s[0]), Integer.valueOf(s[1]) };
    }





}
