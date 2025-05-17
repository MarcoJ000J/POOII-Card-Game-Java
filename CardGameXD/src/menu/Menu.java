package main;

import javax.swing.*;
import java.awt.*;
import dialogo.ControladorDialogo;
import cardForNow.*;
import util.CarregadorImagem;

public class Menu extends JFrame {
	public Menu () {

		super("Orv GAME");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setLayout(null);

		//fundo 
		JLabel fundo = new JLabel (CarregadorImagem.load("menu_background"));
		fundo.setBounds(0, 0, 1280, 720);
		setContentPane(fundo);
		fundo.setLayout(null);
		//fundo.setLayout(new BorderLayout());
		//fundo.setLayout(new GridLayout(1, 1));
		//fundo.setLayout(new FlowLayout());
		//fundo.setLayout(new BoxLayout(fundo, BoxLayout.Y_AXIS));
		//fundo.setLayout(new GridBagLayout());

		//botao Funcionais

		int margenX = 50, margemY = getHeight() - 220;
		String[] nomes = {"Novo Jogo", "Continuar", "Configs" , "Sair"};
		Jbutton [] botoes = new JButton (textos[i]);
		for (int i=0; i<4; i++)




	}

	/* 
	 * fazer aqui o menu pricipal, onde de começa o jogo novo (futuramente continuar um jogo antigo)
	 * sair do jogo e ver placar de lideres e onde vai se escolher a dificuldade 
	 * */	
}
