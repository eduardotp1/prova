package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import br.pro.hashi.ensino.desagil.lucianogic.model.LED;


public class GateView extends FixedPanel implements ItemListener, ActionListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox[] outBox;

	private Switch[] switches;
	private Gate gate;

	private LED led;
	private LED led2;
	private JButton ledButton = new JButton();
	private JButton ledButton2 = new JButton();
	private Color color;


	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;

		led = new LED(255,0,0);
		led2 = new LED(255,0,0);
		
		color = new Color(led.getR(),led.getG(),led.getB());
		
	    ledButton.setBorder(new RoundedBorder(50));
	    ledButton.addActionListener(this);
	    ledButton2.setBorder(new RoundedBorder(50));
	    ledButton2.addActionListener(this);
	    
		image = loadImage(gate.toString());

		int inSize = gate.getInSize();

		inBoxes = new JCheckBox[inSize];

		switches = new Switch[inSize];

		for(int i = 0; i < inSize; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		int outSize = gate.getOutSize();
		outBox = new JCheckBox[outSize];
		outBox[0] =new JCheckBox();
		
		

		if(inSize == 1) {
			add(inBoxes[0], 0, 60, 20, 20);			
		}
		else {
			for(int i = 0; i < inSize; i++) {
				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
			}			
		}

		if(outSize == 1) {
//			outBox[0].setEnabled(false);
//			add(outBox[0], 184, 60, 20, 20);
			add(ledButton, 184, 60, 20, 20);
		}
		else {
			for(int i = 0; i < inSize; i++) {
				
				outBox[1] =new JCheckBox();
				outBox[0].setEnabled(false);
				outBox[1].setEnabled(false);
				add(ledButton, 184, 65, 20, 20);
				add(ledButton2, 184, 100, 20, 20);
//				add(outBox[0], 184, 65, 20, 20);
//				add(outBox[1], 184, 100, 20, 20);				
//				outBox[0].setSelected(gate.read(0));
//				outBox[1].setSelected(gate.read(1));
				
			}			
			led.connect(gate, 0);
		
			ledButton.setEnabled(led.isOn());
			if(led.isOn()){
				   ledButton.setBackground(color); }
			else {
				makeColorGray ();
				}
			
			led2.connect(gate, 0);
		
			ledButton2.setEnabled(led2.isOn());
			if(led2.isOn()){
				   ledButton2.setBackground(color); }
			else {
				makeColorGray ();
				}
		}
	}


	public void makeColorGray(){
		led.setR(220);
		led.setG(220);
		led.setB(220);
		ledButton.setBackground(new Color(led.getR(),led.getG(),led.getB()));
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());

		led.connect(gate, 0);
		if(led.isOn()==true){
			ledButton.setBackground(color);
		}else{
			this.makeColorGray();
		}
		
		outBox[0].setSelected(gate.read(0));
		if(outBox.length>1){
		outBox[1].setSelected(gate.read(1));
		}
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		color = JColorChooser.showDialog(this, null, null);

		if(color != null) {
			ledButton.setBackground(color);
		}
	}
}