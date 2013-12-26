package com.xianlinbox.dozer.source;

import java.util.List;

public class ConsentType {
    private ConsentIndicatorType globalIndicator;
    private List<DetailedConsentType> detailedConsents;

    public ConsentIndicatorType getGlobalIndicator() {
        return globalIndicator;
    }

    public void setGlobalIndicator(ConsentIndicatorType globalIndicator) {
        this.globalIndicator = globalIndicator;
    }

    public List<DetailedConsentType> getDetailedConsents() {
        return detailedConsents;
    }

    public void setDetailedConsents(List<DetailedConsentType> detailedConsents) {
        this.detailedConsents = detailedConsents;
    }
}
