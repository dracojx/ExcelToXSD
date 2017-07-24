package com.jx.tools.excel2xsd.def;
/** 
 * @author Draco
 * @version 1.0
 * @date 2017Äê6ÔÂ30ÈÕ
 *  
 */
public class SimpleContent {
	
	private Extension extension;
	
	private SimpleContent(String base) throws Exception {
		this.extension = Extension.create(base);
	}
	
	public static SimpleContent create(String base) throws Exception {
		return new SimpleContent(base);
	}

	public Extension getExtension() {
		return extension;
	}


	public void setExtension(Extension extension) {
		this.extension = extension;
	}
	
	public void addAttribute(Attribute attribute) {
		extension.addAttribute(attribute);
	}


/*	public static void main(String[] args) {
		try {
			System.out.println(new SimpleContent("string"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("<xsd:simpleContent>").append(extension.toString()).append("</xsd:simpleContent>");
		return buff.toString();
	}

}
