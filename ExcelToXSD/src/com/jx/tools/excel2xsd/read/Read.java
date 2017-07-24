package com.jx.tools.excel2xsd.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jx.tools.excel2xsd.def.Attribute;
import com.jx.tools.excel2xsd.def.Constants;
import com.jx.tools.excel2xsd.def.Element;
import com.jx.tools.excel2xsd.def.Schema;
import com.jx.tools.excel2xsd.def.XSD;

/** 
 * @author Draco
 * @version 1.0
 * @date 2017年7月1日
 *  
 */
public class Read {

	public static void main(String[] args) throws IOException {
		try {
			List<XSD> list = read();
			for(XSD xsd : list) {
				System.out.println(xsd.getSchema().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<XSD> read() throws Exception {
		List<XSD> xsds = new ArrayList<XSD>();
		InputStream is = new FileInputStream(Constants.TEMPLATE_FILE_NAME);
		Workbook wb = new XSSFWorkbook(is);
		Iterator<Sheet> it = wb.iterator();
		while(it.hasNext()) {
			Sheet sheet = it.next();
			if(!wb.isSheetHidden(wb.getSheetIndex(sheet))) {
				xsds.add(readSheet(sheet));
			}
		}
		
		wb.close();
		return xsds;
	}
	
	private static XSD readSheet(Sheet sheet) throws Exception {
		String filename = sheet.getRow(Constants.ROW_FILENAME).getCell(Constants.CELL_FILENAME).getStringCellValue().trim();
		String path = sheet.getRow(Constants.ROW_PATH).getCell(Constants.CELL_PATH).getStringCellValue().trim();
		String namespace = sheet.getRow(Constants.ROW_NAMESPACE).getCell(Constants.CELL_NAMESPACE).getStringCellValue().trim();
		
		Schema schema = Schema.create(filename, namespace);
		Iterator<Row> it = sheet.iterator();
		it.next();
		while(it.hasNext()) {
			readRow(it.next(), schema);
		}
		
		return XSD.create(filename, path, schema);
	}
	
	private static void readRow(Row row, Schema schema) throws Exception {
		if(row.getCell(Constants.CELL_NAME) == null) {
			return;
		}
		String name = row.getCell(Constants.CELL_NAME).getStringCellValue().trim();
		String type = row.getCell(Constants.CELL_TYPE).getStringCellValue().trim();
		row.getCell(Constants.CELL_LENGTH).setCellType(CellType.STRING);
		String length = row.getCell(Constants.CELL_LENGTH).getStringCellValue().trim();
		String required = row.getCell(Constants.CELL_REQUIRED).getStringCellValue().trim();
		String attr = row.getCell(Constants.CELL_ATTR).getStringCellValue().trim();
		String doc = row.getCell(Constants.CELL_DOC).getStringCellValue().trim();
		String parent = row.getCell(Constants.CELL_PARENT).getStringCellValue().trim();
		
		if(name == null || "".equals(name)) {
			throw new Exception("No name");
		}
		if(type == null || "".equals(type)) {
			throw new Exception("No type");
		}

		if(Constants.ATTR.equalsIgnoreCase(attr)) {
			Element p = schema.getChild(parent);
			if(length == null || "".equals(length)) {
				p.addAttribute(Attribute.create(name, type, required, doc), schema, parent);
			} else {
				String[] lengths = length.split(",");
				if(lengths.length == 1) {
					p.addAttribute(Attribute.create(name, type, lengths[0], required, doc), schema, parent);
				} else if (lengths.length == 2) {
					p.addAttribute(Attribute.create(name, type, lengths[0], lengths[1], required, doc), schema, parent);
				} else {
					throw new Exception(name + " Wrong Length");
				}
			}
		} else {
			if(parent == null || "".equals(parent)) {
				if(length == null || "".equals(length)) {
					schema.addElement(Element.create(name, type, required, doc));
				} else {
					String[] lengths = length.split(",");
					if(lengths.length == 1) {
						schema.addElement(Element.create(name, type, lengths[0], required, doc));
					} else if (lengths.length == 2) {
						schema.addElement(Element.create(name, type, lengths[0], lengths[1], required, doc));
					} else {
						throw new Exception(name + " Wrong Length");
					}
				}
			} else {
				Element p = schema.getChild(parent);
				if(length == null || "".equals(length)) {
					p.addElement(Element.create(name, type, required, doc));
				} else {
					String[] lengths = length.split(",");
					if(lengths.length == 1) {
						p.addElement(Element.create(name, type, lengths[0], required, doc));
					} else if (lengths.length == 2) {
						p.addElement(Element.create(name, type, lengths[0], lengths[1], required, doc));
					} else {
						throw new Exception(name + " Wrong Length");
					}
				}
			}
		}
	}
	

}
