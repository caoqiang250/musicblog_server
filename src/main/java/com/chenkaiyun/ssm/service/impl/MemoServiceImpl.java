package com.chenkaiyun.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenkaiyun.ssm.common.util.PageData;
import com.chenkaiyun.ssm.mapper.MemoMapper;
import com.chenkaiyun.ssm.service.MemoService;

@Service
@Transactional

public class MemoServiceImpl implements MemoService{
	@Autowired
	private MemoMapper memoMapper;
	
	//定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(MemoServiceImpl.class); 
	
	@Override
	//获取备忘录信息列表
	public List<PageData> getMemoList(PageData pd) {
		// TODO Auto-generated method stub
		return memoMapper.getMemoList(pd);

	}
	
}
