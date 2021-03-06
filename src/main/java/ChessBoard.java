import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
	private OutputStream stream;
	private List<Knight> knights;
	
	public ChessBoard(OutputStream stream) {
		this.stream = stream;
		this.knights = new ArrayList<>();
	}
	
	public void addPiece(Knight knight, char x, int y) {
		knight.x = x;
		knight.y = y;
		knights.add(knight);
	}
	
	public void movePiece(Knight knight, char x, int y) throws IOException {
		if (validMove(knight, x, y)) {
			knight.x = x;
			knight.y = y;
		} else {
			stream.write("Move to location failed.\n".getBytes());
		}
	}
	
	
	
	private boolean validMove(Knight knight, char x, int y) {
		return onBoard(x, y) && !occupied(knight.color, x, y) && knight.validMove(x, y);
	}
	
	private boolean onBoard(char x, int y) {
		boolean inCol = x >= 'a' && x <= 'h';
		boolean inRow = y >= 1 && y <= 8;
		
		return inCol && inRow;
	}
	
	private boolean occupied(String color, char x, int y) {
		for (Knight k : knights) {
			if (k.x == x && k.y == y) {
				return k.color.equals(color);
			}
		}
		return false;
	}
}
