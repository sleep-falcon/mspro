package middleware.schedule.controller.service.impl;

import middleware.schedule.controller.service.DcsScheduleService;
import middleware.schedule.domain.DataCollect;
import middleware.schedule.domain.DcsScheduleInfo;
import middleware.schedule.domain.DcsServerNode;
import middleware.schedule.domain.Instruct;
import middleware.schedule.export.DcsScheduleResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("dcsScheduleService")
public class DcsScheduleServiceImpl implements DcsScheduleService {

    @Value("${middleware.scheudle.zkAddress}")
    private String zkAddress;

    private DcsScheduleResource dcsScheduleResource = null;

    @Override
    public List<String> queryPathRootServerList() throws Exception {
        return getDcsScheduleResource().queryPathRootServerList();
    }

    @Override
    public List<DcsScheduleInfo> queryDcsScheduleInfoList(String schedulerServerId) throws Exception {
        return getDcsScheduleResource().queryDcsScheduleInfoList(schedulerServerId);
    }

    @Override
    public void pushInstruct(Instruct instruct) throws Exception {
        getDcsScheduleResource().pushInstruct(instruct);
    }

    @Override
    public DataCollect queryDataCollect() throws Exception {
        return getDcsScheduleResource().queryDataCollect();
    }

    @Override
    public List<DcsServerNode> queryDcsServerNodeList() throws Exception {
        return getDcsScheduleResource().queryDcsServerNodeList();
    }

    private DcsScheduleResource getDcsScheduleResource() {
        if (null == dcsScheduleResource) {
            dcsScheduleResource = new DcsScheduleResource(zkAddress);
        }
        return dcsScheduleResource;
    }

}
