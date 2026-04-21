package com.albertogalvez.Kinalapp.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        System.out.println("=== INTERCEPTOR ===");
        System.out.println("URI: " + uri);
        System.out.println("Sesión usuarioLogueado: " + session.getAttribute("usuarioLogueado"));


        if (uri.equals("/login") || uri.equals("/register") ||
                uri.startsWith("/css") || uri.startsWith("/js") ||
                uri.equals("/") || uri.startsWith("/test") ||
                uri.equals("/error")) {   // ← AGREGAR ESTO
            System.out.println("Permitiendo acceso a: " + uri);
            return true;
        }


        if (session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("/login");
            System.out.println("No hay usuario logueado, redirigiendo a login");
            return false;
        }

        System.out.println("Usuario logueado, permitiendo acceso a: " + uri);
        return true;
    }
}