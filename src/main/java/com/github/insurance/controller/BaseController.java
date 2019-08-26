package com.github.insurance.controller;

import com.github.insurance.domain.CasAuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    public CasAuthUser getCurrUser() {
        return (CasAuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
