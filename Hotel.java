package HotelReservationSystem;
import java.io.*;
import java.util.*;

public class Hotel {
    private List<Room> rooms;
    private final String ROOM_FILE = "data/rooms.txt";

    public Hotel() {
        rooms = loadRooms();
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> results = new ArrayList<>();
        for (Room r : rooms) {
            if (r.getCategory().equalsIgnoreCase(category) && r.isAvailable()) {
                results.add(r);
            }
        }
        return results;
    }

    public Room bookRoom(String category) {
        for (Room r : rooms) {
            if (r.getCategory().equalsIgnoreCase(category) && r.isAvailable()) {
                r.setAvailable(false);
                saveRooms();
                return r;
            }
        }
        return null;
    }

    public void updateRoomAvailability(String roomId, boolean available) {
        for (Room r : rooms) {
            if (r.getRoomId().equals(roomId)) {
                r.setAvailable(available);
                break;
            }
        }
        saveRooms();
    }

    private List<Room> loadRooms() {
        List<Room> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ROOM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Room.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
        }
        return list;
    }

    private void saveRooms() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOM_FILE))) {
            for (Room r : rooms) {
                writer.write(r.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }
}

