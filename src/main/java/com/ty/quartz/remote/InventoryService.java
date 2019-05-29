//package com.ty.quartz.remote;
//
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//
///**
// * 使用feign远程调用服务接口
// *
// * @ClassName QuartzJobFactoryConfig
// * @Author tianye
// * @Date 2019/5/22 10:22
// * @Version 1.0
// */
//@FeignClient(name = "rondaful-supplier-service", fallback = InventoryService.InventoryServiceImpl.class)
//public interface InventoryService {
//
//	/** 定时发送系统公告 **/
//	@PostMapping("/provider/inventoryWarnNotice")
//	public void inventoryWarnNotice();
//
//	@PostMapping("/provider/initGranaryInventory")
//	public void initGranaryInventory();
//
//	@PostMapping("/provider/initERPInventory")
//	public void syncERPInventory();
//
//	@PostMapping("/provider//syncWarrantStatus")
//	public void syncWarrantStatus();
//
//
//
//	@Service
//	class InventoryServiceImpl implements InventoryService {
//
//		@Override
//		public void inventoryWarnNotice() {
//
//		}
//
//		@Override
//		public void initGranaryInventory() {
//
//		}
//
//		@Override
//		public void syncERPInventory() {
//
//		}
//
//		@Override
//		public void syncWarrantStatus() {
//			// TODO Auto-generated method stub
//
//		}
//
//	}
//
//}
