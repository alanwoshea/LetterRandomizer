
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

class Tile { 
	private int col; 
	private int row; 
	private int width; 
	private int height; 
	private int shapeType; 
	private char shapeLetter;
	private Color shapeColor;
	private Color letterColor;
	private int r, g, b; 
	
	public int getCol() { 
		return col; 
	}
	public void setCol(int col) { 
		this.col = col; 
	}
	public int getRow() { 
		return row;
	}
	public void setRow(int row) { 
		this.row = row; 
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) { 
		this.width = width;
	}
	public int getHeight() { 
		return height;
	}
	public void setHeight(int height) { 
		this.height = height; 
	}
	public int getShapeType() { 				//For shapeType, 0 will represent circle, and 1 will represent square
		return shapeType;
	}
	public void setShapeType(int shapeType) {
		if ( 0<= shapeType && shapeType <= 1) {
			this.shapeType = shapeType;
		}else { 
			System.out.println("Invalid shape type");
		}
	}
	public char getShapeLetter() { 
		return shapeLetter; 
	}
	public void setShapeLetter(int l) { 
		shapeLetter = (char)(l); 
	}
	public Color getShapeColor() { 
		return shapeColor;
	}
	public Color getLetterColor() { 
		return letterColor; 
	}
	public void setColor(Color shapeColor, Color letterColor) { 
		this.shapeColor = shapeColor;
		this.letterColor = letterColor;
	}
	public void setColor(int r, int g, int b) { 
		shapeColor = new Color(r, g, b); 
		this.r = rgbContrastor(r);
		this.g = rgbContrastor(g); 
		this.b = rgbContrastor(b); 
		letterColor = new Color(this.r, this.g, this.b); 
	}

	public int rgbContrastor(int x) { 
		if(x <= 127) { 
			x += 128; 
		}else if (x >= 128) { 
			x = 255; 
		}
		return x; 
	}  
	public Tile() { 
		setCol(0); 
		setRow(0); 
		setWidth(0);
		setHeight(0);
		setShapeType(0); 
		setShapeLetter(65); //
		setColor(Color.BLACK, Color.WHITE); 
	}
	public Tile(int col, int row, int width, int height, int shapeType, int shapeLetter, Color scol, Color lcol) { 
		setCol(col);
		setRow(row);
		setWidth(width);
		setHeight(height);
		setShapeType(shapeType); 
		setShapeLetter(shapeLetter);
		setColor(shapeColor, letterColor);
	}
	public Tile(int col, int row, int width, int height, int shapeType, int shapeLetter, int r, int g, int b) { 
		setCol(col);
		setRow(row);
		setWidth(width);
		setHeight(height);
		setShapeType(shapeType); 
		setShapeLetter(shapeLetter);
		setColor(r,g,b);
	}
	public String getColorAsString() { 
		return String.format("%d %d %d", shapeColor.getRed(), shapeColor.getGreen(), shapeColor.getBlue()); 
	}
	public String toString() { 
		return String.format("%d %d %d %d %s %s", getCol(), getRow(), getWidth(), getHeight(), getShapeType(), getShapeLetter(), getColorAsString()); 
	}
}
class TilePrinter { 
	private ArrayList<Tile> tiles;
	private Tile tile;
	public void print() { 
		for(Tile tile : tiles) {
			System.out.println(tile); 
		}
	}
	public TilePrinter(ArrayList<Tile> tiles) {  //add print function
		this.tiles = tiles; 
		print();
	}
}
class TileRandomizer extends Tile { 
	private Tile tile; 
	private ArrayList<Tile> tiles;
	private Random rnd = new Random(); 
	private TilePrinter tprint;
	private int col, row, height, width, shapeType, shapeLetter, r, g, b; 
	
