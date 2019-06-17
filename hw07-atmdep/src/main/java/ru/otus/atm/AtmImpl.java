package ru.otus.atm;

import ru.otus.momento.AtmMomento;

import java.util.List;

public class AtmImpl implements Atm {
    private final AtmMomento atmMomento;
    private MoneyBundle moneyBundle;

    public AtmImpl(MoneyBundle moneyBundle) {
        this.moneyBundle = moneyBundle;
        this.atmMomento = new AtmMomento(new MoneyBundle(moneyBundle));
    }

    public AtmImpl(List<Integer> banknoteList) {
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
