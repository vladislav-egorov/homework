package ru.otus;

import ru.otus.classes.Atm;
import ru.otus.classes.AtmCommander;
import ru.otus.classes.Department;

import java.util.List;

public class Starter {
    public static void main(String[] args) {
        var atm = new Atm(List.of(100, 100, 2000, 5000, 1000, 100));

        AtmCommander atmCommander = new AtmCommander(atm);
        atmCommander.showStatus();
        atmCommander.replenish(List.of(2000, 100));
        atmCommander.showStatus();
        atmCommander.extradite(3200);
        atmCommander.showStatus();
        atmCommander.extradite(3215);
        atmCommander.showStatus();
        atmCommander.resetStatus();
        atmCommander.showStatus();

        var atm2 = new Atm(List.of(5000, 5000, 500, 200, 100, 50, 50, 5000, 2000, 1000));
        AtmCommander atmCommander2 = new AtmCommander(atm2);
        atmCommander2.showStatus();
        atmCommander2.replenish(List.of(5000, 500));
        atmCommander2.showStatus();

        Department department = new Department();
        department.addAtm("VTB24", atm);
        department.addAtm("SBER", atm2);
        System.out.println("Balance of department = " + department.getCommonBalance());
        department.resetAtms();
        System.out.println("Balance of department = " + department.getCommonBalance());
    }
}
