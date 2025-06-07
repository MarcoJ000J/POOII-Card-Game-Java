package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;

import main.GameFrame;
import save.GerenciadorSave;
import save.PerfilSave;
import ui.Button;
import util.BackgroundPanel;
import util.FontLoader;

public class Placar {

	public BackgroundPanel painel = new BackgroundPanel("/background/raw.png");
	
	private JTextArea placar;
	
	private Font fonte = new FontLoader().fonte;
	
	private Button back = new Button("scr/main/resources/ui/spr_banner_hud.png", "BACK", 5, 30);

	
	public Placar(GameFrame frame) {
		//painel config
		painel.setSize(1000, 600);
		painel.setOpaque(false);
		painel.setLayout(new GridBagLayout());
		
		//palcar confg
		placar = new JTextArea();
		placar.setEditable(false);
		placar.setOpaque(false);
		placar.setForeground(Color.WHITE);
		placar.setFont(fonte);
		
		displayPlacar();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		///configura o gridbagconstraints para o painel;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.CENTER;
		
		painel.add(placar, gbc);
		
		gbc.fill = GridBagConstraints.WEST;
		
		painel.add(back, gbc);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.backToMenu(frame);
			}
		});
	}
	
	public void displayPlacar() {
		GerenciadorSave gerenciador = new GerenciadorSave();
        List<PerfilSave> placarSalvo = gerenciador.getListaSave();
        
        StringBuilder sb = new StringBuilder();
        sb.append("-------- PLACAR --------\n\n");

        if (placarSalvo.isEmpty()) {
            sb.append("Nenhuma pontuação registrada ainda.\n");
        } else {
            for (int i = 0; i < placarSalvo.size(); i++) {
                PerfilSave perfil = placarSalvo.get(i);
                sb.append(String.format("%d. %s - %d pontos\n", (i + 1), perfil.getNome(), perfil.getPontos()));
            }
        }
        placar.setText(sb.toString());
	}
}
