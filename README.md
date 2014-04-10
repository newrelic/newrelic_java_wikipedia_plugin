Wikipedia Java Plugin for New Relic
========================================

Prerequisites
-------------

1. A New Relic account. Signup for a free account at http://newrelic.com
2. A configured Java Developer Kit (JDK) - version 1.6 or better

Running the Agent
----------------------------------

1. Download the latest `newrelic_wikipedia_plugin-X.Y.Z.tar.gz` from https://github.com/newrelic-platform/newrelic_java_wikipedia_plugin/tree/master/dist by clicking on the file name and selecting `Raw` from the gray menu bar
2. Extract the downloaded archive to the location you want to run the example agent from
3. Copy `config/newrelic.template.json` to `config/newrelic.json`
4. Edit `config/newrelic.json` and replace "YOUR_LICENSE_KEY_HERE" with your New Relic license key
5. From your terminal run: `java -jar plugin.jar`
6. Wait a few minutes for New Relic to begin processing the data sent from your agent.
6. Login into your New Relic account at http://newrelic.com and click on `Wikipedia` on the left hand nav bar to start seeing your Wikipedia metrics


## New Relic Platform Installer (Beta)

The New Relic Platform Installer (NPI) is a simple, lightweight command line tool that helps you easily download, configure and manage New Relic Platform Plugins.  If you're interested in participating in our public beta, simply go to [our forum category](https://discuss.newrelic.com/category/platform-plugins/platform-installer-beta) and checkout the ['Getting Started' section](https://discuss.newrelic.com/t/getting-started-for-the-platform-installer-beta/842).  If you have any questions, concerns or feedback, please do not hesitate to reach out through the forums as we greatly appreciate your feedback!

Once you've installed the NPI tool, run the following command:

	./npi install com.newrelic.examples.wikipedia


Source Code
-----------

This plugin can be found at https://github.com/newrelic-platform/newrelic_java_wikipedia_plugin