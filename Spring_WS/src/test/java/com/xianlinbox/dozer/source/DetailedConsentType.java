package com.xianlinbox.dozer.source;

public class DetailedConsentType {
    private String channelCode;
    private String channelName;
    private ConsentIndicatorType consentIndicator;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public ConsentIndicatorType getConsentIndicator() {
        return consentIndicator;
    }

    public void setConsentIndicator(ConsentIndicatorType consentIndicator) {
        this.consentIndicator = consentIndicator;
    }
}
