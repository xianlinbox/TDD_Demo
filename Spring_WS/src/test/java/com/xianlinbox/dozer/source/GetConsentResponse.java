package com.xianlinbox.dozer.source;

public class GetConsentResponse {
    private CustomerConsent consent;

    public GetConsentResponse(CustomerConsent customerConsent) {
        this.consent = customerConsent;
    }

    public CustomerConsent getConsent() {
        return consent;
    }

    public void setConsent(CustomerConsent consent) {
        this.consent = consent;
    }
}
