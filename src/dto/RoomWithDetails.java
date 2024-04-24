package dto;

import entity.room.Room;
import entity.room.RoomDetails;

public class RoomWithDetails {
    private Room room;
    private RoomDetails roomDetails;

    public RoomWithDetails(Room room, RoomDetails roomDetails) {
        this.room = room;
        this.roomDetails = roomDetails;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }
}
