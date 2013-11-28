package com.xianlinbox.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {

    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.GET)
    @ResponseBody
    public Request get(@PathVariable String requestId, @RequestParam(value = "userId") String userId) {
        return new Request(userId, requestId, "GET");
    }

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    @ResponseBody
    public Request post(@RequestParam(value = "userId") String userId, @RequestBody String content) {
        Request request = new Request(userId, "1", "POST");
        request.setMessage(content);
        return request;
    }

}
