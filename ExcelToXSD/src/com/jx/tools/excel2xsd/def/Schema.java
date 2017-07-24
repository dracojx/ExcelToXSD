package com.jx.tools.excel2xsd.def;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Draco
 * @version 1.0
 * @date 2017年6月30日
 * 
 */
public class Schema {
	private String namespace;
	private List<SimpleType> simpleTypes;
	private ComplexType complexType;

	private Schema(String name, String namespace) {
		this.namespace = namespace;
		simpleTypes = new ArrayList<SimpleType>();
		complexType = ComplexType.create(name);
	}
	
	public static Schema create(String name, String namespace) {
		return new Schema(name, namespace);
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public ComplexType getComplexType() {
		return complexType;
	}

	public void setComplexType(ComplexType complexType) {
		this.complexType = complexType;
	}

	public void addElement(Element element) {
		complexType.addElement(element);
	}
	public void addSimpleType(SimpleType simpleType) {
		simpleTypes.add(simpleType);
	}
	public String getName() {
		return complexType.getName();
	}
	public Element getElement(String name) {
		return complexType.getElement(name);
	}
	public Element getChild(String parent) throws Exception {
		String[] children = parent.split("\\.");
		Element child = getElement(children[0]);
		if(child == null) {
			child = Element.create(children[0]);
			addElement(child);
		}
		
		for(int i = 1; i < children.length; i++) {
			Element sub = child.getElement(children[i]);
			if(sub == null) {
				sub = Element.create(children[i]);
				child.addElement(sub);
			}
			child = sub;
		}
		
		return child;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();

		buff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buff.append(
				"<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"")
				.append(namespace).append("\" xmlns=\"").append(namespace)
				.append("\">");
		
		for(SimpleType simpleType : simpleTypes) {
			buff.append(simpleType.toString());
		}
		
		buff.append(complexType.toString());
		buff.append("</xsd:schema>");

		return buff.toString();
	}

}
