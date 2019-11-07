package HotelBooking;

import java.util.Scanner;

public class HotelBookingTest {
    //for console clear
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //driver (main) function to run the program
    public static void main(String[] args) {
        HotelBook h1;
        h1= new Hotel();
        int choice;
        Scanner obj = new Scanner(System.in);
        System.out.println("--------------------------------------------");
        System.out.println("----- Welcome to FedUni Hotel Bookings -----");
        System.out.println("--------------------------------------------");
        do {
            System.out.println("Main Menu - please select an option:");
            System.out.println("  1.) Add a guest");
            System.out.println("  2.) Add a room");
            System.out.println("  3.) Add a booking");
            System.out.println("  4.) View Bookings");
            System.out.println("  5.) Quit");
            System.out.println("Enter Your option - ");
            choice = obj.nextInt();
            obj.nextLine();
            switch (choice) {
                case 1: {
                    h1.add_guest();
                    break;
                }
                case 2:
                {
                    h1.add_room();
                    break;
                }
                case 3:
                {
                    h1.add_booking();
                    break;
                }
                case 4: {
                    h1.view_booking();
                    break;
                }
                default:
                    break;
            }
        } while (choice != 5);
        System.out.println("Thanks for the visit :)");
        clearScreen();
    }
}
