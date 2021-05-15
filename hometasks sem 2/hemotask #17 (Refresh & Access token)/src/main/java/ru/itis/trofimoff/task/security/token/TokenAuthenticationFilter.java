package ru.itis.trofimoff.task.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.trofimoff.task.exceptions.ExpiredAccessTokenException;
import ru.itis.trofimoff.task.models.User;
import ru.itis.trofimoff.task.utils.TokenGenerator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, IOException, ServletException {

        String token = request.getHeader("X-TOKEN");

        if (token != null) {
            // if with token everything is ok - we'll get a user
            User user = tokenGenerator.verifyToken(token);
            if (user == null) throw new ExpiredAccessTokenException();
        }

        filterChain.doFilter(request, response);
    }
}
