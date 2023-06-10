package programacbl.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {
    private static BufferedReader reader;
    Helper() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public static int menuRead(String message, int min, int max) {
        int option;
        do {
            System.out.print(message);
            try {
                String input = reader.readLine();
                if (input.equals("0")) {
                    return 0;
                }
                option = Integer.parseInt(input);
                if (option < min || option > max) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler a opção. Tente novamente.");
                option = -1;
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Tente novamente.");
                option = -1;
            }
        } while (option < min || option > max);

        return option;
    }

}
