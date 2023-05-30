package middleware.schedule.controller.service;

import middleware.schedule.domain.DataCollect;
import middleware.schedule.domain.DcsScheduleInfo;
import middleware.schedule.domain.DcsServerNode;
import middleware.schedule.domain.Instruct;

import java.util.List;
public interface DcsScheduleService {

    List<String> queryPathRootServerList() throws Exception;

    List<DcsScheduleInfo> queryDcsScheduleInfoList(String schedulerServerId) throws Exception;

    void pushInstruct(Instruct instruct) throws Exception;

    DataCollect queryDataCollect() throws Exception;

    List<DcsServerNode> queryDcsServerNodeList() throws Exception;

}
