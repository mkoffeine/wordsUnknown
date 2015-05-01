package mkoffeine.wordsunk.controller;

import mkoffeine.wordsunk.dao.UserDao;
import mkoffeine.wordsunk.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Michael on 02.04.2015.
 */
@Controller

public class Worker {
    @Autowired
    private UserDao userDao;
    @RequestMapping("/work")
    public ModelAndView work() {
        ModelAndView modelAndView = new ModelAndView("work-init");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        modelAndView.addObject("user",userDao.getUserByName(auth.getName()));
        modelAndView.addObject("user", getLoggedUserEntity());
        return modelAndView;
    }

    @RequestMapping("/users")
    public ModelAndView usersList() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users",userDao.getAll());
        return modelAndView;
    }

    private UserEntity getLoggedUserEntity() {
        return userDao.getUserById(3);
    }
}
