package cloud.cholewa.autorization.configuration;

import cloud.cholewa.autorization.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TokenFilter extends GenericFilterBean {

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader != null) {
            userService.findUserByToken(authorizationHeader.substring(7).trim())
                    .filter(user -> user.getAccessToken().getExpiresAt().isAfter(LocalDateTime.now()))
                    .map(user -> new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()))
                    .ifPresent(usernamePasswordAuthenticationToken -> SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
