package com.cjs.service;

import com.cjs.model.Judgment;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.List;

public class AIService {
    public String detectCrimePattern(String crimeDescription) throws Exception {
        try {
            // Placeholder: Load dataset and train model
            DataSource source = new DataSource("resources/crime_data.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            J48 tree = new J48();
            tree.buildClassifier(data);

            // Simulate crime type detection
            // In a real implementation, create an Instance from crimeDescription
            Instance instance = data.instance(0); // Placeholder
            double predictedClass = tree.classifyInstance(instance);
            String crimeType = data.classAttribute().value((int) predictedClass);

            return "Detected crime type: " + crimeType;
        } catch (Exception e) {
            throw new Exception("Error in crime pattern detection: " + e.getMessage());
        }
    }

    public String suggestLegalPrecedent(int caseId, List<Judgment> pastJudgments) throws Exception {
        try {
            // Placeholder: Analyze past judgments for similarity
            if (pastJudgments == null || pastJudgments.isEmpty()) {
                return "No precedents found";
            }

            // Simulate precedent suggestion based on act_section
            for (Judgment judgment : pastJudgments) {
                if (judgment.getCaseId() != caseId && judgment.getActSection() != null) {
                    return "Suggested precedent: Case ID " + judgment.getCaseId() +
                            ", Act Section: " + judgment.getActSection() +
                            ", Verdict: " + judgment.getVerdict();
                }
            }

            return "No relevant precedents found";
        } catch (Exception e) {
            throw new Exception("Error in precedent suggestion: " + e.getMessage());
        }
    }
}