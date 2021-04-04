package io.nutz.nutzsite.module.test.models;

import io.nutz.nutzsite.common.base.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.nutz.dao.entity.annotation.*;
import java.io.Serializable;
			import java.util.Date;
		
/**
 * 告警记录表 zw_alertinfo
 * 
 * @author haiming
 * @date 2021-03-27
 */
@Table("zw_alertinfo")
public class Alertinfo extends BaseModel implements Serializable {
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

		/** 监测对象 */
	@Column("obj_id")
	@Comment("监测对象")
	private String objId;

		/** 监测点 */
	@Column("ckp_id")
	@Comment("监测点")
	private String ckpId;

		/** 监测设备 */
	@Column("monitor_id")
	@Comment("监测设备")
	private String monitorId;

		/** 告警消息 */
	@Column("alert_msg")
	@Comment("告警消息")
	private String alertMsg;

		/** 告警数值 */
	@Column("alert_data")
	@Comment("告警数值")
	private Integer alertData;



		/** 告警时点 */
	@Column("alert_time")
	@Comment("告警时点")
	private Date alertTime;

		/** 缺陷等级 */
	@Column("qx_level")
	@Comment("缺陷等级")
	private String qxLevel;

		/** 放电类型 */
	@Column("qx_type")
	@Comment("放电类型")
	private String qxType;

		/** 告警处理状态 */
	@Column("alert_status")
	@Comment("告警处理状态")
	private String alertStatus;

		/** 告警图片信息 */
	@Column("alert_pic")
	@Comment("告警图片信息")
	private String alertPic;

		/** 告警视频 */
	@Column("alert_video")
	@Comment("告警视频")
	private String alertVideo;

		/** 备注说明 */
	@Column("comment")
	@Comment("备注说明")
	private String comment;

	
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

	public void setObjId(String objId) 
	{
		this.objId = objId;
	}

	public String getObjId() 
	{
		return objId;
	}

	public void setCkpId(String ckpId) 
	{
		this.ckpId = ckpId;
	}

	public String getCkpId() 
	{
		return ckpId;
	}

	public void setMonitorId(String monitorId) 
	{
		this.monitorId = monitorId;
	}

	public String getMonitorId() 
	{
		return monitorId;
	}

	public void setAlertMsg(String alertMsg) 
	{
		this.alertMsg = alertMsg;
	}

	public String getAlertMsg() 
	{
		return alertMsg;
	}

	
	public void setAlertTime(Date alertTime) 
	{
		this.alertTime = alertTime;
	}

	public Date getAlertTime() 
	{
		return alertTime;
	}

	public void setQxLevel(String qxLevel) 
	{
		this.qxLevel = qxLevel;
	}

	public String getQxLevel() 
	{
		return qxLevel;
	}

	public void setQxType(String qxType) 
	{
		this.qxType = qxType;
	}

	public String getQxType() 
	{
		return qxType;
	}

	public void setAlertStatus(String alertStatus) 
	{
		this.alertStatus = alertStatus;
	}

	public String getAlertStatus() 
	{
		return alertStatus;
	}

	public void setAlertPic(String alertPic) 
	{
		this.alertPic = alertPic;
	}

	public String getAlertPic() 
	{
		return alertPic;
	}

	public void setAlertVideo(String alertVideo) 
	{
		this.alertVideo = alertVideo;
	}

	public String getAlertVideo() 
	{
		return alertVideo;
	}

	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment() 
	{
		return comment;
	}
	public Integer getAlertData() {
		return alertData;
	}

	public void setAlertData(Integer alertData) {
		this.alertData = alertData;
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
            .append("objId", getObjId())
            .append("ckpId", getCkpId())
            .append("monitorId", getMonitorId())
            .append("alertMsg", getAlertMsg())
            .append("alertData", getAlertData())
            .append("alertTime", getAlertTime())
            .append("qxLevel", getQxLevel())
            .append("qxType", getQxType())
            .append("alertStatus", getAlertStatus())
            .append("alertPic", getAlertPic())
            .append("alertVideo", getAlertVideo())
            .append("comment", getComment())
            .toString();
    }
}
