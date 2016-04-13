package com.j1.health.persitent.service.impl;

import org.springframework.stereotype.Service;

import com.j1.health.persitent.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{

	@Override
	public void test() {
		System.out.println(111);
	}

}
