package com.hazelcast.demo.worldcup.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import com.hazelcast.demo.worldcup.MyConfigurationProperties;

/**
 * <p>
 * Create a custom prompt for the shell.
 * </p>
 */
@Component
public class MyPrompt implements PromptProvider {

	@Autowired
	public MyConfigurationProperties myConfigurationProperties;
	@Value("${spring.application.name}")
	public String springApplicationName;

    private AttributedString prompt;
    
    @Override
    public AttributedString getPrompt() {
    	if (this.prompt == null) {
    		String ps1 = 
    				this.springApplicationName +
    				" '" +
    				this.myConfigurationProperties.getHashtag() + 
    				"' $ ";
    		this.prompt = new AttributedString(ps1,
    				AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
    				);
    	}
    	return this.prompt;
    }

}
