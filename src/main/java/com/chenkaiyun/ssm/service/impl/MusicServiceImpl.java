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
import com.chenkaiyun.ssm.mapper.MusicMapper;
import com.chenkaiyun.ssm.service.MusicService;

@Service
@Transactional

public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicMapper musicMapper;
	
	//定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(MusicServiceImpl.class); 
	
	@Override
	//获取桌面客户端推送信息
	public List<PageData> getMusicList() {
		// TODO Auto-generated method stub
		return musicMapper.getMusicList();

	}
	
}
