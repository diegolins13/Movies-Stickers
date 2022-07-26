import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	//private static final String STAR = "★";
	
    public static void main(String[] args) throws Exception {
        
    	//fazer uma conexão HTTP e buscar os tops 250 filmes.
    	String url = "https://alura-filmes.herokuapp.com/conteudos"; //end point da API
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient(); 
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		//System.out.println(body);
		
		//extrair só os dados que interessam (titulo, poster, classificação)
		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes	= parser.parse(body); 	
		
		//exibir e manipular os dados
		var gerador = new GeradorDeFigurinhas();
		for (Map<String, String> filme : listaDeFilmes) {
			
			String titulo = filme.get("title");
			String urlImagem = filme.get("image");
	        int CorteUrl = urlImagem.indexOf("_");
	        String resultado = urlImagem.substring(CorteUrl);
	        
	        
			String urlImagemEdit = urlImagem.replaceAll(resultado, "");
			
			InputStream inputStream = new URL(urlImagemEdit + ".jpg").openStream();
			String nomeArquivo = titulo + ".png";
			
			gerador.cria(inputStream, nomeArquivo);
			
			System.out.println(titulo);
			//System.out.println("Poster: " + filme.get("image"));
			//System.out.println("Classificação:");
			//String classificacao = filme.get("imDbRating");
			//double convertDouble = Double.parseDouble(classificacao);
			//Math.round(convertDouble);
			//for (int i=0; i < convertDouble; i++) {
				//System.out.print(STAR);
			//}
			//System.out.println(" " + filme.get("imDbRating"));
			System.out.println();
		}
    }
}