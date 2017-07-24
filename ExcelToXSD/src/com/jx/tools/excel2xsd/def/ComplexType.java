package com.jx.tools.excel2xsd.def;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author Draco
 * @version 1.0
 * @date 2017年6月30日
 *  
 */
public class ComplexType {
	
	private String name;
	private SimpleContent simpleContent;
	private List<Element> sequence;
	private List<Attribute> attributes;
	
	private ComplexType() {
		sequence = new ArrayList<Element>();
		attributes = new ArrayList<Attribute>();
	}
	
	private ComplexType(String name) {
		this();
		this.name = name;
	}
	
	private ComplexType(String name, String base) throws Exception {
		this.name = name;
		simpleContent = SimpleContent.create(base);
	}
	
	public static ComplexType create() {
		return new ComplexType();
	}
	
	public static ComplexType create(String name) {
		return new ComplexType(name);
	}
	
	public static ComplexType create(String name, String base) throws Exception {
		return new ComplexType(name, base);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SimpleContent getSimpleContent() {
		return simpleContent;
	}
	public void setSimpleContent(SimpleContent simpleContent) {
		this.simpleContent = simpleContent;
	}
	public List<Element> getSequence() {
		return sequence;
	}
	public void setSequence(List<Element> sequence) {
		this.sequence = sequence;
	}
	public void addElement(Element element) {
		sequence.add(element);
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public void addAttribute(Attribute attribute) {
		if(simpleContent != null) {
			simpleContent.addAttribute(attribute);
		} else {
			attributes.add(attribute);
		}
	}
	public Element getElement(String name) {
		for(Element element : sequence) {
			if(element.getName().equalsIgnoreCase(name)) {
				return element;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		buff.append("<xsd:complexType");
		if(name != null) {
			buff.append(" name=\"").append(name).append("\"");
		}
		buff.append(">");
		
		if(simpleContent != null) {
			buff.append(simpleContent.toString());
		} else {
			if(sequence != null) {
				buff.append("<xsd:sequence>");
				for(Element element : sequence) {
					buff.append(element.toString());
				}
				buff.append("</xsd:sequence>");
			}
			
			if(attributes != null) {
				for(Attribute attribute : attributes) {
					buff.append(attribute.toString());
				}
			}
		}
		
		buff.append("</xsd:complexType>");
		
		
		return buff.toString();
	}

}
