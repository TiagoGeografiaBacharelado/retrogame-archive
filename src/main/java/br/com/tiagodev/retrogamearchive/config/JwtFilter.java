package br.com.tiagodev.retrogamearchive.config;

import br.com.tiagodev.retrogamearchive.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Pega o header Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Se não tem token, deixa passar (rotas públicas)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrai o token removendo "Bearer "
        String token = authHeader.substring(7);

        // 4. Extrai email e role do token
        String email = jwtService.extractEmail(token);
        String role = jwtService.extractRole(token);

        // 5. Se tem email e ainda não está autenticado
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Valida o token
            if (jwtService.isTokenValid(token, email)) {

                // 7. Cria a autenticação e coloca no contexto de segurança
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 8. Continua para o próximo filtro
        filterChain.doFilter(request, response);
    }
}
