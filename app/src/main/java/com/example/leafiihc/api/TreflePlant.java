package com.example.leafiihc.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TreflePlant {
    @SerializedName("id")
    private int id;

    @SerializedName("common_name")
    private String commonName;

    @SerializedName("scientific_name")
    private String scientificName;

    @SerializedName("family")
    private String family;

    @SerializedName("family_common_name")
    private String familyCommonName;

    @SerializedName("genus")
    private String genus;

    @SerializedName("year")
    private Integer year;

    @SerializedName("bibliography")
    private String bibliography;

    @SerializedName("description")
    private String description;

    @SerializedName("observations")
    private String observations;

    @SerializedName("vegetable")
    private Boolean vegetable;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("synonyms")
    private List<String> synonyms;

    @SerializedName("edible_parts")
    private List<String> edibleParts;

    @SerializedName("edible")
    private Boolean edible;

    @SerializedName("growth_months")
    private List<String> growthMonths;

    @SerializedName("bloom_months")
    private List<String> bloomMonths;

    @SerializedName("fruit_months")
    private List<String> fruitMonths;

    @SerializedName("growth_rate")
    private String growthRate;

    @SerializedName("growth_form")
    private String growthForm;

    @SerializedName("growth_habit")
    private String growthHabit;

    @SerializedName("light")
    private Integer light;

    @SerializedName("atmospheric_humidity")
    private Integer atmosphericHumidity;

    @SerializedName("soil_nutriments")
    private Integer soilNutriments;

    @SerializedName("soil_humidity")
    private Integer soilHumidity;

    @SerializedName("minimum_temperature")
    private Temperature minimumTemperature;

    @SerializedName("maximum_temperature")
    private Temperature maximumTemperature;

    @SerializedName("planting_description")
    private String plantingDescription;

    @SerializedName("planting_sowing_description")
    private String sowingDescription;

    @SerializedName("toxicity")
    private String toxicity;

    @SerializedName("adapted_to_coarse_textured_soils")
    private String adaptedToCoarseTexturedSoils;

    @SerializedName("adapted_to_fine_textured_soils")
    private String adaptedToFineTexturedSoils;

    @SerializedName("adapted_to_medium_textured_soils")
    private String adaptedToMediumTexturedSoils;

    @SerializedName("minimum_precipitation")
    private Measurement minimumPrecipitation;

    @SerializedName("maximum_precipitation")
    private Measurement maximumPrecipitation;

    @SerializedName("minimum_root_depth")
    private Measurement minimumRootDepth;

    public static class Temperature {
        @SerializedName("deg_c")
        private Double celsius;

        @SerializedName("deg_f")
        private Double fahrenheit;

        public Double getCelsius() {
            return celsius;
        }

        public Double getFahrenheit() {
            return fahrenheit;
        }
    }

    public static class Measurement {
        @SerializedName("value")
        private Double value;

        @SerializedName("unit")
        private String unit;

        public Double getValue() {
            return value;
        }

        public String getUnit() {
            return unit;
        }
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public List<String> getGrowthMonths() {
        return growthMonths;
    }

    public List<String> getBloomMonths() {
        return bloomMonths;
    }

    public List<String> getFruitMonths() {
        return fruitMonths;
    }

    public String getGrowthRate() {
        return growthRate;
    }

    public String getGrowthForm() {
        return growthForm;
    }

    public String getGrowthHabit() {
        return growthHabit;
    }

    public Integer getLight() {
        return light;
    }

    public Integer getAtmosphericHumidity() {
        return atmosphericHumidity;
    }

    public Integer getSoilNutriments() {
        return soilNutriments;
    }

    public Integer getSoilHumidity() {
        return soilHumidity;
    }

    public Temperature getMinimumTemperature() {
        return minimumTemperature;
    }

    public Temperature getMaximumTemperature() {
        return maximumTemperature;
    }

    public String getPlantingDescription() {
        return plantingDescription;
    }

    public String getSowingDescription() {
        return sowingDescription;
    }

    public String getToxicity() {
        return toxicity;
    }

    public String getAdaptedToCoarseTexturedSoils() {
        return adaptedToCoarseTexturedSoils;
    }

    public String getAdaptedToFineTexturedSoils() {
        return adaptedToFineTexturedSoils;
    }

    public String getAdaptedToMediumTexturedSoils() {
        return adaptedToMediumTexturedSoils;
    }

    public Measurement getMinimumPrecipitation() {
        return minimumPrecipitation;
    }

    public Measurement getMaximumPrecipitation() {
        return maximumPrecipitation;
    }

    public Measurement getMinimumRootDepth() {
        return minimumRootDepth;
    }

    // Existing getters
    public int getId() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getFamily() {
        return family;
    }

    public String getFamilyCommonName() {
        return familyCommonName;
    }

    public String getGenus() {
        return genus;
    }

    public Integer getYear() {
        return year;
    }

    public String getBibliography() {
        return bibliography;
    }

    public String getObservations() {
        return observations;
    }

    public Boolean getVegetable() {
        return vegetable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public List<String> getEdibleParts() {
        return edibleParts;
    }

    public Boolean getEdible() {
        return edible;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setFamilyCommonName(String familyCommonName) {
        this.familyCommonName = familyCommonName;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setVegetable(Boolean vegetable) {
        this.vegetable = vegetable;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public void setEdibleParts(List<String> edibleParts) {
        this.edibleParts = edibleParts;
    }

    public void setEdible(Boolean edible) {
        this.edible = edible;
    }

    public void setGrowthMonths(List<String> growthMonths) {
        this.growthMonths = growthMonths;
    }

    public void setBloomMonths(List<String> bloomMonths) {
        this.bloomMonths = bloomMonths;
    }

    public void setFruitMonths(List<String> fruitMonths) {
        this.fruitMonths = fruitMonths;
    }

    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }

    public void setGrowthForm(String growthForm) {
        this.growthForm = growthForm;
    }

    public void setGrowthHabit(String growthHabit) {
        this.growthHabit = growthHabit;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public void setAtmosphericHumidity(Integer atmosphericHumidity) {
        this.atmosphericHumidity = atmosphericHumidity;
    }

    public void setSoilNutriments(Integer soilNutriments) {
        this.soilNutriments = soilNutriments;
    }

    public void setSoilHumidity(Integer soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public void setMinimumTemperature(Temperature minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public void setMaximumTemperature(Temperature maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public void setPlantingDescription(String plantingDescription) {
        this.plantingDescription = plantingDescription;
    }

    public void setSowingDescription(String sowingDescription) {
        this.sowingDescription = sowingDescription;
    }

    public void setToxicity(String toxicity) {
        this.toxicity = toxicity;
    }

    public void setAdaptedToCoarseTexturedSoils(String adaptedToCoarseTexturedSoils) {
        this.adaptedToCoarseTexturedSoils = adaptedToCoarseTexturedSoils;
    }

    public void setAdaptedToFineTexturedSoils(String adaptedToFineTexturedSoils) {
        this.adaptedToFineTexturedSoils = adaptedToFineTexturedSoils;
    }

    public void setAdaptedToMediumTexturedSoils(String adaptedToMediumTexturedSoils) {
        this.adaptedToMediumTexturedSoils = adaptedToMediumTexturedSoils;
    }

    public void setMinimumPrecipitation(Measurement minimumPrecipitation) {
        this.minimumPrecipitation = minimumPrecipitation;
    }

    public void setMaximumPrecipitation(Measurement maximumPrecipitation) {
        this.maximumPrecipitation = maximumPrecipitation;
    }

    public void setMinimumRootDepth(Measurement minimumRootDepth) {
        this.minimumRootDepth = minimumRootDepth;
    }
} 