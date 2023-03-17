package javaevents;

public class Main {
    public static void main(String[] args) {

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
    }
}