package com.thinkgem.jeesite.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;


/**
 * The Class JsMaker.<br>
 * 
 * @author FNST)WeiCL
 * @version 1.0
 */
public class JsMaker {
	private StringBuilder scriptBuilder;
	
	public JsMaker() {
		this("");
	}
	
	public JsMaker(String script) {
		this.scriptBuilder = new StringBuilder(script);
	}
	
	public String toJs() {
		return "<script type='text/javascript' >\n"+scriptBuilder.toString() + "\n</script>";
	}
	
	public JsMaker append(String script) {
		scriptBuilder.append("\n");
		scriptBuilder.append(script);
		return this;
	}
	
	public JsMaker append(InputStream input) throws IOException {
		scriptBuilder.append("\n");
		String script = IOUtils.toString(input);
		scriptBuilder.append(script);
		return this;
	}
	
	public JsMaker append(File file) throws IOException {
		scriptBuilder.append("\n");
		String script = FileUtils.readFileToString(file, "UTF8");
		scriptBuilder.append(script);
		return this;
	}
	
	public JsMaker addAlert(String alert) {
		append("alert('" + escape(alert) + "');");
		
		return this;
	}
	
	public JsMaker addVar(String name, String value) {
		append("var " + name + " = '" + escape(value) + "';");
		
		return this;
	}
	
	private String escape(String str) {
		if (str != null) {
			str = str.replace("\\", "\\\\").replace("\n", "\\n").replace("'", "\\'");
		}
		
		return str;
	}
	
	@Override
	public String toString() {
	    return scriptBuilder.toString();
	}
	
	public static JsMaker alert(String alert) {
		return new JsMaker().addAlert(alert);
	}
	
	public static JsMaker born() {
		return new JsMaker();
	}
}
