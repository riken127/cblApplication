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

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.InstituitionType;
import ma02_resources.participants.Participant;
import ma02_resources.project.Project;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participants.*;
import project.CblManagement;
import project.TaskImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProjectMenu {
    private CblManagement cbl;
    private BufferedReader reader;
    private TaskMenu taskMenu;
    private static final String ADD_PARTICIPANT_MESSAGE = "Add Pariticipant:\n";
    private static final String ASK_PARTICIPANT_TYPE_MESSAGE = "What type of participant do you wish to add?\n1 - Facilitator.\n2 - Partner.\n3 - Student.\n";
    private static final String ASK_PARTICIPANT_NAME_MESSAGE = "Participant Name:\n";
    private static final String ASK_PARTICIPANT_EMAIL_MESSAGE = "Participant Email:\n";
    private static final String ASK_INSTITUITION_NAME_MESSAGE = "Instituition's Name:\n";
    private static final String ASK_INSTITUITION_EMAIL_MESSAGE = "Instituition's Email:\n";
    private static final String ASK_INSTITUITION_TYPE_MESSAGE = "Instituition's Type:\n";
    private static final String ASK_INSTITUITION_CONTACT_MESSAGE = "Instituition's Contact:\n";
    private static final String ASK_INSTITUITION_WEBSITE_MESSAGE = "Instituition's Website:\n";
    private static final String ASK_INSTITUITION_DESCRIPTION_MESSAGE = "Instituition's Description:\n";
    private static final String ASK_CONTACT_STREET_MESSAGE = "Contact's Street:\n";
    private static final String ASK_CONTACT_CITY_MESSAGE = "Contact's City:\n";
    private static final String ASK_CONTACT_STATE_MESSAGE = "Contact's State:\n";
    private static final String ASK_CONTACT_ZIPCODE_MESSAGE = "Contact's Zip Code:\n";
    private static final String ASK_CONTACT_COUNTRY_MESSAGE = "Contact's Country:\n";
    private static final String ASK_CONTACT_PHONE_MESSAGE = "Contact's Phone:\n";
    private static final String INSTITUITION_TYPE_OPTIONS = "1 - NGO\n2 - COMPANY\n3 - OTHER\n4 - UNIVERSITY\n";
    private static final String ASK_FACILITATOR_AREA_OF_EXPERTISE = "Facilitator's Area of Expertise:\n";
    private static final String ASK_PARTNER_VAT = "Partner's Var:\n";
    private static final String ASK_PARTNER_WEBSITE = "Partner's Website:\n";
    private static final String ASK_STUDENT_NUMBER = "Student's Number:\n";
    private static final String ASK_TASK_START_DATE = "Task's Start Date:\n";
    private static final String ASK_TASK_END_DATE = "Task's End Date:\n";
    private static final String ASK_TASK_TITLE = "Task's Title:\n";
    private static final String ASK_TASK_DESCRIPTION = "Task's Description:\n";
    ProjectMenu(CblManagement cbl) {
        this.cbl = cbl;
        this.taskMenu = new TaskMenu(cbl);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads the instituition type from the user.
     * @return the selected intituition type as an instance of InstituitionType.
     * @throws IOException If an error occurs while reading the input.
     */
    private InstituitionType getInsituitionType() throws IOException{
        int option;
        System.out.println(INSTITUITION_TYPE_OPTIONS);
        do {

            String stringOption = reader.readLine();
            option = Integer.parseInt(stringOption);

            switch (option) {
                case 1:
                    return InstituitionType.NGO;
                case 2:
                    return InstituitionType.COMPANY;
                case 3:
                    return InstituitionType.OTHER;
                case 4:
                    return InstituitionType.UNIVERSITY;
            }
        } while(option < 0 && option > 4);
        return null;
    }

    /**
     * Retrieves contact information from the user.
     * @return a ContactImpl object containing the contact information
     * @throws IOException if an error occurs while reading the user input.
     */
    private ContactImpl getContactInformation() throws IOException {
        String street, city, state, zipCode, country, phone;
        System.out.println(ASK_CONTACT_STREET_MESSAGE);
        street = reader.readLine();
        System.out.println(ASK_CONTACT_CITY_MESSAGE);
        city = reader.readLine();
        System.out.println(ASK_CONTACT_STATE_MESSAGE);
        state = reader.readLine();
        System.out.println(ASK_CONTACT_ZIPCODE_MESSAGE);
        zipCode = reader.readLine();
        System.out.println(ASK_CONTACT_COUNTRY_MESSAGE);
        country = reader.readLine();
        System.out.println(ASK_CONTACT_PHONE_MESSAGE);
        phone = reader.readLine();

        return new ContactImpl(street, city, state, zipCode, country, phone);
    }

    /**
     * Retrieves and constructs an instance of InstituitionImpl with user-provided information.
     * @return an instance of InstituitionImpl with the filled information.
     * @throws IOException if an error occurs while reading user input.
     */
    private InstituitionImpl getInstituitionInformation() throws IOException {
        String name, email, website, description;
        InstituitionType type;
        Contact contact;
        System.out.println(ASK_INSTITUITION_NAME_MESSAGE);
        name = reader.readLine();
        System.out.println(ASK_INSTITUITION_EMAIL_MESSAGE);
        email = reader.readLine();
        System.out.println(ASK_INSTITUITION_TYPE_MESSAGE);
        type = getInsituitionType();
        System.out.println(ASK_INSTITUITION_CONTACT_MESSAGE);
        contact = getContactInformation();
        System.out.println(ASK_INSTITUITION_WEBSITE_MESSAGE);
        website = reader.readLine();
        System.out.println(ASK_INSTITUITION_DESCRIPTION_MESSAGE);
        description = reader.readLine();
        return new InstituitionImpl(name, email, type, contact, website, description);
    }

    /**
     * Adds a participant by collecting their information.
     * @return the created Participant object
     * @throws IOException if an error occurs while reading input.
     */
    private Participant addParticipant() throws IOException{
        String name, email;
        InstituitionImpl instituition = getInstituitionInformation();
        ContactImpl contact = getContactInformation();
        System.out.println(ADD_PARTICIPANT_MESSAGE);
        System.out.println(ASK_PARTICIPANT_NAME_MESSAGE);
        name = reader.readLine();
        System.out.println(ASK_PARTICIPANT_EMAIL_MESSAGE);
        email = reader.readLine();

        return new ParticipantImpl(name, email, instituition, contact);
    }

    /**
     * Adds a facilitator to the current project
     * @param project the project to add the facilitator to
     * @param participantBasicInfo the basic information of the participant to be added as a facilitator
     * @throws IOException if there is an error while reading input
     * @throws ParticipantAlreadyInProject if the participant is already added to the project
     * @throws IllegalNumberOfParticipantType if the number of participants of the specified type exceeds the limit.
     */
    private void addFacilitatorToCurrentProject(Project project, Participant participantBasicInfo) throws IOException, ParticipantAlreadyInProject, IllegalNumberOfParticipantType {
        System.out.println(ASK_FACILITATOR_AREA_OF_EXPERTISE);
        String expertise = reader.readLine();
        project.addParticipant(new FacilitatorImpl(participantBasicInfo.getName(), participantBasicInfo.getEmail(), (InstituitionImpl) participantBasicInfo.getInstituition(), participantBasicInfo.getContact(), expertise));
    }

    /**
     * Adds a partner to the current project.
     * @param project the project to add the partner to.
     * @param participantBasicInfo the basic information of the participant.
     * @throws IOException if an I/O error occurs while reading input.
     * @throws ParticipantAlreadyInProject if the participant is already added to the project.
     * @throws IllegalNumberOfParticipantType if the number of participant types exceeds the limit.
     */
    private void addPartnerToCurrentProject(Project project, Participant participantBasicInfo) throws IOException, ParticipantAlreadyInProject, IllegalNumberOfParticipantType {
        String vat, website;
        System.out.println(ASK_PARTNER_VAT);
        vat = reader.readLine();
        System.out.println(ASK_PARTNER_WEBSITE);
        website = reader.readLine();
        project.addParticipant(new PartnerImpl(participantBasicInfo.getName(), participantBasicInfo.getEmail(), (InstituitionImpl) participantBasicInfo.getInstituition(), participantBasicInfo.getContact(), vat, website));
    }

    /**
     * Adds a student participant to the current project.
     * @param project the project to which  the participant will be added.
     * @param participantBasicInfo the basic information of the participant.
     * @throws IOException if there is an error reading the student number.
     * @throws ParticipantAlreadyInProject if the participant is already associated with the project.
     * @throws IllegalNumberOfParticipantType if the participant type is valid.
     */
    private void addStudentToCurrentProject(Project project, Participant participantBasicInfo) throws IOException, ParticipantAlreadyInProject, IllegalNumberOfParticipantType {
        int number;
        String numberInString;
        System.out.println(ASK_STUDENT_NUMBER);
        numberInString = reader.readLine();
        number = Integer.parseInt(numberInString);
        project.addParticipant(new StudentImpl(participantBasicInfo.getName(), participantBasicInfo.getEmail(), (InstituitionImpl) participantBasicInfo.getInstituition(), participantBasicInfo.getContact(), number));
    }

    /**
     * Adds a participant to the current project.
     * @param project the project to add the participants to
     * @throws IOException if an error occurs while reading input.
     * @throws ParticipantAlreadyInProject if the participant is already in the project.
     * @throws IllegalNumberOfParticipantType if the participant type is invalid.
     */
    private void addParticipantToCurrentProject(Project project) throws IOException, ParticipantAlreadyInProject, IllegalNumberOfParticipantType {
        Participant basicInfo = addParticipant();
        System.out.println(this.ASK_PARTICIPANT_TYPE_MESSAGE);
        String stringOption = reader.readLine();
        int option = Integer.parseInt(stringOption);
        switch (option) {
            case 1:
                addFacilitatorToCurrentProject(project, basicInfo);
                break;
            case 2:
                addPartnerToCurrentProject(project, basicInfo);
                break;
            case 3:
                addStudentToCurrentProject(project, basicInfo);
                break;
            default:
                break;
        }
    }

    /**
     * Removes a participant from the current project.
     * @param project the project from which to remove the participant
     * @throws IOException if an I/O occurs while reading the participant's email.
     */
    private void removeParticipantFromCurrentProject(Project project) throws IOException {
        System.out.println(ASK_PARTICIPANT_EMAIL_MESSAGE);
        String email = reader.readLine();
        if (project.getParticipant(email) == null) {
            System.out.println("No participant found with the given email.\n");
        }else {
            project.removeParticipant(email);
        }
    }

    /**
     * Adds a task to the current project.
     * @param project the project to which the task will be added.
     * @throws IOException if an I/O error occurs while reading task details.
     * @throws TaskAlreadyInProject if the task is already present in the project.
     * @throws IllegalNumberOfTasks if adding the task exceeds the maximum allowed number of tasks in the project.
     */
    private void addTaskToCurrentProject(Project project) throws IOException, TaskAlreadyInProject, IllegalNumberOfTasks {
        LocalDate start, end;
        String title, description, startString, endString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(ASK_TASK_START_DATE);
        startString = reader.readLine();
        System.out.println(ASK_TASK_END_DATE);
        endString = reader.readLine();
        System.out.println(ASK_TASK_TITLE);
        title = reader.readLine();
        System.out.println(ASK_TASK_DESCRIPTION);
        description = reader.readLine();
        start = LocalDate.parse(startString, formatter);
        end = LocalDate.parse(endString, formatter);
        project.addTask(new TaskImpl(start, end, title, description));
    }

    /**
     * Displays the project management menu and performs various operations based on user input.
     * @param project the project to perform management operations on.
     * @throws IOException if an I/O occurs.
     */
    public void projectManagementMenu(Project project) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int option;
        do {
            option = Helper.menuRead("Project Menu:\n 1 - Add Participant\n 2 - Remove Participant\n" +
                                            " 3 - Get Number Of Participants  \n 4 - Add Task \n 5 - Edit Task \n" +
                                            " 6 - Is Completed?\n Option: ", 0, 8);
            switch (option) {
                case 1:
                    try {
                        addParticipantToCurrentProject(project);
                    } catch (ParticipantAlreadyInProject e) {
                        System.out.println("The participant is already in the project.\n");
                    } catch (IllegalNumberOfParticipantType e) {
                        System.out.println("The maximum number of participants was hit already");
                    }
                    break;
                case 2:
                    try {
                        removeParticipantFromCurrentProject(project);
                    } catch (IOException e) {
                        System.out.println("An error as occurred while trying to delete the participant.");
                    }
                    break;
                case 3:
                    System.out.println("Current Number of Participants in " + project.getName() + ":\t" + project.getNumberOfParticipants());
                    break;
                case 4:
                    try {
                        addTaskToCurrentProject(project);
                    } catch (TaskAlreadyInProject e) {
                        System.out.println("The given task is already in the project.");
                    } catch (IllegalNumberOfTasks e) {
                        System.out.println("The project has already reached the maxed number of tasks.");
                    }
                    break;
                case 5:
                    this.taskMenu.tasksManagementMenu(project);
                    break;
                case 6:
                    if (project.isCompleted()) {
                        System.out.println(project.getName() + " is completed.\n");
                    }else {
                        System.out.println(project.getName() + " is not completed.\n");
                    }
                    break;
            }
        } while (option != 0);
    }

}
