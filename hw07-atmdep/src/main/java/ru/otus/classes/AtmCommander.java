package ru.otus.classes;

import ru.otus.interfaces.IAtmCommand;
import ru.otus.momento.AtmCaretaker;

import java.util.List;

public class AtmCommander implements IAtmCommand {
    private Atm atm;

    public AtmCommander(Atm atm) {
        this.atm = atm;
    }

    public void minusSum(double amount) {
        if (amount == 0) {
            return;
        }
        var banknote = atm.getMoneyBundle().getMaxValueBanknoteForAmount(amount);
        var maxValue = banknote.getNominalValue();
        if (amount >= maxValue) {
            atm.getMoneyBundle().minusBanknote(banknote);
            minusSum(amount - maxValue);
        } else {
            throw new RuntimeException("Impossible minus sum calculation for sum " + amount + " money bundle: " + this);
        }
    }

    @Override
    public void replenish(List<Integer> banknotes) {
        System.out.println("Trying to replenish: " + banknotes);
        AtmCaretaker caretaker = new AtmCaretaker(atm.saveState());
        try {
            atm.getMoneyBundle().concat(new MoneyBundle(banknotes));
            System.out.println("Replenish done!");
        } catch (Exception e) {
            atm.restoreState(caretaker.getAtmMomento());
            throw new RuntimeException("Invalid sum");
        }
    }

    @Override
    public void extradite(double amount) {
        System.out.println("Trying to extradite: " + amount);
        AtmCaretaker caretaker = new AtmCaretaker(atm.saveState());
        try {
            minusSum(amount);
            System.out.println("Extradite done!");
        } catch (Exception e) {
            System.out.println("Impossible minus sum calculation for sum " + amount);
            atm.restoreState(caretaker.getAtmMomento());
        }
    }

    @Override
    public void showStatus() {
        System.out.println("Current status = " + atm.getMoneyBundle());
    }

    @Override
    public void resetStatus() {
        atm.reset();
    }
}
