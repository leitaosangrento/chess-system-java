package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static final String ANSI_RED = "\u001B[31m";
	
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<ChessPiece>();
		while (!chessMatch.getCheckMate()) {
			try {
			UI.clearScreen();
			UI.printMatch(chessMatch, captured);
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(read);
			
			boolean[][] possibleMoves = chessMatch.possibleMoves(source);
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces(), possibleMoves);
			System.out.println();
			
			System.out.print("target: ");
			ChessPosition target = UI.readChessPosition(read);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
			if (capturedPiece != null) {
				captured.add(capturedPiece);
				}
			}
			catch(ChessException e) {
				System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
				read.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
				read.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
