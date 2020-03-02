package org.example;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static Logger log = Logger.getLogger(AppTest.class);

    private static final String JMETER_HOME = "/usr/local/Cellar/jmeter/5.2.1/libexec";


    @Test
    public void test() throws IOException, URISyntaxException {
        log.info("start loadControl1()");

        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties(JMETER_HOME + "/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(JMETER_HOME);
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //URL url = classLoader.getResource("tutorial/jmeter/Tutorial6.jmx");
        URL url = classLoader.getResource("shot1-1.jmx");
        File file = new File(url.toURI());
        HashTree testPlanTree = SaveService.loadTree(file);

        // Run JMeter Test
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        jmeter.configure(testPlanTree);
        jmeter.run();
    }
}
