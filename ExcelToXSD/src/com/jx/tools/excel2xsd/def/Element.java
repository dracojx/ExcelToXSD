package com.jx.tools.excel2xsd.def;

/**
 * @author Draco
 * @version 1.0
 * @date 2017年6月30日
 * 
 */
public class Element {
	private static final String OPTIONAL = "0";
	private static final String MULTI = "unbounded";
	private static final String STRING = "string";
	private static final String DECIMAL = "decimal";
	private static final String BOOLEAN = "boolean";
	private static final String REQUIRED = "y";

	private String name;
	private String type;
	private String minOccurs;
	private String maxOccurs;
	private String documentation;
	private SimpleType simpleType;
	private ComplexType complexType;

	private Element(String name) {
		this.name = name;
		this.complexType = ComplexType.create();
	}

	private Element(String name, Boolean multi) {
		this.name = name;
		if (multi) {
			this.maxOccurs = MULTI;
		}
		this.complexType = ComplexType.create();
	}

	private Element(String name, String type, String required,
			String documentation) throws Exception {
		if (STRING.equalsIgnoreCase(type) || DECIMAL.equalsIgnoreCase(type)
				|| BOOLEAN.equalsIgnoreCase(type)) {
			this.name = name;
			this.type = type;
			this.documentation = documentation;
			this.minOccurs = REQUIRED.equalsIgnoreCase(required) ? null
					: OPTIONAL;
		} else {
			throw new Exception("Element: " + name + ", " + type);
		}
	}

	private Element(String name, String type, String param, String required,
			String documentation) throws Exception {
		if (STRING.equalsIgnoreCase(type) || DECIMAL.equalsIgnoreCase(type)
				|| BOOLEAN.equalsIgnoreCase(type)) {
			this.name = name;
			this.documentation = documentation;
			this.minOccurs = REQUIRED.equalsIgnoreCase(required) ? null
					: OPTIONAL;
			this.simpleType = SimpleType.create(type, param);
		} else {
			throw new Exception("Element: " + name + ", " + type + ", " + param);
		}
	}

	private Element(String name, String type, String param1, String param2,
			String required, String documentation) throws Exception {
		if (STRING.equalsIgnoreCase(type) || DECIMAL.equalsIgnoreCase(type)
				|| BOOLEAN.equalsIgnoreCase(type)) {
			this.name = name;
			this.documentation = documentation;
			this.minOccurs = REQUIRED.equalsIgnoreCase(required) ? null
					: OPTIONAL;
			this.simpleType = SimpleType.create(type, param1, param2);
		} else {
			throw new Exception("Element: " + name + ", " + type + ", "
					+ param1 + ", " + param2);
		}
	}
	
	public static Element create(String name) {
		return new Element(name);
	}
	
	public static Element create(String name, Boolean multi) {
		return new Element(name, multi);
	}

	public static Element create(String name, String type, String required,
			String documentation) throws Exception {
		return new Element(name, type, required, documentation);
	}

	public static Element create(String name, String type, String param, String required,
			String documentation) throws Exception {
		return new Element(name, type, param, required, documentation);
	}

	public static Element create(String name, String type, String param1, String param2,
			String required, String documentation) throws Exception {
		return new Element(name, type, param1, param2, required, documentation);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(String minOccurs) {
		this.minOccurs = minOccurs;
	}

	public String getMaxOccurs() {
		return maxOccurs;
	}

	public void setMaxOccurs(String maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public SimpleType getSimpleType() {
		return simpleType;
	}

	public void setSimpleType(SimpleType simpleType) {
		this.simpleType = simpleType;
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

	public void addAttribute(Attribute attribute, Schema schema,
			String parent) throws Exception {
		if (simpleType != null) {
			String stName = schema.getName() + "." + parent + ".Content";
			simpleType.setName(stName);
			schema.addSimpleType(simpleType);
			complexType = ComplexType.create(null, simpleType.getName());
			simpleType = null;
		} else if (type != null) {
			complexType = ComplexType.create(null, type);
			type = null;
		}
		complexType.addAttribute(attribute);
	}
	
	public Element getElement(String name) {
		return complexType.getElement(name);
	}
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();

		buff.append("<xsd:element name=\"").append(name).append("\"");
		if (type != null) {
			buff.append(" type=\"xsd:").append(type).append("\"");
		}
		if (minOccurs != null) {
			buff.append(" minOccurs=\"").append(minOccurs).append("\"");
		}
		if (maxOccurs != null) {
			buff.append(" maxOccurs=\"").append(maxOccurs).append("\"");
		}
		buff.append(">");

		if (documentation != null && !"".equals(documentation)) {
			buff.append("<xsd:annotation><xsd:documentation>")
					.append(documentation)
					.append("</xsd:documentation></xsd:annotation>");
		}

		if (simpleType != null) {
			buff.append(simpleType.toString());
		} else if (complexType != null) {
			buff.append(complexType.toString());
		}

		buff.append("</xsd:element>");

		return buff.toString();
	}

}
