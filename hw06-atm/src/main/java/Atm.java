import java.util.ArrayList;
import java.util.List;

public class Atm {
    private MoneyBundle moneyBundle;

    public Atm() {
        this.moneyBundle = new MoneyBundle(new ArrayList<>());
    }

    public Atm(MoneyBundle moneyBundle) {
        this.moneyBundle = moneyBundle;
    }

    public Atm(List<Integer> banknoteList) {
        this(new MoneyBundle(banknoteList));
    }

    public void replenish(MoneyBundle moneyBundle) {
        concat(moneyBundle);
    }

    public void replenish(List<Integer> banknoteList) {
        replenish(new MoneyBundle(banknoteList));
    }

    public void extradite(int amount) {
        if (amount == 0) {
            return;
        }
        var banknote = getMaxValueBanknoteForAmount(amount);
        var maxValue = banknote.getNominalValue();
        if (amount >= maxValue) {
            minusBanknote(banknote);
            extradite(amount - maxValue);
        } else {
            throw new RuntimeException("Impossible minus sum calculation for sum " + amount + " money bundle: " + this);
        }
    }

    public void showStatus() {
        System.out.println("Current status = " + moneyBundle);
    }

    private Banknote getMaxValueBanknoteForAmount(int amount) {
        final Banknote[] maxBanknote = {Banknote.FIFTY};
        moneyBundle.getBanknotesMap().forEach(
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

    private void concat(MoneyBundle moneyBundle) {
        moneyBundle.getBanknotesMap().forEach(
                (banknote, count) -> {
                    var totalCount = this.moneyBundle.getBanknotesMap().get(banknote) + count;
                    this.moneyBundle.getBanknotesMap().put(banknote, totalCount);
                }
        );
        this.moneyBundle.calcTotalCost();
    }

    private void minusBanknote(Banknote targetBanknote) {
        moneyBundle.getBanknotesMap().forEach(
                (banknote, count) -> {
                    if (banknote.equals(targetBanknote)) {
                        if (count > 0) {
                            moneyBundle.getBanknotesMap().put(banknote, count - 1);
                        } else {
                            throw new RuntimeException("Empty banknote slot: " + banknote);
                        }
                    }
                }
        );
        moneyBundle.calcTotalCost();
    }

    @Override
    public String toString() {
        return moneyBundle.toString();
    }
}
