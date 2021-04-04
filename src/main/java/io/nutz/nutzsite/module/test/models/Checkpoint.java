package io.nutz.nutzsite.module.test.models;

import io.nutz.nutzsite.common.base.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.nutz.dao.entity.annotation.*;
import java.io.Serializable;
			import java.util.Date;
		
/**
 * 监测点位置表 zw_checkpoint
 * 
 * @author haiming
 * @date 2021-03-27
 */
@Table("zw_checkpoint")
public class Checkpoint extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
		@Name
	@Column("id")
	@Comment("id")
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

		/** 监测点名称 */
	@Column("ckp_name")
	@Comment("监测点名称")
	private String ckpName;

		/** 监测点描述 */
	@Column("ckp_desc")
	@Comment("监测点描述")
	private String ckpDesc;

		/** 关联的监测对象ID */
	@Column("obj_id")
	@Comment("关联的监测对象ID")
	private String objId;

	
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

	public void setCkpName(String ckpName) 
	{
		this.ckpName = ckpName;
	}

	public String getCkpName() 
	{
		return ckpName;
	}

	public void setCkpDesc(String ckpDesc) 
	{
		this.ckpDesc = ckpDesc;
	}

	public String getCkpDesc() 
	{
		return ckpDesc;
	}

	public void setObjId(String objId) 
	{
		this.objId = objId;
	}

	public String getObjId() 
	{
		return objId;
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
            .append("ckpName", getCkpName())
            .append("ckpDesc", getCkpDesc())
            .append("objId", getObjId())
            .toString();
    }
}
