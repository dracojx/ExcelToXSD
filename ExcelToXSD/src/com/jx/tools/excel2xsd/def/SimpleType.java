package com.jx.tools.excel2xsd.def;
/** 
 * @author Draco
 * @version 1.0
 * @date 2017Äê6ÔÂ30ÈÕ
 *  
 */
public class SimpleType {
	
	private String name;
	private Restriction restriction;
	
	private SimpleType(String base, String param) throws Exception {
		this.restriction = Restriction.create(base, param);
	}
	
	private SimpleType(String base, String param1, String param2) throws Exception {
		this.restriction = Restriction.create(base, param1, param2);
	}
	
	public static SimpleType create(String base, String param) throws Exception {
		return new SimpleType(base, param);
	}
	
	public static SimpleType create(String base, String param1, String param2) throws Exception {
		return new SimpleType(base, param1, param2);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

/*	public static void main(String[] args) {
		try {
			SimpleType s1 = new SimpleType("string", "200");
			System.out.println(s1.toString());
			SimpleType s2 = new SimpleType("decimal", "200", "3");
			System.out.println(s2.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("<xsd:simpleType");
		if(name != null && !"".equals(name)) {
			buff.append(" name=\"").append(name).append("\"");
		}
		buff.append(">");
		buff.append(restriction.toString()).append("</xsd:simpleType>");
		return buff.toString();
	}

}
