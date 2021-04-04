package io.nutz.nutzsite.module.open.api;

import io.nutz.nutzsite.module.test.models.Jcobject;
import io.nutz.nutzsite.module.test.services.JcobjectService;
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
 * 监测对象 信息操作处理
 *
 * @author haiming
 * @date 2021-03-27
 */
@IocBean
@At("/api/api/jcobject")
public class ApiJcobjectController {
    private static final Log log = Logs.get();

    @Inject
    private JcobjectService jcobjectService;

    /**
     * 查询监测对象列表
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
        List<Jcobject> list = jcobjectService.query(cnd,pageNum,pageSize);
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
            return Result.success("system.success",jcobjectService.fetch(id));
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
     * 新增保存监测对象
     */
    @At
    @POST
    @Ok("json")
    @AccessToken
    public Object addDo(@Param("..") Jcobject jcobject, Errors es, HttpServletRequest req) {
        try {
            if (es.hasError()) {
                return Result.error(es);
            }
				jcobjectService.insert(jcobject);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error(e instanceof ErrorException ? e.getMessage() : "system.error");
        }
    }


    /**
     * 修改保存监测对象
     */
    @At
    @POST
    @Ok("json")
    @AccessToken
    public Object editDo(@Param("..") Jcobject jcobject, Errors es, HttpServletRequest req) {
        try {
            if (es.hasError()) {
                return Result.error(es);
            }
            if(Lang.isNotEmpty(jcobject)){
				jcobject.setUpdateBy(JWTUtil.getId());
				jcobject.setUpdateTime(new Date());
					jcobjectService.update(jcobject);
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error(e instanceof ErrorException ? e.getMessage() : "system.error");
        }
    }

    /**
     * 删除监测对象
     */
    @At("/remove")
    @Ok("json")
    @AccessToken
    public Object remove(@Param("ids")String[] ids, HttpServletRequest req) {
        try {
				jcobjectService.delete(ids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

}
