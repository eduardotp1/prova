package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {
	private NandGate nandRight;
	private NandGate nandMUp;
	private NandGate nandMDown;
	private NandGate nandLUp;
	private NandGate nandLDown;
	
	public HalfGate() {
		super(2, 2);

		name = "HALF";

		nandRight = new NandGate();
		nandMUp = new NandGate();
		nandMDown = new NandGate();
		nandLUp = new NandGate();
		nandLDown = new NandGate();
		
		nandMUp.connect(nandRight, 1);
		nandMDown.connect(nandRight, 0);
		nandLDown.connect(nandRight, 0);
		nandLDown.connect(nandRight, 1);
		nandLUp.connect(nandMUp, 0);
		nandLUp.connect(nandMDown, 1);
	}

	@Override
	public boolean doRead(int index) {
		switch(index) {
		case 0:
			return nandLUp.read();
		case 1:
			return nandLDown.read();
		}
		return false;
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			nandRight.connect(emitter, 0);
			nandMUp.connect(emitter, 0);
			break;
		case 1:
			nandRight.connect(emitter, 1);
			nandMDown.connect(emitter, 1);			
			break;
		}
	}
}
