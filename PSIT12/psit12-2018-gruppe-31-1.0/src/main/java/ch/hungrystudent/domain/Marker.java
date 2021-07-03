package ch.hungrystudent.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Marker class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class Marker {

	private long id;
	private String name;
	private String address;
	private String website;
	private String description;
	private String openingHours;
	private List<Kitchen> kitchen;
	private Price price;
	private boolean vegetarian;
	private boolean takeAway;
	private String image;
	private String location;

	// Modifier of constructor shouldn't be private even with builder-pattern
	// because class should be creatable as a bean in jsp pages
	public Marker() {
		this.id = -1;
		this.name = "";
		this.address = "";
		this.website = "";
		this.description = "";
		this.description = "";
		this.openingHours = "";
		this.kitchen = new ArrayList<>();
		this.price = null;
		this.vegetarian = false;
		this.takeAway = false;
		this.image = "";
		this.location = "0, 0";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public List<Kitchen> getKitchens() {
		return kitchen;
	}

	public void setKitchens(List<Kitchen> kitchen) {
		this.kitchen = kitchen;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public boolean isTakeAway() {
		return takeAway;
	}

	public void setTakeAway(boolean takeAway) {
		this.takeAway = takeAway;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static class MarkerBuilder {

		private final Marker marker;

		public MarkerBuilder() {
			this.marker = new Marker();
		}

		public MarkerBuilder setId(long id) {
			marker.id = id;
			return this;
		}

		public MarkerBuilder setName(String name) {
			marker.name = name;
			return this;
		}

		public MarkerBuilder setAddress(String address) {
			marker.address = address;
			return this;
		}

		public MarkerBuilder setWebsite(String website) {
			marker.website = website;
			return this;
		}

		public MarkerBuilder setDescription(String description) {
			marker.description = description;
			return this;
		}

		public MarkerBuilder setOpeningHours(String openingHours) {
			marker.openingHours = openingHours;
			return this;
		}

		public MarkerBuilder setKitchens(List<Kitchen> kitchen) {
			marker.kitchen = kitchen;
			return this;
		}

		public MarkerBuilder setPrice(Price price) {
			marker.price = price;
			return this;
		}

		public MarkerBuilder setVegetarian(boolean vegetarian) {
			marker.vegetarian = vegetarian;
			return this;
		}

		public MarkerBuilder setTakeAway(boolean takeAway) {
			marker.takeAway = takeAway;
			return this;
		}

		public MarkerBuilder setImage(String image) {
			marker.image = image;
			return this;
		}

		public MarkerBuilder setLocation(String location) {
			marker.location = location;
			return this;
		}

		public Marker build() {
			return this.marker;
		}
	}
}
