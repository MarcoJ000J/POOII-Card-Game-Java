package save;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * classe responsalvel pelo save, escrever e ler o arquivo
 */
public class GerenciadorSave {
	private static final String nomeArquivo = "scr\\main\\resources\\saves\\savePlacar.json";
	private static final int maxSaves = 10;

	private Gson gson;

	// uma lista com os perfil ja salvos e os que serao salvos
	private List<PerfilSave> saves;

	// temporario para gerenciar o perfil recebido
	private PerfilSave temp;
	
	/**
	 * Construtor apenas para ler o arquivo!
	 */
    public GerenciadorSave() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.saves = new ArrayList<PerfilSave>();
        load(); // Apenas carrega os dados existentes
    }

	/**
	 * constrututor que recebe o perfil para salvalo no arquivo
	 */
	public GerenciadorSave(PerfilSave perfilASerSalvo) {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.saves = new ArrayList<PerfilSave>();

		this.temp = perfilASerSalvo;
		
		// Chamada dos métodos para carregar, adicionar e salvar
        load(); // Carrega os dados existentes
        addToSave(); // Adiciona o novo perfil e gerencia o limite
	}

	/**
	 * Carrega o aruivo se ele existe(incluindo o qesta nele) ou cria se nao
	 */
	private void load() {
		File arquivo = new File(nomeArquivo);

		if (arquivo.exists()) {
			try (FileReader reader = new FileReader(arquivo)) {
				Type listaSaves = new TypeToken<ArrayList<PerfilSave>>() {
				}.getType();
				List<PerfilSave> loadedSaves = gson.fromJson(reader, listaSaves);
				if (loadedSaves != null) {
					this.saves = loadedSaves;
					Collections.sort(this.saves); // Garante a ordenação ao carregar
				}
			} catch (IOException e) {
				System.err.println("Erro ao carregar jogadores do arquivo: " + e.getMessage());
			}
		}
	}

	/**
	 * adciona, ou nao, o save novo a lista
	 */
	private void addToSave() {
		if (saves.size() >= maxSaves) {

			// Verifica se o novo jogador tem pontuação maior que o último (menor pontuação)
			// Lembre-se que a lista está ordenada do maior para o menor
			if (temp.getPontos() > saves.get(maxSaves - 1).getPontos()) {
				saves.remove(maxSaves - 1); // Remove o jogador de menor pontuação
				saves.add(temp);
				Collections.sort(saves); // Reordena a lista
			}

		} else {
			saves.add(temp);
			Collections.sort(saves); // Reordena a lista
		}

		save(); // Salva após cada adição ou modificação

	}

	/**
	 * Realmente escreve a lista no arquivo
	 */
	private void save() {
		try (FileWriter writer = new FileWriter(nomeArquivo)) {
			gson.toJson(saves, writer);
		} catch (IOException e) {
			System.err.println("Erro ao salvar jogadores no arquivo: " + e.getMessage());
		}
	}

	/**
	 * Retorna a lista se precisar
	 */
	public List<PerfilSave> getListaSave() {
		return new ArrayList<PerfilSave>(saves);
	}
}
