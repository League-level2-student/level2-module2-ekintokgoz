package _06_overloading;

import java.awt.Color;

import javax.swing.JPanel;

public class LeagueOptionPaneRunner {
	public static void main(String[] args) {
		LeagueOptionPane.showMessageDialog("hello");
		LeagueOptionPane.showMessageDialog("hi there", "greeting");
		JPanel newPanel = LeagueOptionPane.showMessageDialog("I am coding", "coding fact", "src/_06_overloading/leagueDark.png");
		newPanel.setBackground(null)
		LeagueOptionPane.showMessageDialog("I chose the background color", "cool color", "src/_06_overloading/leagueDark.png", Color.ORANGE);
	}
}
