/**
 * 
 */
package studentmanagmantsystem;

import java.awt.Choice;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
     // Load student records from CSV file to the hashmap
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
                    // TODO: handle exception
                }

        while (true) {
            System.out.println("1. Register a student");
            System.out.println("2. Search for a student");
            System.out.println("3. retain all students");
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

            } else if (choice==2) {
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
            else if (choice==3) {
               
                        for (Map.Entry<Integer, String[]> entry : store.entrySet()) {
                            System.out.println("ID"+" : "+entry.getKey() +"|" +" NAME AND EMAIL : " + Arrays.toString(entry.getValue()));
                        }
                      

                    }

            else {
                System.out.println("Invalid choice");
            }

        }
    }
}



