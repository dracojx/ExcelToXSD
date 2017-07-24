package com.jx.tools.excel2xsd;

import com.jx.tools.excel2xsd.read.Read;
import com.jx.tools.excel2xsd.write.Write;

/** 
 * @author Draco
 * @version 1.0
 * @date 2017年7月1日
 *  
 */
public class Run {

	public static void main(String[] args) {
		try {
			Write.write(Read.read());
		} catch (Exception e) {
			Write.writeError(e.getMessage());
			e.printStackTrace();
		}
	}

}
