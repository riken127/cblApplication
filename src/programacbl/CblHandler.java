package programacbl;

import exceptions.EditionAlreadyInListException;
import exceptions.InvalidIndexException;
import exceptions.InvalidProjectNameException;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import participants.StudentImpl;
import project.CblManagement;
import project.EditionImpl;
import project.SubmissionImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CblHandler {
    CblManagement cbl;
    BufferedReader reader;

    public CblHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.cbl = new CblManagement();
    }

    public int menuRead(String message, int min, int max) {
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

    private void cblStats() throws IOException {
        int option;
        do {
            option = menuRead("Cbl Statistic Menu:\n 1 - Get Edition With Projects Missing Submissions\n " +
                    "2 - Get Projects With Missing Submissions\n 3 - Get Number Of Projects in Edition" +
                    "\n 4 - Get Edition Progress\n 5 - Get Project Progress in Edition\n 6 - List Edition Information" +
                    "\n 7 - List Project Information By Edition\n 8 - List Project Status By Edition \nOption: ", 0, 9);
            switch (option) {
                case 1:
                    // como devo utilizar o getEditionWIthProjectsMissingSubmissions?
                    break;
                case 2:
                    // mesma situação que case 1.
                    break;
                case 3:
                    /*
                        Listar todas as edições.
                     */
                    try {
                        System.out.println("Edition nº: ");
                        String index = reader.readLine();
                        int editionIndex = Integer.parseInt(index);

                        System.out.println(cbl.returnNumberOfProjects(editionIndex));
                    } catch (InvalidIndexException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 4:
                    /*
                        Listar todas as edições.
                     */
                    try {
                        System.out.println("Edition nº: ");
                        String stringIndex = reader.readLine();
                        int intIndex = Integer.parseInt(stringIndex);

                        System.out.println(cbl.returnEditionProgress(intIndex));
                    } catch (InvalidIndexException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    /*
                        Listar todas as edições.
                     */
                    try {
                        System.out.println("Edition nº: ");
                        String indexString = reader.readLine();
                        int editionPosition = Integer.parseInt(indexString);
                        if (cbl.returnEdition(editionPosition) != null) {
                            /*
                                Listar todos os projetos da edição (index).
                             */

                            System.out.println("Project name: ");
                            String projectName = reader.readLine();
                            System.out.println(cbl.returnProjectProgress(editionPosition, projectName));
                        }
                    } catch (InvalidIndexException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 6:
                    System.out.println(cbl.listEditionInformation());
                    break;
                case 7:
                    System.out.println(cbl.listProjectInformationByEdition());
                    break;
                case 8:
                    System.out.println(cbl.listProjectStatusByEdition());
                    break;
            }
        } while (option != 0);
    }

    private void tasksManagementMenu(CblManagement cbl, BufferedReader reader) throws IOException {
        int option;
        do {
            option = menuRead("Task Menu:\n 1 - Add Submission\n 2 - Extend DeadLine\n Option: ", 0, 2);
            switch (option) {
                case 1:
                    /**
                     * List all tasks
                     */
                    break;
                case 2:
                    /**
                     * List all tasks
                     */
                    break;
            }
        } while (option != 0);
    }

    private void projectManagementMenu(Project project) throws IOException {
        int option;
        do {
            option = menuRead("Project Menu:\n 1 - Add Participant\n 2 - Remove Participant\n 3 - Get Participant\n" +
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

    private void addProjectHandler(int editionIndex) throws IOException {
        //Listagem de todas as edições disponíveis (por número index).
        System.out.println("Select the Edition");
        String selectedEdition = reader.readLine();
        int indexEdition = Integer.parseInt(selectedEdition);
        if (cbl.returnEdition(indexEdition) != null) {
            System.out.println("Type the given project name:");
            String name = reader.readLine();
            System.out.println("Type the given project description:");
            String description = reader.readLine();
            System.out.println("Type the given project tags (separate) by white spaces:");
            String tagsString = reader.readLine();
            String[] tags = tagsString.split(" ");
            try {
                cbl.addProjectToEdition(indexEdition, name, description, tags);
            } catch (InvalidIndexException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("The given index is invalid.");
        }
    }

    private void removeProjectHandler(int editionIndex) throws IOException {
        
    }
    private void editionManagementMenu(int editionIndex) throws IOException {
        int option;
        Edition currentEdition = cbl.returnEdition(editionIndex);
        do {
            option = menuRead("Edition Menu:\n 1 - Add Project\n 2 - Remove Project\n 3 - Get Project\n 4 - Get Projects By Tag " +
                    "\n 5 - Get Projects By Email \n 6 - Get Number Of Projects \n 7 - Get End Date.\n Option: ", 0, 7);
            switch (option) {
                case 1:
                    addProjectHandler(editionIndex);
                    break;
                case 2:
                    /*
                     * Listagem de todos os projetos de uma dada edição.
                     */
                    try {
                        System.out.println("Project name: ");
                        String projectName = reader.readLine();
                        currentEdition.removeProject(projectName);
                    } catch (IllegalArgumentException e) {
                        System.out.println("The given project name was not found.");
                    }
                    break;
                case 3:
                    /**
                     Lista de todos os projetos disponíveis na edição.
                     */
                    try {
                        System.out.println("Project name: ");
                        String projectName = reader.readLine();
                        projectManagementMenu(currentEdition.getProject(projectName));
                    } catch (IllegalArgumentException e) {
                        System.out.println("The given project name was not found");
                    }
                    break;
                case 4:
                    /**
                     Retorna um array de projetos, o que fazer?
                     */
                    break;
                case 5:
                    /**
                     Retorna um array de projetos, o que fazer?
                     */
                    break;
                case 6:
                    System.out.println("Number of projects: " + currentEdition.getNumberOfProjects());
                    break;
                case 7:
                    System.out.println("Edition End Date: " + currentEdition.getEnd());
                    break;
            }
        } while (option != 0);
    }

    private void addEditionHandler() {
        try {
            System.out.print("\nWrite the name:\t");
            String name = reader.readLine();
            System.out.print("\nWrite the start date(dd/MM/yyyy format)\t");
            String startDateString = reader.readLine();
            System.out.println("\nWrite the end date(dd/MM/yyyy format)\t");
            String endDateString = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);

            System.out.println("\nWrite the desired project template:\t");
            String projectTemplate = reader.readLine();
            cbl.addEdition(new EditionImpl(name, startDate, endDate, projectTemplate, Status.INACTIVE));
        } catch (DateTimeException e) {
            System.out.println("Invalid date format. Make sure you are using the right format.");
        } catch (EditionAlreadyInListException e) {
            System.out.println("All the given edition information is already available in another edition.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeEditionHandler() throws IOException {
        //Listagem de todas as edições disponiveis (por número index).
        System.out.println("Select the Edition to remove:\t");
        String inputEditionIndex = reader.readLine();
        int index = Integer.parseInt(inputEditionIndex);
        try {
            cbl.removeEdition(index);
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        }

    }

    private void editEditionHandler() throws IOException {
        /*
         * Listagem de todas as edições disponíveis por número.
         */
        System.out.println("Edition:\t");
        String indexString = reader.readLine();
        int index = Integer.parseInt(indexString);
        editionManagementMenu(index);
    }

    private void setActiveEditionHandler() throws IOException {
        //Listagem de todas as edições disponíveis (por número index).
        System.out.println("Select the Edition to set Active:\t");
        String inputActiveEdition = reader.readLine();
        int indexActiveEdition = Integer.parseInt(inputActiveEdition);
        try {
            cbl.setActiveEdition(indexActiveEdition);
        } catch (InvalidIndexException e) {
            System.out.println("The given index is invalid.");
        }
    }

    private void addSubmissionToProject() throws IOException {
        //Listagem de todas as submiçoes disponiveis
        //Algo que retorne a ediçao ativa
        try {
            System.out.print("Write the student email: ");
            String studentEmail = reader.readLine();
            System.out.print("Write the project name: ");
            String projectName = reader.readLine();
            System.out.print("Write the task title: ");
            String taskTitle = reader.readLine();
            System.out.print("Write the submission text: ");
            String submissionText = reader.readLine();
            System.out.println("Select the Submission");
            SubmissionImpl submission = new SubmissionImpl(LocalDateTime.now(), (StudentImpl) cbl.returnEdition(0).getProject(projectName).getParticipant(studentEmail), submissionText);
            cbl.addProjectSubmissionToActiveEdition(submission, studentEmail, projectName, taskTitle);
        } catch (InvalidIndexException e) {
            throw new RuntimeException(e);
        } catch (InvalidProjectNameException e) {
            throw new RuntimeException(e);
        }
    }

    public void cblManagementMenu() throws IOException {
        int option;
        do {
            // Faz sentido termos dois addProject's? um no cblManagement e outro no editionManagement???
            option = menuRead("Cbl Menu:\n 1 - Add Edition\n 2 - Remove Edition\n 3 - Edit Edition\n 4 - Set Active Edition" +
                    "\n 6 - Add Submission to Project\n 7 - Cbl Stats\n Option: ", 0, 7);
            switch (option) {
                case 1:
                    this.addEditionHandler();
                    break;
                case 2:
                    this.removeEditionHandler();
                    break;
                case 3:
                    this.editEditionHandler();
                    break;
                case 4:
                    this.setActiveEditionHandler();
                    break;
                case 6:
                    this.addSubmissionToProject();
                    break;
                case 7:
                    this.cblStats();
                    break;
            }
        } while (option != 0);
    }
}
