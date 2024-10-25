package org.mudoker;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.mudoker.config.AppConfig;
import org.mudoker.config.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		// Initialize the web application context
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class, WebConfig.class);

		// Initialize Tomcat server
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		// Set the context path correctly
		Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

		// Initialize and register the DispatcherServlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet).setLoadOnStartup(1);
		ctx.addServletMappingDecoded("/*", "dispatcher"); // Map all requests to the dispatcher

		try {
			// Start the Tomcat server
			tomcat.start();
			tomcat.getServer().await(); // Wait for requests
		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace for better debugging
		}
	}
}

