package com.cjs.ai;

import com.cjs.dao.LegalActDAO;
import com.cjs.model.Case;
import com.cjs.model.Judgment;
import com.cjs.model.LegalAct; // Add this import
import com.cjs.service.CaseService;

import java.util.List;

public class PrecedentSuggester {
    private final CaseService caseService;
    private final LegalActDAO legalActDAO;

    public PrecedentSuggester() {
        this.caseService = new CaseService();
        this.legalActDAO = new LegalActDAO();
    }

    public String suggestPrecedent(int caseId) throws Exception {
        Case currentCase = caseService.getCaseById(caseId);
        if (currentCase == null) {
            throw new Exception("Case not found");
        }

        // Get legal act details from database
        LegalAct act = legalActDAO.getLegalActByCrimeName(currentCase.getCrimeType());
        String actDetails = act != null
                ? "Section: " + act.getSectionCodes() + ", Punishment: " + act.getPunishment()
                : "No legal act found";

        // Get all cases to find judgments
        List<Case> allCases = caseService.getAllCases();
        List<Judgment> pastJudgments = allCases.stream()
                .filter(c -> c.getCaseId() != caseId)
                .map(c -> {
                    Judgment j = new Judgment();
                    j.setCaseId(c.getCaseId());
                    j.setActSection("PPC-" + c.getCrimeType().hashCode() % 100); // Simulated
                    j.setVerdict("Guilty"); // Placeholder
                    return j;
                })
                .toList();

        // Simplified suggestion logic
        StringBuilder suggestion = new StringBuilder("Suggested Precedent for Case ID " + caseId + ":\n");
        suggestion.append("Crime Type: ").append(currentCase.getCrimeType()).append("\n");
        suggestion.append("Legal Act: ").append(actDetails).append("\n");
        if (!pastJudgments.isEmpty()) {
            suggestion.append("Similar Past Case: FIR-").append(pastJudgments.get(0).getCaseId())
                    .append(", Verdict: ").append(pastJudgments.get(0).getVerdict()).append("\n");
        }

        return suggestion.toString();
    }
}