package io.nutz.nutzsite.module.test.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.nutz.nutzsite.module.test.models.Monitor;
import io.nutz.nutzsite.module.test.services.MonitorService;
import io.nutz.nutzsite.common.base.Result;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.plugins.slog.annotation.Slog;
import io.nutz.nutzsite.common.utils.ShiroUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Date;
/**
 * 数据接口 信息操作处理
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean
@At("/test/monitor")
public class MonitorController {
	private static final Log log = Logs.get();

	@Inject
	private MonitorService monitorService;
	
	@RequiresPermissions("test:monitor:view")
	@At("")
	@Ok("th:/test/monitor/monitor.html")
	public void index(HttpServletRequest req) {

	}

	/**
	 * 查询数据接口列表
	 */
	@RequiresPermissions("test:monitor:list")
	@At
	@Ok("json")
	public Object list(@Param("pageNum")int pageNum,
					   @Param("pageSize")int pageSize,
					   @Param("name") String name,
					   @Param("beginTime") Date beginTime,
					   @Param("endTime") Date endTime,
					   @Param("orderByColumn") String orderByColumn,
					   @Param("isAsc") String isAsc,
					   HttpServletRequest req) {
		Cnd cnd = Cnd.NEW();
		if (!Strings.isBlank(name)){
			//cnd.and("name", "like", "%" + name +"%");
		}
		if(Lang.isNotEmpty(beginTime)){
			cnd.and("create_time",">=", beginTime);
		}
		if(Lang.isNotEmpty(endTime)){
			cnd.and("create_time","<=", endTime);
		}
		return monitorService.tableList(pageNum,pageSize,cnd,orderByColumn,isAsc,null);
	}

	/**
	 * 新增数据接口
	 */
	@At("/add")
	@Ok("th:/test/monitor/add.html")
	public void add( HttpServletRequest req) {

	}

	/**
	 * 新增保存数据接口
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:monitor:add")
	@Slog(tag="数据接口", after="新增保存数据接口 id=${args[0].id}")
	public Object addDo(@Param("..") Monitor monitor,HttpServletRequest req) {
		try {
			monitorService.insert(monitor);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 修改数据接口
	 */
	@At("/edit/?")
	@Ok("th:/test/monitor/edit.html")
	public void edit(String id, HttpServletRequest req) {
		Monitor monitor = monitorService.fetch(id);
		req.setAttribute("monitor",monitor);
	}

    /**
     * 详情数据接口
     */
    @At("/detail/?")
    @Ok("th:/test/monitor/detail.html")
    public void detail(String id, HttpServletRequest req) {
		Monitor monitor = monitorService.fetch(id);
        req.setAttribute("monitor",monitor);
    }

    /**
	 * 修改保存数据接口
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:monitor:edit")
	@Slog(tag="数据接口", after="修改保存数据接口")
	public Object editDo(@Param("..") Monitor monitor,HttpServletRequest req) {
		try {
			if(Lang.isNotEmpty(monitor)){
				monitor.setUpdateBy(ShiroUtils.getSysUserId());
				monitor.setUpdateTime(new Date());
				monitorService.update(monitor);
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 删除数据接口
	 */
	@At("/remove")
	@Ok("json")
	@RequiresPermissions("test:monitor:remove")
	@Slog(tag ="数据接口", after= "删除数据接口:${array2str(args[0])}")
	public Object remove(@Param("ids")String[] ids, HttpServletRequest req) {
		try {
			monitorService.delete(ids);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

}
