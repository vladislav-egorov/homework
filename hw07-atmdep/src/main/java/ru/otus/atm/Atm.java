package ru.otus.atm;

import ru.otus.momento.AtmMomento;

public interface Atm {
    // observer pattern
    void reset();

    MoneyBundle getMoneyBundle();

    void restoreState(AtmMomento atmMomento);
}
