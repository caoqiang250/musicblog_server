package com.chenkaiyun.ssm.service;

import java.util.List;
import com.chenkaiyun.ssm.common.util.PageData;


public interface MemoService {
	
	//获取音乐列表
	List<PageData> getMemoList(PageData pd);
	
}
