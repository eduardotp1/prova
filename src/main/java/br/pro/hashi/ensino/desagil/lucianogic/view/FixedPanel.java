package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FixedPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public FixedPanel(int width, int height) {
		setLayout(null);
		setPreferredSize(new Dimension(width, height));
	}

	public Component add(Component outBox, int x, int y, int width, int height) {
		super.add(outBox);
		outBox.setBounds(x, y, width, height);
		return outBox;
	}
}
