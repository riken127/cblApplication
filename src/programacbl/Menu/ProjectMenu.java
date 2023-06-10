package programacbl.Menu;

import ma02_resources.project.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectMenu {
    private void projectManagementMenu(Project project) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int option;
        do {
            option = Helper.menuRead("Project Menu:\n 1 - Add Participant\n 2 - Remove Participant\n 3 - Get Participant\n" +
                    " 4 - Get Number Of Participants  \n 5 - Add Task \n 6 - Get Task \n" +
                    " 7 - Is Completed?\n Option: ", 0, 8);
            switch (option) {
                case 1:
                    /**
                     * Existem vários objetos do tipo participant, como prosseguir?

                     project.addParticipant(new ParticipantImpl() {
                     }*/
                    break;
                case 2:
                    /**
                     * Listagem de todos os participantes no projeto currente.
                     */
                    System.out.println("Participant email: ");
                    String participantEmail = reader.readLine();
                    try {
                        project.removeParticipant(participantEmail);
                    } catch (Exception e) {
                        System.out.println("Email inválido.");
                    }
                    break;
                case 3:
                    //participantManagementMenu(reader);
                    break;
                case 4:
                    System.out.println(project.getNumberOfParticipants());
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    //tasksManagementMenu(cbl, reader);
                    break;
                case 8:
                    break;
            }
        } while (option != 0);
    }

}
