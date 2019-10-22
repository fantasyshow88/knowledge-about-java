package com.ztl.simple.impl.save;

import java.util.List;

import com.ztl.simple.iface.save.ISave;
import com.ztl.simple.pojos.WangYiDaiItemPojo;
import com.ztl.simple.utils.StaticValue;

public class SaveImplToMysql4WangYiDai implements ISave<List<WangYiDaiItemPojo>> {
	private String save_file_path;

	public String getSave_file_path() {
		return save_file_path;
	}

	public void setSave_file_path(String saveFilePath) {
		save_file_path = saveFilePath;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	private String encoding = StaticValue.encoding_default;

	public SaveImplToMysql4WangYiDai(String save_file_path, String encoding) {
		this.save_file_path = save_file_path;
		this.encoding = encoding;
	}

	public SaveImplToMysql4WangYiDai(String save_file_path) {
		this.save_file_path = save_file_path;
	}

	@Override
	public boolean save(List<WangYiDaiItemPojo> t) {
		try {
			// IOUtil.writeFile(this.getSave_file_path(), t,
			// this.getEncoding());
			// jdbc,ibatis,hibernate之类的将类型预计解析并构建sql语句
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
