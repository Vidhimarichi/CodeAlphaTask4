package HotelReservationSystem;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private String customerName;
    String roomId;
    private String category;
    private String status;
    private double price;

    public Booking(String customerName, Room room) {
        this.bookingId = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.roomId = room.getRoomId();
        this.category = room.getCategory();
        this.status = "Confirmed";
        this.price = room.getPrice();
    }

    public String getBookingId() { return bookingId; }
    public String getStatus() { return status; }
    public void cancel() { this.status = "Cancelled"; }

    @Override
    public String toString() {
        return bookingId + "," + customerName + "," + roomId + "," + category + "," + status + "," + price;
    }

    public static Booking fromString(String line) {
        String[] parts = line.split(",");
        Booking b = new Booking(parts[1], new Room(parts[2], parts[3], Double.parseDouble(parts[5]), false));
        b.bookingId = parts[0];
        b.status = parts[4];
        return b;
    }
}
