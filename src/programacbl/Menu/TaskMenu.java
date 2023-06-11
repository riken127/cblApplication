package programacbl.Menu;

import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import project.CblManagement;
import project.SubmissionImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;

public class TaskMenu {
    private CblManagement cbl;
    private BufferedReader reader;
    private static String ASK_STUDENT_SUBMISSION_TEXT = "Submission text:\t";
    private static String ASK_EXTEND_DEADLINE_BY = "Extend Deadline by:\t";
    public TaskMenu(CblManagement cbl) {
        this.cbl = cbl;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints all the tasks with their details
     * @param tasks an array of tasks to be printed
     * @param numberOfTasks the number of tasks to be printed
     */
    private void printAllTasks(Task[] tasks, int numberOfTasks) {
        for(int i = 0; i < numberOfTasks; i++) {
            System.out.println((i+1) + " " + tasks[i].getTitle() + " " + tasks[i].getNumberOfSubmissions() + " " + tasks[i].getDescription());
        }
    }

    /**
     * Adds a submission to the desired task.
     * @param task the task which the submission is added.
     * @param student the student submitting the task.
     * @throws IOException if an I/O error occurs while reading the submission text.
     */
    private void addSubmissionToDesiredTask(Task task, Student student) throws IOException{
        LocalDateTime date = LocalDateTime.now();
        String submissionText;
        System.out.println(ASK_STUDENT_SUBMISSION_TEXT);
        submissionText = reader.readLine();

        task.addSubmission(new SubmissionImpl(date, student, submissionText));
    }

    /**
     * Extends the deadline of the desired task.
     * @param task the task whose deadlines is being extended
     * @throws IOException if an I/O error occurs while reading the duration.
     */
    private void extendDesiredTaskDeadline(Task task) throws IOException{
        String durationString;
        int duration;
        System.out.println(ASK_EXTEND_DEADLINE_BY);
        durationString = reader.readLine();
        duration = Integer.parseInt(durationString);
        task.extendDeadline(duration);
    }

    /**
     * Manages tasks in the project, providing options to add submissions and extend deadlines for a selected task.
     * @param project the project for which tasks are managed
     * @throws IOException if an I/O error occurs while reading input.
     */
    public void tasksManagementMenu(Project project) throws IOException {
        int option;
        String taskName;
        printAllTasks(project.getTasks(), project.getNumberOfTasks());
        taskName = reader.readLine();
        if (project.getTask(taskName) == null) {
            System.out.println("Invalid Task Name.");
        } else {
            do {
                option = Helper.menuRead("Task Menu:\n 1 - Add Submission\n 2 - Extend DeadLine\n Option: ", 0, 2);
                switch (option) {
                    case 1:
                        try {
                            String email;
                            email = reader.readLine();
                            if (project.getParticipant(email) == null) {
                                System.out.println("Invalid email.");
                            }else {
                                addSubmissionToDesiredTask(project.getTask(taskName), (Student) project.getParticipant(email));
                            }
                        } catch (IOException e) {
                            System.out.println("Invalid submission text.");
                        }
                        break;
                    case 2:
                        try {
                            extendDesiredTaskDeadline(project.getTask(taskName));
                        } catch (IOException e) {
                            System.out.println("Invalid duration.");
                        }
                        break;
                }
            } while (option != 0);
        }
    }

}
