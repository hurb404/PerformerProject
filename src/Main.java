import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] possibleVenues = {"thiruvananthapuram", "panaji", "gandhinagar", "chandigarh", "shimla", "srinagar",
                "ranchi", "bengaluru", "bhopal", "mumbai", "imphal", "shillong",
                "aizawl", "kohima", "bhubaneswar", "jaipur", "gangtok", "chennai", "agartala",
                "lucknow", "dehradun", "kolkata", "raipur", "dispur", "panaji", "puducherry",
                "amaravati", "hyderabad", "itanagar", "guwahati", "new delhi"};

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        Performer[] performers = new Performer[10];

        performers[0] = new Performer("John", "Violin");
        performers[1] = new Performer("Arjun", "Piano");
        performers[2] = new Performer("Karam", "Drums");
        performers[3] = new Performer("Johan", "Trumpet");
        performers[4] = new Performer("Anshuman", "Tabla");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                performers[i].perform(possibleVenues[j]);
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("Please choose what you would like to do: ");
            System.out.println("1. List Orchestra");
            System.out.println("2. Add Performer");
            System.out.println("3. Perform");
            System.out.println("4. Find Best Performance");
            System.out.println("5. Sort Performers");
            System.out.println("6. List by Venue");
            System.out.println("7. Exit");
            String input = sc.next();

            if (input.equals("7")) {
                break;
            } else if (input.equals("1")) {
                listOrchestra(performers);
            } else if (input.equals("2")) {
                for (int i = 0; i < performers.length; i++) {
                    if (performers[i] == null) {
                        performers[i] = addPerformer();
                        break;
                    }
                }
            } else if (input.equals("3")) {
                Perform(performers);
            } else if (input.equals("4")) {
                findBestPerformance(performers);
            } else if (input.equals("5")) {
                sortPerformers(performers);
            } else if (input.equals("6")) {
                System.out.println("Enter a venue: ");
                String ve = sc.next();
                listByVenue(performers, ve);
            } else {
                System.out.println("Please enter a valid input - RESTARTING PROGRAM");
                continue;
            }
        }
    }

    public static double[] bubbleSort(double[] arr, Performer[] performers) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    Performer tempPerformer = performers[j];
                    performers[j] = performers[j + 1];
                    performers[j + 1] = tempPerformer;
                }
        return arr;
    }

    public static void listOrchestra(Performer[] performers) {
        for (int i = 0; i < performers.length; i++) {
            if (performers[i] != null) {
                System.out.println("Name: " + performers[i].getName());
                System.out.println("Instrument: " + performers[i].getInstrument());
                System.out.println("Average rating: " + performers[i].aveRating());
            }
        }
    }

    public static Performer addPerformer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please name the new performer: ");
        String newName = sc.nextLine();

        System.out.println("Please name the new instrument of the new performer: ");
        String newInstrument = sc.nextLine();

        Performer newP = new Performer(newName, newInstrument);
        return newP;
    }

    public static void Perform(Performer[] performers) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < performers.length; i++) {
            if (performers[i] != null) {
                System.out.println((i + 1) + " " + "performer: " + performers[i].getName());
            }
        }

        System.out.println("Please select a performer by their number: ");
        if (sc.nextInt() != 1 || sc.nextInt() != 2 || sc.nextInt() != 3 || sc.nextInt() != 4 || sc.nextInt() != 5 || sc.nextInt() != 6 || sc.nextInt() != 7 || sc.nextInt() != 8 || sc.nextInt() != 9 || sc.nextInt() != 0) {
            System.out.println("Retry");
            return;
        }

        int perfNumber = (sc.nextInt() - 1);

        System.out.println("Please enter the city where you want them to perform: ");
        String input = sc.next();

        performers[perfNumber].perform(input);
    }

    public static void findBestPerformance(Performer[] performers) {
        Performer bestPerformer = null;
        double bestScore = 0;
        String bestVenue = "";
        for (int i = 0; i < performers.length; i++) {
            Performer performer = performers[i];
            if (performer != null) {
                for (int j = 0; j < performer.getVenues().length; j++) {
                    if (performer.getRatings()[j] > bestScore) {
                        bestPerformer = performer;
                        bestScore = performer.getRatings()[j];
                        bestVenue = performer.getVenues()[j];
                    }
                }
            }
        }
        if (bestPerformer != null) {
            System.out.println("Best Performance:");
            System.out.println("Name: " + bestPerformer.getName());
            System.out.println("Instrument: " + bestPerformer.getInstrument());
            System.out.println("Rating: " + bestScore);
            System.out.println("Venue: " + bestVenue);
        } else {
            System.out.println("No performances have been recorded.");
        }
    }

    public static Performer[] sortPerformers(Performer[] performers) {
        double[] averageRatings = new double[performers.length];

        for (int i = 0; i < performers.length; i++) {
            if (performers[i] != null) {
                averageRatings[i] = performers[i].aveRating();
            } else {
                continue;
            }
        }

        double[] sortedAverageRatings = bubbleSort(averageRatings, performers);
        Performer[] sortedPerformers = new Performer[performers.length];

        for (int i = 0; i < performers.length; i++) {
            int index = Arrays.binarySearch(averageRatings, sortedAverageRatings[i]);
            sortedPerformers[i] = performers[index];
        }

        return sortedPerformers;
    }

    public static void listByVenue(Performer[] performers, String venue) {
        venue = venue.toLowerCase();
        boolean found = false;
        for (int i = 0; i < performers.length; i++) {
            Performer performer = performers[i];
            if (performer != null) {
                for (int j = 0; j < performer.getVenues().length; j++) {
                    if (performer.getVenues()[j] != null && performer.getVenues()[j].equals(venue)) {
                        found = true;
                        System.out.println("Name: " + performer.getName());
                        System.out.println("Instrument: " + performer.getInstrument());
                        System.out.println("Rating: " + performer.getRatings()[j]);
                        System.out.println("Venue: " + performer.getVenues()[j]);
                        System.out.println("---");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("No performers have performed at " + venue + ".");
        }
    }
}
