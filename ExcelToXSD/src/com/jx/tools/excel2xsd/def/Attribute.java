package com.jx.tools.excel2xsd.def;

/**
 * @author Draco
 * @version 1.0
 * @date 2017年6月30日
 * 
 */
public class Attribute {
	private static final String STRING = "string";
	private static final String DECIMAL = "decimal";
	private static final String BOOLEAN = "boolean";
	private static final String REQUIRED = "y";

	private String name;
	private boolean use;
	private String type;
	private String documentation;
	private SimpleType simpleType;

	private Attribute(String name, String type, String required,
			String documentation) throws Exception {
		if (STRING.equalsIgnoreCase(type) || DECIMAL.equalsIgnoreCase(type)
				|| BOOLEAN.equalsIgnoreCase(type)) {
			this.name = name;
			this.type = type;
			this.use = REQUIRED.equalsIgnoreCase(required);
			this.documentation = documentation;
		} else {
			throw new Exception("attribute: " + name + ", " + type);
		}
	}

	private Attribute(String name, String type,
			String param, String required, String documentation) throws Exception {
		this.simpleType = SimpleType.create(type, param);
		this.name = name;
		this.use = REQUIRED.equalsIgnoreCase(required);
		this.documentation = documentation;
	}

	private Attribute(String name, String type, String param1,
			String param2, String required, String documentation) throws Exception {
		this.simpleType = SimpleType.create(type, param1, param2);
		this.name = name;
		this.use = REQUIRED.equalsIgnoreCase(required);
		this.documentation = documentation;
	}
	
	public static Attribute create(String name, String type, String required,
			String documentation) throws Exception {
		return new Attribute(name, type, required, documentation);
	}
	
	public static Attribute create(String name, String type,
			String param, String required, String documentation) throws Exception {
		return new Attribute(name, type, param, required, documentation);
	}
	
	public static Attribute create(String name, String type, String param1,
			String param2, String required, String documentation) throws Exception {
		return new Attribute(name, type, param1, param2, required, documentation);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("<xsd:attribute name=\"").append(name).append("\"");
		if (type != null) {
			buff.append(" type=\"xsd:").append(type).append("\"");
		}
		if (use) {
			buff.append(" use=\"required\"");
		}
		buff.append(">");

		if (documentation != null && !"".equals(documentation)) {
			buff.append("<xsd:annotation><xsd:documentation>")
					.append(documentation)
					.append("</xsd:documentation></xsd:annotation>");
		}
		
		if(simpleType != null) {
			buff.append(simpleType.toString());
		}
		
		buff.append("</xsd:attribute>");
		
		return buff.toString();
	}

}
