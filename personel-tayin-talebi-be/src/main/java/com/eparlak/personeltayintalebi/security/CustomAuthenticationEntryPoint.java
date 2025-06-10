package com.eparlak.personeltayintalebi.security;


    
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Component 
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    //jwt süresi dolmuşsa ya da jwt bulunmayan kişiler istek atarsa bu kısım çalışacak..
    //securtiy confige eklendi.

    private final ObjectMapper objectMapper;
    
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", System.currentTimeMillis());
        data.put("status", HttpStatus.UNAUTHORIZED.value());
        data.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
        data.put("message", "Kimlik doğrulama başarısız Tekrar oturum açınız: " + authException.getMessage());
        data.put("path", request.getRequestURI());

        objectMapper.writeValue(response.getOutputStream(), data);
    }
}
    

