package org.oaci.oaci;

public class OaciMetar extends OaciAbstract {

	public OaciMetar() {
		super("METAR");
	}

	@Override
	public void decode() {
		this.decodeCommon();
	}
}
