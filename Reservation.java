package HotelReservationSystem;
import java.io.*;
import java.util.*;

public class ReservationSystem {
    private final String BOOKING_FILE = "data/bookings.txt";
    private Hotel hotel;

    public ReservationSystem(Hotel hotel) {
        this.hotel = hotel;
    }

    public Booking makeBooking(String name, String category) {
        Room room = hotel.bookRoom(category);
        if (room == null) return null;
        Booking booking = new Booking(name, room);
        saveBooking(booking);
        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        List<Booking> bookings = loadBookings();
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                b.cancel();
                hotel.updateRoomAvailability(b.roomId, true);
                saveAllBookings(bookings);
                return true;
            }
        }
        return false;
    }

    public void showAllBookings() {
        for (Booking b : loadBookings()) {
            System.out.println(b.toString());
        }
    }

    private void saveBooking(Booking b) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE, true))) {
            writer.write(b.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving booking: " + e.getMessage());
        }
    }

    private List<Booking> loadBookings() {
        List<Booking> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Booking.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
        return list;
    }

    private void saveAllBookings(List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE))) {
            for (Booking b : bookings) {
                writer.write(b.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }
}
