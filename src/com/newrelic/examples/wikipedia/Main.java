package com.newrelic.examples.wikipedia;

import com.newrelic.metrics.publish.Runner;
import com.newrelic.metrics.publish.configuration.ConfigurationException;

/**
 * Main class for Wikipedia Agent Example
 * @author jstenhouse
 */
public class Main {

	public static void main(String[] args) {
		Runner runner = new Runner();
		try {
			runner.add(new WikipediaAgentFactory());
			// Never returns
			runner.setupAndRun();
		} catch (ConfigurationException e) {
			e.printStackTrace();
			System.err.println("Error configuring");
			System.exit(-1);
		}
	}
}
