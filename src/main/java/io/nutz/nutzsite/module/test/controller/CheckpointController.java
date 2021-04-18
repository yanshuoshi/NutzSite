package io.nutz.nutzsite.module.test.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.nutz.nutzsite.module.test.models.Checkpoint;
import io.nutz.nutzsite.module.test.services.CheckpointService;
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
 * 监测点位置 信息操作处理
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean
@At("/test/checkpoint")
public class CheckpointController {
	private static final Log log = Logs.get();

	@Inject
	private CheckpointService checkpointService;
	
	@RequiresPermissions("test:checkpoint:view")
	@At("")
	@Ok("th:/test/checkpoint/checkpoint.html")
	public void index(HttpServletRequest req) {

	}

	/**
	 * 查询监测点位置列表
	 */
	@RequiresPermissions("test:checkpoint:list")
	@At
	@Ok("json")
	public Object list(@Param("pageNum")int pageNum,
					   @Param("pageSize")int pageSize,
					   @Param("name") String name,
					   @Param("beginTime") Date beginTime,
					   @Param("endTime") Date endTime,
					   @Param("orderByColumn") String orderByColumn,
                       @Param("isAsc") String isAsc,
                       @Param("objId") String objId,
					   HttpServletRequest req) {
	    Logs.get().info("objId:" + objId);
//		Cnd cnd = Cnd.NEW();
//		if (!Strings.isBlank(name)){
//			//cnd.and("name", "like", "%" + name +"%");
//		}
//		if(Lang.isNotEmpty(beginTime)){
//			cnd.and("create_time",">=", beginTime);
//		}
//		if(Lang.isNotEmpty(endTime)){
//			cnd.and("create_time","<=", endTime);
//		}
//        return checkpointService.tableList(pageNum,pageSize,cnd,orderByColumn,isAsc,null);
        return checkpointService.tableList(pageNum,pageSize,objId);
	}

	/**
	 * 新增监测点位置
	 */
	@At("/add")
	@Ok("th:/test/checkpoint/add.html")
	public void add( HttpServletRequest req) {

	}

	/**
	 * 新增保存监测点位置
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:checkpoint:add")
	@Slog(tag="监测点位置", after="新增保存监测点位置 id=${args[0].id}")
	public Object addDo(@Param("..") Checkpoint checkpoint,HttpServletRequest req) {
		try {
			checkpointService.insert(checkpoint);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 修改监测点位置
	 */
	@At("/edit/?")
	@Ok("th:/test/checkpoint/edit.html")
	public void edit(String id, HttpServletRequest req) {
		Checkpoint checkpoint = checkpointService.fetch(id);
		req.setAttribute("checkpoint",checkpoint);
	}

    /**
     * 详情监测点位置
     */
    @At("/detail/?")
    @Ok("th:/test/checkpoint/detail.html")
    public void detail(String id, HttpServletRequest req) {
		Checkpoint checkpoint = checkpointService.fetch(id);
        req.setAttribute("checkpoint",checkpoint);
    }

    /**
	 * 修改保存监测点位置
	 */
	@At
	@POST
	@Ok("json")
	@RequiresPermissions("test:checkpoint:edit")
	@Slog(tag="监测点位置", after="修改保存监测点位置")
	public Object editDo(@Param("..") Checkpoint checkpoint,HttpServletRequest req) {
		try {
			if(Lang.isNotEmpty(checkpoint)){
				checkpoint.setUpdateBy(ShiroUtils.getSysUserId());
				checkpoint.setUpdateTime(new Date());
				checkpointService.update(checkpoint);
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	/**
	 * 删除监测点位置
	 */
	@At("/remove")
	@Ok("json")
	@RequiresPermissions("test:checkpoint:remove")
	@Slog(tag ="监测点位置", after= "删除监测点位置:${array2str(args[0])}")
	public Object remove(@Param("ids")String[] ids, HttpServletRequest req) {
		try {
			checkpointService.delete(ids);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

    @At
    @Ok("json")
    public Object home(@Param("id")String id,HttpServletRequest req) {
        return checkpointService.home(id);
    }

    @At
    @Ok("json")
    public Object number(@Param("id")String id,HttpServletRequest req) {
        return checkpointService.number(id);
    }


}
