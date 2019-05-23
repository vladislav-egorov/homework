import java.util.ArrayList;

public class Atm {
    private MoneyBundle moneyBundle;

    public Atm() {
        this.moneyBundle = new MoneyBundle(new ArrayList<>());
    }

    public Atm(MoneyBundle moneyBundle) {
        this.moneyBundle = moneyBundle;
    }

    public void replenish(MoneyBundle moneyBundle) {
        this.moneyBundle.concat(moneyBundle);
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
