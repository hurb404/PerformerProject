import java.util.*;

public class Main {

    // TODO:
//    Perform (list out all performers and allow user to select one, then enter a city)
//*Bonus* Find Best Venue (Find what venue gives the highest performance rating to all performers)
//    Exit
    public static void main(String[] args) {
        String[] possibleVenues = {"Thiruvananthapuram", "Panaji", "Gandhinagar", "Chandigarh", "Shimla", "Srinagar",
                                   "Ranchi", "Bengaluru", "Thiruvananthapuram", "Bhopal", "Mumbai", "Imphal", "Shillong",
                                   "Aizawl", "Kohima", "Bhubaneswar", "Jaipur", "Gangtok", "Chennai", "Agartala",
                                   "Lucknow", "Dehradun", "Kolkata", "Raipur", "Dispur", "Panaji", "Puducherry",
                                   "Amaravati", "Hyderabad", "Itanagar", "Guwahati", "New Delhi"};

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
                addPerformer();
            } else if (input == 3) {
                continue; //TODO: make the perform function
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

    public static double[] bubbleSort(double[] arr, int[] indices) {
        int n = arr.length;

        // Iterate over the array, starting from the end and working backwards
        for (int i = n - 1; i > 0; i--) {
            // Flag to track whether any swaps were made in the current iteration
            boolean swapped = false;

            // Iterate over the array up to the current position
            for (int j = 0; j < i; j++) {
                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    int tempo = indices[j];

                    arr[j] = arr[j + 1];
                    indices[j] = indices[j + 1];

                    arr[j + 1] = temp;
                    indices[j + 1] = tempo;

                    swapped = true;
                }
            }

            // If no swaps were made in the current iteration, the array is already sorted
            if (!swapped) {
                break;
            }
        }

        return arr;
    }

    public static int[] bubbleSort1(double[] arr, int[] indices) {
        int n = arr.length;

        // Iterate over the array, starting from the end and working backwards
        for (int i = n - 1; i > 0; i--) {
            // Flag to track whether any swaps were made in the current iteration
            boolean swapped = false;

            // Iterate over the array up to the current position
            for (int j = 0; j < i; j++) {
                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    int tempo = indices[j];

                    arr[j] = arr[j + 1];
                    indices[j] = indices[j + 1];

                    arr[j + 1] = temp;
                    indices[j + 1] = tempo;

                    swapped = true;
                }
            }

            // If no swaps were made in the current iteration, the array is already sorted
            if (!swapped) {
                break;
            }
        }

        return indices;
    }

    public static void listOrchestra(Performer[] performers) {
        for (int i = 0; i < performers.length; i++) {
            System.out.println("Name: " + performers[i].getName());
            System.out.println("Instrument: " + performers[i].getInstrument());
            System.out.println("Average rating: " + performers[i].aveRating());
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
            System.out.println((i+1) + "performer: " + performers[i].getName());
        }

        System.out.println("Please select a performer by their number: ");
        int perfNumber = (sc.nextInt() - 1);
    }

    public static void FindBestPerformance(Performer[] performers) {
        double[] highestRatings = new double[performers.length];
        int[] indices = new int[performers.length];
        double[] currentRating;

        for (int i = 0; i < performers.length; i++) {
            currentRating = performers[i].getRatings();
            double greatest = 0;

            for (int j = 0; j < currentRating.length; j++) {
                if (currentRating[i] > greatest) {
                    greatest = currentRating[i];
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
        double[] averageRatings = new double[10];
        int[] indices = new int[10];

        for (int i = 0; i < performers.length; i++) {
            averageRatings[i] = performers[i].aveRating();
            indices[i] = i;
        }

        double[] sorterAverageRatings = bubbleSort(averageRatings, indices);
        int[] indexes = bubbleSort1(averageRatings, indices);

        for (int i = 0; i < indices.length; i++) {
            if (performers[i] != performers[indices[i]]) {
                performers[i] = performers[indexes[i]];
            } else {
                continue;
            }
        }

        return performers;
    }

    public static void listByVenue(Performer[] performers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a venue: ");
        String input = sc.nextLine();
        int counter = 0;
        int[] indices = new int[10];
        int currentIndex = -1;

        for (int i = 0; i < performers.length; i++) {
            String[] vens = performers[i].getVenues();
            currentIndex ++;

            for (int j = 0; j < vens.length; j++) {
                if (vens[i].equals(input)) {
                    counter ++;
                    indices[currentIndex] = i;
                }
            }
        }

        for (int i = 0; i < performers.length; i++) {
            System.out.println(performers[indices[i]].getName());
        }
    }
}