	public void buildTile() { 
		this.tile = tile; 
		shapeType = rnd.nextInt(2);
		shapeLetter = 65 + rnd.nextInt(26); 
		r = rnd.nextInt(256); 
		g = rnd.nextInt(256);
		b = rnd.nextInt(256);
		tile.setShapeType(shapeType); 
		tile.setShapeLetter(shapeLetter);
		tile.setColor(r,g,b); 
 
	
	}
	public void changeTile() { 
		this.tile = tile; 
		shapeType = rnd.nextInt(2); 
		tile.setShapeType(shapeType); 
		shapeLetter = 65 + rnd.nextInt(26);
		r = rnd.nextInt(256); 
		g = rnd.nextInt(256);
		b = rnd.nextInt(256);
		tile.setColor(r,g,b); 		
	}
	public void changeTiles() { 
		this.tiles = tiles; 
		for (Tile tile : tiles) { 
			this.tile = tile; 
			shapeType = rnd.nextInt(2); 
			tile.setShapeType(shapeType); 
			shapeLetter = 65 + rnd.nextInt(26);
			tile.setShapeLetter(shapeLetter);
			r = rnd.nextInt(256); 
			g = rnd.nextInt(256);
			b = rnd.nextInt(256);
			tile.setColor(r,g,b); 
		}
		tprint = new TilePrinter(tiles);
	}
	public Tile getTile() { 
		return tile; 
	}
	public ArrayList<Tile> getTiles() { 
		return tiles; 
	}
	public TileRandomizer () { 
		tile = new Tile(); 
	}
	public TileRandomizer(Tile tile) { 
		this.tile = tile; 
	}
	public TileRandomizer(ArrayList<Tile> tiles) { 
		this.tiles = tiles; 
	}
	
}

class ButtonHandler implements ActionListener { 
	private ArrayList<Tile> tiles; 
	private TileRandomizer trand;
	private TileFrame tframe;
	private Random rnd; 

	public void randomizeTiles() { 
		trand.changeTiles();
		trand.getTiles(); 
	}
	public ButtonHandler(ArrayList<Tile> tiles, TileFrame tf) { 
		this.tiles = tiles;
		tframe = tf; 
		rnd = new Random();
		trand = new TileRandomizer(tiles); 
	}
	public void actionPerformed(ActionEvent e) { 
		randomizeTiles();
		tframe.repaint(); 
	}
}
class TilePanel extends JPanel { 
	private ArrayList<Tile> tiles; 
	private Tile tile; 
	private int letterX;
	private int letterY;
	private int letterWidth;
	private int letterAscent;
	private String shapeLetter;
	public TilePanel(ArrayList<Tile> tiles) { 
		this.tiles = tiles;
	}
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		Font f = new Font("sans serif", Font.BOLD, 26); 
		g.setFont(f);
		for (Tile tl : tiles) { 
			g.setColor(tl.getShapeColor()); 	
			if (tl.getShapeType() == 0) { 
				g.fillOval(tl.getCol(), tl.getRow(), tl.getWidth(), tl.getHeight());
			} else { 
				g.fillRect(tl.getCol(), tl.getRow(), tl.getWidth(), tl.getHeight());
			}
			g.setColor(tl.getLetterColor()); 	
			FontMetrics fm = g.getFontMetrics(); 
			shapeLetter = String.valueOf(tl.getShapeLetter());
			letterWidth = fm.stringWidth(shapeLetter)/2; 
			letterAscent = 2 + fm.getMaxAscent() + fm.getDescent(); 
			
			letterX = tl.getCol() + tl.getWidth()/2 - letterWidth;  
			letterY = tl.getRow() + letterAscent;
			g.drawString(shapeLetter, letterX, letterY);
		}
		
	}
}
class TileFrame extends JFrame { 
	private ArrayList<Tile> tiles; 
	private Random rnd; 
	
	public TileFrame(ArrayList<Tile> tiles) {
		rnd = new Random(); 
		setTitle("Tiles");
		setBounds(100, 100, 516, 575
		);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container c = getContentPane(); 
		c.setLayout(new BorderLayout()); 
		JPanel buttonPan = new JPanel(); 
		JButton btnRando = new JButton("Randomize!"); 
		ButtonHandler bh = new ButtonHandler(tiles, this); 
		btnRando.addActionListener(bh);
		buttonPan.add(btnRando); 
		c.add(buttonPan, BorderLayout.SOUTH);
		TilePanel tpan = new TilePanel(tiles); 
		c.add(tpan, BorderLayout.CENTER);
		this.tiles = tiles; 
	}
}
public class Tiles_OShea { 
	public static void main(String[] args) { 
		ArrayList<Tile> tiles = new ArrayList<Tile>(); 
		int col, row, width, height;
		Tile tl; 
		TileRandomizer tileRandizo;
		for (int i = 0; i < 100; i++) {
			col = i/10;
			row = i%10; 
			width = 50; 
			height = 50; 
			tl = new Tile(); 
			tl.setCol(col *50);
			tl.setRow(row *50); 
			tl.setWidth(width);
			tl.setHeight(height);
			tileRandizo = new TileRandomizer(tl);
			tileRandizo.buildTile();
			tl = tileRandizo.getTile(); 
			tiles.add(tl);
		}
		TileFrame tf = new TileFrame(tiles); 
		tf.setVisible(true); 
		TilePrinter tilePrint = new TilePrinter(tiles); 
	}
}
	