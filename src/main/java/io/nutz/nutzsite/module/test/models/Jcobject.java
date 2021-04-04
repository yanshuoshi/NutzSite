package io.nutz.nutzsite.module.test.models;

import io.nutz.nutzsite.common.base.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.nutz.dao.entity.annotation.*;
import java.io.Serializable;
			import java.util.Date;
		
/**
 * 监测对象表 zw_jcobject
 * 
 * @author haiming
 * @date 2021-03-27
 */
@Table("zw_jcobject")
public class Jcobject extends BaseModel implements Serializable {
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

		/** 监测对象名称 */
	@Column("object_name")
	@Comment("监测对象名称")
	private String objectName;

		/** 监测对象简介 */
	@Column("object_desc")
	@Comment("监测对象简介")
	private String objectDesc;

		/** 设备照片 */
	@Column("object_pic")
	@Comment("设备照片")
	private String objectPic;

		/** 监测对象编码 */
	@Column("object_code")
	@Comment("监测对象编码")
	private String objectCode;

	
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

	public void setObjectName(String objectName) 
	{
		this.objectName = objectName;
	}

	public String getObjectName() 
	{
		return objectName;
	}

	public void setObjectDesc(String objectDesc) 
	{
		this.objectDesc = objectDesc;
	}

	public String getObjectDesc() 
	{
		return objectDesc;
	}

	public void setObjectPic(String objectPic) 
	{
		this.objectPic = objectPic;
	}

	public String getObjectPic() 
	{
		return objectPic;
	}

	public void setObjectCode(String objectCode) 
	{
		this.objectCode = objectCode;
	}

	public String getObjectCode() 
	{
		return objectCode;
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
            .append("objectName", getObjectName())
            .append("objectDesc", getObjectDesc())
            .append("objectPic", getObjectPic())
            .append("objectCode", getObjectCode())
            .toString();
    }
}
