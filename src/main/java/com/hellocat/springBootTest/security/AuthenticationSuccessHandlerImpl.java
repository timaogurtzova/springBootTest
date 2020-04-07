package com.hellocat.springBootTest.security;

import com.hellocat.springBootTest.domen.Role;
import com.hellocat.springBootTest.domen.RoleType;
import com.hellocat.springBootTest.domen.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) authentication.getPrincipal();

        session.setAttribute("user", user);
        session.setAttribute("authorities", authentication.getAuthorities());

        response.setStatus(HttpServletResponse.SC_OK);

        if (user.getRoles().contains(new Role(RoleType.ROLE_ADMIN))) {
            response.sendRedirect("/admin");
        } else if (user.getRoles().contains(new Role(RoleType.ROLE_USER))) {
            response.sendRedirect("/user");
        }
    }
}