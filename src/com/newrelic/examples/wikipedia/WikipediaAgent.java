package com.newrelic.examples.wikipedia;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.newrelic.metrics.publish.Agent;
import com.newrelic.metrics.publish.configuration.ConfigurationException;
import com.newrelic.metrics.publish.processors.EpochCounter;
import com.newrelic.metrics.publish.processors.Processor;

/**
 * An agent for Wikipedia. 
 * This agent will log both Articles Created and Article Count metrics.
 * @author jstenhouse
 */
public class WikipediaAgent extends Agent {

	private String name;
	private URL url;
	private Processor articleCreationRate;

	/**
	 * Constructor for Wikipedia Agent.
	 * Uses name for Component Human Label and host for building Wikipedia's Metric service.
	 * @param name
	 * @param host
	 * @throws ConfigurationException if URL for Wikipedia's metric service could not be built correctly from provided host
	 */
	public WikipediaAgent(String name, String host) throws ConfigurationException {
		super("com.newrelic.examples.wikipedia", "1.0.3");
		try {
			this.name = name;
			this.url = new URL("http", host, "/w/api.php?action=query&format=json&meta=siteinfo&siprop=statistics");
			this.articleCreationRate = new EpochCounter();
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Wikipedia metric URL could not be parsed", e);
		}
	}

	@Override
	public String getComponentHumanLabel() {
		return name;
	}

	@Override
	public void pollCycle() {
		Integer numArticles = getNumArticles();
		if (numArticles != null) {
			 reportMetric("Articles/Created", "articles/sec", articleCreationRate.process(numArticles).floatValue());
			 reportMetric("Articles/Count", "articles", numArticles);
		}
	}

	/**
	 * Get Number of Articles from Wikipedia
	 * @return Integer the number of articles
	 */
	private Integer getNumArticles() {
		JSONObject jsonObj = getJSONResponse();
		if (jsonObj != null) {
			JSONObject query = (JSONObject) jsonObj.get("query");
			JSONObject statistics = (JSONObject) query.get("statistics");
			Long numArticles = (Long) statistics.get("articles");
			return numArticles.intValue();
		} else {
			return null;
		}
	}

	/**
	 * Get JSON Response from Wikipedia
	 * @return JSONObject the JSONObject response
	 */
	private JSONObject getJSONResponse() {
		Object response = null;
		InputStream is = null;
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("Accept", "application/json");
			is = connection.getInputStream();
			response = JSONValue.parse(new InputStreamReader(is));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {}
			}
			if(connection != null) {
				connection.disconnect();
			}
		}
		return (JSONObject) response;
	}

}
