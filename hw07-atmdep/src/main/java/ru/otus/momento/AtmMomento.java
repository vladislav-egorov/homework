package ru.otus.momento;

import ru.otus.atm.MoneyBundle;

public class AtmMomento {
    private MoneyBundle moneyBundle;

    public AtmMomento(MoneyBundle moneyBundle) {
        this.moneyBundle = moneyBundle;
    }

    public MoneyBundle getMoneyBundle() {
        return moneyBundle;
    }
}
