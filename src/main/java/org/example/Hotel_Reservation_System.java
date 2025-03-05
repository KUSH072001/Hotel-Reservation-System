package org.example;

import java.sql.*;
import java.util.Scanner;

public class Hotel_Reservation_System {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\nHotel Management System");
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Details");
                System.out.println("4. Update a Reservation");
                System.out.println("5. Delete a Reservation");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: reserveRoom(connection, sc); break;
                    case 2: viewReservations(connection); break;
                    case 3: getRoomDetails(connection, sc); break;
                    case 4: updateReservation(connection, sc); break;
                    case 5: deleteReservation(connection, sc); break;
                    case 0:
                        System.out.println("Exiting...");
                        sc.close();
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void reserveRoom(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter guest name: ");
        String guestName = sc.next();
        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        System.out.print("Enter contact number: ");
        String contactNumber = sc.next();
        String sql = "INSERT INTO reservation (guest_name, room_no, contact_no) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, guestName);
            pstmt.setInt(2, roomNumber);
            pstmt.setString(3, contactNumber);
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows > 0 ? "Reservation successful." : "Reservation failed.");
        }
    }

    private static void viewReservations(Connection connection) throws SQLException {
        String sql = "SELECT * FROM reservation";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("+----+--------------+----------+------------+-------------------+");
            System.out.println("| ID | Guest Name  | Room No  | Contact No | Reservation Date  |");
            System.out.println("+----+--------------+----------+------------+-------------------+");
            while (resultSet.next()) {
                System.out.printf("| %-2d | %-12s | %-8d | %-10s | %-18s |\n",
                        resultSet.getInt("id"), resultSet.getString("guest_name"), resultSet.getInt("room_no"), resultSet.getString("contact_no"), resultSet.getTimestamp("reservation_date"));
            }
            System.out.println("+----+--------------+----------+------------+-------------------+");
        }
    }

    private static void getRoomDetails(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter reservation ID: ");
        int reservationId = sc.nextInt();
        String sql = "SELECT room_no FROM reservation WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Room Number: " + resultSet.getInt("room_no"));
            } else {
                System.out.println("Reservation not found.");
            }
        }
    }

    private static void updateReservation(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter reservation ID to update: ");
        int reservationId = sc.nextInt();
        System.out.print("Enter new guest name: ");
        String guestName = sc.next();
        System.out.print("Enter new room number: ");
        int roomNumber = sc.nextInt();
        System.out.print("Enter new contact number: ");
        String contactNumber = sc.next();
        String sql = "UPDATE reservation SET guest_name = ?, room_no = ?, contact_no = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, guestName);
            pstmt.setInt(2, roomNumber);
            pstmt.setString(3, contactNumber);
            pstmt.setInt(4, reservationId);
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows > 0 ? "Reservation updated." : "Update failed. Reservation not found.");
        }
    }

    private static void deleteReservation(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter reservation ID to delete: ");
        int reservationId = sc.nextInt();
        String sql = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows > 0 ? "Reservation deleted." : "Deletion failed. Reservation not found.");
        }
    }
}
