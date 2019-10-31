package com.chenkaiyun.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chenkaiyun.ssm.common.util.PageData;


@Mapper
public interface MusicMapper {
	//获取音乐列表
	List<PageData> getMusicList();
	
}
