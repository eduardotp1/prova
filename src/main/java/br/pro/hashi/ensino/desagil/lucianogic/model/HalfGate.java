package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {
	private XorGate xor;
	private AndGate andGate;
	public HalfGate() {
		super(2, 2);

		name = "HALF";

		xor = new XorGate();
		andGate = new AndGate();
	}

	@Override
	public boolean doRead(int index) {
		switch(index) {
		case 0:
			return xor.read();
		case 1:
			return andGate.read();
		}
		return false;
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			xor.connect(emitter, 0);
			andGate.connect(emitter, 0);
			break;
		case 1:
			xor.connect(emitter, 1);
			andGate.connect(emitter, 1);
			break;
		}
	}
}
