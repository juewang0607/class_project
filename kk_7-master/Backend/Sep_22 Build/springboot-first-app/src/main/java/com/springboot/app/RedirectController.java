package com.springboot.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

public class RedirectController {

	 @GetMapping("/redirectWithRedirectView")
	 public RedirectView redirectWithUsingRedirectView(
	      RedirectAttributes attributes) {
	        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
	        attributes.addAttribute("attribute", "redirectWithRedirectView");
	        return new RedirectView("redirectedUrl");      
	 }
}
