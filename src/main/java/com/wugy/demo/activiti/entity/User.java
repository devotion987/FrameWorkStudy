package com.wugy.demo.activiti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	// 对应数据库字段为blob类型
	private Object remark;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark")
	public Object getRemark() {
		return remark;
	}

	public void setRemark(Object remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", remark=" + remark + ", id=" + id + "]";
	}

}
