package com.jx.tools.excel2xsd.def;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Draco
 * @version 1.0
 * @date 2017年6月30日
 * 
 */
public class Extension {
	private static final String STRING = "string";
	private static final String DECIMAL = "decimal";
	private static final String BOOLEAN = "boolean";

	private String base;
	private List<Attribute> attributes;

	private Extension(String base) throws Exception {
		if (STRING.equalsIgnoreCase(base) || DECIMAL.equalsIgnoreCase(base)
				|| BOOLEAN.equalsIgnoreCase(base)) {
			this.base = "xsd:" + base;
		} else {
			this.base = base;
		}
		attributes = new ArrayList<Attribute>();
	}
	
	public static Extension create(String base) throws Exception {
		return new Extension(base);
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}

	/*
	 * public static void main(String[] args) { try { Extension ex = new
	 * Extension("string"); ex.addAttribute(new Attribute("Attr1", "N",
	 * "string", "����1")); ex.addAttribute(new Attribute("Attr2", "Y", "string",
	 * "4", "����2")); ex.addAttribute(new Attribute("Attr3", "Y", "decimal",
	 * "17", "2", "����3")); ex.addAttribute(new Attribute("Attr4", "N",
	 * "boolean", "����4")); System.out.println(ex.toString()); } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("<xsd:extension base=\"").append(base).append("\">");

		for (Attribute attr : attributes) {
			buff.append(attr.toString());
		}

		buff.append("</xsd:extension>");
		return buff.toString();
	}

}
