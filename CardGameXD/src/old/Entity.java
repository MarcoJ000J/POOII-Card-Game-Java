package old;

import java.util.ArrayList;

import javax.swing.ImageIcon;


/*
 * @since 17/05/2025
 *Classe para criar todas as entidades para os momentos de combate
 * 
 */
public class Entity {
	/*
	 * as caracteriscas e aprametros
	 * */
	public String name;
	
	public int life;
	public int atk;
	public int mind;
	public int fis;
	
	public ArrayList<String> skills = new ArrayList<String>();
	
	public ImageIcon face;
	
	/*
	 *	para criar os inimigos, para cada nome diferentes parametros. 
	 */
	public Entity(String name) {
		this.name = name;
		
		if(name == "generic") {
			this.life = 10;
			this.atk = 4;
			this.mind = 3;
			this.fis = 3;
		}
	}
	
	
	/*
	 * para o personagem principal, @see nivel serve para definir o momento da historia e sua força e suas
	 * skills.
	 */
	public Entity(String name, int nivel) {
		this.name = name;
		
		if (nivel == 0) {
			//nao sei os valores ainda, depois mudar
			this.life = 10;
			this.atk = 5;
			this.mind = 2;
			this.fis = 1;
		}
	}
	
}
