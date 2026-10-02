package com.telemedcine.filter;

import com.telemedcine.entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private final Map<String, List<String>> rolePermissions = Map.of(
            "ADMIN", List.of("/"),
            "PATIENT", List.of("/patient", "/consultations", "/dashboard"),
            "INFIRMIER", List.of("/infirmier", "/patients", "/dashboard"),
            "GENERALISTE", List.of("/generaliste", "/consultations", "/patients", "/dashboard"),
            "SPECIALISTE", List.of("/specialiste", "/expertises", "/patients", "/dashboard")
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        Object sessionUser = session == null ? null : session.getAttribute("user");
        if (!(sessionUser instanceof User user)) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String userRole = user.getRole() == null ? "" : user.getRole().name();

        List<String> allowedPrefixes = rolePermissions.getOrDefault(userRole, List.of());
        boolean hasAccess = allowedPrefixes.stream().anyMatch(allowedPath -> matchesPath(allowedPath, path));

        if (hasAccess) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé : vous n'avez pas les droits nécessaires.");
        }
    }

    private boolean isPublicPath(String path) {
        return path.equals("/login")
                || path.equals("/login.jsp")
                || path.equals("/")
                || path.startsWith("/public/")
                || path.startsWith("/assets/")
                || path.endsWith(".css")
                || path.endsWith(".js")
                || path.endsWith(".png")
                || path.endsWith(".jpg")
                || path.endsWith(".jpeg")
                || path.endsWith(".gif")
                || path.endsWith(".svg")
                || path.equals("/favicon.ico");
    }

    private boolean matchesPath(String allowedPath, String path) {
        return "/".equals(allowedPath)
                || allowedPath.equals(path)
                || path.startsWith(allowedPath + "/");
    }
}