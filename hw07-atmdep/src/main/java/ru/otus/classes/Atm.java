package ru.otus.classes;

import ru.otus.interfaces.IAtm;
import ru.otus.momento.AtmMomento;

import java.util.List;

public class Atm implements IAtm {
    private final AtmMomento atmMomento;
    private MoneyBundle moneyBundle;

    public Atm(MoneyBundle moneyBundle) {
        this.moneyBundle = moneyBundle;
        this.atmMomento = new AtmMomento(new MoneyBundle(moneyBundle));
    }

    public Atm(List<Integer> banknoteList) {
        this(new MoneyBundle(banknoteList));
    }

    public MoneyBundle getMoneyBundle() {
        return moneyBundle;
    }

    public double getBalance() {
        return moneyBundle.calcTotalCost();
    }

    //momento pattern
    public AtmMomento saveState() {
        return new AtmMomento(moneyBundle);
    }

    public void restoreState(AtmMomento atmMomento) {
        this.moneyBundle = atmMomento.getMoneyBundle();
    }

    @Override
    public void reset() {
        this.moneyBundle = atmMomento.getMoneyBundle();
    }
}
