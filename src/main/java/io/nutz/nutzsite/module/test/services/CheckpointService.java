package io.nutz.nutzsite.module.test.services;

import io.nutz.nutzsite.common.service.BaseService;
import io.nutz.nutzsite.module.test.models.Checkpoint;

/**
 * 监测点位置 服务层实现
 * 
 * @author haiming
 * @date 2021-03-27
 */
public interface CheckpointService extends BaseService<Checkpoint> {

    Object home(String id);
}
