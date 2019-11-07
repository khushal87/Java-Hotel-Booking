package HotelBooking;

class Booking {
    String name;
    int id, number, capacity, check_in_date, check_out_date, check_in_month, check_out_month;

    Booking(String visitor_name, int guest_id, int room_number, int room_capacity, int check_in_date, int check_out_date, int check_in_month, int check_out_month) {
        this.name = visitor_name;
        this.id = guest_id;
        this.number = room_number;
        this.capacity = room_capacity;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.check_in_month = check_in_month;
        this.check_out_month = check_out_month;
    }
}
