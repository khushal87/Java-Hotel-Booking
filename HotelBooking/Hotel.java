package HotelBooking;
import java.util.ArrayList;
import java.util.Scanner;

//Class for driving the program(main class)
class Hotel implements HotelBook {
    //array list of type guest,room and booking class
    private static ArrayList<Guest> gList;
    private static ArrayList<Room> rList;
    private static ArrayList<Booking> bList;
    private static int counter = 0;

    //constructor
    Hotel() {
        gList = new ArrayList<>();
        rList = new ArrayList<>();
        bList = new ArrayList<>();
    }

    //To add guest to the hotel portal
    public void add_guest() {
        Scanner obj = new Scanner(System.in);
        String gname, flag;
        do {
            System.out.println("Please enter guest name:");
            gname = obj.nextLine();
            Guest tempGuest = new Guest(++counter, gname);
            gList.add(tempGuest);
            System.out.println("Would you like to [A]dd a new guest or [R]eturn to the previous menu?");
            flag = obj.nextLine();
        } while (!flag.equals("R"));
    }

    //to check if any guest is present in the database. Checked by id
    private static boolean guestChecker(int g_id) {
        for (Guest x : gList) {
            if (x.id == g_id) {
                return true;
            }
        }
        return false;
    }

    //to check if the room is present in the database.
    private static boolean roomChecker(int room_no) {
        for (Room x : rList) {
            if (x.number == room_no) {
                return true;
            }
        }
        return false;
    }

    //to get name by id(argument)
    private static String visitor_name(int g_id) {
        String ans = "";
        for (Guest x : gList) {
            if (x.id == g_id) {
                ans = x.name;
            }
        }
        return ans;
    }

    //to add Room info to the hotel's database
    public void add_room() {
        Scanner obj = new Scanner(System.in);
        String flag;
        do {
            System.out.println("Please enter room number: ");
            int room_number = obj.nextInt();
            boolean temporary_data = roomChecker(room_number);
            if (!temporary_data) {
                System.out.println("Please enter room capacity: ");
                int room_capacity = obj.nextInt();
                obj.nextLine();
                Room tempRoom = new Room(room_number, room_capacity);
                rList.add(tempRoom);
                System.out.println("Would you like to [A]dd a new room or [R]eturn to the previous menu?");
            } else {
                System.out.println("Room already exists.");
            }
            flag = obj.nextLine();
        } while (!flag.equals("R"));
    }

    //to fetch capacity by room number(argument)
    private static int getRoomCapacity(int room_no) {
        int flagCheck = 0;
        for (int counter = 0; counter < rList.size(); counter++) {
            Room x = rList.get(counter);
            if (x.number == room_no) {
                flagCheck = counter;
                break;
            }
        }
        Room temp = rList.get(flagCheck);
        return temp.capacity;
    }


    //to fetch booking details by id(argument)
    private static ArrayList<Booking> booking_details(int g_id) {
        ArrayList<Booking> booked_data;
        booked_data = new ArrayList<>();
        for (Booking temporary_data : bList) {
            if (temporary_data.id == g_id)
                booked_data.add(temporary_data);
        }
        return booked_data;
    }

    //to fetch booking detail by number
    private static ArrayList<Booking> booking_details_room(int room_no) {
        ArrayList<Booking> booked_data;
        booked_data = new ArrayList<>();
        for (Booking temp : bList) {
            if (temp.number == room_no)
                booked_data.add(temp);
        }
        return booked_data;
    }

    //function that checks if the room is already booked
    private static Room status_of_room(int room_number) {
        Room ans = null;
        for (Room y : rList) {
            if (y.number == room_number) {
                ans = y;
            }
        }
        return ans;
    }

    //to check room is free for the slot and return true if free
    private static boolean status_of_book(int room_number, int trans_checkin, int trans_checkout) {
        Room room = status_of_room(room_number);
        int ctr = 0;
        for (int counter = trans_checkin; counter <= trans_checkout; counter++) {
            if (!room.occupied[counter]) {
                ctr++;
            }
        }
        return ctr == (trans_checkout - trans_checkin + 1);
    }

    //Converts day and month with to specific format of 365 days of year
    private static int dateToDayNumber(int month, int day) {
        if (month < 1 || month > 12 || day < 1 || day > 31)
            return 0;
        if (month == 1)
            return day;
        if (month == 2)
            return 31 + day;
        if (month == 3)
            return 59 + day;
        if (month == 4)
            return 90 + day;
        if (month == 5)
            return 120 + day;
        if (month == 6)
            return 151 + day;
        if (month == 7)
            return 181 + day;
        if (month == 8)
            return 212 + day;
        if (month == 9)
            return 243 + day;
        if (month == 10)
            return 273 + day;
        if (month == 11)
            return 304 + day;
        return 334 + day;
    }


