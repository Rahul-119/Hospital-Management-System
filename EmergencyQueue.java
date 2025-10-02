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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        EmergencyQueue eq = new EmergencyQueue();

        UUID id;
        String name, condition, gender, mobile, medicalHistory;
        int age, priority;
        int option;
        boolean running = true;

        while (running) {
            System.out.println("Hi, how can I help: ");
            System.out.println("1.Add Patient in Emergency Queue");
            System.out.println("2.Treat Patient");
            System.out.println("3.Display Patients in Emergency Queue");
            System.out.println("4.Exit");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    id = UUID.randomUUID();
                    System.out.println("Enter Patient's name: ");
                    name = sc.nextLine();
                    System.out.println("Enter Patient's age (eg: 19): ");
                    age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Patient's sex (eg: M): ");
                    gender = sc.nextLine();
                    System.out.println("Enter Patient's Mobile No: ");
                    mobile = sc.nextLine();
                    System.out.println("Patient's Medical History: ");
                    medicalHistory = sc.nextLine();
                    System.out.println("Patient's Diagnosis: ");
                    condition = sc.nextLine();

                    System.out.println("How Critical is the patient? (1-5):");
                    priority = sc.nextInt();

                    EmergencyPatient patient = new EmergencyPatient(id, name, age, condition, gender, mobile,
                            medicalHistory, priority);

                    eq.addPatient(patient);
                    break;

                case 2:
                    eq.treatPatient();
                    break;

                case 3:
                    eq.display();
                    break;

                case 4:
                    System.out.println("Have a nice day!");
                    running = false;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
