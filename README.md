Wikipedia Java Plugin for New Relic
========================================

Prerequisites
-------------

The Wikipedia Plugin requires the following:

	1. A New Relic account. Signup for a free account at http://newrelic.com
	2. A configured Java Runtime (JRE) environment Version 1.7 or better
	3. Internet access to report gathered metrics from Wikipedia to New Relic.
	
Configuring your Agent Environment
----------------------------------

A few simple steps:
	
	1. Extract the downloaded plugin archive and open the config folder.
	2. Edit the newrelic.properties file within the config folder and replace the string YOUR_LICENSE_KEY_HERE with the license key from your New Relic account.

Running the Agent
-----------------

From your shell run: java -jar wikipedia-beta.jar

Source Code
-----------

This plugin can be found at https://github.com/newrelic-platform/newrelic_java_wikipedia_plugin