    //to add Booking to the Hotel's database
    public void add_booking() {
        int gid, x;
        Scanner obj = new Scanner(System.in);
        boolean present;
        String check;
        do {
            do {
                System.out.println("Please enter guest ID:");
                gid = obj.nextInt();
                present = guestChecker(gid);
                if (!present) {
                    System.out.println("Guest does not exist.");
                }
            } while (!present);
            boolean checker;
            do {
                System.out.println("Please enter room number:");
                int room_no = obj.nextInt();
                checker = roomChecker(room_no);
                if (checker) {
                    System.out.println("Please enter number of guests:");
                    int guest_no = obj.nextInt();
                    x = getRoomCapacity(room_no);
                    if (guest_no > x) {
                        System.out.println("Guest count exceeds room capacity of: " + x);
                        checker = false;
                    } else {
                        int checkinMonth, checkinDay, checkoutMonth, checkoutDay;
                        //taking chekin month input
                        do {
                            System.out.println("Please enter check-in month:");
                            checkinMonth = obj.nextInt();
                            if (checkinMonth > 12) {
                                System.out.println("Invalid month.");
                            }
                        } while (checkinMonth > 12);
                        //taking checkin day input
                        do {
                            System.out.println("Please enter check-in day:");
                            checkinDay = obj.nextInt();
                            if (checkinDay > 31) {
                                System.out.println("Invalid day.");
                            }
                        } while (checkinDay > 31);
                        //taking checkout month input
                        do {
                            System.out.println("Please enter check-out month:");
                            checkoutMonth = obj.nextInt();
                            if (checkoutMonth > 12) {
                                System.out.println("Invalid month.");
                            }
                        } while (checkoutMonth > 12);
                        //taking checkout day input
                        do {
                            System.out.println("Please enter check-out day:");
                            checkoutDay = obj.nextInt();
                            if (checkoutDay > 31) {
                                System.out.println("Invalid day.");
                            }
                        } while (checkoutDay > 31);
                        String visitor_name = visitor_name(gid);
                        int checkin_day = dateToDayNumber(checkinMonth, checkinDay);
                        int checkout_day = dateToDayNumber(checkoutMonth, checkoutDay);
                        boolean answer = status_of_book(room_no, checkin_day, checkout_day);
                        boolean ans1;
                        if (answer) {
                            Room room_temp = status_of_room(room_no);
                            for (int counter = checkin_day; counter <= checkout_day; counter++) {
                                room_temp.occupied[counter] = true;
                            }
                            System.out.println("*** Booking successful! ***");
                            Booking book = new Booking(visitor_name, gid, room_no
                                    , x, checkinDay, checkoutDay,
                                    checkinMonth, checkoutMonth);
                            bList.add(book);
                            checker = true;
                        } else {
                            System.out.println("Room is not available during that period.");
                            boolean temporary_data;
                            do {
                                System.out.println("Please enter new room number:");
                                int new_room_no = obj.nextInt();
                                temporary_data = roomChecker(new_room_no);
                                ans1 = status_of_book(new_room_no, checkin_day, checkout_day);
                                Booking book = new Booking(visitor_name, gid, new_room_no, x, checkinDay,
                                        checkoutDay, checkinMonth, checkoutMonth);
                                if (!temporary_data)
                                    System.out.println("Room does not exist.");
                                else if (ans1) {
                                    bList.add(book);
                                    System.out.println("*** Booking successful! ***");
                                    checker = true;
                                    break;
                                }
                            } while (!temporary_data && !ans1);

                        }
                    }
                } else {
                    System.out.println("Room does not exist.");
                }
            } while (!checker);
            System.out.println("Would you like to [A]dd a new booking or [R]eturn to the previous menu?");
            obj.nextLine();
            check = obj.nextLine();
        } while (!check.contains("R"));
    }

    //function that allows us to view Bookings that were successful by guestid and room number
    public void view_booking() {
        Scanner obj = new Scanner(System.in);
        String check;
        System.out.println("Would you like to view [G]uest booked_data, [R]oom booking, or e[X]it?");
        do {
            check = obj.nextLine();
            switch (check) {
                //by guest id
                case "G":
                    int guest_id;
                    boolean temporary_data;
                    do {
                        System.out.println("Please enter guest ID:");
                        guest_id = obj.nextInt();
                        if (!guestChecker(guest_id)) {
                            System.out.println("Guest does not exist.");
                            temporary_data = false;
                        } else {
                            ArrayList<Booking> details = booking_details(guest_id);
                            for (Booking detail : details) {
                                System.out.println("Guest " + detail.id + " : " + detail.name);
                                System.out.println("Booking " + " : " + "Room " + detail.number + " , "
                                        + detail.capacity + " guest(s) from " + detail.check_in_date + "/"
                                        + detail.check_in_month + " to " + detail.check_out_date + "/" + detail.check_out_month);
                            }
                            System.out.println("Would you like to view [G]uest booked_data, [R]oom booking, or e[X]it?");
                            temporary_data = true;
                        }
                    } while (!temporary_data);
                    break;
                //by room number
                case "R":
                    boolean temp1;
                    do {
                        int room_number;
                        System.out.println("Please enter room number:");
                        room_number = obj.nextInt();
                        if (!roomChecker(room_number)) {
                            System.out.println("Room does not exist.");
                            temp1 = false;
                        } else {
                            ArrayList<Booking> details = booking_details_room(room_number);
                            for (Booking detail : details) {
                                System.out.println("Room " + detail.number + " booked_data:");
                                System.out.println("Guest " + detail.id + " - " + detail.name + ", "
                                        + detail.capacity + " guest(s) from " + detail.check_in_date + "/"
                                        + detail.check_in_month + " to " + detail.check_out_date + "/" + detail.check_out_month);
                            }
                            System.out.println("Would you like to view [G]uest booked_data, [R]oom booking, or e[X]it?");
                            temp1 = true;
                        }
                    } while (!temp1);
                    break;
            }
        } while (!check.equals("X"));
    }
}

//End of program