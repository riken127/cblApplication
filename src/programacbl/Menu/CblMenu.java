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

    public CblMenu() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.cbl = new CblManagement();
        this.editionMenu = new EditionMenu(cbl);
    }

    private int returnIndexToActiveEdition(){
        for(int i = 0;i<cbl.returnNumberOfEditions();i++){
            if(cbl.returnEdition(i).getStatus().equals(Status.ACTIVE)){
                return i;
            }
        }
        return -1;
    }
    private void returnEditionsText(){
        for(int i = 0;i<cbl.returnNumberOfEditions();i++){
            System.out.println("Index - " + i + "\tName - " + cbl.returnEdition(i).getName());
        }
    }
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

    private void addSubmissionToProjectOnActiveEdition() throws IOException {
        /*try {
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
                System.out.println("Active Edition not Found");
            }
        } catch (InvalidIndexException e) {
            System.out.println(e);;
        } catch (InvalidProjectNameException e) {
            System.out.println(e);;
        }*/
    }
    public void cblManagementMenu() throws IOException {
        int option;
        do {
            option = Helper.menuRead("Cbl Menu:\n 1 - Add Edition\n 2 - Remove Edition\n 3 - Edit Edition\n 4 - Set Active Edition" +
                    "\n 5 - Add Submission to Active Edition Project\n 6 - Cbl Statistics\n Option: ", 0, 5);
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
                    //CblStatsMenu();
                    break;
            }
        } while (option != 0);
    }
}
