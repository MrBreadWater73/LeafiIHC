package com.example.leafiihc.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PerenualPlant {
    @SerializedName("id")
    private int id;
    
    @SerializedName("common_name")
    private String commonName;
    
    @SerializedName("scientific_name")
    private List<String> scientificName;
    
    @SerializedName("family")
    private String family;
    
    @SerializedName("family_common_name")
    private String familyCommonName;
    
    @SerializedName("genus")
    private String genus;
    
    @SerializedName("genus_id")
    private int genusId;
    
    @SerializedName("year")
    private int year;
    
    @SerializedName("bibliography")
    private String bibliography;
    
    @SerializedName("author")
    private String author;
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("rank")
    private String rank;
    
    @SerializedName("observations")
    private String observations;
    
    @SerializedName("vegetable")
    private boolean vegetable;
    
    @SerializedName("image_url")
    private String imageUrl;
    
    @SerializedName("synonyms")
    private String[] synonyms;
    
    @SerializedName("edible_parts")
    private String[] edibleParts;
    
    @SerializedName("edible")
    private boolean edible;
    
    @SerializedName("light")
    private int light;
    
    @SerializedName("soil")
    private String[] soil;
    
    @SerializedName("watering")
    private String watering;
    
    @SerializedName("dimensions")
    private Dimensions dimensions;
    
    @SerializedName("propagation")
    private List<String> propagation;
    
    @SerializedName("hardiness")
    private Hardiness hardiness;
    
    @SerializedName("harvest")
    private Harvest harvest;
    
    @SerializedName("flowers")
    private boolean flowers;
    
    @SerializedName("flowering")
    private boolean flowering;
    
    @SerializedName("conspicuous")
    private boolean conspicuous;
    
    @SerializedName("fruits")
    private boolean fruits;
    
    @SerializedName("edible_fruit")
    private boolean edibleFruit;
    
    @SerializedName("edible_fruit_taste_profile")
    private String edibleFruitTasteProfile;
    
    @SerializedName("fruit_nutritional_value")
    private String fruitNutritionalValue;
    
    @SerializedName("fruit_color")
    private String[] fruitColor;
    
    @SerializedName("fruiting")
    private boolean fruiting;
    
    @SerializedName("leaf")
    private boolean leaf;
    
    @SerializedName("leaf_color")
    private String[] leafColor;
    
    @SerializedName("edible_leaf")
    private boolean edibleLeaf;
    
    @SerializedName("cuisine")
    private boolean cuisine;
    
    @SerializedName("medicinal")
    private boolean medicinal;
    
    @SerializedName("poisonous_to_humans")
    private int poisonousToHumans;
    
    @SerializedName("poisonous_to_pets")
    private int poisonousToPets;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("default_image")
    private DefaultImage defaultImage;
    
    @SerializedName("tropical")
    private boolean tropical;
    
    @SerializedName("indoor")
    private boolean indoor;
    
    @SerializedName("care_level")
    private String careLevel;
    
    @SerializedName("pest_susceptibility_api")
    private String pestSusceptibilityApi;
    
    @SerializedName("pest_susceptibility")
    private String[] pestSusceptibility;
    
    @SerializedName("growth_rate")
    private String growthRate;
    
    @SerializedName("maintenance")
    private String maintenance;
    
    @SerializedName("care_instructions")
    private String careInstructions;
    
    @SerializedName("soil_nutrients")
    private String soilNutrients;
    
    @SerializedName("pruning_month")
    private String[] pruningMonth;
    
    @SerializedName("pruning_count")
    private int pruningCount;
    
    @SerializedName("seeds")
    private int seeds;
    
    @SerializedName("attracts")
    private String[] attracts;
    
    @SerializedName("propagation_methods")
    private String[] propagationMethods;
    
    @SerializedName("average_height")
    private Dimensions averageHeight;
    
    @SerializedName("maximum_height")
    private Dimensions maximumHeight;
    
    @SerializedName("average_width")
    private Dimensions averageWidth;
    
    @SerializedName("maximum_width")
    private Dimensions maximumWidth;
    
    @SerializedName("planting_days_to_harvest")
    private int plantingDaysToHarvest;
    
    @SerializedName("harvest_days")
    private int harvestDays;
    
    @SerializedName("harvest_method")
    private String harvestMethod;
    
    @SerializedName("seeding_vegetable")
    private boolean seedingVegetable;
    
    @SerializedName("sowing")
    private String sowing;
    
    @SerializedName("spacing")
    private String spacing;
    
    @SerializedName("row_spacing")
    private String rowSpacing;
    
    @SerializedName("plant_spacing")
    private String plantSpacing;
    
    @SerializedName("planting_depth")
    private String plantingDepth;
    
    @SerializedName("planting_instructions")
    private String plantingInstructions;
    
    @SerializedName("diseases")
    private String[] diseases;
    
    @SerializedName("pests")
    private String[] pests;
    
    @SerializedName("known_issues")
    private String[] knownIssues;
    
    @SerializedName("growing_from_seeds")
    private String growingFromSeeds;
    
    @SerializedName("other_names")
    private String[] otherNames;
    
    @SerializedName("varieties")
    private String[] varieties;
    
    @SerializedName("subspecies")
    private String[] subspecies;
    
    @SerializedName("hybrids")
    private String[] hybrids;
    
    @SerializedName("cultivars")
    private String[] cultivars;
    
    @SerializedName("sources")
    private Source[] sources;
    
    @SerializedName("distributions")
    private Distribution distributions;
    
    @SerializedName("specifications")
    private Specifications specifications;
    
    @SerializedName("external_ids")
    private ExternalIds externalIds;
    
    @SerializedName("taxonomy")
    private Taxonomy taxonomy;
    
    @SerializedName("url")
    private String url;
    
    @SerializedName("sunlight")
    private List<String> sunlight;
    
    @SerializedName("growth")
    private String growth;
    
    @SerializedName("care")
    private String care;
    
    @SerializedName("poisonous")
    private Boolean poisonous;
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCommonName() {
        return commonName;
    }
    
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
    
    public List<String> getScientificName() {
        return scientificName;
    }
    
    public void setScientificName(List<String> scientificName) {
        this.scientificName = scientificName;
    }
    
    public String getFamily() {
        return family;
    }
    
    public void setFamily(String family) {
        this.family = family;
    }
    
    public String getFamilyCommonName() {
        return familyCommonName;
    }
    
    public void setFamilyCommonName(String familyCommonName) {
        this.familyCommonName = familyCommonName;
    }
    
    public String getGenus() {
        return genus;
    }
    
    public void setGenus(String genus) {
        this.genus = genus;
    }
    
    public int getGenusId() {
        return genusId;
    }
    
    public void setGenusId(int genusId) {
        this.genusId = genusId;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getBibliography() {
        return bibliography;
    }
    
    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRank() {
        return rank;
    }
    
    public void setRank(String rank) {
        this.rank = rank;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public boolean isVegetable() {
        return vegetable;
    }
    
    public void setVegetable(boolean vegetable) {
        this.vegetable = vegetable;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String[] getSynonyms() {
        return synonyms;
    }
    
    public void setSynonyms(String[] synonyms) {
        this.synonyms = synonyms;
    }
    
    public String[] getEdibleParts() {
        return edibleParts;
    }
    
    public void setEdibleParts(String[] edibleParts) {
        this.edibleParts = edibleParts;
    }
    
    public boolean isEdible() {
        return edible;
    }
    
    public void setEdible(boolean edible) {
        this.edible = edible;
    }
    
    public int getLight() {
        return light;
    }
    
    public void setLight(int light) {
        this.light = light;
    }
    
    public String[] getSoil() {
        return soil;
    }
    
    public void setSoil(String[] soil) {
        this.soil = soil;
    }
    
    public String getWatering() {
        return watering;
    }
    
    public void setWatering(String watering) {
        this.watering = watering;
    }
    
    public Dimensions getDimensions() {
        return dimensions;
    }
    
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }
    
    public List<String> getPropagation() {
        return propagation;
    }
    
    public void setPropagation(List<String> propagation) {
        this.propagation = propagation;
    }
    
    public Hardiness getHardiness() {
        return hardiness;
    }
    
    public void setHardiness(Hardiness hardiness) {
        this.hardiness = hardiness;
    }
    
    public Harvest getHarvest() {
        return harvest;
    }
    
    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }
    
    public boolean isFlowers() {
        return flowers;
    }
    
    public void setFlowers(boolean flowers) {
        this.flowers = flowers;
    }
    
    public boolean isFlowering() {
        return flowering;
    }
    
    public void setFlowering(boolean flowering) {
        this.flowering = flowering;
    }
    
    public boolean isConspicuous() {
        return conspicuous;
    }
    
    public void setConspicuous(boolean conspicuous) {
        this.conspicuous = conspicuous;
    }
    
    public boolean isFruits() {
        return fruits;
    }
    
    public void setFruits(boolean fruits) {
        this.fruits = fruits;
    }
    
    public boolean isEdibleFruit() {
        return edibleFruit;
    }
    
    public void setEdibleFruit(boolean edibleFruit) {
        this.edibleFruit = edibleFruit;
    }
    
    public String getEdibleFruitTasteProfile() {
        return edibleFruitTasteProfile;
    }
    
    public void setEdibleFruitTasteProfile(String edibleFruitTasteProfile) {
        this.edibleFruitTasteProfile = edibleFruitTasteProfile;
    }
    
    public String getFruitNutritionalValue() {
        return fruitNutritionalValue;
    }
    
    public void setFruitNutritionalValue(String fruitNutritionalValue) {
        this.fruitNutritionalValue = fruitNutritionalValue;
    }
    
    public String[] getFruitColor() {
        return fruitColor;
    }
    
    public void setFruitColor(String[] fruitColor) {
        this.fruitColor = fruitColor;
    }
    
    public boolean isFruiting() {
        return fruiting;
    }
    
    public void setFruiting(boolean fruiting) {
        this.fruiting = fruiting;
    }
    
    public boolean isLeaf() {
        return leaf;
    }
    
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
    
    public String[] getLeafColor() {
        return leafColor;
    }
    
    public void setLeafColor(String[] leafColor) {
        this.leafColor = leafColor;
    }
    
    public boolean isEdibleLeaf() {
        return edibleLeaf;
    }
    
    public void setEdibleLeaf(boolean edibleLeaf) {
        this.edibleLeaf = edibleLeaf;
    }
    
    public boolean isCuisine() {
        return cuisine;
    }
    
    public void setCuisine(boolean cuisine) {
        this.cuisine = cuisine;
    }
    
    public boolean isMedicinal() {
        return medicinal;
    }
    
    public void setMedicinal(boolean medicinal) {
        this.medicinal = medicinal;
    }
    
    public int getPoisonousToHumans() {
        return poisonousToHumans;
    }
    
    public void setPoisonousToHumans(int poisonousToHumans) {
        this.poisonousToHumans = poisonousToHumans;
    }
    
    public int getPoisonousToPets() {
        return poisonousToPets;
    }
    
    public void setPoisonousToPets(int poisonousToPets) {
        this.poisonousToPets = poisonousToPets;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public DefaultImage getDefaultImage() {
        return defaultImage;
    }
    
    public void setDefaultImage(DefaultImage defaultImage) {
        this.defaultImage = defaultImage;
    }
    
    public boolean isTropical() {
        return tropical;
    }
    
    public void setTropical(boolean tropical) {
        this.tropical = tropical;
    }
    
    public boolean isIndoor() {
        return indoor;
    }
    
    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }
    
    public String getCareLevel() {
        return careLevel;
    }
    
    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }
    
    public String getPestSusceptibilityApi() {
        return pestSusceptibilityApi;
    }
    
    public void setPestSusceptibilityApi(String pestSusceptibilityApi) {
        this.pestSusceptibilityApi = pestSusceptibilityApi;
    }
    
    public String[] getPestSusceptibility() {
        return pestSusceptibility;
    }
    
    public void setPestSusceptibility(String[] pestSusceptibility) {
        this.pestSusceptibility = pestSusceptibility;
    }
    
    public String getGrowthRate() {
        return growthRate;
    }
    
    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }
    
    public String getMaintenance() {
        return maintenance;
    }
    
    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }
    
    public String getCareInstructions() {
        return careInstructions;
    }
    
    public void setCareInstructions(String careInstructions) {
        this.careInstructions = careInstructions;
    }
    
    public String getSoilNutrients() {
        return soilNutrients;
    }
    
    public void setSoilNutrients(String soilNutrients) {
        this.soilNutrients = soilNutrients;
    }
    
    public String[] getPruningMonth() {
        return pruningMonth;
    }
    
    public void setPruningMonth(String[] pruningMonth) {
        this.pruningMonth = pruningMonth;
    }
    
    public int getPruningCount() {
        return pruningCount;
    }
    
    public void setPruningCount(int pruningCount) {
        this.pruningCount = pruningCount;
    }
    
    public int getSeeds() {
        return seeds;
    }
    
    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }
    
    public String[] getAttracts() {
        return attracts;
    }
    
    public void setAttracts(String[] attracts) {
        this.attracts = attracts;
    }
    
    public String[] getPropagationMethods() {
        return propagationMethods;
    }
    
    public void setPropagationMethods(String[] propagationMethods) {
        this.propagationMethods = propagationMethods;
    }
    
    public Dimensions getAverageHeight() {
        return averageHeight;
    }
    
    public void setAverageHeight(Dimensions averageHeight) {
        this.averageHeight = averageHeight;
    }
    
    public Dimensions getMaximumHeight() {
        return maximumHeight;
    }
    
    public void setMaximumHeight(Dimensions maximumHeight) {
        this.maximumHeight = maximumHeight;
    }
    
    public Dimensions getAverageWidth() {
        return averageWidth;
    }
    
    public void setAverageWidth(Dimensions averageWidth) {
        this.averageWidth = averageWidth;
    }
    
    public Dimensions getMaximumWidth() {
        return maximumWidth;
    }
    
    public void setMaximumWidth(Dimensions maximumWidth) {
        this.maximumWidth = maximumWidth;
    }
    
    public int getPlantingDaysToHarvest() {
        return plantingDaysToHarvest;
    }
    
    public void setPlantingDaysToHarvest(int plantingDaysToHarvest) {
        this.plantingDaysToHarvest = plantingDaysToHarvest;
    }
    
    public int getHarvestDays() {
        return harvestDays;
    }
    
    public void setHarvestDays(int harvestDays) {
        this.harvestDays = harvestDays;
    }
    
    public String getHarvestMethod() {
        return harvestMethod;
    }
    
    public void setHarvestMethod(String harvestMethod) {
        this.harvestMethod = harvestMethod;
    }
    
    public boolean isSeedingVegetable() {
        return seedingVegetable;
    }
    
    public void setSeedingVegetable(boolean seedingVegetable) {
        this.seedingVegetable = seedingVegetable;
    }
    
    public String getSowing() {
        return sowing;
    }
    
    public void setSowing(String sowing) {
        this.sowing = sowing;
    }
    
    public String getSpacing() {
        return spacing;
    }
    
    public void setSpacing(String spacing) {
        this.spacing = spacing;
    }
    
    public String getRowSpacing() {
        return rowSpacing;
    }
    
    public void setRowSpacing(String rowSpacing) {
        this.rowSpacing = rowSpacing;
    }
    
    public String getPlantSpacing() {
        return plantSpacing;
    }
    
    public void setPlantSpacing(String plantSpacing) {
        this.plantSpacing = plantSpacing;
    }
    
    public String getPlantingDepth() {
        return plantingDepth;
    }
    
    public void setPlantingDepth(String plantingDepth) {
        this.plantingDepth = plantingDepth;
    }
    
    public String getPlantingInstructions() {
        return plantingInstructions;
    }
    
    public void setPlantingInstructions(String plantingInstructions) {
        this.plantingInstructions = plantingInstructions;
    }
    
    public String[] getDiseases() {
        return diseases;
    }
    
    public void setDiseases(String[] diseases) {
        this.diseases = diseases;
    }
    
    public String[] getPests() {
        return pests;
    }
    
    public void setPests(String[] pests) {
        this.pests = pests;
    }
    
    public String[] getKnownIssues() {
        return knownIssues;
    }
    
    public void setKnownIssues(String[] knownIssues) {
        this.knownIssues = knownIssues;
    }
    
    public String getGrowingFromSeeds() {
        return growingFromSeeds;
    }
    
    public void setGrowingFromSeeds(String growingFromSeeds) {
        this.growingFromSeeds = growingFromSeeds;
    }
    
    public String[] getOtherNames() {
        return otherNames;
    }
    
    public void setOtherNames(String[] otherNames) {
        this.otherNames = otherNames;
    }
    
    public String[] getVarieties() {
        return varieties;
    }
    
    public void setVarieties(String[] varieties) {
        this.varieties = varieties;
    }
    
    public String[] getSubspecies() {
        return subspecies;
    }
    
    public void setSubspecies(String[] subspecies) {
        this.subspecies = subspecies;
    }
    
    public String[] getHybrids() {
        return hybrids;
    }
    
    public void setHybrids(String[] hybrids) {
        this.hybrids = hybrids;
    }
    
    public String[] getCultivars() {
        return cultivars;
    }
    
    public void setCultivars(String[] cultivars) {
        this.cultivars = cultivars;
    }
    
    public Source[] getSources() {
        return sources;
    }
    
    public void setSources(Source[] sources) {
        this.sources = sources;
    }
    
    public Distribution getDistributions() {
        return distributions;
    }
    
    public void setDistributions(Distribution distributions) {
        this.distributions = distributions;
    }
    
    public Specifications getSpecifications() {
        return specifications;
    }
    
    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }
    
    public ExternalIds getExternalIds() {
        return externalIds;
    }
    
    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }
    
    public Taxonomy getTaxonomy() {
        return taxonomy;
    }
    
    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public List<String> getSunlight() {
        return sunlight;
    }
    
    public void setSunlight(List<String> sunlight) {
        this.sunlight = sunlight;
    }
    
    public String getGrowth() {
        return growth;
    }
    
    public void setGrowth(String growth) {
        this.growth = growth;
    }
    
    public String getCare() {
        return care;
    }
    
    public void setCare(String care) {
        this.care = care;
    }
    
    public Boolean getPoisonous() {
        return poisonous;
    }
    
    public void setPoisonous(Boolean poisonous) {
        this.poisonous = poisonous;
    }
    
    // Inner classes for nested objects
    public static class Dimensions {
        @SerializedName("cm")
        private double cm;
        
        public double getCm() {
            return cm;
        }
        
        public void setCm(double cm) {
            this.cm = cm;
        }
    }
    
    public static class Hardiness {
        @SerializedName("zone")
        private String zone;
        
        public String getZone() {
            return zone;
        }
        
        public void setZone(String zone) {
            this.zone = zone;
        }
    }
    
    public static class Harvest {
        @SerializedName("recommended_months")
        private String[] recommendedMonths;
        
        @SerializedName("harvest_method")
        private String harvestMethod;
        
        @SerializedName("days_to_harvest")
        private int daysToHarvest;
        
        public String[] getRecommendedMonths() {
            return recommendedMonths;
        }
        
        public void setRecommendedMonths(String[] recommendedMonths) {
            this.recommendedMonths = recommendedMonths;
        }
        
        public String getHarvestMethod() {
            return harvestMethod;
        }
        
        public void setHarvestMethod(String harvestMethod) {
            this.harvestMethod = harvestMethod;
        }
        
        public int getDaysToHarvest() {
            return daysToHarvest;
        }
        
        public void setDaysToHarvest(int daysToHarvest) {
            this.daysToHarvest = daysToHarvest;
        }
    }
    
    public static class DefaultImage {
        @SerializedName("license")
        private int license;
        
        @SerializedName("license_name")
        private String licenseName;
        
        @SerializedName("license_url")
        private String licenseUrl;
        
        @SerializedName("original_url")
        private String originalUrl;
        
        @SerializedName("regular_url")
        private String regularUrl;
        
        @SerializedName("medium_url")
        private String mediumUrl;
        
        @SerializedName("small_url")
        private String smallUrl;
        
        @SerializedName("thumbnail")
        private String thumbnail;
        
        public int getLicense() {
            return license;
        }
        
        public void setLicense(int license) {
            this.license = license;
        }
        
        public String getLicenseName() {
            return licenseName;
        }
        
        public void setLicenseName(String licenseName) {
            this.licenseName = licenseName;
        }
        
        public String getLicenseUrl() {
            return licenseUrl;
        }
        
        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }
        
        public String getOriginalUrl() {
            return originalUrl;
        }
        
        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }
        
        public String getRegularUrl() {
            return regularUrl;
        }
        
        public void setRegularUrl(String regularUrl) {
            this.regularUrl = regularUrl;
        }
        
        public String getMediumUrl() {
            return mediumUrl;
        }
        
        public void setMediumUrl(String mediumUrl) {
            this.mediumUrl = mediumUrl;
        }
        
        public String getSmallUrl() {
            return smallUrl;
        }
        
        public void setSmallUrl(String smallUrl) {
            this.smallUrl = smallUrl;
        }
        
        public String getThumbnail() {
            return thumbnail;
        }
        
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
    
    public static class Source {
        @SerializedName("last_update")
        private String lastUpdate;
        
        @SerializedName("id")
        private String id;
        
        @SerializedName("name")
        private String name;
        
        @SerializedName("url")
        private String url;
        
        public String getLastUpdate() {
            return lastUpdate;
        }
        
        public void setLastUpdate(String lastUpdate) {
            this.lastUpdate = lastUpdate;
        }
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getUrl() {
            return url;
        }
        
        public void setUrl(String url) {
            this.url = url;
        }
    }
    
    public static class Distribution {
        @SerializedName("native")
        private String[] native_;
        
        @SerializedName("introduced")
        private String[] introduced;
        
        public String[] getNative() {
            return native_;
        }
        
        public void setNative(String[] native_) {
            this.native_ = native_;
        }
        
        public String[] getIntroduced() {
            return introduced;
        }
        
        public void setIntroduced(String[] introduced) {
            this.introduced = introduced;
        }
    }
    
    public static class Specifications {
        @SerializedName("ligneous_type")
        private String ligneousType;
        
        @SerializedName("growth_form")
        private String growthForm;
        
        @SerializedName("growth_habit")
        private String growthHabit;
        
        @SerializedName("growth_rate")
        private String growthRate;
        
        @SerializedName("average_height")
        private Dimensions averageHeight;
        
        @SerializedName("maximum_height")
        private Dimensions maximumHeight;
        
        @SerializedName("toxicity")
        private String toxicity;
        
        public String getLigneousType() {
            return ligneousType;
        }
        
        public void setLigneousType(String ligneousType) {
            this.ligneousType = ligneousType;
        }
        
        public String getGrowthForm() {
            return growthForm;
        }
        
        public void setGrowthForm(String growthForm) {
            this.growthForm = growthForm;
        }
        
        public String getGrowthHabit() {
            return growthHabit;
        }
        
        public void setGrowthHabit(String growthHabit) {
            this.growthHabit = growthHabit;
        }
        
        public String getGrowthRate() {
            return growthRate;
        }
        
        public void setGrowthRate(String growthRate) {
            this.growthRate = growthRate;
        }
        
        public Dimensions getAverageHeight() {
            return averageHeight;
        }
        
        public void setAverageHeight(Dimensions averageHeight) {
            this.averageHeight = averageHeight;
        }
        
        public Dimensions getMaximumHeight() {
            return maximumHeight;
        }
        
        public void setMaximumHeight(Dimensions maximumHeight) {
            this.maximumHeight = maximumHeight;
        }
        
        public String getToxicity() {
            return toxicity;
        }
        
        public void setToxicity(String toxicity) {
            this.toxicity = toxicity;
        }
    }
    
    public static class ExternalIds {
        @SerializedName("id")
        private int id;
        
        @SerializedName("id_type")
        private String idType;
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getIdType() {
            return idType;
        }
        
        public void setIdType(String idType) {
            this.idType = idType;
        }
    }
    
    public static class Taxonomy {
        @SerializedName("id")
        private int id;
        
        @SerializedName("family")
        private String family;
        
        @SerializedName("genus")
        private String genus;
        
        @SerializedName("species")
        private String species;
        
        @SerializedName("subspecies")
        private String subspecies;
        
        @SerializedName("variety")
        private String variety;
        
        @SerializedName("form")
        private String form;
        
        @SerializedName("hybrid")
        private String hybrid;
        
        @SerializedName("cultivar")
        private String cultivar;
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getFamily() {
            return family;
        }
        
        public void setFamily(String family) {
            this.family = family;
        }
        
        public String getGenus() {
            return genus;
        }
        
        public void setGenus(String genus) {
            this.genus = genus;
        }
        
        public String getSpecies() {
            return species;
        }
        
        public void setSpecies(String species) {
            this.species = species;
        }
        
        public String getSubspecies() {
            return subspecies;
        }
        
        public void setSubspecies(String subspecies) {
            this.subspecies = subspecies;
        }
        
        public String getVariety() {
            return variety;
        }
        
        public void setVariety(String variety) {
            this.variety = variety;
        }
        
        public String getForm() {
            return form;
        }
        
        public void setForm(String form) {
            this.form = form;
        }
        
        public String getHybrid() {
            return hybrid;
        }
        
        public void setHybrid(String hybrid) {
            this.hybrid = hybrid;
        }
        
        public String getCultivar() {
            return cultivar;
        }
        
        public void setCultivar(String cultivar) {
            this.cultivar = cultivar;
        }
    }
} 