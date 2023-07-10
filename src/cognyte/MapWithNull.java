package cognyte;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapWithNull {

    public static void main(String[] args) {
        Values v1 = new Values("hi", "hi2");
        Values v2 = new Values("hi3", null);

        List<Values> valuesList = new ArrayList<>(); valuesList.add(v1);  valuesList.add(v2);
        Map<String, String> map = valuesList.stream()
                .collect(Collectors.toMap(Values::getValue1, Values::getValue2));
    }
}

class Values {
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    String value1;
    String value2;

    public Values(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}