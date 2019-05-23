import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoneyBundle {
    private final Map<Banknote, Integer> banknotesMap;
    private long totalCost;

    public MoneyBundle(List<Integer> banknotes) {
        banknotesMap = fromList(banknotes);
        calcTotalCost();
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

    public void calcTotalCost() {
        long totalCost = 0;
        for (var entry : banknotesMap.entrySet()) {
            totalCost = totalCost + entry.getKey().getNominalValue() * entry.getValue();
        }
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return banknotesMap.toString() + "\nTotal Cost: " + totalCost;
    }

    public Map<Banknote, Integer> getBanknotesMap() {
        return banknotesMap;
    }
}
