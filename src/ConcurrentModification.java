import java.util.ArrayList;
import java.util.List;

public class ConcurrentModification {
    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("apple"));
        fruits.add(new Fruit("pear"));
        fruits.add(new Fruit("mango"));

        for (Fruit fruit : fruits) {
            fruits.removeIf(fruit::equals);
        }

        fruits.forEach(f-> System.out.println("fruit is: +" + f));
    }
}

class Fruit {
    String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }
}
