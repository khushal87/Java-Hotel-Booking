package HotelBooking;

//class representing data of the guest who visited the hotel
class Guest {
    int id;
    String name;

    Guest(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Guest " + this.name + " has been created with Guest Id: " + this.id);
    }
}
