package ru.otus.atm;

import java.util.Arrays;

public enum Banknote {
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private int nominalValue;

    Banknote(int nominalValue) {
        this.nominalValue = nominalValue;
    }

    public static Banknote of(int cost) {
        return Arrays.stream(values()).filter(banknote -> cost == banknote.nominalValue)
                .findFirst().orElse(null);
    }

    public int getNominalValue() {
        return nominalValue;
    }

    @Override
    public String toString() {
        return "banknote" + nominalValue;
    }
}
