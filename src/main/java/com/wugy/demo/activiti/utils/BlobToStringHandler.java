package com.wugy.demo.activiti.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BlobToStringHandler extends BaseTypeHandler<String> {

	private static final String CAHRSET = "UTF-8";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {

	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Blob blob = rs.getBlob(columnName);
		return blobToString(blob);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Blob blob = rs.getBlob(columnIndex);
		return blobToString(blob);
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Blob blob = cs.getBlob(columnIndex);
		return blobToString(blob);
	}

	private String blobToString(Blob blob) throws SQLException {
		byte[] retValue = null;
		if (null != blob) {
			retValue = blob.getBytes(1, (int) blob.length());
		}
		try {
			return new String(retValue, CAHRSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("blob类型转换成string出错");
		}
	}

}
