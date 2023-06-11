package programacbl.Menu;

import exceptions.InvalidIndexException;
import ma02_resources.project.Edition;
import participants.StudentImpl;
import project.CblManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class EditionMenu {

    CblManagement cbl;
    BufferedReader reader;
    ProjectMenu projectMenu;

    public EditionMenu(CblManagement cbl) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.cbl = cbl;
        projectMenu = new ProjectMenu(cbl);
    }

    private void returnProjectsText(int editionIndex){
        for(int i = 0;i<cbl.returnEdition(editionIndex).getNumberOfProjects();i++){
            System.out.println("Name - " + cbl.returnEdition(editionIndex).getProjects()[i].getName());
        }
    }

    private void addProjectHandler(int editionIndex) throws IOException {
            System.out.println("Type the given project name:");
            String name = reader.readLine();
            System.out.println("Type the given project description:");
            String description = reader.readLine();
            System.out.println("Type the given project tags (separate) by white spaces:");
            String tagsString = reader.readLine();
            String[] tags = tagsString.split(" ");
            try {
                cbl.addProjectToEdition(editionIndex, name, description, tags);
            } catch (InvalidIndexException e) {
                System.out.println("Invalid index.");
            } catch (ParseException e) {
                System.out.println("An error has occurred while trying to read the json template.");
            }
    }

    private void removeProjectHandler(int editionIndex) throws IOException {
        this.returnProjectsText(editionIndex);
        try {
            System.out.println("Project name: ");
            String projectName = reader.readLine();
            cbl.returnEdition(editionIndex).removeProject(projectName);
        } catch (IllegalArgumentException e) {
            System.out.println("The given project name was not found.");
        }
    }
    private void editProjectHandler(int editionIndex) throws IOException {
        this.returnProjectsText(editionIndex);
        try {
            System.out.println("Project name: ");
            String projectName = reader.readLine();
            projectMenu.projectManagementMenu(cbl.returnEdition(editionIndex).getProject(projectName));
        } catch (IllegalArgumentException e) {
            System.out.println("The given project name was not found");
        }
    }
    private void getProjectsByTag(int editionIndex) throws IOException {
        System.out.println("Project tag: ");
        String projectTag = reader.readLine();
        for(int i = 0;i<cbl.returnEdition(editionIndex).getNumberOfProjects();i++){
            if(cbl.returnEdition(editionIndex).getProjects()[i].hasTag(projectTag)){
                System.out.println("Name - " + cbl.returnEdition(editionIndex).getProjects()[i].getName());
            }
        }
    }

    private void getProjectsByEmail(int editionIndex) throws IOException {
        System.out.println("Student email: ");
        String studentEmail = reader.readLine();
        for(int i = 0;i<cbl.returnEdition(editionIndex).getNumberOfProjects();i++){
            if(cbl.returnEdition(editionIndex).getProjects()[i].getParticipant(studentEmail).getEmail() == studentEmail){
                System.out.println("Name - " + cbl.returnEdition(editionIndex).getProjects()[i].getName());
            }
        }
    }
    public void editionManagementMenu(int editionIndex) throws IOException {
        int option;
        Edition currentEdition = cbl.returnEdition(editionIndex);
        System.out.println(currentEdition.getName());
        do {
            option = Helper.menuRead("Edition Menu:\n 1 - Add Project\n 2 - Remove Project\n 3 - Get Project\n 4 - Get Projects By Tag " +
                    "\n 5 - Get Projects By Email \n 6 - Get Number Of Projects \n 7 - Get End Date.\n Option: ", 0, 7);
            switch (option) {
                case 1:
                    addProjectHandler(editionIndex);
                    break;
                case 2:
                    removeProjectHandler(editionIndex);
                    break;
                case 3:
                    editProjectHandler(editionIndex);
                    break;
                case 4:
                    getProjectsByTag(editionIndex);
                    break;
                case 5:
                    getProjectsByEmail(editionIndex);
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

}
