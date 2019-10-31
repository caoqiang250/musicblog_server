package com.chenkaiyun.ssm.controller.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenkaiyun.ssm.common.util.PageData;
import com.chenkaiyun.ssm.controller.base.BaseController;
import com.chenkaiyun.ssm.service.MusicService;


@RestController
@RequestMapping("/api/music")
public class MusicController extends BaseController {
	@Autowired
	private MusicService musicService;

	
	//获取音乐列表
	@RequestMapping("/getMusicList")
	List<PageData> getBulletinList(){
		return musicService.getMusicList();
	}


}
