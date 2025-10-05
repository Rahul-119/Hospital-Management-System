import java.util.Scanner;

class Appointment {
    String date;
    String doctor;
    String department;

    public Appointment(String date, String doctor, String department) {
        this.date = date;
        this.doctor = doctor;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Doctor: " + doctor + ", Department: " + department;
    }
}

public class AppointmentHistory {
    private final AppointmentQueue appointmentQueue = new AppointmentQueue(5);

    public void runMenu(Scanner sc) {
        appointmentQueue.runMenu(sc);
    }

    public class AppointmentQueue {
        int front;
        int rear;
        int size;
        Appointment appointmentLog[];

        public AppointmentQueue(int size) {
            this.front = -1;
            this.rear = -1;
            this.size = size;
            this.appointmentLog = new Appointment[size];
        }

        public void logAppointmentInteractive(Scanner sc) {
            System.out.print("Enter Date of Appointment: ");
            String date = sc.nextLine();
            System.out.print("Enter Doctor Name: ");
            String doctor = sc.nextLine();
            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            Appointment newAppointment = new Appointment(date, doctor, department);
            this.logAppointment(newAppointment);
        }

        public void runMenu(Scanner sc) {
            boolean running = true;
            while (running) {
                System.out.println("\n--- Appointment Menu ---");
                System.out.println("1. Add Appointment");
                System.out.println("2. Show Appointments");
                System.out.println("3. Exit to Main Menu");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        logAppointmentInteractive(sc);
                        break;
                    case 2:
                        this.getAppointments();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }

        void logAppointment(Appointment appointment) {
            rear = (rear + 1) % size;
            if (front == -1) {
                front = 0;
            }
            if (front == rear) {
                front = (front + 1) % size;
            }
            appointmentLog[rear] = appointment;
        }

        public void getAppointments() {
            if (isEmpty()) {
                System.out.println("No Appointments currently");
                return;
            }
            int idx = front;
            while (true) {
                System.out.println(appointmentLog[idx]);
                if (idx == rear)
                    break;
                idx = (idx + 1) % size;
            }
        }

        public boolean isEmpty() {
            return front == -1;
        }
    }
}
