import java.util.Scanner;
import java.util.UUID;

public class HospitalSystem {
    private static PatientInfo patientInfo;
    private static EmergencyQueue emergencyQueue;
    private static AppointmentHistory appointmentHistory;
    private static DeptNavigation deptNavigation;

    private static void patientMenu(Scanner sc) {
        System.out.println("\n--- Patient Menu ---");
        System.out.println("1. Add Patient");
        System.out.println("2. Delete Patient");
        System.out.println("3. View All Patients");
        System.out.println("4. Update Patient Info");
        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1:
                patientInfo.addPatientInteractive(sc);
                break;
            case 2:
                patientInfo.deletePatientInteractive(sc);
                break;
            case 3:
                patientInfo.display();
                break;
            case 4:
                patientInfo.updatePatientInteractive(sc);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    // ------------------- Search Patient -------------------
    private static void searchPatient(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        String idStr = sc.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            patientInfo.findPatient(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format!");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        patientInfo = new PatientInfo();
        emergencyQueue = new EmergencyQueue();
        appointmentHistory = new AppointmentHistory();
        deptNavigation = new DeptNavigation();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Emergency Queue");
            System.out.println("3. Appointment History");
            System.out.println("4. Department Navigation");
            System.out.println("5. Search Patient by ID");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    patientMenu(sc);
                    break;
                case 2:
                    emergencyQueue.runMenu(sc);
                    break;
                case 3:
                    appointmentHistory.runMenu(sc);
                    break;
                case 4:
                    deptNavigation.runNavigationMenu();
                    break;
                case 5:
                    searchPatient(sc);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Hospital System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
