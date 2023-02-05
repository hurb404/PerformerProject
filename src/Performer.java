import java.util.*;
import java.text.*;

public class Performer {
    DecimalFormat df = new DecimalFormat("##.#");
    Random random = new Random();
    private String name;
    private String instrument;
    private String[] venues = new String[1000];
    private double[] ratings = new double[1000];

    public Performer() {
        name = "Johan";
        instrument = "Trumpet";
        venues = new String[]{"Mumbai", "Delhi", "Dubai", "Singapore", "London"};
        ratings = new double[]{random.nextDouble(1.0, 10.0), random.nextDouble(1.0, 10.0),
                random.nextDouble(1.0, 10.0), random.nextDouble(1.0, 10.0), random.nextDouble(1.0, 10.0)};
    }

    public Performer(String name, String instrument) {
        this.name = name;
        this.instrument = instrument;
    }

    public void perform(String venue) {
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] == 0) {
                ratings[i] = random.nextDouble(1.0, 10.0);
            }

            if(venues[i].equals(null)) {
                venues[i] = venue;
            }
        }
    }

    public String getName() {
        return name;
    }
    public String getInstrument() {
        return instrument;
    }
    public String[] getVenues() {
        return venues;
    }
    public double[] getRatings() {
        return ratings;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
    public void setVenues(String[] venues) {
        this.venues = venues;
    }
    public void setRatings(double[] ratings) {
        this.ratings = ratings;
    }

    public double aveRating() {
        double total = 0;
        int count = 0;

        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] != 0) {
                total += ratings[i];
                count ++;
            }
        }

        return (total/count);
    }
}
