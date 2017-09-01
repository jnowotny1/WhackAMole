

import java.util.Random;
import java.util.Scanner;

public class WhackAMole {
	public int score;
	public int molesLeft;
	public int attemptsLeft;
	public char[][] moleGrid;
	
	// constructor
	public WhackAMole(int numAttempts, int gridDimension){
		attemptsLeft = numAttempts;
		score = 0;
		molesLeft = 0;
		moleGrid = new char[gridDimension][gridDimension];
		for (int j = 0; j < gridDimension; j++){
			for (int i = 0; i < gridDimension; i++){
				moleGrid[i][j] = '*';
			}
		}
	}
	
	// places a mole into moleGrid and updates molesLeft
	public boolean place(int x, int y){
		if (moleGrid[x][y] == '*'){
			moleGrid[x][y] = 'M';
			molesLeft++;
			return true;
		}
		return false;
	}
	
	// take a whack at x,y location
	public void whack(int x, int y){
		attemptsLeft--;
		if (moleGrid[x][y] == 'M'){
			moleGrid[x][y] = 'W';
			molesLeft--;
			score++;
			System.out.println("WHACK!");
		} else {
			moleGrid[x][y] = 'W';
			System.out.println("MISS!");
		}
	
	}
	
	// prints grid without showing the mole locations
	public void printGridToUser(){
		System.out.println("Game Grid");
		for (int j = 0; j < moleGrid.length; j++){
			for (int i = 0; i < moleGrid.length; i++){
				if (moleGrid[i][j] == 'M'){
					System.out.print(" * ");
				} else {
					System.out.print(" " + moleGrid[i][j] + " ");
				}
				if (i == moleGrid.length - 1){
					System.out.println("\n");
				}
			}
		}
	}
	
	// prints grid with mole locations
	public void printGrid(){
		System.out.println("Print Grid");
		for (int j = 0; j < moleGrid.length; j++){
			for (int i = 0; i < moleGrid.length; i++){
				System.out.print(" " + moleGrid[i][j] + " ");
				if (i == moleGrid.length - 1){
					System.out.println("\n");
				}
			}
		}
	}
	
	// main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Create new game
		WhackAMole newgame = new WhackAMole(50,10);
		
		// Place Moles in new game
		int numberOfMoles = 10;
		int xmole;
		int ymole;
		boolean placeResult;
		Random rand = new Random();
		for (int i = 0; i < numberOfMoles; i++){
			do {
				xmole = rand.nextInt(newgame.moleGrid.length);
			//	System.out.println(xmole);
				ymole = rand.nextInt(newgame.moleGrid.length);
			//	System.out.println(ymole);
				placeResult = newgame.place(xmole,ymole);
			//	System.out.println(placeResult);
			//	newgame.printGrid();
			} while (!placeResult);
		}
		
		// User Input
		int xInput;
		int yInput;
		Scanner userInput = new Scanner(System.in);
		System.out.println("Whack A Mole \n \n");
		while (newgame.attemptsLeft != 0 && newgame.molesLeft != 0){
			newgame.printGridToUser();
			System.out.println("There are " + newgame.molesLeft + " moles.");
			System.out.println("You have " + newgame.attemptsLeft + " tries.");
			do {
				System.out.println("Enter x coordinate:  ");
				xInput = userInput.nextInt();
			} while (xInput > 9 || xInput < -1);
			do {
				System.out.println("Enter y coordinate:  ");
				yInput = userInput.nextInt();
			} while (yInput > 9 || yInput < -1);
			if (xInput == -1 || yInput == -1){
				newgame.attemptsLeft = 0;
			} else {
				newgame.whack(xInput, yInput);
			}
		}
		newgame.printGrid();
		System.out.println("Score = " + newgame.score);
		if (newgame.molesLeft == 0){
			System.out.println("YOU WIN");
		} else {
			System.out.println("TRY AGAIN");
		}
		userInput.close();
	}
}
