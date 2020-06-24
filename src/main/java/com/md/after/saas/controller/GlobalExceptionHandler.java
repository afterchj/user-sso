package com.md.after.saas.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname GlobalExceptionHandler
 * @Description TODO
 * @Date 2020/06/24 08:53
 * @Created by hjchen
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView unauthorizedException(UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("forbidden");
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView Exception(Exception e) {
//        log.warn("exception {}", e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
