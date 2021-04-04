package io.nutz.nutzsite.module.test.services.impl;

import io.nutz.nutzsite.common.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import io.nutz.nutzsite.module.test.models.Jcobject;
import io.nutz.nutzsite.module.test.services.JcobjectService;


/**
 * 监测对象 服务层实现
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean(args = {"refer:dao"})
public class JcobjectServiceImpl extends BaseServiceImpl<Jcobject> implements JcobjectService{
	public JcobjectServiceImpl(Dao dao) {
		super(dao);
	}
}
