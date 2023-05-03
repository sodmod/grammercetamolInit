package com.grammercetamol.controllers.api.secure;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/secured")
public class SecuredApi {

    @GetMapping
    @PreAuthorize("hasAuthority('student:read')")
    public String admin() {
        return "admin";
    }
}
