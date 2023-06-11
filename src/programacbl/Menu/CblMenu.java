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
import programacbl.Menu.*;
public class CblMenu {
    CblManagement cbl;
    BufferedReader reader;
    EditionMenu editionMenu;
    CblStatsMenu cblStatsMenu;

    public CblMenu() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.cbl = new CblManagement();
        this.editionMenu = new EditionMenu(cbl);
        this.cblStatsMenu = new CblStatsMenu(cbl);
    }

    /**
        This private method returns the index of the active edition, if found.
        If no active edition is found, it returns -1.
        @return The index of the active edition if found, otherwise -1.
     */
    private int returnIndexToActiveEdition(){
        for(int i = 0;i<cbl.returnNumberOfEditions();i++){
            if(cbl.returnEdition(i).getStatus() == Status.ACTIVE){
                return i;
            }
        }
        return -1;
    }

    /**
        This private method, returnEditionsText(), iterates through the editions
        in the cbl object and prints information about each edition.
     */
    private void returnEditionsText(){
        for(int i = 0;i<cbl.returnNumberOfEditions();i++){
            System.out.println("Index - " + i + "\tName - " + cbl.returnEdition(i).getName());
        }
    }

    /**
     * Adds a new edition by capturing user input for edition details.
     * The edition name, start date, end date, project template, and status are collected from the user.
     * The edition information is then added to the 'cbl' using the 'addEdition' method.
     *
     * @throws DateTimeException If an invalid date format is entered by the user.
     * @throws EditionAlreadyInListException If the edition information provided is already available in another edition.
     * @throws RuntimeException If an I/O exception occurs while reading user input.
     */
    private void addEditionHandler() {
        try {
            System.out.print("\nWrite the name:\t");
            String name = reader.readLine();
            System.out.print("\nWrite the start date(dd/MM/yyyy format)\t");
            String startDateString = reader.readLine();
            System.out.print("\nWrite the end date(dd/MM/yyyy format)\t");
            String endDateString = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);

            System.out.print("\nWrite the desired project template:\t");
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

    /**
     * Removes an edition by index.
     * Invokes the "returnEditionText" method to display a text representation of the available editions.
     * Prompts the user to select the edition to be removed by displaying the message "Select the Edition to Remove:\t"
     * Reads the user's input edition index from the standard input using a "BufferedReader" named "reader"
     * Parses the input edition index as a "String" into an "int" value using the "Integer.parseInt" method.
     * Attempts to remove the edition at the specified index by calling the "cbl.removeEdition(index)" method.
     * If the "removeEdition" method throws an "InvalidIndexException", catches the exception and prints its message
     * using "System.out.println".
     * @throws IOException If an I/O exception occurs while reading user input.
     */
    private void removeEditionHandler() throws IOException {
        this.returnEditionsText();
        System.out.print("Select the Edition to remove:\t");
        String inputEditionIndex = reader.readLine();
        int index = Integer.parseInt(inputEditionIndex);
        try {
            cbl.removeEdition(index);
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }

    }

    /**
     * Handles editing of an edition.
     * @throws IOException If an I/O error occurs during input/output operations.
     * Retrieves editions text.
     * Prompt user to select edition to edit.
     * Checks if the selected index is valid.
     * Invoke the edition management menu for the selected index.
     * Display error message if the edition index is not found.
     */
    private void editEditionHandler() throws IOException {
        this.returnEditionsText();
        System.out.println("Select the Edition to edit:\t");
        String indexString = reader.readLine();
        int index = Integer.parseInt(indexString);
        if(index < cbl.returnNumberOfEditions()){
            editionMenu.editionManagementMenu(index);
        }
        System.out.println("Edition Index not found");
    }

    /**
     * Sets active edition based on user input.
     * @throws IOException If an I/O error occurs while reading user input.
     * The "setActiveEditionHandler" method sets the active edition by taking user input and
     * calling the "setActiveEdition" method of an instance "cbl".
     * This method handles any "InvalidIndexException" that may occur during the process.
     */
    private void setActiveEditionHandler() throws IOException {
        this.returnEditionsText();
        System.out.println("Select the Edition to set Active:\t");
        String inputActiveEdition = reader.readLine();
        int indexActiveEdition = Integer.parseInt(inputActiveEdition);
        try {
            cbl.setActiveEdition(indexActiveEdition);
        } catch (InvalidIndexException e) {
            System.out.println("The given index is invalid.");
        }
    }

    /**
     * Adds a submission to a project in the active edition.
     * Creates a new submission using current date and atime, a student object, and the submission text.
     * Adds the submission to the active edition.
     * @throws IOException If an I/O exception occurs.
     */
    private void addSubmissionToProjectOnActiveEdition() throws IOException {
        try {
            if(this.returnIndexToActiveEdition() != -1){
                System.out.print("Write the student email: ");
                String studentEmail = reader.readLine();
                System.out.print("Write the project name: ");
                String projectName = reader.readLine();
                System.out.print("Write the task title: ");
                String taskTitle = reader.readLine();
                System.out.print("Write the submission text: ");
                String submissionText = reader.readLine();
                SubmissionImpl submission = new SubmissionImpl(LocalDateTime.now(), (StudentImpl) cbl.returnEdition(this.returnIndexToActiveEdition()).getProject(projectName).getParticipant(studentEmail), submissionText);
                cbl.addProjectSubmissionToActiveEdition(submission, studentEmail, projectName, taskTitle);
            }
            else{
                System.out.println("Active Edition was not found.");
            }
        } catch (InvalidIndexException e) {
            System.out.println("The given index is invalid.");
        } catch (InvalidProjectNameException e) {
            System.out.println("The given project name is invalid.");
        }
    }

    /**
     * This method represents the management menu for the CBL (Challenged Based Learning) system.
     * Allows the user to perform various operations related to CBL editions, project, submissions, participants and tasks.
     * The user is presented with a menu of options and can choose an option to execute the corresponding functionality.
     * @throws IOException If an I/O exception occurs.
     */
    public void cblManagementMenu() throws IOException {
        int option;
        do {
            option = Helper.menuRead("Cbl Menu:\n 1 - Add Edition\n 2 - Remove Edition\n 3 - Edit Edition\n 4 - Set Active Edition" +
                    "\n 5 - Add Submission to Active Edition Project\n 6 - Cbl Statistics\n Option: ", 0, 6);
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
                case 5:
                    this.addSubmissionToProjectOnActiveEdition();
                    break;
                case 6:
                    this.cblStatsMenu.cblStatsMenu();
                    break;
            }
        } while (option != 0);
    }
}
