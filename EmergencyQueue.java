import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.UUID;

class EmergencyPatient implements Comparable<EmergencyPatient> {
    UUID id;
    String name;
    int age;
    String condition;
    String gender;
    String mobile;
    String medicalHistory;
    int priority;

    public EmergencyPatient(UUID id, String name, int age, String condition, String gender, String mobile,
            String medicalHistory, int priority) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.gender = gender;
        this.mobile = mobile;
        this.medicalHistory = medicalHistory;
        this.priority = priority;
    }

    @Override
    public int compareTo(EmergencyPatient p) {
        return p.priority - this.priority;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Age: " + age +
                ", Gender: " + gender +
                ", Mobile: " + mobile +
                ", Medical History: " + medicalHistory +
                ", Condition: " + condition +
                ", Priority: " + priority;
    }

}

public class EmergencyQueue {
    private final PriorityQueue<EmergencyPatient> pq = new PriorityQueue<>();

    public void addPatientInteractive(Scanner sc) {
        UUID id = UUID.randomUUID();
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Mobile: ");
        String mobile = sc.nextLine();
        System.out.print("Enter Medical History: ");
        String history = sc.nextLine();
        System.out.print("Enter Diagnosis/Condition: ");
        String condition = sc.nextLine();
        System.out.print("Enter Priority (1-5): ");
        int priority = sc.nextInt();
        sc.nextLine();

        EmergencyPatient patient = new EmergencyPatient(id, name, age, condition, gender, mobile, history, priority);
        addPatient(patient);
    }

    public void runMenu(Scanner sc) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Emergency Queue Menu ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Treat Patient");
            System.out.println("3. Display Queue");
            System.out.println("4. Exit to Main Menu");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addPatientInteractive(sc);
                    break;
                case 2:
                    treatPatient();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    void addPatient(EmergencyPatient patient) {
        pq.add(patient);
        System.out.println("Patient added to Emergency Queue");
    }

    public void treatPatient() {
        if (pq.isEmpty()) {
            System.out.println("No patient in Emergency Queue");
            return;
        }
        EmergencyPatient patient = pq.poll();
        System.out.println("Treating Patient: " + patient.name + " of ID: " + patient.id + " having condition: "
                + patient.condition);
    }

    public void display() {
        if (pq.isEmpty()) {
            System.out.println("No patient in Emergency Queue");
            return;
        }
        System.out.println("Patients currently in Emergency Queue: ");
        for (EmergencyPatient patient : pq) {
            System.out.println(patient);
        }
    }
}
