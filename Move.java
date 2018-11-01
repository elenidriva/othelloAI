
public class Move{
	
	private int row;
	private int col;
	private int val;

	//Constructors.. na dw ti tha xreiastw
	//gia tin arxi tou game mono
	public Move(){
		row=-1;
		col=-1;
		val=0;
	}
	public Move(int row, int col, int val){
		this.row=row;
		this.col=col;
		this.val=val;
	}
	
	//Setters + Getters.
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row=row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col=col;
	}
	
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int val) {
		this.val=val;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
