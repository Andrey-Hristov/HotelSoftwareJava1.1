package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    static String[][] allReservations = RegistrationsClass.reservationsCopy;

    public static void main(String[] args)  throws ParseException {

        Scanner input = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        RegistrationsClass.getFile();

        // Hotel Java is a six floor hotel. There are 10 rooms on each floor from 00 to 09.
        // Every room with number 09 is a luxurious apartment with 3 beds and beautiful sea sight. The rooms with number 08
        // have capacity of 3 beds. The other rooms with even numbers have 2 beds capacity and the rooms with odd number
        // have 1 bed capacity. Rooms from 00 to 05 have a sight to the swimming pool and rooms from 06 to 09 have a beautiful sea sight
        // without room 08 which has a sight to the swimming pool.

        Date today = Calendar.getInstance().getTime();

        int chosenRoom = 0;


        //This array contains the user's information and it is ready to be initialized.
        String[] accommodationInformation = new String[4];
        //This array contains list with available rooks for the chosen date and it is later filtrated by the bed count and sight type.
        int[] availableRooms = {100, 101, 103, 105, 107, 109, 200, 202, 206, 208, 207, 209, 301, 302, 303, 307, 309, 400,
                401, 403, 405, 406, 407, 503, 504, 507, 508, 509, 600, 601, 602, 604, 605, 608, 609};
        //This is the bed capacity of a room.

        char toChoose;
        // This char is used when the user miss clicks so he can choose what to do again.
        char toChooseSpare;
        // This char is used when the user has chosen one option but after that he wants to choose the other one.
        char chooseToRegisterAfterReadingInfo;
        char chooseTypeOfSight = '0';
        // press c to end the program,
        char breakWhile;



        while (true) {
            // By the way after the first iteration of the loop if you want to choose something just press the "toChoose" letter twice.
            // I don't know why it is doing that.  It wouldn't do that if i did the choosing with switch case and numbers instead of letters, but it is way
            // too late now.

            //Press 'x' to stop the program.

            printWelcomeScreen();

            toChoose = sc.next().charAt(0);

            if (toChoose == 'a' || toChoose == 'A') {

                printInfoAboutHotel();

            } else if (toChoose == 'b' || toChoose == 'B') {

                register(chooseTypeOfSight, availableRooms, accommodationInformation, allReservations, today);

            } else if (toChoose == 'c' || toChoose == 'C') {

                System.out.println("Enter your first and your last name:");
                String customer = input.next();
                System.out.println("Enter number of the room:");
                int roomNumber = input.nextInt();
                System.out.println("Start date of the reservation:");
                String startDate = input.next();
                int flag = 0;
                for (String[] reservation : allReservations) {
                    if (roomNumber == chosenRoom && customer.equals(accommodationInformation[0]) && startDate.equals(accommodationInformation[1])) {
                        flag++;
                        allReservations = deleteElementOfReservations2dArray(allReservations, reservation);
                    }
                }
                if (flag == 0) {
                    System.out.println("Error. Such reservation was not found.");
                }

            }
            RegistrationsClass.reservationsCopy = allReservations;
            for (String[] reservation : RegistrationsClass.reservationsCopy) {
                System.out.println(Arrays.toString(reservation));
            }

            breakWhile = sc.next().charAt(0);
            if (breakWhile == 'x' || breakWhile == 'X') {
                break;
            }

        }

    }

    public static void printAvailableRooms(int[] available) {

        for (int i = 0; i < available.length; i++) {
            if (i == 0) {
                System.out.println("                                       Available rooms");
            }

            System.out.print(available[i] + ", ");
        }
        System.out.println(" ");
        System.out.println(" ");
    }


    public static void printInfoAboutHotel() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Java Hotel is situated at one of the most serene areas of Laoag City, Ilocos Norte."
                + "\n" + " It is famous for its unique Balinese Moroccan atmosphere, exquisite Spanish design and warm Ilocano touch. "
                + "\n" + "Java Hotel offers 1st class services, with affordable rates & exceptional amenities."
                + "\n" + "Its recreational facilities will surely meet your needs and expectations for it includes a souvenir shop, "
                + "\n" + "tennis court, fitness gym, swimming pool, and the only wall climbing facility in Laoag City,"
                + "\n" + "as well as The Majestic and The Venetian function halls."
                + "\n");
        System.out.println(" ");
    }


    public static void printWelcomeScreen() {
        System.out.println(" " + "\n" + " ");
        System.out.println(" Welcome to Hotel Java!" + "\n" + "What do you want to do?");
        System.out.println(" ");
        System.out.println("Press 'a' to read information about the hotel.");
        System.out.println(" ");
        System.out.println("Press 'b' to register in the hotel.");
        System.out.println(" ");
        System.out.println("Press 'c' to cancel reservation." + "\n");
    }


    public static void fillAccommodationInfoArray(String[] accommodationInformation) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your first and your last name:  ");
        accommodationInformation[0] = input.nextLine();
        System.out.println(" ");

        System.out.print("Please enter date of accommodation (dd/MM/yyyy) :  ");
        accommodationInformation[1] = input.nextLine();
        System.out.println(" ");

        System.out.print("Please enter date of leaving (dd/MM/yyyy) :  ");
        accommodationInformation[2] = input.nextLine();
        System.out.println(" ");

        System.out.print("Extra message:  ");
        accommodationInformation[3] = input.nextLine();
        System.out.println(" ");

    }


    public static void printAccommodationInfoArray(String[] info, int room) {

        System.out.println(" ");
        System.out.println("{" + room + " " + info[0] + ", " + info[1] + " - " + info[2] + ",  " + info[3] + "}");

    }


    public static void register(char chooseTypeOfSight, int[] availableRooms, String[] accommodationInformation, String[][] reservations, Date today) throws ParseException {

        Scanner input = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        fillAccommodationInfoArray(accommodationInformation);
        printAvailableRooms(availableRooms);

        System.out.print("Please enter the bed capacity of your room. "
                + "Enter '1' for one bed '2' for two beds and '3' for three beds");
        System.out.println(" ");
        int roomCapacity = input.nextInt();

        System.out.println("What kind of sight do you want? "
                + "Press 's' for sea sight or press 'p' for inner court and swimming pool sight.");
        chooseTypeOfSight = sc.next().charAt(0);


        for (int i = 0; i < availableRooms.length; i++) {
            if (roomCapacity == 1 && (chooseTypeOfSight == 'p' || chooseTypeOfSight == 'P')) {
                if (availableRooms[i] % 10 == 1 || availableRooms[i] % 10 == 3 || availableRooms[i] % 10 == 5) {
                    System.out.print(availableRooms[i] + ", ");
                }

            }
            if (roomCapacity == 2 && (chooseTypeOfSight == 'p' || chooseTypeOfSight == 'P')) {
                if (availableRooms[i] % 10 == 0 || availableRooms[i] % 10 == 2 || availableRooms[i] % 10 == 4) {
                    System.out.print(availableRooms[i] + ", ");
                }

            }
            if (roomCapacity == 3 && (chooseTypeOfSight == 'p' || chooseTypeOfSight == 'P')) {
                if (availableRooms[i] % 10 == 8) {
                    System.out.print(availableRooms[i] + ", ");
                }

            }
            if (roomCapacity == 1 && (chooseTypeOfSight == 's' || chooseTypeOfSight == 'S')) {
                if (availableRooms[i] % 10 == 7) {
                    System.out.print(availableRooms[i] + ", ");
                }

            }
            if (roomCapacity == 2 && (chooseTypeOfSight == 's' || chooseTypeOfSight == 'S')) {
                if (availableRooms[i] % 10 == 6) {
                    System.out.print(availableRooms[i] + ", ");
                }

            }
            if (roomCapacity == 3 && (chooseTypeOfSight == 's' || chooseTypeOfSight == 'S')) {
                if (availableRooms[i] % 10 == 9) {
                    System.out.print(availableRooms[i] + ", ");
                }

            }
        }
        System.out.println(" ");
        System.out.print("Please enter a room: ");
        int chosenRoom = input.nextInt();

        if (!checkIfReservationIsValidOrNot(reservations, accommodationInformation, today)) {
            System.out.println("Invalid registration info. Please try again. ");
        } else {
            reservations = addElementTo2dReservationsArray (reservations, accommodationInformation);
            System.out.println("Your reservation is successful! ");
        }

        printAccommodationInfoArray(accommodationInformation, chosenRoom);
    }


    public static String[][] addElementTo2dReservationsArray (String[][] baseArray, String[] elementArray) {
        String[][] newArray = new String[baseArray.length + 1][];
        System.arraycopy(baseArray, 0, newArray, 0, baseArray.length);
        newArray[baseArray.length] = elementArray;
        return newArray;
    }


    public static boolean checkIfReservationIsValidOrNot (String[][] allReservations, String[] checkedInfo, Date today) throws ParseException {
        Date minDate = convertInputToDate(checkedInfo[1]);
        Date maxDate = convertInputToDate(checkedInfo[2]);

        int flag = 0;
        if (minDate.before(today) || maxDate.before(today) || minDate.after(maxDate)) {
            System.out.println("Error: Invalid duration of stay.");
            flag = 1;
        }

        if (Byte.parseByte(checkedInfo[0]) > 20 || Byte.parseByte(checkedInfo[0]) < 0) {
            System.out.println("Error 404: Room not found.");
            flag = 1;

        } else {
            for (String[] reservation : allReservations) {
                if (reservation[0].equals(checkedInfo[0]) &&
                        (checkIfTargetIsBetweenTwoDates(minDate, maxDate, convertInputToDate(reservation[1])) ||
                                checkIfTargetIsBetweenTwoDates(minDate, maxDate, convertInputToDate(reservation[2])) ||
                                checkIfTargetIsBetweenTwoDates(convertInputToDate(reservation[1]), convertInputToDate(reservation[2]), minDate) ||
                                checkIfTargetIsBetweenTwoDates(convertInputToDate(reservation[1]), convertInputToDate(reservation[2]), maxDate))
                ) {
                    System.out.println("Error: room already registered.");
                    flag = 1;
                }
            }
        }
        return flag == 0;
    }


    public static Date convertInputToDate(String input) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return format1.parse(input);
    }


    public static boolean checkIfTargetIsBetweenTwoDates(Date min, Date max, Date target) {
        return target.after(min) && target.before(max);
    }


    public static String[][] deleteElementOfReservations2dArray(String[][] baseArray, String[] toDelete) {

        String[][] newArray = new String[baseArray.length - 1][];
        int index = 0;
        for (int i = 0; i < baseArray.length; i++) {
            if (baseArray[i] == toDelete) {
                index = i;
            }
        }
        System.arraycopy(baseArray, 0, newArray, 0, index);
        if (newArray.length - index >= 0)
            System.arraycopy(baseArray, index + 1, newArray, index, newArray.length - index);
        return newArray;
    }
}