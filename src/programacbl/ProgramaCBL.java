/*
 * Nome: <João Pedro Salgado Pereira>
 * Número: <8220102>
 * Turma: <LEI1T4>
 *
 * Nome: <José Henrique Noronha Oliveira e Silva>
 * Número: <8220343>
 * Turma: <LEI1T4>
 */
package programacbl;

import java.io.*;

import ma02_resources.participants.InstituitionType;
import participants.ContactImpl;
import project.CblManagement;


public class ProgramaCBL {

    public static int menuRead(BufferedReader reader, String message, int min, int max) {
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

    public static void cblStats(BufferedReader reader) throws IOException{
        int option;
        do{
            option = menuRead(reader, "Cbl Statistic Menu:\n 1 - Get Edition With Projects Missing Submissions\n " +
                    "2 - Get Projects With Missing Submissions\n 3 - Get Number Of Editions\n 4 - Get Number Of Projects in Edition" +
                    "\n 5 - Get Edition Progress\n 6 - Get Project Progress in Edition\n 7 - List Edition Information" +
                    "\n 8 - List Project Information By Edition\n 9 - List Project Status By Edition \nOption: ", 0, 9);
            switch(option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    editionManagementMenu(reader);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }
        } while (option != 0);
    }

    public static void participantManagementMenu(BufferedReader reader) throws IOException{
        int option;
        do{
            option = menuRead(reader, "Participant Menu:\n 1 - Nao sei oq por\n Option: ", 0, 1);
            switch(option){
                case 1:
                    break;
            }
        } while (option != 0);
    }

    public static void tasksManagementMenu(BufferedReader reader) throws IOException{
        int option;
        do{
            option = menuRead(reader, "Task Menu:\n 1 - Add Submission\n 2 - Extend DeadLine\n Option: ", 0, 2);
            switch(option){
                case 1:
                    break;
                case 2:
                    break;
            }
        } while (option != 0);
    }

    public static void projectManagementMenu(BufferedReader reader) throws IOException{
        int option;
        do{
            option = menuRead(reader, "Project Menu:\n 1 - Add Participant\n 2 - Remove Participant\n 3 - Get Participant\n" +
                    " 4 - Get Number Of Participants  \n 5 - Add Tag  \n 6 - Add Task \n 7 - Get Task \n" +
                    " 8 - Is Completed?\n Option: ", 0, 8);
            switch(option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    participantManagementMenu(reader);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    tasksManagementMenu(reader);
                    break;
                case 8:
                    break;
            }
        } while (option != 0);
    }
    public static void editionManagementMenu(BufferedReader reader) throws IOException{
        int option;
        do{
            option = menuRead(reader, "Edition Menu:\n 1 - Add Project\n 2 - Remove Project\n 3 - Get Project\n 4 - Get Projects By Tag " +
                    "\n 5 - Get Projects By Email \n 6 - Get Number Of Projects \n 7 - Get End\n Option: ", 0, 7);
            switch(option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    projectManagementMenu(reader);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        } while (option != 0);
    }

    public static void cblManagementMenu(BufferedReader reader, CblManagement Cbl) throws IOException{
        int option;
        do{
            option = menuRead(reader, "Cbl Menu:\n 1 - Add Edition\n 2 - Remove Edition\n 3 - Get Edition\n 4 - Set Active Edition" +
                    "\n 5 - Add Project to Edition\n 6 - Add Submission to Project\n 7 - Cbl Stats\n Option: ", 0, 7);
            switch(option){
                case 1:
                    //Cbl.addEdition();
                    break;
                case 2:
                    //Cbl.removeEdition();
                    break;
                case 3:
                    //editionManagementMenu(reader);
                    break;
                case 4:
                    //Cbl.setActiveEdition();
                    break;
                case 5:
                    //Cbl.addProjectToEdition();
                    break;
                case 6:
                    //Cbl.addProjectSubmissionToActiveEdition();
                    break;
                case 7:
                    cblStats(reader);
                    break;
            }
        } while (option != 0);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CblManagement Cbl = new CblManagement();

        cblManagementMenu(reader, Cbl);
    }
}
