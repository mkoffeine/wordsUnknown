package mkoffeine.wordsunk.controller;

import mkoffeine.wordsunk.dao.UserDao;
import mkoffeine.wordsunk.entity.UserEntity;
import mkoffeine.wordsunk.utils.MaxValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Random;

/**
 * Created by Michael on 02.04.2015.
 */
@Controller

public class Worker {
    //    @Autowired
    @Autowired
    @Qualifier("userHibernateDao")
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/work")
    public ModelAndView work() {
        ModelAndView modelAndView = new ModelAndView("work-init");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", userDao.getUserByName(auth.getName()));
//        modelAndView.addObject("user", getUserEntityWithFile());
        return modelAndView;
    }

    @RequestMapping("/users")
    public ModelAndView usersList() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userDao.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTime() {

        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<html><br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result + "</html>";
    }

    @RequestMapping(value = "/saveWords", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveWords(@RequestParam("words") String words) {
        System.out.println("-------------------starting saving words");
        UserEntity user = userDao.getUserById(1);
        user.setWords(words);
        userDao.saveUsersWords(user);
        String result = "<br>The result is <b> successful? </b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("-------------------end saving words");
        return result;
    }


    private UserEntity getLoggedUserEntity() {
        return userDao.getUserById(3);
    }

    private UserEntity getUserEntityWithFile() {
        return userDao.getUserById(1);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerForm() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerProcess(@RequestParam(value = "username", required = true) String userName,
                                        @RequestParam(value = "password", required = true) String password,
                                        @RequestParam(value = "passwordRepeat", required = true) String passwordAgain,
                                        @RequestParam(value = "email", required = true) String email) {
        ModelAndView model;
        if (!password.equals(passwordAgain)) {
            model = new ModelAndView("register");
            model.addObject("error", "Invalid 2 passwords!");
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setLoginName(userName);
            userEntity.setPassword(password);
            userEntity.setUserGroup("user");


            userDao.insertUser(userEntity);

            model = new ModelAndView("login");
            model.addObject("registered", "You (" + userName + ") have been registered successfully");
        }
        return model;

    }
}
