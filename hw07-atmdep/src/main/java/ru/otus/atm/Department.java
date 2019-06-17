package ru.otus.atm;

import java.util.HashMap;
import java.util.Map;

public class Department {
    private Map<String, AtmImpl> atms = new HashMap<>();

    public void addAtm(String atmName, AtmImpl atm) {
        atms.put(atmName, atm);
    }

    public void removeAtm(String atmName) {
        atms.remove(atmName);
    }

    public AtmImpl getAtm(String atmName) {
        return atms.get(atmName);
    }

    public void resetAtms() {
        atms.forEach((name, atm) -> {
            System.out.println("ATM " + name + " reset");
            atm.reset();
        });
    }

    public double getCommonBalance() {
        int sum = 0;
        for (Map.Entry<String, AtmImpl> entry : atms.entrySet()) {
            sum += entry.getValue().getBalance();
        }
        return sum;
    }

}
