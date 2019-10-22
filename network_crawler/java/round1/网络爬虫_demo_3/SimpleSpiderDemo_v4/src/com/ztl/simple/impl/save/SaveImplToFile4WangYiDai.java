package com.ztl.simple.impl.save;

import com.ztl.simple.iface.save.ISave;
import com.ztl.simple.utils.IOUtil;
import com.ztl.simple.utils.StaticValue;

public class SaveImplToFile4WangYiDai implements ISave<String> {
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

	public SaveImplToFile4WangYiDai(String save_file_path, String encoding) {
		this.save_file_path = save_file_path;
		this.encoding = encoding;
	}

	public SaveImplToFile4WangYiDai(String save_file_path) {
		this.save_file_path = save_file_path;
	}

	@Override
	public boolean save(String t) {
		try {
			IOUtil.writeFile(this.getSave_file_path(), t, this.getEncoding());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
