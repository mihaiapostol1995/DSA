package cognyte;

public class ObjectToString {
    public static void main(String[] args) {
        Object shortNumber = (short) 1;
        Object intNumber = (int) 1;
        //System.out.println(Byte.valueOf(shortNumber.toString()));

        byte byteValue = Byte.valueOf(shortNumber.toString());

        byte intValue = Byte.valueOf(intNumber.toString());
    }
}
