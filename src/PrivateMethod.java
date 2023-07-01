import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrivateMethod {
        void callMe(){

        }

        public static void main(String[] args) {
                ArrayList<String> stringList = new ArrayList<>();

                stringList.add("hi");

                ArrayList<String> stringList1 = (ArrayList<String>) stringList.clone();

                //stringList.add("hello");
                stringList1.set(0, "hello");
                stringList.add("bro");
                stringList1.forEach(System.out::println);

                LinkedList<String> linkedList = new LinkedList<>();
        }
}
