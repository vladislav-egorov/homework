package ru.otus.classes;

import ru.otus.enums.Banknote;

import java.util.*;

public class MoneyBundle {
    private final TreeMap<Banknote, Integer> banknotesMap;

    public MoneyBundle(List<Integer> banknotes) {
        banknotesMap = fromList(banknotes);
    }

    public MoneyBundle(MoneyBundle moneyBundle) {
        this.banknotesMap = (TreeMap<Banknote, Integer>) moneyBundle.banknotesMap.clone();
    }

    private TreeMap<Banknote, Integer> fromList(List<Integer> banknotes) {
        TreeMap<Banknote, Integer> map = new TreeMap<>(Comparator.comparingInt(Banknote::getNominalValue));
        Arrays.stream(Banknote.values()).forEach(
                banknote -> map.put(banknote, 0)
        );
        banknotes.forEach(
                banknote -> {
                    var currentBanknote = Banknote.of(banknote);
                    if (currentBanknote == null) {
                        throw new RuntimeException("Invalid banknote format: " + banknote);
                    }
                    var banknotesCount = map.get(currentBanknote);
                    map.put(currentBanknote, banknotesCount + 1);
                }
        );
        return map;
    }

    public double calcTotalCost() {
        long totalCost = 0;
        for (var entry : banknotesMap.entrySet()) {
            totalCost = totalCost + entry.getKey().getNominalValue() * entry.getValue();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return banknotesMap.toString() + "\nTotal Cost: " + calcTotalCost();
    }

    public Map<Banknote, Integer> getBanknotesMap() {
        return banknotesMap;
    }

    public Banknote getMinBanknote() {
        for (var entry : banknotesMap.entrySet()) {
            if (entry.getValue() > 1) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Banknote getMaxValueBanknoteForAmount(double amount) {
        final Banknote[] maxBanknote = {getMinBanknote()};
        banknotesMap.forEach(
                (banknote, value) -> {
                    var nominalValue = banknote.getNominalValue();
                    if (value > 0 &&
                            nominalValue > maxBanknote[0].getNominalValue() &&
                            nominalValue <= amount) {
                        maxBanknote[0] = banknote;
                    }
                }
        );
        return maxBanknote[0];
    }

    public void minusBanknote(Banknote targetBanknote) {
        banknotesMap.forEach(
                (banknote, count) -> {
                    if (banknote.equals(targetBanknote)) {
                        if (count > 0) {
                            banknotesMap.put(banknote, count - 1);
                        } else {
                            throw new RuntimeException("Empty banknote slot: " + banknote);
                        }
                    }
                }
        );
    }

    public void concat(MoneyBundle moneyBundle) {
        moneyBundle.banknotesMap.forEach(
                (banknote, count) -> {
                    var totalCount = this.banknotesMap.get(banknote) + count;
                    this.banknotesMap.put(banknote, totalCount);
                }
        );
    }
}
