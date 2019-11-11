package com.chenkaiyun.ssm.controller.memo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenkaiyun.ssm.common.util.PageData;
import com.chenkaiyun.ssm.controller.base.BaseController;
import com.chenkaiyun.ssm.service.MemoService;

//备忘信息相关
@RestController
@RequestMapping("/api/memo")
public class MemoController extends BaseController {
	@Autowired
	private MemoService memoService;

	
	//获取被网络列表（当月）
	@RequestMapping("/getMemoList")
	List<PageData> getMemoList(){
		PageData pd = this.getPageData();
		return memoService.getMemoList(pd);
	}


}
