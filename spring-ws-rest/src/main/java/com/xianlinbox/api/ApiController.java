package com.xianlinbox.api;

import com.xianlinbox.api.model.AuditDetail;
import com.xianlinbox.api.model.Request;
import com.xianlinbox.api.model.exception.InvalidRequestIdException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ApiController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:SS"),true));
    }


    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.GET)
    public Request get(@PathVariable int requestId, @RequestParam(value = "userId") String userId) {
        if (requestId > 10) {
            throw new InvalidRequestIdException("Request id must less than 10");
        }
        if (requestId == 10) {
            throw new RuntimeException("Unexpected Server Error");
        }
        return new Request(userId, requestId, "GET");
    }


    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Request post(@RequestParam(value = "userId") String userId, @RequestBody String content) {
        Request request = new Request(userId, 1, "POST");
        request.setMessage(content);
        return request;
    }

    @RequestMapping(value = "/propagate", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public AuditDetail propagate(@Valid @RequestBody AuditDetail operationBy) {
        return operationBy;
    }
}
