package com.blueware.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.service.WeChatMenuService;
import com.blueware.util.TimeTools;

public class RunAccessToken implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(RunAccessToken.class);
	 public void run() {
         while (true) {
                 try {
//                	 WeChatMenuService.updateAccessToken();
                	 LOG.info(TimeTools.format()+":更新accesstoken完成");
                 } catch (Exception e) {
                	 LOG.error("update error");
                 }
                 try {
                         Thread.sleep(5400000);
                 } catch (Exception e) {
                 }
         }
 }

}
