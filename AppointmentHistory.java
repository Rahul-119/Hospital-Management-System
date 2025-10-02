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
    public static class Queue {
        int front;
        int rear;
        int size;
        Appointment appointmentLog[];

        public Queue(int size) {
            this.front = -1;
            this.rear = -1;
            this.size = size;
            this.appointmentLog = new Appointment[size];
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue appointment = new Queue(5);
        String date, doctor, department;
        int option;
        boolean running = true;
        while (running) {
            System.out.println("Hi, how can I help: ");
            System.out.println("1.Add Appointment");
            System.out.println("2.Show Appointment");
            System.out.println("3.Exit");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter Date of Appointment");
                    date = sc.nextLine();

                    System.out.println("Doctor InCharge");
                    doctor = sc.nextLine();

                    System.out.println("Enter Department");
                    department = sc.nextLine();

                    Appointment newAppointment = new Appointment(date, doctor, department);
                    appointment.logAppointment(newAppointment);
                    break;

                case 2:
                    appointment.getAppointments();
                    break;

                case 3:
                    System.out.println("Have a nice day!");
                    running = false;
                    break;

                default:
                    System.out.println("Error");
                    break;
            }
        }
    }
}
