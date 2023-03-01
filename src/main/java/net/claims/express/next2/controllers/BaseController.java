package net.claims.express.next2.controllers;


import net.claims.express.next2.entities.CoreUser;
import net.claims.express.next2.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

public abstract class BaseController {
  //  private TokenUtil tokenUtil;
    public SecurityUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Type===== " + authentication.getPrincipal().toString());
        return (SecurityUser) authentication.getPrincipal();

    }

}
