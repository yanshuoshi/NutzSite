package io.nutz.nutzsite.module.test.services.impl;

import io.nutz.nutzsite.common.page.TableDataInfo;
import io.nutz.nutzsite.common.service.BaseServiceImpl;
import io.nutz.nutzsite.common.utils.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import io.nutz.nutzsite.module.test.models.Checkpoint;
import io.nutz.nutzsite.module.test.services.CheckpointService;
import org.nutz.log.Logs;

import java.util.List;
import java.util.Map;

/**
 * 监测点位置 服务层实现
 * 
 * @author haiming
 * @date 2021-03-27
 */
@IocBean(args = {"refer:dao"})
public class CheckpointServiceImpl extends BaseServiceImpl<Checkpoint> implements CheckpointService{
	public CheckpointServiceImpl(Dao dao) {
		super(dao);
	}

    @Override
    public Object home(String id) {
        String sqlstr = " SELECT " +
                "  c.id checkpointId,c.ckp_name ckpName,c.ckp_desc ckpDesc,j.`object_name` objectName,j.object_desc objectDesc,j.object_pic objectPic,ifnull(cu.pic_url,'') picUrl " +
                "FROM" +
                "  zw_checkpoint c" +
                "  LEFT JOIN zw_jcobject j " +
                "ON j.id = c.obj_id " +
                "left join zw_checkpoint_url  cu " +
                "on cu.checkpoint_id = c.id " +
                "where j.id = @id";
//                " group by c.id desc" ;
        Sql sql = Sqls.create(sqlstr);
        sql.params().set("id", id);
        sql.setCallback(Sqls.callback.maps());
        dao().execute(sql);
        List<Map> list = sql.getList(Map.class);
        return new TableDataInfo(list, 0);
    }

    @Override
    public Object number(String id) {
        String sqlstr = "SELECT " +
                "  (SELECT " +
                "    COUNT(1) " +
                "  FROM" +
                "    zw_alertinfo " +
                "  WHERE TO_DAYS(create_time) = TO_DAYS(NOW()) and obj_id = @id) AS todaycount," +
                "  (SELECT " +
                "    COUNT(1) " +
                "  FROM" +
                "    zw_alertinfo where 1 = 1  and obj_id = @id) AS allcount  " +
                "";
        Sql sql = Sqls.create(sqlstr);
        sql.params().set("id", id);
        sql.setCallback(Sqls.callback.maps());
        dao().execute(sql);
        List<Map> list = sql.getList(Map.class);
        return new TableDataInfo(list, 0);
    }

    @Override
    public Object tableList(int pageNum, int pageSize, String objId) {
        String sqlstr = "SELECT " +
                "  COUNT(1) cou " +
                "FROM" +
                "  zw_checkpoint c " +
                "  LEFT JOIN zw_jcobject o " +
                "    ON o.id = c.`obj_id`  ";
        Sql sql = null;
        if(StringUtils.isNotEmpty(objId)){
            sqlstr += "where o.id = @id";
            sql = Sqls.create(sqlstr);
            sql.params().set("id", objId);
        }else{
            sql = Sqls.create(sqlstr);
        }
        sql.setCallback(Sqls.callback.maps());
        dao().execute(sql);
        List<Map> list = sql.getList(Map.class);
        Logs.get().info("sum:" + list);

        String sql1 = "SELECT " +
                "  c.id,c.`ckp_name` ckpName,c.`obj_id` objId,c.`ckp_desc` ckpDesc " +
                "FROM" +
                "  zw_checkpoint c " +
                "  LEFT JOIN zw_jcobject o " +
                "    ON o.id = c.`obj_id` ";
        Sql sql2 = null;
        if(StringUtils.isNotEmpty(objId)){
            sql1 += "where o.id = @id";
            sql2 = Sqls.create(sql1);
            sql2.params().set("id", objId);
        }else{
            sql2 = Sqls.create(sql1);
        }
        Pager pager = dao().createPager(pageNum, pageSize); // 起始页，每页数目
        pager.setRecordCount(Integer.parseInt(list.get(0).get("cou").toString()));// 设置记录总数
        sql2.setPager(pager);
        sql2.setCallback(Sqls.callback.maps()); // 设置回调
        dao().execute(sql2);
        List<Map> list1 = sql2.getList(Map.class);
        Logs.get().info("sum:" + list1);
        return new TableDataInfo(list1,Integer.parseInt(list.get(0).get("cou").toString()));
    }
}
