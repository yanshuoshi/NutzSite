package io.nutz.nutzsite.module.test.services.impl;

import io.nutz.nutzsite.common.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import io.nutz.nutzsite.module.test.models.Monitor;
import io.nutz.nutzsite.module.test.services.MonitorService;
/**
 * 数据接口 服务层实现
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean(args = {"refer:dao"})
public class MonitorServiceImpl extends BaseServiceImpl<Monitor> implements MonitorService{
	public MonitorServiceImpl(Dao dao) {
		super(dao);
	}
}
