package com.ty.Controller;

import com.github.pagehelper.PageInfo;
import com.ty.quartz.entity.*;
import com.ty.quartz.service.QuartzService;
import com.ty.quartz.service.TaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * quartz定时任务Controller
 *
 * @ClassName QuartzController
 * @Author tianye
 * @Date 2019/5/22 10:18
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/quartz")
@Api("定时任务管理Controller")
public class QuartzController {

    @Resource
    private QuartzService taskService;

    @Resource
    private TaskLogService taskLogService;

    public static final String PLATFORM_TYPE = "<platform_task>_";

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "任务列表")
    @GetMapping(value = "/list")
    public ResultMessage<Page<QuartzPageModel>> list(String page, String row, QuartzPageModel taskPage) {
        Page.builder(page, row);
        return ResultMessage.message(new Page(new PageInfo(taskService.pageList(taskPage))), true, "任务列表!");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "任务日志")
    @GetMapping(value = "/logList")
    public ResultMessage<Page<QuartzLogModel>> logList(String page, String row, QuartzLogModel taskLog) {
        Page.builder(page, row);
        return ResultMessage.message(new Page(new PageInfo(taskLogService.findTaskLog(taskLog))), true, "任务日志列表!");
    }

    @ApiOperation(value = "[保存&修改]定时任务")
    @PostMapping(value = "/save", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> save(@RequestBody QuartzModel task) {
        try {
            platformCheck(task);
            if ("Y".equalsIgnoreCase(task.getIsEdit())) {
                taskService.edit(task);
            } else {
                taskService.addJob(task);
            }
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "[保存&修改]定时任务成功");
    }

    @ApiOperation(value = "任务检查")
    @PostMapping(value = "/checkTask", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> checkTask(@RequestBody JobModel task) {
        try {
            Class.forName(task.getJobName());
            platformCheck(task);
            if (taskService.checkExists(task.getJobName(), task.getJobGroup())) {
                return ResultMessage.message(false, "任务名已存在不可用");
            } else {
                return ResultMessage.message(true, "任务名可以使用");
            }
        } catch (ClassNotFoundException e) {
            return ResultMessage.message(false, "Job任务类不存在");
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
    }

    @ApiOperation(value = "删除定时任务")
    @PostMapping(value = "/delete", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> delete(@RequestBody JobModel job) {
        try {
            platformCheck(job);
            taskService.delete(job.getJobName(), job.getJobGroup());
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "删除定时任务成功");
    }

    @ApiOperation(value = "暂停定时任务")
    @PutMapping(value = "/pause", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> pause(@RequestBody JobModel job) {
        try {
            platformCheck(job);
            taskService.pause(job.getJobName(), job.getJobGroup());
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "暂停定时任务成功");
    }

    @ApiOperation(value = "重新开始定时任务")
    @PutMapping(value = "/resume", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> resume(@RequestBody JobModel job) {
        try {
            platformCheck(job);
            taskService.resume(job.getJobName(), job.getJobGroup());
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "重新开始定时任务成功");
    }

    @ApiOperation(value = "立即执行定时任务")
    @PostMapping(value = "/executeJob", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> save(@RequestBody JobModel job) {
        try {
            platformCheck(job);
            taskService.executeJob(job.getJobName(), job.getJobGroup());
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "立即执行定时任务");
    }

    @ApiOperation(value = "保存任务[服务后台调用接口]")
    @PostMapping(value = "/saveTask", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> saveTask(@RequestBody List<QuartzModel> list) {
        Map<String, String> data = new HashMap<String, String>();
        try {
            for (QuartzModel task : list) {
                if (taskService.checkExists(task.getJobName(), task.getJobGroup())) {
                    throw new Exception();
                }
                taskService.addJob(task);
                data.put(task.getJobGroup(), task.getJobName());
            }
        }catch (Exception e) {
            data.forEach((k, v) -> taskService.delete(v, k));
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "保存任务成功");
    }

    @ApiOperation(value = "修改任务[服务后台调用接口]")
    @PostMapping(value = "/editTask", produces = "application/json; charset=UTF-8")
    public ResultMessage<String> editTask(@RequestBody QuartzModel task) {
        try {
            Class.forName(task.getJobName());
            taskService.edit(task);
        } catch (ClassNotFoundException not) {
            return ResultMessage.message(false, "没有找到对应的Job类 : " + not.getMessage());
        } catch (Exception e) {
            return ResultMessage.message(false, e.getMessage());
        }
        return ResultMessage.message(true, "修改任务成功");
    }

    @ApiOperation(value = "最近七天统计")
    @GetMapping(value = "/findDayTotal")
    public ResultMessage<Map<String, Object>> findDayTotal() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("total", taskLogService.findDayTotal(1));
        data.put("day", taskLogService.findTotal(null));
        data.put("pt", taskLogService.findTotal(new TotalModel(true)));
        data.put("fw", taskLogService.findTotal(new TotalModel(false)));
        return ResultMessage.message(data, true, "统计数据!");
    }

    @ApiOperation(value = "根据类型获取统计结果")
    @GetMapping(value = "/findDayTotalByType/{type}")
    public ResultMessage<List<TotalModel>> findDayTotalByType(@PathVariable Integer type) {
        return ResultMessage.message(taskLogService.findDayTotal(type), true, "统计数据!");
    }

    @ApiOperation(value = "总任务数")
    @GetMapping(value = "/findTotal")
    public ResultMessage<TotalModel> findTotal(TotalModel total) {
        return ResultMessage.message(taskLogService.findTotal(total), true, "统计数据!");
    }

    private void platformCheck(BaseJobModel model) {
        if (model.platform) {
            model.setJobGroup((new StringBuilder().append(PLATFORM_TYPE).append(model.getJobGroup())).toString());
        }
    }

}
