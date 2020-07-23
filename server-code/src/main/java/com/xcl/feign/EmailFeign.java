package com.xcl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("server-email")
public interface EmailFeign {

    @GetMapping("/email/send/{email}/{code}")
    public Boolean send(@PathVariable String email, @PathVariable String code);
}
