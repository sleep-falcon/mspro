package middleware.schedule.controller.web;

import middleware.schedule.controller.domain.EasyResult;
import middleware.schedule.controller.service.DcsScheduleService;
import com.alibaba.fastjson.JSON;
import middleware.schedule.domain.DcsScheduleInfo;
import middleware.schedule.domain.DcsServerNode;
import middleware.schedule.domain.Instruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class ImcController {

    private Logger logger = LoggerFactory.getLogger(ImcController.class);

    @Autowired
    private DcsScheduleService dcsScheduleService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("api/queryDcsServerNodeList")
    @ResponseBody
    public EasyResult queryDcsServerNodeList() {
        try {
            List<DcsServerNode> dcsServerNodeList = dcsScheduleService.queryDcsServerNodeList();
            return EasyResult.buildEasyResultSuccess(dcsServerNodeList.size(), dcsServerNodeList);
        } catch (Exception e) {
            return EasyResult.buildEasyResultError(e);
        }
    }

    @RequestMapping("api/queryDcsScheduleInfoList")
    @ResponseBody
    public EasyResult queryDcsScheduleInfoList(@RequestParam String schedulerServerId) {
        try {
            logger.info("查询任务列表信息{}开始 ", schedulerServerId);
            List<DcsScheduleInfo> dcsScheduleInfoList = dcsScheduleService.queryDcsScheduleInfoList(schedulerServerId);
            logger.info("查询任务列表信息{}完成 count：{}", schedulerServerId, dcsScheduleInfoList.size());
            return EasyResult.buildEasyResultSuccess(dcsScheduleInfoList.size(), dcsScheduleInfoList);
        } catch (Exception e) {
            logger.error("查询任务列表信息{}失败 ", schedulerServerId, e);
            return EasyResult.buildEasyResultError(e);
        }
    }

    @RequestMapping("api/queryPathRootServerList")
    @ResponseBody
    public EasyResult queryPathRootServerList() {
        try {
            List<String> serverList = dcsScheduleService.queryPathRootServerList();
            return EasyResult.buildEasyResultSuccess(serverList.size(), serverList);
        } catch (Exception e) {
            return EasyResult.buildEasyResultError(e);
        }
    }

    @RequestMapping("api/queryDataCollect")
    @ResponseBody
    public EasyResult queryDataCollect() {
        try {
            return EasyResult.buildEasyResultSuccess(dcsScheduleService.queryDataCollect());
        } catch (Exception e) {
            return EasyResult.buildEasyResultError(e);
        }
    }

    @RequestMapping("api/pushInstruct")
    @ResponseBody
    public EasyResult pushInstruct(@RequestParam String json) {
        try {
            Instruct instruct = JSON.parseObject(json, Instruct.class);
            if (null == instruct) return EasyResult.buildEasyResultError("json is null");
            dcsScheduleService.pushInstruct(instruct);
            return EasyResult.buildEasyResultSuccess();
        } catch (Exception e) {
            return EasyResult.buildEasyResultError(e);
        }
    }

}
