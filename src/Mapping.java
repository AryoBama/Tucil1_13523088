package src;

import java.util.List;

public class Mapping {
    public static List<String> colors = List.of(
        "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m",
        "\u001B[35m", "\u001B[36m", "\u001B[91m", "\u001B[92m",
        "\u001B[93m", "\u001B[94m", "\u001B[95m", "\u001B[96m",
        "\u001B[41m", "\u001B[42m", "\u001B[43m", "\u001B[44m",
        "\u001B[45m", "\u001B[46m", "\u001B[101m", "\u001B[102m",
        "\u001B[103m", "\u001B[104m", "\u001B[105m", "\u001B[106m",
        "\u001B[41;93m", "\u001B[44;96m"
    );
    public static String RESET = "\u001B[0m";

    public static char[] alphabets = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String findColor(char alphabet){
        for (int i = 0; i < alphabets.length; i++ ){
            if (Character.toUpperCase(alphabet)== alphabets[i]){
                return colors.get(i);
            }
        }
        return RESET;
    }
}
