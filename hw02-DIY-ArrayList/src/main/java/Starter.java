import java.util.ArrayList;
import java.util.Collections;

public class Starter {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add((int) (Math.random() * 49 + 1));
        }
        System.out.println("Source arrayList:\n" + arrayList);

        DIYArrayList<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.addAll(arrayList);
        System.out.println("My array with elements:\n" + diyArrayList);

        Collections.sort(diyArrayList);
        System.out.println("My sorted array:\n" + diyArrayList);

        DIYArrayList<Integer> copyList = new DIYArrayList<>();
        for (int i = 0; i < 30; i++) {
            copyList.add((int) (Math.random() * 49 + 1));
        }
        Collections.copy(copyList, diyArrayList);
        System.out.println("Copy of array:\n" + copyList);

    }
}
