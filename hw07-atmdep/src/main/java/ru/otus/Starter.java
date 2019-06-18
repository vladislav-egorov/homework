package ru.otus;

import ru.otus.atm.AtmCommander;
import ru.otus.atm.AtmImpl;
import ru.otus.atm.Department;
import ru.otus.command.AtmCommandReset;
import ru.otus.exeptions.ImpossibleExtraditeException;

import java.util.List;

public class Starter {
    public static void main(String[] args) throws ImpossibleExtraditeException {
        var atm = new AtmImpl(List.of(100, 100, 2000, 5000, 1000, 100));

        AtmCommander atmCommander = new AtmCommander(atm);
        atmCommander.showStatus();
        atmCommander.replenish(List.of(2000, 100));
        atmCommander.showStatus();
        atmCommander.extradite(3200);
        atmCommander.showStatus();
        atmCommander.extradite(3215);
        atmCommander.showStatus();
        AtmCommandReset atmCommandReset = new AtmCommandReset(atm);
        atmCommandReset.execute();
        atmCommander.showStatus();

        var atm2 = new AtmImpl(List.of(5000, 5000, 500, 200, 100, 50, 50, 5000, 2000, 1000));
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
