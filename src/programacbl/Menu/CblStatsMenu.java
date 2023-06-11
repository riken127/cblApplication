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

import exceptions.InvalidIndexException;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import project.CblManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class CblStatsMenu {
    private CblManagement cbl;
    private BufferedReader reader;
    private static final String ASK_EDITION_INDEX = "Edition index:\t";
    private static final String ASK_PROJECT_NAME = "Project name:\t";
    public CblStatsMenu(CblManagement cbl) {
        this.cbl = cbl;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * The listEditionsWithMissingSubmissionsInTasks, method retrieves editions that have
     * missing submissions in tasks.
     * Is uses the cbl object to call the "returnEditionsWithProjectsMissingSubmissionsInTasks" method, which
     * returns an array of "Edition" objects.
     */
    private void listEditionsWithMissingSubmissionsInTasks() {
        Edition[] editions = cbl.returnEditionsWithProjectsMissingSubmissionsInTasks();
        System.out.println("Editions with missing submissions in tasks.");
        for (int i = 0; i < editions.length; i++) {
            System.out.println(editions[i].getName() + " " + editions[i].getStatus() + " " + editions[i].getNumberOfProjects());
        }
    }

    /**
     * Lists projects with missing submissions for a given index.
     * @throws IOException if an I/O error occurs while reading input.
     * @throws InvalidIndexException If the index is invalid.
     */
    private void listProjectsWithMissingSubmissions() throws IOException, InvalidIndexException {
        listEditions();

        System.out.println(ASK_EDITION_INDEX);
        String indexString = reader.readLine();
        int index = Integer.parseInt(indexString);
        if (cbl.returnEdition(index) != null) {
            System.out.println("Projects with missing submissions in the given index.");
            listGivenProjects(cbl.returnProjectsWithMissingSubmissions(index));
        }else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Lists the progress of an edition
     * Calls the listEditions method
     * Prompt the user to enter the edition index.
     * Check if the edition at the specified index exists
     * Retrieve the edition at the specified index.
     * Display the edition progress.
     * Handles the exception if an invalid index is provided.
     * @throws IOException if an I/O error occurs while reading user input.
     */
    private void listEditionProgress() throws IOException{
        listEditions();
        System.out.println(ASK_EDITION_INDEX);
        String indexString = reader.readLine();
        int index = Integer.parseInt(indexString);
        if (cbl.returnEdition(index) != null) {
            cbl.returnEdition(index);
            try {
                System.out.println("Edition progress.");
                System.out.println(cbl.returnEditionProgress(index));
            } catch (InvalidIndexException e) {
                System.out.println("Invalid Index!");
            }
        }
    }

    /**
     * Lists the progress of a project based on user input.
     * Checks if the edition at the specified index exists.
     * Prints the projects of the specified edition.
     * return project progress if not null.
     * @throws IOException if an I/O error occurs while reading user input.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    private void listProjectProgress() throws IOException, InvalidIndexException {
        listEditions();
        System.out.println(ASK_EDITION_INDEX);
        String indexString = reader.readLine();
        int index = Integer.parseInt(indexString);
        if (cbl.returnEdition(index) != null) {
            listGivenProjects(cbl.returnEdition(index).getProjects());
            System.out.println(ASK_PROJECT_NAME);
            String projectName = reader.readLine();
            System.out.println("Project progress.");
            System.out.println(cbl.returnProjectProgress(index, projectName));
        }else {
            System.out.println("The given index is invalid.");
        }

    }

    /**
     * Lists all the projects in the given projects array, makes sure to verify is the position "i" is not null.
     * @param projects array of given projects.
     */
    private void listGivenProjects(Project[] projects) {
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null) {
                System.out.println(projects[i].getName() + " " + projects[i].getDescription());
            }
        }
    }

    /**
     * Lists the number of editions in a given index.
     * Prompts the user for an edition index.
     * If the given index is not null, prints the number of editions.
     * @throws IOException
     */
    private void listNumberOfProjectsInEdition() throws IOException{
        listEditions();
        System.out.println(ASK_EDITION_INDEX);
        String indexString = reader.readLine();
        int index = Integer.parseInt(indexString);
        if (cbl.returnEdition(index - 1) != null) {
            System.out.println("Number of projects in the given index.");
            System.out.println(cbl.returnEdition(index).getNumberOfProjects());
        }else {
            System.out.println("The given index is invalid.");
        }
    }

    /**
     * Lists all the editions in the cbl.
     * call the function ".returnEditionList()", and then
     * prints every non-null element in the array.
     */
    private void listEditions() {
        /*Edition[] editions = cbl.returnEditionList();
        System.out.println("Available Editions.");
        for (int i = 0; i < cbl.returnNumberOfEditions(); i++) {
            if (editions[i] != null) {
                System.out.println((i + 1) + " " + editions[i].getName() + " " + editions[i].getNumberOfProjects());
            }
        }*/
    }

    /**
     *  Displays the CBL Statistics Menu and performs corresponding actions based on user input.
     */
    public void cblStatsMenu() {
        int option;
        do {

            option = Helper.menuRead("CBL Statistics Menu\n" +
                                              "1. List editions with missing submissions in tasks\n" +
                                                "2. List projects with missing submissions\n" +
                                                "3. List edition progress\n" +
                                                "4. List number of projects in edition\n" +
                                                "5. List project progress\n" +
                                                "6. List edition information\n" +
                                                "7. List project information by edition\n" +
                                                "8. List project status by edition\n" +
                                                "0. Exit\nChoose an option: ", 0, 8);
            switch(option) {
                case 1:
                    listEditionsWithMissingSubmissionsInTasks();
                    break;
                case 2:
                    try {
                        listProjectsWithMissingSubmissions();
                    } catch (InvalidIndexException e) {
                        System.out.println("Invalid index");
                    } catch (IOException e) {
                        System.out.println("An error as occurred.");
                    }
                    break;
                case 3:
                    try {
                        listEditionProgress();
                    } catch (IOException e) {
                        System.out.println("An error has occurred.");
                    }
                    break;
                case 4:
                    try {
                        listNumberOfProjectsInEdition();
                    } catch (IOException e) {
                        System.out.println("Invalid index.");
                    }
                    break;
                case 5:
                    try {
                        listProjectProgress();
                    } catch (IOException e) {
                        System.out.println("An error has occurred.");
                    } catch (InvalidIndexException e) {
                        System.out.println("The given index is invalid.");
                    }
                case 6:
                    System.out.println(cbl.listEditionInformation());
                    break;
                case 7:
                    System.out.println(cbl.listProjectInformationByEdition());
                    break;
                case 8:
                    System.out.println(cbl.listProjectStatusByEdition());
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }
}
