package javaevents;

import java.text.DecimalFormat;
import java.time.DateTimeException;
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
        boolean inputIsValid = false;
        String inputDate = null;
        int inputCapacity = 0;

        // User input for event creation
        System.out.println("--- Gestore eventi ---");
        System.out.println("Inserire un nuovo evento");
        do {
            System.out.print("\nTitolo: ");
            inputTitle = scan.nextLine();
        } while (inputTitle.isEmpty());

        // Decimal format for days and month

        do {
            try {
                inputIsValid = true;
                DecimalFormat decimalFormatDayMonth = new DecimalFormat("00");
                DecimalFormat decimalFormatYear = new DecimalFormat("0000");
                System.out.println("\nData");
                System.out.print("Anno: ");
                inputDate = decimalFormatYear.format(Integer.parseInt(scan.nextLine()));
                System.out.print("Mese: ");
                inputDate += "-" + decimalFormatDayMonth.format(Integer.parseInt(scan.nextLine()));
                System.out.print("Giorno: ");
                inputDate += "-" + decimalFormatDayMonth.format(Integer.parseInt(scan.nextLine()));

                // Input capacity
                System.out.print("\nCapienza della location: ");
                inputCapacity = Integer.parseInt(scan.nextLine());

                // Event creation and show
                new Event(inputTitle, inputDate, inputCapacity);
            } catch (DateTimeException e) {
                System.out.println("La data è stata inserita in maniera incorreta");
                inputIsValid = false;
            } catch (NumberFormatException e) {
                System.out.println("Non è stato inserito un numero");
                inputIsValid = false;
            } catch (DateInPastException e) {
                System.out.println(e);
                inputIsValid = false;
            }
        } while (!inputIsValid);


        Event eventOne = new Event(inputTitle, inputDate, inputCapacity);
        System.out.println(eventOne.toString());
        // Booking
        System.out.print("\nVuoi effettuare delle prenotazioni (s per si)? ");
        userSelection = scan.nextLine();

        if (userSelection.equalsIgnoreCase("s")) {
            int inputBooking = 0;

            inputIsValid = false;

            while (!inputIsValid) {
                try {
                    System.out.print("Quanti posti vuoi prenotare (0 per annullare)? ");
                    inputBooking = Integer.parseInt(scan.nextLine());
                    inputIsValid = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Si prega di inserire un numero valido\n");
                    inputIsValid = false;
                }
            }

            if (inputBooking > eventOne.reservetionAvailable()) {
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

        showReport(eventOne);

        // Cancel reservation
        System.out.print("\nVuoi effettuare delle disdette (s per si)? ");
        userSelection = scan.nextLine();

        if (eventOne.getBooked() == 0) {
            System.out.println("Non ci sono posti prenotati.");
        } else if (userSelection.equalsIgnoreCase("s")) {
            int inputCancelBooking = 0;
            inputIsValid = false;

            while (!inputIsValid) {
                try {
                    System.out.print("Quanti posti vuoi disdire (0 per annullare)? ");
                    inputCancelBooking = Integer.parseInt(scan.nextLine());
                    inputIsValid = true;

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
                } catch (IllegalArgumentException e) {
                    System.out.println("Si prega di inserire un numero valido\n");
                    inputIsValid = false;
                }
            }
        }

        showReport(eventOne);
    }

    public static void showReport(Event event) {
        System.out.println("\n--------------------------------");
        System.out.print("Posti prenotati: " + event.getBooked() + " - Posti disponibili: " + event.reservetionAvailable());
        System.out.println("\n--------------------------------");
    }
}