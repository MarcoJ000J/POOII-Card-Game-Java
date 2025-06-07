package save;

import java.util.Objects;

/**
 * usar para registrar o perfil ao final da partida, e preparalo para save
 */
public class PerfilSave implements Comparable<PerfilSave>{
	private int pontos;
	private String nome;
	
	/**
	 * cria a classe com os parametros passados ao fim do jogo
	 */
	public PerfilSave(int pontos, String nome) {
		this.nome = nome;
		this.pontos = pontos;
	}
	
	public int getPontos() {
		return this.pontos;
	};
	
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * prepara o que sera escrito ao salvar
	 */
	@Override
	public String toString() {
		return "Nome: "+nome+" Pontos: "+pontos;
	}

	/**
	 * Compara pra organizar na lista
	 */
	@Override
	public int compareTo(PerfilSave o) {
		// TODO Auto-generated method stub
		return Integer.compare(o.getPontos(), this.pontos);
	}
	
	/**
	 * Verifica o objeto
	 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfilSave save = (PerfilSave) o;
        return pontos == save.pontos && Objects.equals(nome, save.nome);
    }
    /**
     * sla
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, pontos);
    }
	
}
