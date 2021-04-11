package io.nutz.nutzsite.module.test.services.impl;

import io.nutz.nutzsite.common.page.TableDataInfo;
import io.nutz.nutzsite.common.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import io.nutz.nutzsite.module.test.models.Checkpoint;
import io.nutz.nutzsite.module.test.services.CheckpointService;

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
}
