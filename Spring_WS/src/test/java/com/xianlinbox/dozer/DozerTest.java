package com.xianlinbox.dozer;

import com.xianlinbox.dozer.destination.CisCustomerConsent;
import com.xianlinbox.dozer.destination.ConsentIndicator;
import com.xianlinbox.dozer.source.ConsentIndicatorType;
import com.xianlinbox.dozer.source.ConsentType;
import com.xianlinbox.dozer.source.CustomerConsent;
import com.xianlinbox.dozer.source.DetailedConsentType;
import org.dozer.DozerBeanMapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.xianlinbox.dozer.source.ConsentIndicatorType.YES;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DozerTest {
    @Test
    public void testMappingGlobalConsent() throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer-bean-mappings.xml"));

        CustomerConsent response = buildGlobalCustomerConsent(12345, ConsentIndicatorType.NO);
        CisCustomerConsent cisCustomerConsent = mapper.map(response, CisCustomerConsent.class);
        assertThat(cisCustomerConsent.getCisCustomerId(), equalTo("12345"));
        assertThat(cisCustomerConsent.getConsent().getGlobalConsentIndicator(), equalTo(ConsentIndicator.NO));
    }


    @Test
    public void testMappingDetailedConsent() throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer-bean-mappings.xml"));

        CustomerConsent response = buildDetailedCustomerConsent(12345);
        CisCustomerConsent cisCustomerConsent = mapper.map(response, CisCustomerConsent.class);
        assertThat(cisCustomerConsent.getCisCustomerId(), equalTo("12345"));
        assertThat(cisCustomerConsent.getConsent().getDetailedConsents().size(), equalTo(2));
    }

    private CustomerConsent buildDetailedCustomerConsent(int customerId) {
        CustomerConsent customerConsent = new CustomerConsent();
        customerConsent.setCustomerId(customerId);
        customerConsent.setConsent(buildDetailedConsent());
        return customerConsent;
    }

    private ConsentType buildDetailedConsent() {
        ConsentType consentType = new ConsentType();
        List<DetailedConsentType> detailedConsent = Arrays.asList(emailConsent(YES), mailConsent(YES));
        consentType.setDetailedConsents(detailedConsent);
        return consentType;
    }

    private DetailedConsentType mailConsent(ConsentIndicatorType indicator) {
        DetailedConsentType consentType = new DetailedConsentType();
        consentType.setChannelCode("MAIL");
        consentType.setChannelName("Mail");
        consentType.setConsentIndicator(indicator);
        return consentType;
    }

    private DetailedConsentType emailConsent(ConsentIndicatorType indicator) {
        DetailedConsentType consentType = new DetailedConsentType();
        consentType.setChannelCode("EMAIL");
        consentType.setChannelName("E-MAIL");
        consentType.setConsentIndicator(indicator);
        return consentType;
    }

    private CustomerConsent buildGlobalCustomerConsent(int customerId, ConsentIndicatorType consentIndicator) {
        CustomerConsent customerConsent = new CustomerConsent();
        customerConsent.setCustomerId(customerId);
        customerConsent.setConsent(buildGlobalConsent(consentIndicator));
        return customerConsent;
    }

    private ConsentType buildGlobalConsent(ConsentIndicatorType globalConsentIndicator) {
        ConsentType consentType = new ConsentType();
        consentType.setGlobalIndicator(globalConsentIndicator);
        return consentType;
    }
}
