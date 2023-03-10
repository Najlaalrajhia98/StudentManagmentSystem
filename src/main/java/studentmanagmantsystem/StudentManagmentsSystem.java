/**
 * 
 */
package studentmanagmantsystem;

import java.awt.Choice;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author LAP-7
 *
 */
public class StudentManagmentsSystem {

    /**
     * Acceptance criteria 
     * 1. school managment system app that should be able to register the students.
     * 2. able to search the registerd students.
     * 3.retain students regestration information 
     * such that when the application restart we cn still search the exisiting registerd students
     * @throws IOException 
     */
    @SuppressWarnings("unlikely-arg-type")
    public static void main(String[] args) throws IOException {

        HashMap<Integer , String []> store= new HashMap<>();
        Scanner inputScanner = new Scanner(System.in); 

        final String DATA_FILE_PATH="studentdata/studentt.csv";
        File dataFile = new File(DATA_FILE_PATH);
        // Load student records from csv file to the hashmap
        try {

            if (dataFile.exists()) {

                Scanner readFile = new Scanner(dataFile); 
                while(readFile.hasNextLine()) 
                {
                    String data[] = readFile.nextLine().split(",");
                    int id = Integer.parseInt(data[0]);
                    store.put(id, new String[] { data[1], data[2] });
                }
                readFile.close(); } 
        } 
        catch (Exception e) {
            System.out.println("file does not exist ");
        }

        while (true) {
            System.out.println("1. Register a student");
            System.out.println("2. Search for a student");
            System.out.println("3. list all students");
            System.out.println("4. Delete a student ");
            System.out.println("5. Update a student ");
            System.out.println("6.Exit");

            System.out.print("Enter a choice: ");
            int choice = inputScanner.nextInt();

            if (choice == 1) {
                // Register a student

                System.out.println("Enter the id :");
                int id = inputScanner.nextInt();

                System.out.println("Enter the name :");
                String name  = inputScanner.next();

                System.out.println("Enter the email :");
                String email = inputScanner.next();
                store.put(id, new String[] { name, email });
                try {
                    FileWriter writer= new FileWriter(DATA_FILE_PATH,true);

                    writer.write(id+","+name+","+email+","+"\n");
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } // Search a student
            else if (choice==2) {
                System.out.print("Enter the student's ID: ");
                int id = inputScanner.nextInt();
                String[] student = store.get(id);
                if (student!=null){
                    System.out.println("Student info");
                    System.out.println("id : "+ id);
                    System.out.println("name: " + student[0]);
                    System.out.println("email: " + student[1]);


                } else {
                    System.out.println("student not found");
                }

            }
            // Retain records
            else if (choice==3) {

                for (Map.Entry<Integer, String[]> entry : store.entrySet()) {
                    System.out.println("ID"+" : "+entry.getKey() +"|" +" NAME AND EMAIL : " + Arrays.toString(entry.getValue()));
                }


            }
            // Delete a student 
            else if (choice==4) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter student ID that you want to remove : ");
                int id = scanner.nextInt();

                // Check if student ID exists in the HashMap
                if (store.containsKey(id)) {
                    // Remove student record from HashMap
                    store.remove(id);
                    try (FileWriter writer= new FileWriter(DATA_FILE_PATH)) {

                        for (Integer studentId : store.keySet()) {
                            String[] record = store.get(studentId);
                            String name = record[0];
                            String email= record[1];
                            writer.write(studentId+","+name+","+email+","+"\n");

                        }

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            else if (choice==5) {

                System.out.print("Enter student ID: ");
                int id = inputScanner.nextInt();
                if (store.containsKey(id)) {
                    System.out.print("Enter student name: ");
                    String name = inputScanner.next();
                    System.out.print("Enter student email: ");
                    String email = inputScanner.next();
                    store.put(id, new String[] {name, email});

                    try (FileWriter writer= new FileWriter(DATA_FILE_PATH)) {

                        for (Integer studentId : store.keySet()) {
                            String[] record = store.get(studentId);
                            String nameString = record[0];
                            String emailsString= record[1];
                            writer.write(studentId+","+nameString+","+emailsString+","+"\n");
                        }  
                        System.out.println("Student record updated."); 
                    }

                }
                else {
                    System.out.println("Student not found.");
                }
            }
            else if (choice==6) {
                System.exit(0);
            }


            else {
                System.out.println("Invalid choice");
            }

        }
    }
}





