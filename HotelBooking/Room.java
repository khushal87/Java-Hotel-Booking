package HotelBooking;

//Class representing details of the rooms of the hotel. It also is used for checking the occupancy
class Room {
    int number, capacity;
    boolean[] occupied;

    Room(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        this.occupied = new boolean[366];
    }
}
