import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyBundle {
    private final Map<Banknote, Integer> banknotesMap;
    private long totalCost;

    public MoneyBundle(List<Integer> banknotes) {
        banknotesMap = fromList(banknotes);
        totalCost = calcTotalCost();
    }

    private Map<Banknote, Integer> fromList(List<Integer> banknotes) {
        Map<Banknote, Integer> map = new HashMap<>();
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

    private long calcTotalCost() {
        long totalCost = 0;
        for (var entry : banknotesMap.entrySet()) {
            totalCost = totalCost + entry.getKey().getNominalValue() * entry.getValue();
        }
        return totalCost;
    }

    public void concat(MoneyBundle moneyBundle) {
        moneyBundle.banknotesMap.forEach(
                (banknote, count) -> {
                    var totalCount = this.banknotesMap.get(banknote) + count;
                    this.banknotesMap.put(banknote, totalCount);
                }
        );
        totalCost = calcTotalCost();
    }

    public void minusSum(int sum) {
        if (sum == 0) {
            return;
        }
        var banknote = getMaxValueBanknoteForAmount(sum);
        var maxValue = banknote.getNominalValue();
        if (sum >= maxValue) {
            minusBanknote(banknote);
            minusSum(sum - maxValue);
        } else {
            throw new RuntimeException("Impossible minus sum calculation for sum " + sum + " money bundle: " + this);
        }
    }

    private void minusBanknote(Banknote targetBanknote) {
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
        totalCost = calcTotalCost();
    }

    private Banknote getMaxValueBanknoteForAmount(int amount) {
        final Banknote[] maxBanknote = {Banknote.FIFTY};
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

    @Override
    public String toString() {
        return banknotesMap.toString() + "\nTotal Cost: " + totalCost;
    }
}
