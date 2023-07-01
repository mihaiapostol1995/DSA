public class SquareIds {

    public static void main(String[] args) {
        double coordinateX = 277.75263;
        double coordinateY = -277.1548231;

        long retValue =
                (long)Math.floor((coordinateX+180.0)/8.333333333333333E-4)*216000+
                        (long)Math.floor((coordinateY+90.0)/8.333333333333333E-4);

        System.out.println(retValue);
        //System.out.println(Math.floor((coordinateX+180.0)/0.000833));

        new RuntimeException().printStackTrace();
    }
}
