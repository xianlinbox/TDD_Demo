package com.xianlinbox.dozer.source;

public class CustomerConsent {
    private int customerId;
    private ConsentType consent;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public ConsentType getConsent() {
        return consent;
    }

    public void setConsent(ConsentType consent) {
        this.consent = consent;
    }
}
