package resources.config.properties;

import java.util.HashMap;
import java.util.Map;

public class UIConstants {
    private static final String BASE_ADDRESS = "src/resources/assets/";

    private static final Map<String, String> colorAddresses = new HashMap<>();
    private static final Map<Integer, String> colorTitles = new HashMap<>();

    static {
        colorAddresses.put("Black", BASE_ADDRESS + "planeBlack.png");
        colorAddresses.put("Blue", BASE_ADDRESS + "planeBlue.png");
        colorAddresses.put("Brown", BASE_ADDRESS + "planeBrown.png");
        colorAddresses.put("Gray", BASE_ADDRESS + "planeGray.png");
        colorAddresses.put("Green", BASE_ADDRESS + "planeGreen.png");
        colorAddresses.put("Orange", BASE_ADDRESS + "planeOrange.png");
        colorAddresses.put("Purple", BASE_ADDRESS + "planePurple.png");
        colorAddresses.put("Red", BASE_ADDRESS + "planeRed.png");

        colorTitles.put(0, "Black");
        colorTitles.put(1, "Blue");
        colorTitles.put(2, "Brown");
        colorTitles.put(3, "Gray");
        colorTitles.put(4, "Green");
        colorTitles.put(5, "Orange");
        colorTitles.put(6, "Purple");
        colorTitles.put(7, "Red");
    }

    public static String getPlaneAddress(String color) {
        return colorAddresses.get(color);
    }

    public static String getPlaneTitle(int color) {
        return colorTitles.get(color);
    }
}
