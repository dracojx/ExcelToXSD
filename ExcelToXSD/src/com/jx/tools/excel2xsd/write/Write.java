package com.jx.tools.excel2xsd.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.jx.tools.excel2xsd.def.XSD;

/**
 * @author Draco
 * @version 1.0
 * @date 2017年7月1日
 * 
 */
public class Write {

	public static void write(List<XSD> list) {
		for (XSD xsd : list) {
			try {
				String filename = "./" + xsd.getPath() + "/"
						+ xsd.getFilename() + ".xsd";
				File file = new File(filename);

				if (!file.exists()) {
					file.getParentFile().mkdir();
					file.createNewFile();
				}

				Writer bw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file.getPath()),
						StandardCharsets.UTF_8));
				bw.write(xsd.getSchema().toString());
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeError(String message) {
		try {
			File file = new File("./error.txt");

			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();
			}

			Writer fw = new FileWriter(file.getPath());
			Writer bw = new BufferedWriter(fw);
			bw.write(message);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
