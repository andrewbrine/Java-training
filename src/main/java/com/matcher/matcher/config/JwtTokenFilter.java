package com.matcher.matcher.config;

import com.matcher.matcher.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = getToken(request);

        if (!tokenUtil.validateToken(token)) {
            filterChain.doFilter(request,response);
            return;
        }

        setAuthenticationContext(token,request);
        filterChain.doFilter(request,response);

    }

    private UserDetails getAccountDetails(String token) {
        Account accountDetails = new Account();
        String[] jwtSubject = tokenUtil.getSubject(token).split(",");

        accountDetails.setId(Integer.parseInt(jwtSubject[0]));
        accountDetails.setUsername(jwtSubject[1]);

        return accountDetails;
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }

        return true;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
      UserDetails accountDetails = getAccountDetails(token);

      UsernamePasswordAuthenticationToken authentication =
             new UsernamePasswordAuthenticationToken(accountDetails,null,null);

      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
