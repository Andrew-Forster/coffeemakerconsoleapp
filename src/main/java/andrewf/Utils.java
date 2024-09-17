package andrewf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Utils {

    // Colors
    private static HashMap<String, String> colors = new HashMap<String, String>();

    static {
        // Colors
        colors.put("red", "\u001B[31m");
        colors.put("green", "\u001B[32m");
        colors.put("yellow", "\u001B[33m");
        colors.put("blue", "\u001B[34m");
        colors.put("magenta", "\u001B[35m");
        colors.put("cyan", "\u001B[36m");
        colors.put("black", "\u001B[30m");
        colors.put("white", "\u001B[37m");
        colors.put("reset", "\u001B[0m");
    }

    /**
     * Color a string
     * 
     * @param color red, green, yellow, blue, magenta, cyan, black, white, reset
     * @param str   string to color
     * @return colored string
     */
    public static String colorString(String color, String str) {
        return colors.get(color) + str + colors.get("reset");
    }

    /**
     * Fail message
     * @param msg
     * @return colored fail message
     */
    public static String fail(String msg) {
        return colorString("red", "\nX: " + msg);
    }

    /**
     * Info message
     * @param msg
     * @return colored info message
     */
    public static String info(String msg) {
        return colorString("blue", "\n■ " + msg);
    }

    /**
     * Warn message
     * @param msg
     * @return colored warn message
     */
    public static String warn(String msg) {
        return colorString("yellow", "\n¡!¡ " + msg);
    }

    /**
     * Success message
     * @param msg
     * @return colored success message
     */
    public static String success(String msg) {
        return colorString("green", "\n√: " + msg);
    }

    /**
     * Validate a double
     * @param input double to validate
     * @return validated double
     */
    public static Double validateDouble(String input) {
        while (true) {
            try {
                Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println(fail("Invalid input, please enter a number"));
                input = System.console().readLine();
            }
        }
        return Double.parseDouble(input);
    }

    /**
     * Validate an integer
     * @param input integer to validate
     * @return validated integer
     */
    public static int validateInt(String input) {
        while (true) {
            try {
                Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println(fail("Invalid input, please enter a number"));
                input = System.console().readLine();
            }
        }
        return Integer.parseInt(input);
    }
    /**
     * Validate a string
     * @param input string to validate
     * @return validated string
     */
    public static String validateString(String input) {
        while (true) {
            if (input.length() == 0 || input.isBlank() || input.isEmpty()) {
                System.out.println(fail("Invalid input, please enter a string"));
                input = System.console().readLine();
            } else {
                break;
            }
        }
        return input;
    }

    /**
     * Round a double to a specific decimal place
     * @param v double to round
     * @param p decimal place
     * @return rounded double
     */
    public static double round(double v, int p) {
        if (p < 0)
            throw new IllegalArgumentException("You can't round to a negative decimal place bro");

        BigDecimal bd = BigDecimal.valueOf(v);
        bd = bd.setScale(p, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Clear the console
     */
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Display the coffee maker control panel
     * @param coffeeMaker coffee maker object
     * @param input user input
     */
    public static void displayOptions(CoffeeMaker coffeeMaker, String input) {
        clear();
        System.out.println("Coffee Maker Control Panel!\n\n"
        + coffeeMaker.coffeeMakerInfo() 
        + "Please select an option below to begin to brew your coffee!\n"
        + "-----------------------------------\n"
        + "1. Fill water tank\n"
        + "2. Load filter\n"
        + "3. Add grounds\n"
        + "4. Turn on coffee maker & heat\n"
        + "5. Brew coffee\n"
        + "6. Turn off coffee maker\n"
        + "7. Empty filter\n"
        + "(E). Exit\n"
        +"-----------------------------------"
        + "\nYour selection: " + input + ""
        );
    }

    /**
     * Display the burnt house
     */
    public static void burntHouseArt() {
        System.out.println(colorString("red", "                    (          )"));
        System.out.println(colorString("red", "                 (      *   *   )"));
        System.out.println(colorString("red", "                  (   *  *  *  )"));
        System.out.println(colorString("red", "                 (     * * *    )"));
        System.out.println(colorString("red", "                 (  *  *  *    )"));
        System.out.println(colorString("red", "                (  *   *   *   )"));
        System.out.println(colorString("white", "               |\\_ ") + colorString("red", "  *  *  *") + colorString("white", "   _/|"));
        System.out.println(colorString("white", "               |   \\__________/   |"));
        System.out.println(colorString("white", "               |  /__________\\  |"));
        System.out.println(colorString("white", "               |  |  |    |  |  |"));
        System.out.println(colorString("white", "               |  |  |    |  |  |"));
        System.out.println(colorString("white", "               |__|__|____|__|__|"));
        System.out.println(colorString("white", "               |__|__|__|__|__|__|"));
        System.out.println(colorString("white", "               |__|__|__|__|__|__|"));
    }
}
