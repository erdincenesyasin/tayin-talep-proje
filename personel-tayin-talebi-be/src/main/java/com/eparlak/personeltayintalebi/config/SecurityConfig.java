package com.eparlak.personeltayintalebi.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.eparlak.personeltayintalebi.security.CustomAccessDeniedHandler;
import com.eparlak.personeltayintalebi.security.CustomAuthenticationEntryPoint;
import com.eparlak.personeltayintalebi.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/auth/**", "/resources/**", "/*.js", "/*.css", "/*.ico", "/*.json","/favicon.ico", "/index.html", "/assets/**","/test-verilerini-yukle/**", "/static/**").permitAll()
                        .requestMatchers("/admin/tercih/aktif-basvurular", "/adliye/tercih-formu-icin-adliye-listesi")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/talep-turleri/**").hasAnyRole("TESTADMIN","GNLMDR","GNLMDRYRD","DAIREBSK","SBMDR") //yetkililer post etsin, herkes get yapabilsin.
                        .requestMatchers(HttpMethod.DELETE, "/admin/tercih/**").hasAnyRole("TESTADMIN","GNLMDR","GNLMDRYRD") 
                        .requestMatchers("/admin/tercih/onayla").hasAnyRole("TESTADMIN","GNLMDR","GNLMDRYRD")////ONAYLAMA GENEL MÜDÜR VE GENEL MÜDÜR YARDIMCISI ROLUNDE
                        .requestMatchers("/admin/tercih/isActive/**").hasAnyRole("TESTADMIN","GNLMDR","GNLMDRYRD","DAIREBSK","SBMDR")//ONAYLANDIKTAN SONRADA aktif-DEAKTİF EDİLEBİLİR.
                        .requestMatchers("/admin/tercih/**", "/personel/**").hasAnyRole("TESTADMIN","GNLMDR","GNLMDRYRD","DAIREBSK","SBMDR","SBMEMUR")//memurda çalışma hazırlar ama aktif-onay yetkisi yok.
                        .requestMatchers("/admin/**", "/adliye/**").hasAnyRole("TESTADMIN","GNLMDR","GNLMDRYRD","DAIREBSK","SBMDR")//YUKARIDAKİ İSTİSNLAR DIŞINDA PGM PERSONELİ TAM YETKİLİ
                        .anyRequest().authenticated())

                    //daha kapsamlı rol-yetki ayarlaması verilebilirdi, ancak gerçek bir proje olmadığı için kısa tutuldu. 

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable()) 
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions 
                .authenticationEntryPoint(customAuthenticationEntryPoint)//oturum açmayan kişi istek atarsa bu kısım çalışacak.
                .accessDeniedHandler(customAccessDeniedHandler)
            )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080"));
       
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        configuration.setAllowedHeaders(Arrays.asList("*"));

        configuration.setAllowCredentials(true);

        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
