package com.hui.common.utils;

public class PaginationConfig {
	
	public static final int pageSize = 10;
	
	public static int pageOffSet(int page){
		return (page-1)*pageSize;
	}
	
	
}
