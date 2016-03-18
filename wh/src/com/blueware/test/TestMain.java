package com.blueware.test;

import java.util.List;

import com.blueware.entity.WebHook;
import com.blueware.web.WebQuey;

public class TestMain {

	public static void main(String[] args) {
		WebQuey query = new WebQuey();
		List<WebHook> list = query.SearchByDate("2015-8-5");
		for(int i = 0; i < list.size(); i++){
		System.out.println(list.get(i).getEvent());
		}

	}

}
