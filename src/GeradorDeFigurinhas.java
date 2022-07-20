
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

	public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
	// leitura da imagem
	//InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
	//InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@").openStream();
	BufferedImage imagemOriginal = ImageIO.read(inputStream);
	
	// cria nova imagem em memória com tranparencia e com tamanho novo
	int largura = imagemOriginal.getWidth();
	int altura = imagemOriginal.getHeight();
	int novaAltura = altura + 200;
	BufferedImage novaimagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
	
	// copiar a imagem original pra nova imagem (em memória)
	Graphics2D graphics = (Graphics2D) novaimagem.getGraphics();
	graphics.drawImage(imagemOriginal, 0, 0, null);
	
	// configurar a fonte
	var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
	graphics.setFont(fonte);
	graphics.setColor(Color.YELLOW);
	
	// escrever uma frase na nova imagem
	graphics.drawString("SURPREENDENTE", 50, novaAltura - 100); 
	
	// escrever a nova imagem em um arquivo
	if (!new File("saida/").exists()) {
		new File("saida/").mkdir();
	}
	ImageIO.write(novaimagem, "png", new File("saida/" + nomeArquivo));
	
	}
}
