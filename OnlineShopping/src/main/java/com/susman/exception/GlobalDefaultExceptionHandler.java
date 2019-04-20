package com.susman.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
@ExceptionHandler(NoHandlerFoundException.class)
public ModelAndView noHandlerFoundExceptio(){
	ModelAndView mav=new ModelAndView("error");
	mav.addObject("errorTitle","The page is not constructed");
	mav.addObject("errorDescription","The page you looking for is not available now !");
	mav.addObject("title","404 error page");
	return mav;
}
@ExceptionHandler(ProductNotFoundException.class)
public ModelAndView productNotFoundException(){
	ModelAndView mav=new ModelAndView("error");
	mav.addObject("errorTitle","Product not available");
	mav.addObject("errorDescription","The product you looking for is not available now !");
	mav.addObject("title","Product Unavailable");
	return mav;
}
@ExceptionHandler(Exception.class)
public ModelAndView handlerException(Exception ex){
	ModelAndView mav=new ModelAndView("error");
	mav.addObject("errorTitle","Contact your Administrator!");
	//only for debugging not for production envirnoment
	StringWriter sw=new StringWriter();
	PrintWriter pw=new PrintWriter(sw);
	ex.printStackTrace(pw);
	
	
	mav.addObject("errorDescription",sw.toString());
	mav.addObject("title","Error");
	return mav;
}
}
