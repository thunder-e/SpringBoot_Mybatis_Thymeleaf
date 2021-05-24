package com.sw.s1.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {

	@Scheduled(fixedRateString = "1000", initialDelayString = "2000")
	public void fixRateScheduleTest()throws Exception{
		System.out.println("fixRateSchedule");
	}
	
	@Scheduled(cron = "0 0 9-18/2 * * 1-5")
	public void cronTest()throws Exception {
		System.out.println("Cron ~~~~~~");
	}
	
}
