package javaevents;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /* OLD TEST
        //TEST
        Event eventOne = new Event("Sagra del gorgonzola", "2023-10-08", 500);
        System.out.println(eventOne.toString());

        //TESTING EXCEPTIONS
        /*
        Event eventTwo = new Event("Concerto per archi e frecce", "2010-3-18", 200);
        System.out.println(eventOne.toString());
         */

        /*
        Event eventTwo = new Event("Concerto per archi e frecce", "2010-03-18", 200);
        System.out.println(eventOne.toString());
         */

        /*
        Event eventTwo = new Event("Concerto per archi e frecce", "2025-03-18", -200);
        System.out.println(eventOne.toString());
         */

        /*
        Event eventTwo = new Event("Concerto per archi e frecce", "2025-03-18", 200);
        eventTwo.cancelDate();
         */

        /*
        Event eventTwo = new Event("Concerto per archi e frecce", "2025-03-18", 10);
        for (int i = 0; i < 30; i++) {
            eventTwo.reserveDate();
        }
        eventTwo.cancelDate();
        */

        Scanner scan = new Scanner(System.in);
        String inputTitle;
        String userSelection;

        // User input for event creation
        System.out.println("--- Gestore eventi ---");
        System.out.println("Inserire un nuovo evento");
        System.out.print("\nTitolo: ");
        do {
            inputTitle = scan.nextLine();
        } while (inputTitle.isEmpty());
        System.out.println("\nData");
        System.out.print("Anno: ");
        String inputDate = scan.nextLine();
        System.out.print("Mese: ");
        inputDate += "-" + scan.nextLine();
        System.out.print("Giorno: ");
        inputDate += "-" + scan.nextLine();
        System.out.print("\nCapienza della location: ");
        int inputCapacity = Integer.parseInt(scan.nextLine());

        // Event creation and show
        Event eventOne = new Event(inputTitle, inputDate, inputCapacity);
        System.out.println(eventOne.toString());

        // Booking
        System.out.print("\nVuoi effettuare delle prenotazioni (s per si)? ");
        userSelection = scan.nextLine();

        if (userSelection.equalsIgnoreCase("s")) {

            System.out.print("Quanti posti vuoi prenotare? ");
            int inputBooking = Integer.parseInt(scan.nextLine()); //ADD Handle exception for wrong input type


            if (inputBooking > eventOne.reservetionAvailable()) { //ADD Handle exception for wrong input type
                System.out.println("Non ci sono sufficienti posti liberi:");
                System.out.println("Posti in totale: " + eventOne.getCapacity() + " - Posti disponibili: " + eventOne.reservetionAvailable());


                boolean endSelection = false;
                do {
                    System.out.println("\nCosa vuoi fare?");
                    System.out.println("1 - Prenota " + eventOne.reservetionAvailable() + " posti.");
                    System.out.println("2 - Annulla prenotazione");
                    System.out.print("-> ");

                    userSelection = scan.nextLine();

                    switch (userSelection) {
                        case "1":
                            endSelection = true;

                            for (int i = 0; i < eventOne.reservetionAvailable(); i++) {
                                eventOne.reserveDate();
                            }
                            break;
                        case "2":
                            endSelection = true;
                            break;
                        default:
                            System.out.println("- Selezione sbagliata -\n");
                    }
                } while (endSelection);
            } else {
                for (int i = 0; i < inputBooking; i++) {
                    eventOne.reserveDate();
                }
            }
        }

        System.out.println("\n--------------------------------");
        System.out.println("Posti prenotati: " + eventOne.getBooked() + " - Posti disponibili: " + eventOne.reservetionAvailable());


        // Cancel reservation
        System.out.print("\nVuoi effettuare delle disdette (s per si)? ");
        userSelection = scan.nextLine();

        if (userSelection.equalsIgnoreCase("s")) {

            System.out.print("Quanti posti vuoi disdire? ");
            int inputCancelBooking = Integer.parseInt(scan.nextLine()); //ADD Handle exception for wrong input type

            if (inputCancelBooking > eventOne.getBooked()) { //ADD Handle exception for wrong input type
                System.out.println("Vuoi disdire più prenotazioni di quante ce ne sono.");
                System.out.println("Posti prenotati: " + eventOne.getBooked());

                System.out.println("Vuoi disdire tutte le prenotazioni (s per si)? ");
                userSelection = scan.nextLine();

                if (userSelection.equalsIgnoreCase("s")) {
                    inputCancelBooking = eventOne.getBooked();
                } else {
                    inputCancelBooking = 0;
                }
            }

            for (int i = 0; i < inputCancelBooking; i++) {
                eventOne.cancelDate();
            }
        }

        System.out.println("\n--------------------------------");
        System.out.println("Posti prenotati: " + eventOne.getBooked() + " - Posti disponibili: " + eventOne.reservetionAvailable());
    }
}