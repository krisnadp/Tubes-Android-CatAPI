package com.test.thecatapi.ui.breed;

import com.google.gson.annotations.SerializedName;

public class BreedResponse {

	@SerializedName("suppressed_tail")
	private int suppressedTail;

	@SerializedName("wikipedia_url")
	private String wikipediaUrl;

	@SerializedName("origin")
	private String origin;

	@SerializedName("description")
	private String description;

	@SerializedName("experimental")
	private int experimental;

	@SerializedName("life_span")
	private String lifeSpan;

	@SerializedName("cfa_url")
	private String cfaUrl;

	@SerializedName("rare")
	private int rare;

	@SerializedName("country_codes")
	private String countryCodes;

	@SerializedName("lap")
	private int lap;

	@SerializedName("id")
	private String id;

	@SerializedName("short_legs")
	private int shortLegs;

	@SerializedName("shedding_level")
	private int sheddingLevel;

	@SerializedName("dog_friendly")
	private int dogFriendly;

	@SerializedName("natural")
	private int natural;

	@SerializedName("rex")
	private int rex;

	@SerializedName("health_issues")
	private int healthIssues;

	@SerializedName("hairless")
	private int hairless;

	@SerializedName("weight")
	private Weight weight;

	@SerializedName("alt_names")
	private String altNames;

	@SerializedName("adaptability")
	private int adaptability;

	@SerializedName("vocalisation")
	private int vocalisation;

	@SerializedName("intelligence")
	private int intelligence;

	@SerializedName("social_needs")
	private int socialNeeds;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("child_friendly")
	private int childFriendly;

	@SerializedName("temperament")
	private String temperament;

	@SerializedName("vcahospitals_url")
	private String vcahospitalsUrl;

	@SerializedName("grooming")
	private int grooming;

	@SerializedName("hypoallergenic")
	private int hypoallergenic;

	@SerializedName("name")
	private String name;

	@SerializedName("vetstreet_url")
	private String vetstreetUrl;

	@SerializedName("indoor")
	private int indoor;

	@SerializedName("energy_level")
	private int energyLevel;

	@SerializedName("stranger_friendly")
	private int strangerFriendly;

	@SerializedName("reference_image_id")
	private String referenceImageId;

	@SerializedName("affection_level")
	private int affectionLevel;

	public int getSuppressedTail(){
		return suppressedTail;
	}

	public String getWikipediaUrl(){
		return wikipediaUrl;
	}

	public String getOrigin(){
		return origin;
	}

	public String getDescription(){
		return description;
	}

	public int getExperimental(){
		return experimental;
	}

	public String getLifeSpan(){
		return lifeSpan;
	}

	public String getCfaUrl(){
		return cfaUrl;
	}

	public int getRare(){
		return rare;
	}

	public String getCountryCodes(){
		return countryCodes;
	}

	public int getLap(){
		return lap;
	}

	public String getId(){
		return id;
	}

	public int getShortLegs(){
		return shortLegs;
	}

	public int getSheddingLevel(){
		return sheddingLevel;
	}

	public int getDogFriendly(){
		return dogFriendly;
	}

	public int getNatural(){
		return natural;
	}

	public int getRex(){
		return rex;
	}

	public int getHealthIssues(){
		return healthIssues;
	}

	public int getHairless(){
		return hairless;
	}

	public Weight getWeight(){
		return weight;
	}

	public String getAltNames(){
		return altNames;
	}

	public int getAdaptability(){
		return adaptability;
	}

	public int getVocalisation(){
		return vocalisation;
	}

	public int getIntelligence(){
		return intelligence;
	}

	public int getSocialNeeds(){
		return socialNeeds;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public int getChildFriendly(){
		return childFriendly;
	}

	public String getTemperament(){
		return temperament;
	}

	public String getVcahospitalsUrl(){
		return vcahospitalsUrl;
	}

	public int getGrooming(){
		return grooming;
	}

	public int getHypoallergenic(){
		return hypoallergenic;
	}

	public String getName(){
		return name;
	}

	public String getVetstreetUrl(){
		return vetstreetUrl;
	}

	public int getIndoor(){
		return indoor;
	}

	public int getEnergyLevel(){
		return energyLevel;
	}

	public int getStrangerFriendly(){
		return strangerFriendly;
	}

	public String getReferenceImageId(){
		return referenceImageId;
	}

	public int getAffectionLevel(){
		return affectionLevel;
	}
}

class Weight{

	@SerializedName("metric")
	private String metric;

	@SerializedName("imperial")
	private String imperial;

	public String getMetric(){
		return metric;
	}

	public String getImperial(){
		return imperial;
	}
}