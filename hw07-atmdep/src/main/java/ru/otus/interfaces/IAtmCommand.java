package ru.otus.interfaces;

import java.util.List;

public interface IAtmCommand {

    //command pattern
    void replenish(List<Integer> banknotes);

    void extradite(double amount);

    void showStatus();

    void resetStatus();
}
