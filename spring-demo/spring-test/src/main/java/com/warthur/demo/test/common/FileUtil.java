package com.warthur.demo.test.common;

import java.io.*;
import java.net.URL;

public final class FileUtil {

	public static String read(URL url) {

		InputStream inputStream;
		BufferedReader br = null;
		StringBuilder contentHolder = new StringBuilder("");
		if (url != null) {
			try {
				inputStream = url.openStream();
				if (inputStream != null) {
					br = new BufferedReader(new InputStreamReader(inputStream));
				}

				contentHolder = new StringBuilder();
				String lineContent = null;
				if (br != null) {
					while ((lineContent = br.readLine()) != null) {
						contentHolder.append(lineContent);
					}
				}

				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return contentHolder.toString();
	}

	public static String read(String file) {
		URL url = FileUtil.class.getClassLoader().getResource(file);
		return read(url);
	}
}
