package org.oaci.oaci;

import java.util.StringTokenizer;

public abstract class OaciAbstract {

	protected StringBuffer buffer;
	protected String format;
	protected String oaci;
	
	public OaciAbstract(String format) {
		super();
		this.format = format;
		buffer = new StringBuffer();
	}
	
	public void append(String str){
		buffer.append(str);
		System.out.println(str);
	}

	public abstract void decode();
	
	public void decodeCommon() {
		StringTokenizer str = new StringTokenizer(buffer.toString(), "\n");
		while (str.hasMoreTokens()) {
			String nextToken = str.nextToken();
			String[] split = nextToken.split(" ");
			if (OaciRessources.getInstance().getOaciDic().get(split[1].trim()) != null) {
				System.out.println("OACI code identified: " + split[1].trim());
				oaci = split[1].trim();
			}
		}
	}
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public String getFormat() {
		return format;
	}

	public String getOaci() {
		return oaci;
	}
}
