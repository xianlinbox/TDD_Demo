package com.xianlinbox.dozer;

import com.xianlinbox.dozer.destination.Customer;
import com.xianlinbox.dozer.source.User;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DozerMapperTest {
    private DozerBeanMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer-bean-mappings.xml"));
    }

    @Test
    public void testMapping() throws Exception {
        User user = new User(1, "user1");
        Customer customer = mapper.map(user, Customer.class);
        assertThat(customer.getCustomerId(),equalTo(1));
        assertThat(customer.getCustomerName(),equalTo("user1"));
    }
}
