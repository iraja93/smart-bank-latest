package com.capgemini.config;

import static org.junit.Assert.*;
import org.junit.Test;
import com.capgemini.config.JHipsterProperties.Cache;
import com.capgemini.config.JHipsterProperties.Mail;
import com.capgemini.config.JHipsterProperties.Cache.Ehcache;
import com.capgemini.config.JHipsterProperties.Logging.Logstash;
import com.capgemini.config.JHipsterProperties.Metrics.Graphite;
import com.capgemini.config.JHipsterProperties.Metrics.Logs;
import com.capgemini.config.JHipsterProperties.Metrics.Spark;

public class JhipsterPropertiesTest {

	@Test
	public void setTimeToLiveInDays() {
		Cache cache =new Cache();
		cache.setTimeToLiveSeconds(1461);
		assertTrue(cache.getTimeToLiveSeconds()==1461);
	}

	@Test
	public void setMaxBytesLocalHeap() {
		Ehcache ehcache= new Ehcache();
		ehcache.setMaxBytesLocalHeap("16M");
		assertTrue(ehcache.getMaxBytesLocalHeap()=="16M");

	}

	@Test
	public void setFrom() {
		Mail mail =new Mail();
		mail.setFrom("smartbank@localhost");
		assertTrue(mail.getFrom()=="smartbank@localhost");
	}

	@Test
	public void setEnabled(){
		Spark spark=new Spark();
		spark.setEnabled(false);
		assertTrue(spark.isEnabled()==false);
	}

	@Test
	public void setHost(){
		Spark spark=new Spark();
		spark.setHost("localhost");
		assertTrue(spark.getHost()== "localhost");
	}

	@Test
	public void setPort(){
		Spark spark=new Spark();
		spark.setPort(9999);
		assertTrue(spark.getPort()==9999);
	}

	@Test
	public void setPrefix(){
		Graphite graphite=new Graphite();
		graphite.setPrefix("smartbank");
		assertTrue(graphite.getPrefix()=="smartbank");
	}

	@Test
	public void setReportFrequency(){
		Logs logs=new Logs();
		logs.setReportFrequency(60);
		assertTrue(logs.getReportFrequency()==60);
	}

	@Test
	public void setQueueSize(){
		Logstash logstash=new Logstash();
		logstash.setQueueSize(512);
		assertTrue(logstash.getQueueSize()==512);
	}
}
