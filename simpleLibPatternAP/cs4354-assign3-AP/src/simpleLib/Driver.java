package simpleLib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import simpleLib.LibSystem.MenuState;

public class Driver {

	protected static Scanner sc = new Scanner(System.in);
	static LibSystem libSystem;

	public static void main(String[] args) {
		try {
			libSystem = LoadSerialzedData();
			libSystem.setMenu(MenuState.MAIN_MENU);
		} catch (IOException e) {
			System.err.println("Could not find seralized data to restore from or invalid data found. Loading data from text...");
			libSystem = LibSystem.getInstance();
			libSystem.LoadDataFromText();
		}

		

		int choice = 0;
		boolean exitProgram = false;
		do {
			
			libSystem.getMenu().printMenu();
			try {
				choice = sc.nextInt();
				sc.nextLine();
				
				exitProgram = libSystem.getMenu().parseInput(choice);
			} catch (InputMismatchException ex) {
				System.err.println("Input missmatch. Please Try again." + ex);
				sc.nextLine(); // clear the new line character from the input
			} catch (UnsupportedOperationException ex) {
				ex.printStackTrace(); // For debugging purposes only.
			} catch (Exception ex) {
				System.err.println("An unknown error has occured." + ex);
			}

		} while (!exitProgram);

		try {
			SerializeData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return Data loaded from data.ser serialized file
	 * @throws IOException
	 */
	private static LibSystem LoadSerialzedData() throws IOException {
		LibSystem loadedData = null;
		try {
			FileInputStream fis = new FileInputStream("data.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			loadedData = (LibSystem) ois.readObject();
			ois.close();
			fis.close();

		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
		return loadedData;

	}

	private static void SerializeData() throws Exception {

		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(libSystem);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in data.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
