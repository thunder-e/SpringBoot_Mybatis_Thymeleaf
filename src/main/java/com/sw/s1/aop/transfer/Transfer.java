package com.sw.s1.aop.transfer;

import org.springframework.stereotype.Component;

@Component
public class Transfer {

	public String takeSubway() {
		System.out.println("지하철 탑승 !!!!");
		System.out.println("브이앱 시청");
		
		return "7호선";
	}
	
	public void takeBus() {
		System.out.println("버스 탑승 !!!!");
		System.out.println("음악 듣기");
	}
	
	
}
