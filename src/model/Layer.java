package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Layer{
	public  int mapa[][];
	public  BufferedImage camada;
	private BufferedImage tileSet;
	private int mapWidth;
	private int mapHeight;
	private int tileWidth;
	private int tileHeight;
	private boolean terrain, collectable;
	private ArrayList<Collectable> collectablesColliders;
	private ArrayList<Rectangle> rectangles;

	public Layer(int mapaWidth, int mapaHeight, int tileWidth, int tileHeight, String img, String arquivo, boolean terrain, 
			boolean collectable, boolean enemy) {
		this.mapWidth =mapaWidth;
		this.mapHeight =mapaHeight;
		this.tileWidth=tileWidth;
		this.tileHeight=tileHeight;
		this.terrain = terrain;
		this.collectable = collectable;

		collectablesColliders = new ArrayList<Collectable>();
		rectangles = new ArrayList<Rectangle>();
		
		mapa = new int[mapaWidth][mapaHeight];
		mapa = carregaMatriz(mapa, arquivo);
		try {
			tileSet = ImageIO.read(new File(img));
		} catch (IOException e) {
			System.out.println("Erro ao tileSet, encerrando aplicação");
			System.exit(0);
		}
	}

	public int[][] carregaMatriz(int[][] matz, String arquivo) {
		ArrayList<String> linhasMatrizCamada = new ArrayList<String>();
		InputStream is = getClass().getResourceAsStream (arquivo);
		BufferedReader br = new BufferedReader (new InputStreamReader (is)); 
		String linha="";
		try {

			while ((linha = br.readLine()) != null){
				linhasMatrizCamada.add(linha);
			}
			int j = 0;
			for (int i = 0; i < linhasMatrizCamada.size(); i++) {
				StringTokenizer token = new StringTokenizer(linhasMatrizCamada.get(i), ",");

				while (token.hasMoreElements()) {
					matz[i][j] = Integer.parseInt(token.nextToken());
					j++;
				}
				j = 0;
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("nao carregou arquivo mapa");
			System.exit(0);
		}
		catch (IOException ioException) {
			System.out.println("erro na leitura do mapa");
			System.exit(0);
		}
		return matz;
	}

	public void build(int lar, int alt) {

		camada = new BufferedImage(lar, alt, BufferedImage.TYPE_4BYTE_ABGR);
		camada.createGraphics();

		int tile;
		int tileRow;
		int tileCol;
		int tilesetColumns =tileSet.getWidth()/tileWidth;
		
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				if (mapa[i][j] != 0) {
					tile = mapa[i][j] - 1;
					
					if (collectable) {
						if (tile==3) {
							collectablesColliders.add(new Collectable(j*tileHeight-16, i*tileWidth-16,
									"src/img/Collectable/Cherries.png", 17));
						}
						
					}
					else if (tile+1 == 1 || tile+1 == 2 || tile+1==3 || tile+1==4 || tile+1==6) {
							rectangles.add(new Rectangle(j*tileHeight, i*tileWidth, tileWidth, tileHeight));
					}
					
					tileRow = (tile / (tilesetColumns));
					tileCol = (tile % (tilesetColumns));
					camada.getGraphics().drawImage(tileSet, (j * tileHeight), (i * tileWidth), (j * tileHeight) + tileHeight,
							(i * tileWidth) + tileWidth, (tileCol * tileHeight), (tileRow * tileWidth),
							(tileCol * tileHeight) + tileHeight, (tileRow * tileWidth) + tileWidth, null);
					
					
				}
			}
		}
		
		
	}

	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}

	public BufferedImage getCamada() {
		return camada;
	}

	public void setCamada(BufferedImage camada) {
		this.camada = camada;
	}

	public BufferedImage getTileSet() {
		return tileSet;
	}

	public void setTileSet(BufferedImage tileSet) {
		this.tileSet = tileSet;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public boolean isTerrain() {
		return terrain;
	}

	public void setTerrain(boolean terrain) {
		this.terrain = terrain;
	}

	public boolean isCollectable() {
		return collectable;
	}

	public void setCollectable(boolean collectable) {
		this.collectable = collectable;
	}

	public ArrayList<Collectable> getCollectablesColliders() {
		return collectablesColliders;
	}

	public void setCollectablesColliders(ArrayList<Collectable> collectablesColliders) {
		this.collectablesColliders = collectablesColliders;
	}

	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	public void setRectangles(ArrayList<Rectangle> rectangles) {
		this.rectangles = rectangles;
	}
}
