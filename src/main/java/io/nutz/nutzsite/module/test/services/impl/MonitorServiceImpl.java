package io.nutz.nutzsite.module.test.services.impl;

import io.nutz.nutzsite.common.page.TableDataInfo;
import io.nutz.nutzsite.common.service.BaseServiceImpl;
import io.nutz.nutzsite.common.utils.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import io.nutz.nutzsite.module.test.models.Monitor;
import io.nutz.nutzsite.module.test.services.MonitorService;
import org.nutz.log.Logs;

import java.util.List;
import java.util.Map;

/**
 * 数据接口 服务层实现
 *
 * @author haiming
 * @date 2021-03-27
 */
@IocBean(args = {"refer:dao"})
public class MonitorServiceImpl extends BaseServiceImpl<Monitor> implements MonitorService {
    public MonitorServiceImpl(Dao dao) {
        super(dao);
    }

    @Override
    public Object tableList(int pageNum, int pageSize, String objId, String ckpId) {
        String sqlstr = "SELECT  " +
                "  COUNT(1) cou " +
                "FROM " +
                "  zw_monitor m  " +
                "  LEFT JOIN zw_checkpoint c  " +
                "    ON m.deploy_ckp = c.id   ";
        Sql sql = null;
        if (StringUtils.isNotEmpty(objId)) {
            sqlstr += "where c.obj_id = @objId ";
            if (StringUtils.isNotEmpty(ckpId)) {
                sqlstr += "and c.id = @ckpId ";
            }
            sql = Sqls.create(sqlstr);
            sql.params().set("objId", objId);
            if (StringUtils.isNotEmpty(ckpId)) {
                sql.params().set("ckpId", ckpId);
            }
        } else if (StringUtils.isNotEmpty(ckpId)) {
            sqlstr += "where c.id = @ckpId";
            sql = Sqls.create(sqlstr);
            sql.params().set("ckpId", ckpId);
        } else {
            sql = Sqls.create(sqlstr);
        }
        sql.setCallback(Sqls.callback.maps());
        dao().execute(sql);
        List<Map> list = sql.getList(Map.class);
        Logs.get().info("sum:" + list);

        String sql1 = "SELECT " +
                "  m.monitor_name monitorName,m.`monitor_ip` monitorIp,m.`monitor_port` monitorPort ,m.`monitor_type` monitorType,m.`monitor_channel` monitorChannel,m.`deploy_ckp` deployCkp " +
                "FROM " +
                "  zw_monitor m  " +
                "  LEFT JOIN zw_checkpoint c  " +
                "    ON m.deploy_ckp = c.id  ";
        Sql sql2 = null;
        if (StringUtils.isNotEmpty(objId)) {
            sql1 += "where c.obj_id = @objId ";
            if (StringUtils.isNotEmpty(ckpId)) {
                sql1 += "and c.id = @ckpId ";
            }
            sql2 = Sqls.create(sql1);
            sql2.params().set("objId", objId);
            if (StringUtils.isNotEmpty(ckpId)) {
                sql2.params().set("ckpId", ckpId);
            }
        } else if (StringUtils.isNotEmpty(ckpId)) {
            sql1 += "where c.id = @ckpId";
            sql2 = Sqls.create(sql1);
            sql2.params().set("ckpId", ckpId);
        } else {
            sql2 = Sqls.create(sql1);
        }
        Pager pager = dao().createPager(pageNum, pageSize); // 起始页，每页数目
        pager.setRecordCount(Integer.parseInt(list.get(0).get("cou").toString()));// 设置记录总数
        sql2.setPager(pager);
        sql2.setCallback(Sqls.callback.maps()); // 设置回调
        dao().execute(sql2);
        List<Map> list1 = sql2.getList(Map.class);
        Logs.get().info("sum:" + list1);
        return new TableDataInfo(list1, Integer.parseInt(list.get(0).get("cou").toString()));
    }
}
