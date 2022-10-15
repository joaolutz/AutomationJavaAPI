/**
 * 
 */
package br.com.alloy.springboot.automationapi.controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.alloy.springboot.automationapi.AutomationApiApplication;
import br.com.alloy.springboot.automationapi.model.TypeRequest;

/**
 * @author AlloySD
 *
 */
@RestController
@RequestMapping("/")
public class TypeController {
	
	Robot keyboard;
	
	Logger logger = LoggerFactory.getLogger(TypeController.class);
	
	@GetMapping
	public ModelAndView host() {
		return new ModelAndView("host.html");
	}

	@PostMapping("type")
	public String type(@RequestBody TypeRequest body) throws AWTException {
		if (keyboard == null) {
			keyboard = new Robot();
		}
		//log the code read in the app
		logger.info("Input: " + body.getCode());
		type(body.getCode());
		if (body.isSendEnter()) {
			keyboard.keyPress(KeyEvent.VK_ENTER);
			keyboard.keyRelease(KeyEvent.VK_ENTER);
		}
		return body.getCode() + " sent to keyboard input";
	}
	
	private void type(String s) {
		final StringSelection stringSelection = new StringSelection(s);
		final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		
		keyboard.keyPress(KeyEvent.VK_CONTROL);
		keyboard.keyPress(KeyEvent.VK_V);
		keyboard.keyRelease(KeyEvent.VK_V);
		keyboard.keyRelease(KeyEvent.VK_CONTROL);
	}
	
}
