package io.nutz.nutzsite.module.test.models;

import io.nutz.nutzsite.common.base.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.nutz.dao.entity.annotation.*;
import java.io.Serializable;
			import java.util.Date;
		
/**
 * 数据接口表 zw_monitor
 * 
 * @author haiming
 * @date 2021-03-27
 */
@Table("zw_monitor")
public class Monitor extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
		@Name
	@Column("id")
	@Comment("主键")
	@ColDefine(type = ColType.VARCHAR, width = 64)
	@Prev(els = {@EL("uuid()")})
	private String id;

		/** 创建人 */
	@Column("create_by")
	@Comment("创建人")
	private String createBy;

		/** 创建日期 */
	@Column("create_time")
	@Comment("创建日期")
	private Date createTime;

		/** 更新人 */
	@Column("update_by")
	@Comment("更新人")
	private String updateBy;

		/** 更新日期 */
	@Column("update_time")
	@Comment("更新日期")
	private Date updateTime;

		/** 所属部门 */
	@Column("sys_org_code")
	@Comment("所属部门")
	private String sysOrgCode;

		/** 设备名称 */
	@Column("monitor_name")
	@Comment("设备名称")
	private String monitorName;

		/** 设备ip地址 */
	@Column("monitor_ip")
	@Comment("设备ip地址")
	private String monitorIp;

		/** 服务端口地址 */
	@Column("monitor_port")
	@Comment("服务端口地址")
	private Integer monitorPort;

		/** 设备类型 */
	@Column("monitor_type")
	@Comment("设备类型")
	private String monitorType;

		/** nvr频道id */
	@Column("monitor_channel")
	@Comment("nvr频道id")
	private Integer monitorChannel;

		/** 部署的监测点 */
	@Column("deploy_ckp")
	@Comment("部署的监测点")
	private String deployCkp;

	
	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}

	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}

	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}

	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

	public void setSysOrgCode(String sysOrgCode) 
	{
		this.sysOrgCode = sysOrgCode;
	}

	public String getSysOrgCode() 
	{
		return sysOrgCode;
	}

	public void setMonitorName(String monitorName) 
	{
		this.monitorName = monitorName;
	}

	public String getMonitorName() 
	{
		return monitorName;
	}

	public void setMonitorIp(String monitorIp) 
	{
		this.monitorIp = monitorIp;
	}

	public String getMonitorIp() 
	{
		return monitorIp;
	}

	public void setMonitorPort(Integer monitorPort) 
	{
		this.monitorPort = monitorPort;
	}

	public Integer getMonitorPort() 
	{
		return monitorPort;
	}

	public void setMonitorType(String monitorType) 
	{
		this.monitorType = monitorType;
	}

	public String getMonitorType() 
	{
		return monitorType;
	}

	public void setMonitorChannel(Integer monitorChannel) 
	{
		this.monitorChannel = monitorChannel;
	}

	public Integer getMonitorChannel() 
	{
		return monitorChannel;
	}

	public void setDeployCkp(String deployCkp) 
	{
		this.deployCkp = deployCkp;
	}

	public String getDeployCkp() 
	{
		return deployCkp;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("sysOrgCode", getSysOrgCode())
            .append("monitorName", getMonitorName())
            .append("monitorIp", getMonitorIp())
            .append("monitorPort", getMonitorPort())
            .append("monitorType", getMonitorType())
            .append("monitorChannel", getMonitorChannel())
            .append("deployCkp", getDeployCkp())
            .toString();
    }
}
