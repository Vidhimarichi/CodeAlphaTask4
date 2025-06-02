package HotelReservationSystem;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        ReservationSystem system = new ReservationSystem(hotel);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category: ");
                    String cat = scanner.nextLine();
                    List<Room> results = hotel.searchAvailableRooms(cat);
                    for (Room r : results) System.out.println(r.toString());
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    Booking booking = system.makeBooking(name, category);
                    if (booking != null) {
                        System.out.println("Booking successful! ID: " + booking.getBookingId());
                    } else {
                        System.out.println("No rooms available.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Booking ID to cancel: ");
                    String id = scanner.nextLine();
                    if (system.cancelBooking(id)) {
                        System.out.println("Booking cancelled.");
                    } else {
                        System.out.println("Booking ID not found.");
                    }
                    break;
                case 4:
                    system.showAllBookings();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
