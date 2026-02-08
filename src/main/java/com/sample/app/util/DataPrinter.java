package com.sample.app.util;

import javax.inject.Inject;
import javax.inject.Named;

import com.sample.app.interfaces.DataSource;

public class DataPrinter {

	private DataSource sqlSource;

	private DataSource fileSource;

	@Inject
	public DataPrinter(@Named("sql") DataSource sqlSource, @Named("file") DataSource fileSource) {
		this.sqlSource = sqlSource;
		this.fileSource = fileSource;
	}

	public void print() {
		String dataFromSQL = getDataFromSQL();
		String dataFromFile = getDataFromFile();

		System.out.println(dataFromSQL);
		System.out.println(dataFromFile);
	}

	public String getDataFromFile() {
		if (fileSource == null) {
			throw new RuntimeException("fileSource is null");
		}
		return fileSource.read();
	}

	public String getDataFromSQL() {
		if (sqlSource == null) {
			throw new RuntimeException("sqlSource is null");
		}
		return sqlSource.read();
	}

}
