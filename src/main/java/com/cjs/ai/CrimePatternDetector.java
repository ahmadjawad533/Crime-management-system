package com.cjs.ai;

import com.cjs.model.Case;
import com.cjs.service.CaseService;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrimePatternDetector {
    private final CaseService caseService;
    private J48 classifier;
    private Instances dataset;

    // Define attributes for Weka Instances
    private final List<String> crimeTypes = new ArrayList<>();
    private final List<String> locations = new ArrayList<>();
    private final List<String> statuses = new ArrayList<>();

    public CrimePatternDetector() {
        this.caseService = new CaseService();
        this.classifier = new J48();
        this.dataset = null;
        initializeAttributes();
        loadAndPrepareData();
    }

    private void initializeAttributes() {
        // Populate attribute values from legal_acts and seed.sql data
        crimeTypes.add("Murder (Qatl-e-Amd)");
        crimeTypes.add("Theft");
        crimeTypes.add("Rape");
        crimeTypes.add("Robbery");
        crimeTypes.add("Dacoity");
        crimeTypes.add("Kidnapping for Ransom");
        crimeTypes.add("Blasphemy");
        crimeTypes.add("Bribery");
        crimeTypes.add("Cyber Terrorism");
        crimeTypes.add("Forgery");
        crimeTypes.add("Terrorism");
        crimeTypes.add("Acid Attack");
        crimeTypes.add("Sexual Harassment");
        crimeTypes.add("Domestic Violence");
        crimeTypes.add("Honor Killing");
        crimeTypes.add("Drug Trafficking");
        crimeTypes.add("Corruption by Public Servant");
        crimeTypes.add("Counterfeiting Currency");
        crimeTypes.add("Criminal Breach of Trust");
        crimeTypes.add("Assault");

        locations.add("Lahore");
        locations.add("Karachi");
        locations.add("Islamabad");
        locations.add("Rawalpindi");
        locations.add("Faisalabad");
        locations.add("Multan");
        locations.add("Peshawar");
        locations.add("Quetta");
        locations.add("Hyderabad");
        locations.add("Sialkot");
        locations.add("Gujranwala");
        locations.add("Larkana");
        locations.add("Bahawalpur");
        locations.add("Sargodha");
        locations.add("Mirpur Khas");
        locations.add("Sukkur");
        locations.add("Sheikhupura");
        locations.add("Rahim Yar Khan");
        locations.add("Jhelum");
        locations.add("Mardan");
        locations.add("Abbottabad");
        locations.add("Gujrat");

        statuses.add("Under Investigation");
        statuses.add("In Court");
        statuses.add("Solved");
        statuses.add("Closed");
    }

    private void loadAndPrepareData() {
        try {
            List<Case> cases = caseService.getAllCases();
            if (cases.isEmpty()) {
                System.err.println("No cases found in the database.");
                return;
            }

            // Define attributes
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("crime_type", crimeTypes));
            attributes.add(new Attribute("location", locations));
            attributes.add(new Attribute("status", statuses));

            // Create Instances object
            dataset = new Instances("CrimePatterns", attributes, cases.size());
            dataset.setClassIndex(2); // Status is the class to predict

            // Convert cases to Instances
            for (Case c : cases) {
                double[] values = new double[3];
                values[0] = crimeTypes.indexOf(c.getCrimeType());
                values[1] = locations.indexOf(c.getLocation());
                values[2] = statuses.indexOf(c.getStatus());
                dataset.add(new DenseInstance(1.0, values));
            }
        } catch (SQLException e) {
            System.err.println("Database error loading cases: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void train() {
        if (dataset == null || dataset.isEmpty()) {
            System.err.println("No dataset available for training.");
            return;
        }
        try {
            classifier.buildClassifier(dataset);
            System.out.println("Model trained successfully at " + new java.util.Date());
        } catch (Exception e) {
            System.err.println("Error training model: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String predictStatus(String crimeType, String location) {
        if (dataset == null || classifier == null) {
            return "Model not trained. Train the model first.";
        }
        try {
            double[] values = new double[3];
            values[0] = crimeTypes.indexOf(crimeType);
            values[1] = locations.indexOf(location);
            values[2] = statuses.indexOf("Under Investigation"); // Default class value
            DenseInstance instance = new DenseInstance(1.0, values);
            instance.setDataset(dataset);

            double prediction = classifier.classifyInstance(instance);
            return statuses.get((int) prediction);
        } catch (Exception e) {
            System.err.println("Error predicting status: " + e.getMessage());
            return "Prediction failed";
        }
    }

    public Map<String, Integer> getCrimeTypeDistribution() {
        Map<String, Integer> distribution = new HashMap<>();
        if (dataset == null) return distribution;

        for (int i = 0; i < dataset.numInstances(); i++) {
            String crimeType = crimeTypes.get((int) dataset.instance(i).value(0));
            distribution.put(crimeType, distribution.getOrDefault(crimeType, 0) + 1);
        }
        return distribution;
    }

    public String getPatternInsights() {
        Map<String, Integer> distribution = getCrimeTypeDistribution();
        if (distribution.isEmpty()) return "No patterns detected.";

        StringBuilder insights = new StringBuilder("Crime Pattern Insights (as of " + new java.util.Date() + "):\n");
        int totalCases = distribution.values().stream().mapToInt(Integer::intValue).sum();
        distribution.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> {
                    double percentage = (entry.getValue() * 100.0) / totalCases;
                    insights.append(String.format("- %s: %d cases (%.1f%%)\n", entry.getKey(), entry.getValue(), percentage));
                });
        return insights.toString();
    }
}