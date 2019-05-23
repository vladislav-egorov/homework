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
        this.moneyBundle.concat(moneyBundle);
    }

    public void replenish(List<Integer> banknoteList) {
        replenish(new MoneyBundle(banknoteList));
    }

    public void extradite(int amount) {
        moneyBundle.minusSum(amount);
    }

    public void showStatus() {
        System.out.println("Current status = " + moneyBundle);
    }

    @Override
    public String toString() {
        return moneyBundle.toString();
    }
}
