package io.nutz.nutzsite.module.test.services.impl;

import io.nutz.nutzsite.common.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import io.nutz.nutzsite.module.test.models.Alertinfo;
import io.nutz.nutzsite.module.test.services.AlertinfoService;
/**
 * 告警记录 服务层实现
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean(args = {"refer:dao"})
public class AlertinfoServiceImpl extends BaseServiceImpl<Alertinfo> implements AlertinfoService{
	public AlertinfoServiceImpl(Dao dao) {
		super(dao);
	}
}
