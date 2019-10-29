import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPane extends JPanel 
{
public int squareSize=50;
public int[][] board;

public DrawPane(int [][] b) 
{
	board=b;
}

	public void paintComponent(Graphics g) 
	{
		g.fillRect(20, 20, 8*squareSize, 8*squareSize); // Draw on g here e.g.


		for(int row=0;row<8;row++) 
		{
			for(int col=0;col<8;col++) 
			{
				if(board[row][col]==1) 
				{
					g.setColor(Color.cyan);
				}
				else if(board[row][col]==2) 
				{
					g.setColor(Color.PINK);
				}
				else 
				{
					g.setColor(Color.GREEN);
				}
				g.fillOval(20+col*squareSize, 20+row*squareSize, squareSize, squareSize);
			}
		}
	}
}