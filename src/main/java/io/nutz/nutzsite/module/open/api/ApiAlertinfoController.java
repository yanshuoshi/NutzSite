package io.nutz.nutzsite.module.open.api;

import io.nutz.nutzsite.module.test.models.Alertinfo;
import io.nutz.nutzsite.module.test.services.AlertinfoService;
import io.nutz.nutzsite.common.base.Result;
import io.nutz.nutzsite.common.exception.ErrorException;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.lang.Lang;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.plugins.validation.Errors;

import io.nutz.nutzsite.common.utils.JWTUtil;
import io.nutz.nutzsite.common.annotation.AccessToken;
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
@At("/api/api/alertinfo")
public class ApiAlertinfoController {
    private static final Log log = Logs.get();

    @Inject
    private AlertinfoService alertinfoService;

    /**
     * 查询告警记录列表
     */
    @At
    @Ok("json")
    @AccessToken
    public Object list(@Param("pageNum")int pageNum,
                       @Param("pageSize")int pageSize,
                       @Param("name") String name,
                       @Param("beginTime") Date beginTime,
                       @Param("endTime") Date endTime,
                       @Param("orderByColumn") String orderByColumn,
                       @Param("isAsc") String isAsc,
                       HttpServletRequest req) {
        String uid = JWTUtil.getId();
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
        List<Alertinfo> list = alertinfoService.query(cnd,pageNum,pageSize);
        return Result.success("system.success", list);
    }

    /**
     * 查询一条记录
     */
    @At
    @Ok("json")
    @AccessToken
    public Object get(@Param("id") String id) {
        try {
            return Result.success("system.success",alertinfoService.fetch(id));
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
     * 新增保存告警记录
     */
    @At
    @POST
    @Ok("json")
    @AccessToken
    public Object addDo(@Param("..") Alertinfo alertinfo, Errors es, HttpServletRequest req) {
        try {
            if (es.hasError()) {
                return Result.error(es);
            }
				alertinfoService.insert(alertinfo);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error(e instanceof ErrorException ? e.getMessage() : "system.error");
        }
    }


    /**
     * 修改保存告警记录
     */
    @At
    @POST
    @Ok("json")
    @AccessToken
    public Object editDo(@Param("..") Alertinfo alertinfo, Errors es, HttpServletRequest req) {
        try {
            if (es.hasError()) {
                return Result.error(es);
            }
            if(Lang.isNotEmpty(alertinfo)){
				alertinfo.setUpdateBy(JWTUtil.getId());
				alertinfo.setUpdateTime(new Date());
					alertinfoService.update(alertinfo);
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error(e instanceof ErrorException ? e.getMessage() : "system.error");
        }
    }

    /**
     * 删除告警记录
     */
    @At("/remove")
    @Ok("json")
    @AccessToken
    public Object remove(@Param("ids")String[] ids, HttpServletRequest req) {
        try {
				alertinfoService.delete(ids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

}
