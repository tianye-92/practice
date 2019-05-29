//package com.ty.quartz.job;
//
//import com.ty.quartz.remote.InventoryService;
//import org.quartz.JobExecutionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 谷仓库存同步 一天一次
// * @author xieyanbin
// *
// * @2019年5月8日
// * @version v2.2
// */
//public class GranaryWarrantStatusJob extends BaseQuartzJob {
//
//
//	@Autowired
//    InventoryService inventoryService;
//
//	@Override
//	public void executeJob(JobExecutionContext context) throws Exception {
//		inventoryService.syncWarrantStatus();
//	}
//
//}
