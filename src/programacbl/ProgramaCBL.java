/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacbl;

import java.io.*;
import java.sql.Date;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.EditionAlreadyInListException;
import exceptions.InvalidIndexException;
import exceptions.InvalidProjectNameException;
import ma02_resources.participants.InstituitionType;
import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import participants.ContactImpl;
import participants.StudentImpl;
import project.CblManagement;
import project.EditionImpl;
import project.ProjectImpl;
import project.SubmissionImpl;


public class ProgramaCBL {
    public static void main(String[] args) throws IOException {
        CblHandler cbl = new CblHandler();
        cbl.cblManagementMenu();
    }
}
