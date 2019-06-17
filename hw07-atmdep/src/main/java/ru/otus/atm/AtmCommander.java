package ru.otus.atm;

import ru.otus.exeptions.ImpossibleExtraditeException;
import ru.otus.momento.AtmCaretaker;

import java.util.List;

public class AtmCommander {
    private AtmImpl atm;

    public AtmCommander(AtmImpl atm) {
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
            throw new ImpossibleExtraditeException("Impossible minus sum calculation for sum " + amount + " money bundle: " + this);
        }
    }

    public void replenish(List<Integer> banknotes) {
        System.out.println("Trying to replenish: " + banknotes);
        AtmCaretaker caretaker = new AtmCaretaker(atm.saveState());
        try {
            atm.getMoneyBundle().concat(new MoneyBundle(banknotes));
            System.out.println("Replenish done!");
        } catch (Exception e) {
            atm.restoreState(caretaker.getAtmMomento());
            throw new ImpossibleExtraditeException("Invalid sum");
        }
    }

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

    public void showStatus() {
        System.out.println("Current status = " + atm.getMoneyBundle());
    }
}
