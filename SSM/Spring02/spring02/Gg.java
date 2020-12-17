package com.gk.spring02;

import javax.annotation.Resource;

public class Gg {
	@Resource
	private Mm mm;
	
	public void test(){
		mm.test();
	}
}
