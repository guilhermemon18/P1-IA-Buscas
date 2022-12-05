package classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Algoritmos  {
	private String verticeOrigem;
	private String verticeDestino;
	private Grafo<String> grafo;
	private List<Aresta<String>> heuristicas;

	//Realiza a leitura do arquivo e seta as configurações no construtor.
	public Algoritmos(String nomeArquivo) throws IOException {
		this.grafo = new Grafo<String>();
		this.heuristicas = new LinkedList<Aresta<String>>();
		BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"));
		String linha = buffRead.readLine();

		while(linha != null) {
			String aux[] = linha.split("\\(");
			String nome = aux[0].trim();
			aux = aux[1].split("\\)");
			String parenteses = aux[0].trim();


			if(nome.equalsIgnoreCase("h")) {
				String vertices[] = parenteses.split(",");
				String verticeV = vertices[0].trim();
				String verticeW = vertices[1].trim();
				Integer distancia = Integer.valueOf(vertices[2].trim());

				heuristicas.add(new Aresta<String>(verticeV,verticeW,distancia));
			}else if(nome.equalsIgnoreCase("ponte")) {
				String vertices[] = parenteses.split(",");
				String verticeV = vertices[0].trim();
				String verticeW = vertices[1].trim();
				Integer distancia = Integer.valueOf(vertices[2].trim());

				grafo.inserir(new Aresta<String>(verticeV,verticeW,distancia));
			}
			else if(nome.equalsIgnoreCase("ilha_inicial")) {
				verticeOrigem = parenteses;
			}
			else { //if(nome.equalsIgnoreCase("ilha_final")){
				verticeDestino = parenteses;
			}
			linha = buffRead.readLine();
		}

		buffRead.close();
		
		
		//System.out.println(grafo.getNroVertices());
	}
	
	//pior solução: Busca em profundidade
	public void piorSolucaoDFS() {
		//grafo.imprimirGrafo();
		List<String> l = grafo.DFS(this.verticeOrigem,this.verticeDestino);//retorna a lista de pontos visitados
		List<Aresta<String>> arestas = new LinkedList<Aresta<String>>();
		//forma as arestas que foram percorridas
		for (int i = 0; i < l.size() - 1; i++) {
			arestas.add(grafo.getAresta(l.get(i),l.get(i+1)));
		}
		//desenha o grafo com o percorrimento
		grafo.desenhaGrafo("piorSolucaoDFS", arestas);
	
	}
	
	
	
	public void melhorSolucaoAEstrela(){
//		f (n) = g (n) + h (n);
		
		//grafo.imprimirGrafo();
				List<String> l = grafo.AEstrela(this.verticeOrigem,this.verticeDestino,this.heuristicas);//retorna a lista de pontos visitados
				List<Aresta<String>> arestas = new LinkedList<Aresta<String>>();
				//forma as arestas que foram percorridas
				for (int i = 0; i < l.size() - 1; i++) {
					arestas.add(grafo.getAresta(l.get(i),l.get(i+1)));
				}
				//desenha o grafo com o percorrimento
				grafo.desenhaGrafo("Processado", arestas);
		
	}
	

	public static void main(String[] args) {
		String fileName;
		int option = 0;
		Scanner c = new Scanner(System.in);
		try {
			long tempoInicial = System.currentTimeMillis();
			new Algoritmos("./file/teste1.txt").piorSolucaoDFS();
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/1000)+ " segundos");
			tempoInicial = System.currentTimeMillis();
			new Algoritmos("./file/teste1.txt").melhorSolucaoAEstrela();
			System.out.println("tempo:"+( (float) (System.currentTimeMillis() - tempoInicial)/1000)+ " segundos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
}



