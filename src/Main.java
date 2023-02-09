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

            if (performers[i] == null) {
                continue;
            } else {
                performers[i].perform(possibleVenues[random.nextInt(0, possibleVenues.length)]);
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
            System.out.println("7. Find Best Venue");
            System.out.println("8. Exit");
            int input = sc.nextInt();

            if (input == 8) {
                break;
            } else if (input == 1) {
                listOrchestra(performers);
            } else if (input == 2) {
                for (int i = 0; i < performers.length; i++) {
                    if (performers[i] == null) {
                        performers[i] = addPerformer();
                        break;
                    }
                }
            } else if (input == 3) {
                Perform(performers);
            } else if (input == 4) {
                FindBestPerformance(performers);
            } else if (input == 5) {
                sortPerformers(performers);
            } else if (input == 6) {
                listByVenue(performers);
            } else if (input == 7) {
                continue; //TODO: make FindBestVenue function
            } else {
                System.out.println("Please enter a valid input - RESTARTING PROGRAM");
                continue;
            }
        }
    }

    public static double[] bubbleSort(double[] arr, Performer[] performers) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1]) {
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    Performer tempPerformer = performers[j];
                    performers[j] = performers[j+1];
                    performers[j+1] = tempPerformer;
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
                System.out.println((i+1) + " " + "performer: " + performers[i].getName());
            }
        }

        System.out.println("Please select a performer by their number: ");
        int perfNumber = (sc.nextInt() - 1);

        System.out.println("Please enter the city where you want them to perform: ");
        String input = sc.next();

        performers[perfNumber].perform(input);
    }

    public static void FindBestPerformance(Performer[] performers) {
        double[] highestRatings = new double[performers.length];
        int[] indices = new int[performers.length];
        double[] currentRating;

        for (int i = 0; i < performers.length; i++) {
            if (performers[i] != null) {
                currentRating = performers[i].getRatings();
            } else {
                break;
            }

            double greatest = 0;

            for (int j = 0; j < currentRating.length; j++) {
                if (currentRating[j] > greatest) {
                    greatest = currentRating[j];
                }
            }

            highestRatings[i] = greatest;
            indices[i] = i;
        }

        double newGreatest = 0;
        int finalIndex = 0;
        for (int i = 0; i < highestRatings.length; i++) {
            if (highestRatings[i] > newGreatest) {
                newGreatest = highestRatings[i];
                finalIndex = i;
            }
        }

        System.out.println("Best performer: " + performers[finalIndex].getName());
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

    public static void listByVenue(Performer[] performers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a venue: ");
        String input = sc.next();
        input = input.toLowerCase();
        int counter = 0;
        int[] indices = new int[10];
        int currentIndex = -1;

        for (int i = 0; i < performers.length; i++) {
            if (performers[i] != null) {
                String[] vens = performers[i].getVenues();
                currentIndex ++;

                for (int j = 0; j < vens.length; j++) {
                    if (vens[j].equals(input)) {
                        counter ++;
                        indices[currentIndex] = i;
                    }
                }
            }
        }

        for (int i = 0; i < counter; i++) {
            if (counter != 0) {
                if (performers[indices[i]] != null) {
                    System.out.println(performers[indices[i]].getName());
                }
            }
        }
    }
}
