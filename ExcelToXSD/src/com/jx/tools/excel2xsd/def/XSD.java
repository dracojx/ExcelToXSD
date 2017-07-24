package com.jx.tools.excel2xsd.def;
/** 
 * @author Draco
 * @version 1.0
 * @date 2017Äê7ÔÂ1ÈÕ
 *  
 */
public class XSD {

	private String filename;
	private String path;
	private Schema schema;
	
	private XSD(String filename, String path, Schema schema) {
		this.filename = filename;
		this.path = path;
		this.schema = schema;
	}
	
	public static XSD create(String filename, String path, Schema schema) {
		return new XSD(filename, path, schema);
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Schema getSchema() {
		return schema;
	}
	public void setSchema(Schema schema) {
		this.schema = schema;
	}
	
}
