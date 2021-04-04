package io.nutz.nutzsite.module.test.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.nutz.nutzsite.module.test.models.Alertinfo;
import io.nutz.nutzsite.module.test.services.AlertinfoService;
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
 * 告警记录 信息操作处理
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean
@At("/test/alertinfo")
public class AlertinfoController {
	private static final Log log = Logs.get();

	@Inject
	private AlertinfoService alertinfoService;
	
	@RequiresPermissions("test:alertinfo:view")
	@At("")
	@Ok("th:/test/alertinfo/alertinfo.html")
	public void index(HttpServletRequest req) {

	}

	/**
	 * 查询告警记录列表
	 */
	@RequiresPermissions("test:alertinfo:list")
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
		return alertinfoService.tableList(pageNum,pageSize,cnd,orderByColumn,isAsc,null);
	}

	/**
	 * 新增告警记录
	 */
	@At("/add")
	@Ok("th:/test/alertinfo/add.html")
	public void add( HttpServletRequest req) {

	}

	/**
	 * 新增保存告警记录
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:alertinfo:add")
	@Slog(tag="告警记录", after="新增保存告警记录 id=${args[0].id}")
	public Object addDo(@Param("..") Alertinfo alertinfo,HttpServletRequest req) {
		try {
			alertinfoService.insert(alertinfo);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 修改告警记录
	 */
	@At("/edit/?")
	@Ok("th:/test/alertinfo/edit.html")
	public void edit(String id, HttpServletRequest req) {
		Alertinfo alertinfo = alertinfoService.fetch(id);
		req.setAttribute("alertinfo",alertinfo);
	}

    /**
     * 详情告警记录
     */
    @At("/detail/?")
    @Ok("th:/test/alertinfo/detail.html")
    public void detail(String id, HttpServletRequest req) {
		Alertinfo alertinfo = alertinfoService.fetch(id);
        req.setAttribute("alertinfo",alertinfo);
    }

    /**
	 * 修改保存告警记录
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:alertinfo:edit")
	@Slog(tag="告警记录", after="修改保存告警记录")
	public Object editDo(@Param("..") Alertinfo alertinfo,HttpServletRequest req) {
		try {
			if(Lang.isNotEmpty(alertinfo)){
				alertinfo.setUpdateBy(ShiroUtils.getSysUserId());
				alertinfo.setUpdateTime(new Date());
				alertinfoService.update(alertinfo);
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 删除告警记录
	 */
	@At("/remove")
	@Ok("json")
	@RequiresPermissions("test:alertinfo:remove")
	@Slog(tag ="告警记录", after= "删除告警记录:${array2str(args[0])}")
	public Object remove(@Param("ids")String[] ids, HttpServletRequest req) {
		try {
			alertinfoService.delete(ids);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

}
