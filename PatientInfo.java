import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

class Node {
    UUID id;
    String name;
    int age;
    String gender;
    String mobile;
    String medicalHistory;
    Node next;

    public Node(UUID id, String name, int age, String gender, String mobile, String medicalHistory, Node next) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
        this.medicalHistory = medicalHistory;
        this.next = next;
    }
}

class PatientInfo {
    private Node head;
    private final HashMap<UUID, Node> patientMap = new HashMap<>();

    // Add inside PatientInfo class

    public void addPatientInteractive(Scanner sc) {
        UUID id = UUID.randomUUID();
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Mobile: ");
        String mobile = sc.nextLine();
        System.out.print("Enter Medical History: ");
        String history = sc.nextLine();

        Node newPatient = new Node(id, name, age, gender, mobile, history, null);
        addPatient(newPatient);
    }

    public void deletePatientInteractive(Scanner sc) {
        System.out.print("Enter Patient ID to delete: ");
        String idStr = sc.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            deletePatient(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format!");
        }
    }

    public void updatePatientInteractive(Scanner sc) {
        System.out.print("Enter Patient ID to update: ");
        String idStr = sc.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Patient Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();
            System.out.print("Enter Mobile: ");
            String mobile = sc.nextLine();
            System.out.print("Enter Medical History: ");
            String history = sc.nextLine();

            Node updated = new Node(id, name, age, gender, mobile, history, null);
            updatePatientInfo(id, updated);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format!");
        }
    }

    public void addPatient(Node newPatient) {
        if (head == null) {
            head = newPatient;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newPatient;
        }

        patientMap.put(newPatient.id, newPatient);
        System.out.println("Patient info successfully added.");
    }

    public void deletePatient(UUID id) {
        if (head == null) {
            System.out.println("No Patients admitted");
        }
        if (head.id.equals(id)) {
            head = head.next;
            patientMap.remove(id);
            System.out.println("Patient info successfully deleted.");
            return;
        }
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if (temp.id.equals(id)) {
                break;
            }
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Patient not found by the given ID");
            return;
        }
        prev.next = temp.next;

        patientMap.remove(id);
        System.out.println("Patient info successfully deleted.");
    }

    public void findPatient(UUID id) {
        Node found = patientMap.get(id);

        if (found != null) {
            System.out.println("Patient Info:");
            System.out.println("ID: " + found.id);
            System.out.println("Name: " + found.name);
            System.out.println("Age: " + found.age);
            System.out.println("Mobile No.: " + found.mobile);
            System.out.println("Gender: " + found.gender);
            System.out.println("Medical History: " + found.medicalHistory);
            System.out.println();
        } else {
            System.out.println("Patient not found.");
        }

    }

    public void display() {
        if (head == null) {
            System.out.println("No Patients in Hospital");
            return;
        }

        System.out.println("\n---- All Patient Records ----");
        Node temp = head;
        while (temp != null) {
            System.out.println("Patient Info:");
            System.out.println("ID: " + temp.id);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Mobile No.: " + temp.mobile);
            System.out.println("Gender: " + temp.gender);
            System.out.println("Medical History: " + temp.medicalHistory);
            System.out.println("-----------------------------");
            temp = temp.next;
        }
    }

    public void updatePatientInfo(UUID id, Node updatedPatientInfo) {
        Node temp = head;
        while (temp != null) {
            if (temp.id.equals(id)) {
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Patient not found by the given ID");
            return;
        }
        temp.name = updatedPatientInfo.name;
        temp.age = updatedPatientInfo.age;
        temp.gender = updatedPatientInfo.gender;
        temp.mobile = updatedPatientInfo.mobile;
        temp.medicalHistory = updatedPatientInfo.medicalHistory;

        patientMap.put(id, temp);
        System.out.println("Patient info successfully updated.");
    }
}