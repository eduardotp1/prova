package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
	private XorGate xorRight;
	private XorGate xorLeft;
	private AndGate andTop;
	private AndGate andBottom;
	private OrGate orGate;

	public FullGate() {
		super(3, 2);

		name = "FULL";

		xorRight = new XorGate();
		xorLeft = new XorGate();
		andTop = new AndGate();
		andBottom = new AndGate();
		orGate = new OrGate();
		
		xorRight.connect(xorLeft, 0);
		andTop.connect(xorLeft, 0);
		orGate.connect(andTop, 0);
		orGate.connect(andBottom, 1);
	}

	@Override
	public boolean doRead(int index) {
		switch(index) {
		case 0:
			return xorRight.read();
		case 1:
			return orGate.read();
		}
		return false;
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		case 0:
			xorLeft.connect(emitter, 0);
			andBottom.connect(emitter, 0);
			break;
		case 1:
			xorLeft.connect(emitter, 1);
			andBottom.connect(emitter, 1);
			break;
		case 2:
			xorRight.connect(emitter, 1);
			andTop.connect(emitter, 1);
		}
	}
}
	