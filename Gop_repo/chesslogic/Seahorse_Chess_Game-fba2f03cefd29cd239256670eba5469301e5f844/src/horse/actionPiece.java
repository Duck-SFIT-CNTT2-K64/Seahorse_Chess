package horse;

public class actionPiece {
	private int idHorse; // định danh
	private String color; // đỏ, xanh lá cây, xanh nước biển, vàng
	private int pos; // 0 là chuồng, 1-56 là trên đường đi
	private boolean isFinished; // trạng thái về đích
	private boolean[] isWinning = {false, false, false, false, false, false};
	private boolean isWinner = false;
	
	public actionPiece(int id, String color) {
		this.idHorse = id;
		this.color = color;
		this.pos = 0; // o chuong
		this.isFinished = false;
	}
	
	public int getId() {
		return idHorse;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getPos() {
		return pos;
	}
	
	public boolean isGoHome() {
		return isFinished;
	}
	
	public void moveHorse(int stepsByDice) {
		if(isFinished != false) {
			pos += stepsByDice;
			if(pos > 62) {
				System.out.println("Khong the di duoc");
			}
			else {
				if(pos == 62 && isWinning[5] != true) {
					isWinning[5] = true;
					isFinished = true;
				}
				else if(pos == 61 && isWinning[4] != true) {
					isWinning[4] = true;
					isFinished = true;
				}
				else if(pos == 60 && isWinning[3] != true) {
					isWinning[3] = true;
					isFinished = true;
				}
				else if(pos == 59 && isWinning[2] != true) {
					isWinning[2] = true;
					isFinished = true;
				}
				else if(isWinning[5] == true && isWinning[4] == true && isWinning[3] == true && isWinning[2] == true) {
					isWinner = true;
				}
			}
		}
	}
	
	public void reset() {
		pos = 0;
		isFinished = false;
	}
}