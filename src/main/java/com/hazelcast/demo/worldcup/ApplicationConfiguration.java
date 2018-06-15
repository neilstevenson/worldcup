package com.hazelcast.demo.worldcup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.config.JetConfig;

/**
 * <p>"<i>temporary code</i>"
 * </p>
 * <p>This class defines Spring beans to create a single Hazelcast IMDG and Jet
 * node in this JVM.
 * </p>
 * <p>There is a pull request (<a href="https://github.com/spring-projects/spring-boot/issues/8863">Issue 8863</a>)
 * for Spring Boot to do this for us automatically. Once this is merged, the onerous burden of writing the 13
 * lines of code below can be eliminated and this whole class can be deleted.
 * </p>
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public Config config() {
		return new ClasspathXmlConfig("hazelcast.xml");
	}

	@Bean
	public JetInstance jetInstance(Config config) {
		JetConfig jetConfig = new JetConfig().setHazelcastConfig(config);
		return Jet.newJetInstance(jetConfig);
	}

	@Bean
	public HazelcastInstance hazelcastInstance(JetInstance jetInstance) {
		return jetInstance.getHazelcastInstance();
	}

}
