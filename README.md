# Hotel Reservation System

## Overview

The **Hotel Reservation System** is a Java-based console application that allows users to manage hotel reservations. The system enables guests to book, view, update, and delete reservations, as well as retrieve room details. It connects to a MySQL database to store reservation data.

## Features

- Reserve a room
- View all reservations
- Get room details
- Update a reservation
- Delete a reservation

## Technologies Used

- Java
- MySQL
- JDBC (Java Database Connectivity)

## Database Setup

1. Create a MySQL database named `hotel_db`:
   ```sql
   CREATE DATABASE hotel_db;
   ```
2. Switch to the database:
   ```sql
   USE hotel_db;
   ```
3. Create the `reservation` table:
   ```sql
   CREATE TABLE reservation (
       id INT AUTO_INCREMENT PRIMARY KEY,
       guest_name VARCHAR(20) NOT NULL,
       room_no INT NOT NULL,
       contact_no VARCHAR(10) NOT NULL,
       reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

## Installation & Usage

### Prerequisites

- Install **Java** (JDK 8 or later)
- Install **MySQL**
- Add MySQL JDBC driver to the project

### Steps to Run

1. Clone this repository:
   ```sh
   git clone https://github.com/yourusername/Hotel-Reservation-System.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Hotel-Reservation-System
   ```
3. Compile and run the Java program:
   ```sh
   javac -cp .:mysql-connector-java-8.0.33.jar org/example/Hotel_Reservation_System.java
   java -cp .:mysql-connector-java-8.0.33.jar org.example.Hotel_Reservation_System
   ```

## How to Contribute

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

