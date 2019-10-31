package com.chenkaiyun.ssm.service;

import java.util.List;
import com.chenkaiyun.ssm.common.util.PageData;


public interface MusicService {
	
	//获取音乐列表
	List<PageData> getMusicList();
	
}
