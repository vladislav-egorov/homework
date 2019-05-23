import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) {
        List<Integer> banknotes = new ArrayList<>();
        banknotes.add(100);
        banknotes.add(100);
        banknotes.add(2000);
        banknotes.add(5000);
        banknotes.add(1000);
        List<Integer> banknotes2 = new ArrayList<>();
        banknotes2.add(2000);
        banknotes2.add(100);

        var atm = new Atm(banknotes);
        atm.showStatus();
        atm.replenish(banknotes2);
        atm.showStatus();
        atm.extradite(3200);
        atm.showStatus();
    }
}
