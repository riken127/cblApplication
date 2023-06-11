/*
 * Nome: <João Pedro Salgado Pereira>
 * Número: <8220102>
 * Turma: <LEI1T4>
 *
 * Nome: <José Henrique Noronha Oliveira e Silva>
 * Número: <8220343>
 * Turma: <LEI1T4>
 */
package programacbl.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {
    /**
     * Reads an integer input from the user within a specified range.
     * @param message the message to display to user as a prompt
     * @param min the minimum allowed value for the input.
     * @param max the maximum allowed value for the input
     * @return the users input as an integer within the specified range.
     */
    public static int menuRead(String message, int min, int max) {
        int option;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
