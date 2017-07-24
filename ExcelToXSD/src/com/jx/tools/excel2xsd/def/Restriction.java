package com.jx.tools.excel2xsd.def;
/** 
 * @author Draco
 * @version 1.0
 * @date 2017年6月30日
 *  
 */
public class Restriction {
	
	private static final String STRING = "string";
	private static final String DECIMAL = "decimal";
	
	private String base;
	private String totalDigits;
	private String fractionDigits;
	private String minLength;
	private String maxLength;
	
	private Restriction(String base, String param) throws Exception {
		if(STRING.equalsIgnoreCase(base)) {
			this.base = base;
			this.maxLength = param;
		} else {
			throw new Exception("restriction: " + base + ", " + param);
		}
	}
	
	private Restriction(String base, String param1, String param2) throws Exception {
		if(DECIMAL.equalsIgnoreCase(base)) {
			this.base = base;
			this.totalDigits = param1;
			this.fractionDigits = param2;
		} else if(STRING.equalsIgnoreCase(base)) {
			this.base = base;
			this.minLength = param1;
			this.maxLength = param2;
		} else {
			throw new Exception("restriction: " + base + ", " + param1 + ", " + param2);
		}
	}
	
	public static Restriction create(String base, String param) throws Exception {
		return new Restriction(base, param);
	}
	
	public static Restriction create(String base, String param1, String param2) throws Exception {
		return new Restriction(base, param1, param2);
	}
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getTotalDigits() {
		return totalDigits;
	}
	public void setTotalDigits(String totalDigits) {
		this.totalDigits = totalDigits;
	}
	public String getFractionDigits() {
		return fractionDigits;
	}
	public void setFractionDigits(String fractionDigits) {
		this.fractionDigits = fractionDigits;
	}
	public String getMinLength() {
		return minLength;
	}
	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	
/*	public static void main(String[] args) {
		try {
			Restriction r1 = new Restriction("string", "100");
			System.out.println(r1.toString());
			Restriction r2 = new Restriction("decimal", "100", "4");
			System.out.println(r2.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("<xsd:restriction base=\"xsd:").append(base).append("\">");
		if(minLength != null) {
			buff.append("<xsd:minLength value=\"").append(minLength).append("\"/>");
		}
		if(maxLength != null) {
			buff.append("<xsd:maxLength value=\"").append(maxLength).append("\"/>");
		}
		if(totalDigits != null) {
			buff.append("<xsd:totalDigits value=\"").append(totalDigits).append("\"/>");
		}
		if(fractionDigits != null) {
			buff.append("<xsd:fractionDigits value=\"").append(fractionDigits).append("\"/>");
		}
		buff.append("</xsd:restriction>");
		return buff.toString();
	}
	
}
