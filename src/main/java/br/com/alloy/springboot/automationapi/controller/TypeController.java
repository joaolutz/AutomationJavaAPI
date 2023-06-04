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

import br.com.alloy.springboot.automationapi.model.TypeRequest;

/**
 * @author Codecraft
 *
 */
@RestController
@RequestMapping("/")
public class TypeController {
	
	Robot robot;
	
	Logger logger = LoggerFactory.getLogger(TypeController.class);
	
	@GetMapping
	public ModelAndView host() {
		return new ModelAndView("host.html");
	}

	@PostMapping("type")
	public String type(@RequestBody TypeRequest body) throws AWTException {
		if (robot == null) {
			robot = new Robot();
		}
		//log the code read in the app
		logger.info("Input: " + body.getCode());
		switch(body.getTypeMode()) {
			case CLIPBOARD:
				typeClipboard(body.getCode());
				break;
			case TYPE:
				typeDefault(body.getCode());
		}
		if (body.isSendEnter()) {
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}
		return body.getCode() + " sent to keyboard input";
	}

	private void typeClipboard(String s) {
		final StringSelection stringSelection = new StringSelection(s);
		final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	private void typeDefault(String code) {
		for (char c : code.toCharArray()) {
	        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
	        if (KeyEvent.CHAR_UNDEFINED == keyCode || keyCode == 0) {
	            throw new RuntimeException(
	                "Key code not found for character '" + c + "'");
	        }
	        if (keyCode < 100) {
	        	robot.keyPress(keyCode);
		        robot.keyRelease(keyCode);
	        } else { //pressiona tecla com o shift acionado
	        	robot.keyPress(KeyEvent.VK_SHIFT);
	        	robot.keyPress(keyCode);
		        robot.keyRelease(keyCode);
		        robot.keyRelease(KeyEvent.VK_SHIFT);
	        }
	        //robot.delay(100);
	    }
	}
}
