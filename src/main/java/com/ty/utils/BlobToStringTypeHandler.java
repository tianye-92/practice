package com.ty.utils;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.quartz.JobDataMap;

import java.io.*;
import java.sql.*;

public class BlobToStringTypeHandler extends BaseTypeHandler<String> {

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Object obj = null;
		InputStream binaryInput = rs.getBinaryStream(columnName);
		if (binaryInput != null) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(binaryInput);
				obj = in.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (obj instanceof JobDataMap) {
			return ((JobDataMap) obj).get("dataMap").toString();
		}
		return "";
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Blob blob = rs.getBlob(columnIndex);
		return new String(blob.getBytes(1, (int) blob.length()));
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Blob blob = cs.getBlob(columnIndex);
		return new String(blob.getBytes(1, (int) blob.length()));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ByteArrayInputStream bis;
		try {
			bis = new ByteArrayInputStream(parameter.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Blob Encoding Error!");
		}
		ps.setBinaryStream(i, bis, parameter.length());
	}

}
