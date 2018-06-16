# World Cup 2018

Copa do Mundo

Copa Mundial

Weltmeisterschaft

Coppa del Mondo

Dunya Kupasi

Chempionat Mira

## Instructions

There are three main steps to build and run

### Twitter connection properties

The application makes an OAuth connection to Twitter, for which you need to provide some
credentials. 

These credentials should belong to your Twitter account. You connect as you. So they're not
provided in this public demonstration.

To create credentials, go to [https://apps.twitter.com/](https://apps.twitter.com/) and select the "_Create New App_" button. We're not going to create a Twitter app, but this is just the simplest way to get hold of the necessary credentials. 

Once you have created a dummy app, click on it, and in the "_Keys And Access Tokens_" tab you need to copy the fields "*Consumer Key (API Key)*", "*Consumer Secret (API Secret)*", "*Access Token*" and "*Access Token Secret*".

Create a file called `application.properties` and set these properties with the values copied from Twitter:

```
my.consumerKey=
my.consumerSecret=
my.token=
my.tokenSecret=
```

### Hashtag
In the `pom.xml` file, the property "`my.hashtag`" is defined.

This is the hashtag to search Twiiter for. For example, try "_ARGvICE_" for tweets about the
Argentina v Iceland game (on 16th June, result was 1-1).

The Maven build process will inject this property into the `application.yml` file, and then Spring loads substituted value when the JVM starts.

### Command line

To start the application from the command line, you need to make sure you use both the `application.yml` file updated by Maven, and the `application.properties` file with your Twitter properties.

This command will do it:

```
java -jar target/worldcup-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:application.yml,file:${HOME}/Documents/application.properties
```

The `--spring.config.location` section indicates to use the `application.yml` file from the classpath (ie. inside the *Jar* file) and the `application.properties` from the file-system.

This could be made more elegant, feel free to amend to suit you.

