package com.telemedcine.servlet;

import com.telemedcine.entity.User;
import com.telemedcine.util.JPAUtil;
import com.telemedcine.util.PasswordUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null || email.isBlank() || password.isEmpty()) {
            showLoginError(req, resp);
            return;
        }

        User user = findUserByEmail(email.trim());

        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            HttpSession session = req.getSession(true);
            req.changeSessionId();

            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            showLoginError(req, resp);
        }
    }

    private User findUserByEmail(String email) throws ServletException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            return entityManager.createQuery(
                            "SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)",
                            User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (RuntimeException exception) {
            throw new ServletException("Impossible de vérifier les identifiants.", exception);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    private void showLoginError(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("error", "Identifiants invalides");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}