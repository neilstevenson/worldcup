package com.hazelcast.demo.worldcup;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Test data population.
 * </p>
 * <p>
 * Order with minimum value so runs before <a href="https://projects.spring.io/spring-shell/">Spring Shell</a>,
 * <;p>
 */
@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class ApplicationInitializer implements CommandLineRunner {

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Override
	public void run(String... args) throws Exception {
		for (String mapName : ApplicationConstants.IMAP_NAMES) {
			this.hazelcastInstance.getMap(mapName);
		}
		for (String mapName : ApplicationConstants.MULTIIMAP_NAMES) {
			this.hazelcastInstance.getMultiMap(mapName);
		}
		this.loadGames();
	}

	public void loadGames() {
		MultiMap<String, Game> gamesMap = this.hazelcastInstance.getMultiMap(ApplicationConstants.MULTIMAP_NAME_GAMES);
		if (gamesMap.size() > 0) {
			log.info("Skip loading '{}', not empty", gamesMap.getName());
		} else {
			AtomicLong count = new AtomicLong(0);
			Arrays.stream(ApplicationConstants.GAMES).forEach(line -> {
				count.incrementAndGet();
				Game game = new Game();
				game.setDescription(line[1]);
				game.setHashtag(line[2]);
				gamesMap.put(line[0], game);
			});
			log.info("Loaded {} into '{}'", count.get(), gamesMap.getName());
		}
	}

}
