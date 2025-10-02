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

    public void addPatient(Node newPatient) {
        if (head == null) {
            head = newPatient;
            System.out.println("Patient info successfully added.");
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newPatient;
        System.out.println("Patient info successfully added.");
    }

    public void deletePatient(UUID id) {
        if (head == null) {
            System.out.println("No Patients admitted");
        }
        if (head.id.equals(id)) {
            head = head.next;
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
        prev.next = prev.next.next;
        System.out.println("Patient info successfully deleted.");
    }

    public void findPatient(UUID id) {
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
        System.out.println("Patient Info:");
        System.out.println("ID: " + temp.id);
        System.out.println("Name: " + temp.name);
        System.out.println("Age: " + temp.age);
        System.out.println("Mobile No.: " + temp.mobile);
        System.out.println("Gender: " + temp.gender);
        System.out.println("Medical History: " + temp.medicalHistory);
        System.out.println();
    }

    public void display() {
        if (head == null) {
            System.out.println("No Patients in Hospital");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println("Patient Info:");
            System.out.println("ID: " + temp.id);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Mobile No.: " + temp.mobile);
            System.out.println("Gender: " + temp.gender);
            System.out.println("Medical History: " + temp.medicalHistory);
            System.out.println();
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
    }

    @SuppressWarnings("unused") 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PatientInfo p = new PatientInfo();
        Node head = null;

        UUID id;
        int age;
        String name, mobile, gender, medicalHistory;
        String admit;

        int choice;
        boolean running = true;

        while (running) {
            System.out.println("Welcome to AAR Hospital. How can we assist you: ");
            System.out.println("1.Add Patient");
            System.out.println("2.Display Patients Info");
            System.out.println("3.Delete Patients Info");
            System.out.println("4.Update Patients Info");
            System.out.println("5.Search for a specific Patient");
            System.out.println("6.Exit");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1: // Add Patient
                    System.out.println("Do you want to admit a Patient (Y/N)");
                    admit = sc.nextLine();

                    if (admit.equals("Y")) {
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

                        Node newPatient = new Node(id, name, age, gender, mobile, medicalHistory, null);
                        p.addPatient(newPatient);
                    } else {
                        System.out.println("End");
                    }
                    break;

                case 2: // Display Patients Info
                    p.display();
                    break;

                case 3: // Delete Patients Info
                    System.out.println("Enter Patients ID:");
                    String inputId = sc.nextLine();
                    try {
                        id = UUID.fromString(inputId);
                        p.deletePatient(id);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid ID. Please enter a correct UUID.");
                    }
                    break;

                case 4: // Update Patients Info
                    System.out.println("Enter Patients ID:");
                    inputId = sc.nextLine();
                    try {
                        id = UUID.fromString(inputId);

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

                        System.out.println();

                        Node updatedInfo = new Node(id, name, age, gender, mobile, medicalHistory, null);
                        p.updatePatientInfo(id, updatedInfo);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid ID. Please enter a correct UUID.");
                    }
                    break;

                case 5: // Search for a specific Patient
                    System.out.println("Enter Patients ID:");
                    inputId = sc.nextLine();
                    try {
                        id = UUID.fromString(inputId);
                        p.findPatient(id);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid ID. Please enter a correct UUID.");
                    }
                    break;

                case 6: // Exit
                    System.out.println("Have a Nice Day!");
                    running = false;
                    break;

                default:
                    System.out.println("Error");
                    break;
            }
        }
    }
}