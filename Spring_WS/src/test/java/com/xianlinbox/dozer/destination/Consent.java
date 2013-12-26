package com.xianlinbox.dozer.destination;

import java.util.List;

public class Consent {
    private ConsentIndicator globalConsentIndicator;
    private List<DetailedConsent> detailedConsents;

    public ConsentIndicator getGlobalConsentIndicator() {
        return globalConsentIndicator;
    }

    public void setGlobalConsentIndicator(ConsentIndicator globalConsentIndicator) {
        this.globalConsentIndicator = globalConsentIndicator;
    }

    public List<DetailedConsent> getDetailedConsents() {
        return detailedConsents;
    }

    public void setDetailedConsents(List<DetailedConsent> detailedConsents) {
        this.detailedConsents = detailedConsents;
    }
}
