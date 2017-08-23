package object;

import java.util.ArrayList;
import java.util.Set;

public class App {

    private String name, description;

    private double averageRating;
    
    private int ratingCount;
    
    private int fiveStarsRatings;
    
    private int fourStarsRatings;
    
    private ArrayList<String> recentChanges;
    
	public ArrayList<String> getRecentChanges() {
		return recentChanges;
	}

	public void setRecentChanges(ArrayList<String> recentChanges) {
		this.recentChanges = recentChanges;
	}

	public App() {
        super();
        this.recentChanges = new ArrayList<String>();
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getFiveStarsRatings() {
		return fiveStarsRatings;
	}

	public void setFiveStarsRatings(int fiveStarsRatings) {
		this.fiveStarsRatings = fiveStarsRatings;
	}

	public int getFourStarsRatings() {
		return fourStarsRatings;
	}

	public void setFourStarsRatings(int fourStarsRatings) {
		this.fourStarsRatings = fourStarsRatings;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public void addChange(String text) {
		this.recentChanges.add(text);		
	}
    
    
    /*public String toString() {
        return "{ code: " + code + ",\n"
                + "name: " + name + ",\n"
                + "credits" + credits + ", \n"
                + "prerequisites: " + prerequisites;
    }*/
}
