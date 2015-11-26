  
/**
 * com.zywang.spring.task.SpringTaskDemo.java
 * @author ZYWANG 2011-3-9
 */
package com.ring.front.biz.service.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring3 @Scheduled 演示
 * @author ZYWANG 2011-3-9
 */
@Component
public class ScanIsFinishedProductTask {
	protected Log logger = LogFactory.getLog(this.getClass());
	//相对固定的延迟后，执行某项计划。
	@Scheduled(fixedDelay = 5000)
	void doSomethingWithDelay(){
		//System.out.println("I'm doing with delay now!");
	}
	//已固定的频率来执行某项计划(任务)。 
	@Scheduled(fixedRate = 300000)
	void doSomethingWithRate(){
		//System.out.println("I'm doing with rate now!");
		//logger.info("定时任务，5分钟一次，扫描到期项目..");
		//moneyRefundService.updateInfosAfterFinished();//5分钟执行一次对购买记录的扫描，看是否到期
	}
	//cron 
	@Scheduled(cron = "0/5 * * * * *")
	void doSomethingWith(){
		//System.out.println("I'm doing with cron now!");
	}
}
