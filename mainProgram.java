import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainProgram 
{
	public	JFrame frame;
	public  int squareSize=50;
	public int[][] board;
	public mainProgram() throws NumberFormatException, IOException 
	{
		frame = new JFrame("Connect four");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.setVisible(true);
		board = new int[8][8];
		//board[0][3]=1;
		JPanel dis = new DrawPane(board);
		frame.add(dis);
		//board[4][3]=1;
		int playerTurn=1;
		//System.out.print((GameHasEnded())); 

		while(!GameHasEnded())
		{
			getInput(playerTurn);
			playerTurn=switchPlayer(playerTurn);
			AIMove(playerTurn);
			playerTurn=switchPlayer(playerTurn);
		}
		System.out.print(switchPlayer(playerTurn)+"has won");
		//printBoard();
	}

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		System.out.print("test");
		mainProgram m = new mainProgram();
	}
	public void getInput(int player) throws NumberFormatException, IOException 
	{
		System.out.print("player "+player+ " move:\n");
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in) ));
		int suggestedMove=Integer.parseInt(br.readLine());
		int BoardMove=suggestedMove-1;
		if(isColumnEmpty(BoardMove)) 
		{
			//	System.out.print(isColumnEmpty(BoardMove));
			addMove(BoardMove, player);
		}
	}
	public int switchPlayer(int player) 
	{
		if(player==1) 
		{
			return 2;
		}
		else 
		{
			return 1;
		}
	}
	public boolean isColumnEmpty(int col) 
	{
		if(board[0][col]==0)return true;
		return false;
	}
	public void addMove(int col,int player) 
	{
		for(int i=0;i<8;i++) 
		{
			if(board[i][col]!=0) 
			{
				board[i-1][col]=player;
				break;
			}	
			if(i==7)board[7][col]=player;
		}	
		//Thread.sleep(5000);
	}
	public void removeMove(int col) 
	{
		for(int i=0;i<8;i++) 
		{
			if(board[i][col]!=0) 
			{
				board[i][col]=0;
				break;
			}	
		}
	}
	public int AIMove(int AINumber) 
	{
		for(int i=0;i<8;i++) 
		{
			addMove(i, AINumber);
			if(GameHasEnded()) 
			{
				return i;
			}
			removeMove(i);
		}
		for(int i=0;i<8;i++) 
		{
			addMove(i, switchPlayer(AINumber));
			if(GameHasEnded()) 
			{
				removeMove(i);
				addMove(i, AINumber);
				return i;
			}
			removeMove(i);
		}
		int randomMove= (int)(Math.random()*8);
		addMove(randomMove, AINumber);
		return randomMove;
	}
	public void printBoard() 
	{
		for(int i=0;i<8;i++) 
		{
			for(int j=0;j<8;j++) 
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	public boolean GameHasEnded() 
	{
		boolean draw=true;
		for(int i=0;i<8;i++) 
		{
			if(board[0][i]==0) 
			{
				draw=false;
				break;
			}
		}
		//System.out.print(draw);
		if(draw||checkFour()) 
		{
			return true;
		}
		return false;
	}

	public boolean checkFour() 
	{
		//	System.out.print("diagon"+CheckDiagonal());
		if(CheckDiagonal()) 
		{
			return true;
		}

		if(CheckHoriz()) 
		{
			return true;
		}
		if(CheckVertic()) 
		{
			return true;
		}
		return false;
	}

	private boolean CheckVertic() {
		// TODO Auto-generated method stub
		for(int col=0;col<8;col++) 
		{
			for(int row=0;row<5;row++) 
			{
				if
				(
						board[row][col]==board[row+1][col] &&
						board[row][col]==board[row+2][col] &&
						board[row][col]==board[row+3][col] &&
						board[row][col]!=0
						)
					return true;
			}
		}
		return false;
	}

	private boolean CheckHoriz() {
		for(int col=0;col<5;col++) 
		{
			for(int row=0;row<8;row++) 
			{
				if
				(
						board[row][col]==board[row][col+1] &&
						board[row][col]==board[row][col+2] &&
						board[row][col]==board[row][col+3] &&
						board[row][col]!=0
						)
					return true;
			}
		}
		return false;
	}

	private boolean CheckDiagonal() {
		for(int col=0;col<5;col++) 
		{
			for(int row=0;row<5;row++) 
			{
				if
				(
						board[row][col]==board[row+1][col+1] &&
						board[row][col]==board[row+2][col+2] &&
						board[row][col]==board[row+3][col+3] &&
						board[row][col]!=0
						) 
				{
					//	System.out.print("row:"+row+"col:"+col);
					return true;
				}
				if
				(
						board[row][col+3]==board[row+1][col+2] &&
						board[row][col+3]==board[row+2][col+1] &&
						board[row][col+3]==board[row+3][col] &&
						board[row][col+3]!=0
						)
				{
					//	System.out.print("row:"+row+"col:"+col);
					return true;
				}
			}
		}
		return false;
	}

}
