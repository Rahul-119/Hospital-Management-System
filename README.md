# 🏥 Hospital Management System (Data Structures Project)

This is a console-based hospital management system in Java demonstrating the practical use of fundamental data structures to simulate core hospital operations.

## Features

* **Patient Management:** Add, delete, update, and view patient records.
* **Efficient Patient Search:** Instantly find patients by their unique ID.
* **Emergency Room Triage:** A priority-based queue ensures the most critical patients are treated first.
* **Appointment History:** Logs a rolling history of recent appointments.
* **Hospital Navigation:** Finds the shortest path and all reachable locations from any department.

## Data Structures Implemented

* **Linked List & HashMap:** Used in `PatientInfo` for efficient $O(1)$ average-time patient search, update, and deletion.
* **Priority Queue (Heap):** Used in `EmergencyQueue` to manage patient triage based on priority.
* **Graph (Adjacency List) with BFS/DFS:** Used in `DeptNavigation` to find the shortest path and all reachable hospital departments.
* **Circular Queue (Array):** Used in `AppointmentHistory` to store a rolling log of recent appointments.
