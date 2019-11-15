package ru.alexk.project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexk.project.DAO.UserDAO;
import ru.alexk.project.entities.User;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserDAO userDAO;

    @PostMapping(path = "/login")
    public String processLogin(
            HttpSession session,
            @RequestParam String login,
            @RequestParam String password,
            ModelMap model) {
        try {
            User found = userDAO.findByNickAndPassword(login,password);
            session.setAttribute("accountId", found.getId());

            return "redirect:/dashboard";
        } catch (NoResultException notFound) {
            model.addAttribute("login", "login");
            return "index";
        }
    }
}