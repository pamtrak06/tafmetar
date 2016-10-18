package org.oaci.oaci;

public class OaciTaf extends OaciAbstract {

	
	public OaciTaf() {
		super("TAF");
	}

	@Override
	public void decode() {
		this.decodeCommon();
	}

}
