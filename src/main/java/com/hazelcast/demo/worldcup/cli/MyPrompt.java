package com.hazelcast.demo.worldcup.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Create a custom prompt for the shell.
 * </p>
 */
@Component
public class MyPrompt implements PromptProvider {

    private AttributedString prompt;
    
    @Override
    public AttributedString getPrompt() {
    	if (this.prompt == null) {
    		String ps1 = "WorldCup $ ";
    		this.prompt = new AttributedString(ps1,
    				AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
    				);
    	}
    	return this.prompt;
    }

}
