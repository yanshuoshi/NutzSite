package io.nutz.nutzsite.module.test.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.nutz.nutzsite.module.test.models.Jcobject;
import io.nutz.nutzsite.module.test.services.JcobjectService;
import io.nutz.nutzsite.common.base.Result;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
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
 * 监测对象 信息操作处理
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean
@At("/test/jcobject")
public class JcobjectController {
	private static final Log log = Logs.get();

	@Inject
	private JcobjectService jcobjectService;
	
	@RequiresPermissions("test:jcobject:view")
	@At("")
	@Ok("th:/test/jcobject/jcobject.html")
	public void index(HttpServletRequest req) {

	}

	/**
	 * 查询监测对象列表
	 */
	@RequiresPermissions("test:jcobject:list")
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
		return jcobjectService.tableList(pageNum,pageSize,cnd,orderByColumn,isAsc,null);
	}

	/**
	 * 新增监测对象
	 */
	@At("/add")
	@Ok("th:/test/jcobject/add.html")
	public void add( HttpServletRequest req) {

	}

	/**
	 * 新增保存监测对象
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:jcobject:add")
	@Slog(tag="监测对象", after="新增保存监测对象 id=${args[0].id}")
	public Object addDo(@Param("..") Jcobject jcobject,HttpServletRequest req) {
		try {
			jcobjectService.insert(jcobject);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 修改监测对象
	 */
	@At("/edit/?")
	@Ok("th:/test/jcobject/edit.html")
	public void edit(String id, HttpServletRequest req) {
		Jcobject jcobject = jcobjectService.fetch(id);
		req.setAttribute("jcobject",jcobject);
	}

    /**
     * 详情监测对象
     */
    @At("/detail/?")
    @Ok("th:/test/jcobject/detail.html")
    public void detail(String id, HttpServletRequest req) {
		Jcobject jcobject = jcobjectService.fetch(id);
        req.setAttribute("jcobject",jcobject);
    }

    /**
	 * 修改保存监测对象
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:jcobject:edit")
	@Slog(tag="监测对象", after="修改保存监测对象")
	public Object editDo(@Param("..") Jcobject jcobject,HttpServletRequest req) {
		try {
			if(Lang.isNotEmpty(jcobject)){
				jcobject.setUpdateBy(ShiroUtils.getSysUserId());
				jcobject.setUpdateTime(new Date());
				jcobjectService.update(jcobject);
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 删除监测对象
	 */
	@At("/remove")
	@Ok("json")
	@RequiresPermissions("test:jcobject:remove")
	@Slog(tag ="监测对象", after= "删除监测对象:${array2str(args[0])}")
	public Object remove(@Param("ids")String[] ids, HttpServletRequest req) {
		try {
			jcobjectService.delete(ids);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

}
