package com.blueware.util;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.init.ConfigInfoDepository;
import com.blueware.kf5.service.KF5ApiV2;
import com.blueware.run.RunAccessToken;
import com.blueware.util.db.MongoConnection;
import com.kf5.support.controller.KF5Support;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

public class Listener implements ServletContextListener {

        private static final Logger LOG = LoggerFactory.getLogger(Listener.class);
        public static ServletContext systemApplication;
        public void contextDestroyed(ServletContextEvent arg0) {
                MongoConnection.close();
                LOG.info("error..........................................");
        }

        public void contextInitialized(ServletContextEvent arg0) {
                LOG.info("db finish..........................................");
                try {
                        init(arg0);
                } catch (DocumentException e) {
                        e.printStackTrace();
                }
        }

        public void init(ServletContextEvent event) throws DocumentException {
                SAXReader reader = new SAXReader();
                systemApplication = event.getServletContext();
                Document doc = reader.read(new File(systemApplication.getRealPath("/"), "WEB-INF/classes/config/systemserver-config.xml"));
                Element root = doc.getRootElement();
                try {
        			Class.forName("com.blueware.init.DataSourceManager", true, this.getClass().getClassLoader());
        			Class.forName("com.blueware.init.ListenerConfigManager", true, this.getClass().getClassLoader());
        			Class.forName("com.blueware.init.KF5ConfigManager", true, this.getClass().getClassLoader());
        		} catch (ClassNotFoundException e) {
        			LOG.info("when to load Config Classes", e);
        		}
                LOG.info(ConfigInfoDepository.Kf5.HOST+"................"+ConfigInfoDepository.Kf5.EMAIL+"............"+ConfigInfoDepository.Kf5.PASSWORD);
                try {
                    KF5ApiV2.init(ConfigInfoDepository.Kf5.HOST, ConfigInfoDepository.Kf5.EMAIL, ConfigInfoDepository.Kf5.PASSWORD);
	            } catch (Exception e) {
	                    LOG.error(e.getMessage(), e);
	            }
                LOG.info("init mongodb.......................................................");
                try {
                        initMongoDao((Element) root.selectSingleNode("mongodb/mongodata"));
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                LOG.info("init finished .....................................................");
                LOG.info("start run ......................................................");
                try {
                	new Thread(new RunAccessToken()).start();
                } catch (Exception e) {
                	LOG.error(e.getMessage(), e);
                }
        }

        public static void initMongoDao(Element Element) throws UnknownHostException {
                Element element = (Element) Element.selectSingleNode("serverNode");
                String host = element.getTextTrim();
                element = (Element) Element.selectSingleNode("serverPort");
                int port = Integer.parseInt(element.getTextTrim());
                element = (Element) Element.selectSingleNode("autoConnectRetry");
                boolean autoConnectRetry = Boolean.parseBoolean(element.getText().trim());
                element = (Element) Element.selectSingleNode("connectionsPerHost");
                int connectionsPerHost = Integer.parseInt(element.getText().trim());
                element = (Element) Element.selectSingleNode("maxWaitTime");
                int maxWaitTime = Integer.parseInt(element.getText().trim());
                element = (Element) Element.selectSingleNode("defauledb");
                String defaultDb = element.getText().trim();
                LOG.info(host+"---"+port+"----"+defaultDb);
                List<ServerAddress> replicaSet = new ArrayList<ServerAddress>();
                replicaSet.add(new ServerAddress(host, port));
                MongoClientOptions option = MongoClientOptions.builder().autoConnectRetry(autoConnectRetry).connectionsPerHost(connectionsPerHost).maxWaitTime(maxWaitTime).build();
                MongoConnection.init(replicaSet, option, defaultDb);
                LOG.info("mongodb连接参数初始化完毕.........");

        }
        
        

}
