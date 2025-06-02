package HotelReservationSystem;
public class Room {
    private String roomId;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(String roomId, String category, double price, boolean isAvailable) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getRoomId() { return roomId; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return roomId + "," + category + "," + price + "," + isAvailable;
    }

    public static Room fromString(String line) {
        String[] parts = line.split(",");
        return new Room(parts[0], parts[1], Double.parseDouble(parts[2]), Boolean.parseBoolean(parts[3]));
    }
}
