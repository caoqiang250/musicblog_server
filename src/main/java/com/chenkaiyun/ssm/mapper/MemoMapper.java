package com.chenkaiyun.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chenkaiyun.ssm.common.util.PageData;


@Mapper
public interface MemoMapper {
	//获取备忘录列表
	List<PageData> getMemoList(PageData pd);
	
}
