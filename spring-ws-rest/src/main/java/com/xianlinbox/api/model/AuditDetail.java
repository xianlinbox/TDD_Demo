package com.xianlinbox.api.model;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class AuditDetail {

    @NotNull
    @Length(max = 10, message = "userId must less than 10 digits")
    private String userId;

    @Past(message = "DateTime should not in future")
    private DateTime dateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getDateTime() {
        return dateTime;
    }

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
