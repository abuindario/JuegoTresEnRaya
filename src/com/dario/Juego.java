package com.dario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
	String[] tablero;
	String representarTablero;
	String posicionesTablero = "-------\n" + "|1|2|3|\n" + "-------\n" + "|4|5|6|\n" + "-------\n" + "|7|8|9|\n"
			+ "-------\n";
	static int turnos;
	static boolean terminado = false;
	static String ganador = "";

	public static void main(String[] args) {
		Juego j = new Juego();
		// Array de posiciones de las fichas en el tablero - inicial vacio
		j.tablero = new String[] { " ", " ", " ", " ", " ", " ", " ", " ", " " };
		System.out.println("¡Bienvenidos al juego del 3 en raya!");

		// Registro del Jugador 1
		Scanner sc = new Scanner(System.in);
		String nombre = "";
		do {
			System.out.println("Introduce el nombre del Jugador 1: ");
			nombre = sc.nextLine();
		} while (nombre.trim() == "");
		Jugador j1 = new Jugador(nombre, "X");
		System.out.println(j1.getNombre() + ", eres la ficha " + j1.getFicha());
		
		// Registro del Jugador 2
		do {
			System.out.println("Introduce el nombre del Jugador 2: ");
			nombre = sc.nextLine();
		} while (nombre.trim() == "");
		Jugador j2 = new Jugador(nombre, "O");
		System.out.println(j2.getNombre() + ", eres la ficha " + j2.getFicha());

		// Ejecutar turnos de cada jugador
		Juego: for (turnos = 9; turnos > 0; turnos--) {
			if (!(turnos % 2 == 0)) {
				j.turno(j, j1);
			} else {
				j.turno(j, j2);
			}
			
			// Leer y mostrar las posiciones de fichas en el tablero
			j.representarTablero = "-------\n" + "|" + j.tablero[0] + "|" + j.tablero[1] + "|" + j.tablero[2] + "|\n"
					+ "-------\n" + "|" + j.tablero[3] + "|" + j.tablero[4] + "|" + j.tablero[5] + "|\n" + "-------\n"
					+ "|" + j.tablero[6] + "|" + j.tablero[7] + "|" + j.tablero[8] + "|\n" + "-------\n";
			System.out.println(j.representarTablero);
			j.comprobarCombinaciones(j);
			if (terminado)
				break Juego;
		}

		if (ganador.equals(j1.getFicha())) {
			System.out.println("¡" + j1.getNombre() + " ha ganado la partida!");
		} else if (ganador.equals(j2.getFicha())) {
			System.out.println("¡" + j2.getNombre() + " ha ganado la partida!");
		} else {
			System.out.println("¡La partida ha terminado en empate!");
		}
	}

	public void turno(Juego j, Jugador jug) {
		boolean finTurno = true;
		int posicionFicha = 99;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println(j.posicionesTablero);
			System.out.println(jug.getNombre() + ", ¿donde quieres colocar la ficha?");
			
			// Comprobar que solo se introduzcan datos de tipo int
			try {
				posicionFicha = scan.nextInt();
				finTurno = true;
				// Comprobar que el int introducido se comprenda entre 1 y 9
				try {
					// Comprobar que la casilla no este ya ocupada
					if (j.tablero[posicionFicha - 1].trim().equals("")) {
						j.tablero[posicionFicha - 1] = jug.getFicha();
						finTurno = true;
					} else {
						finTurno = false;
						System.out.println("Esta casilla ya se encuentra ocupada");
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					finTurno = false;
					System.out.println("Debes introducir un numero entre el 1 y el 9");
				}
			} catch (InputMismatchException e) {
				finTurno = false;
				System.out.println("No has introducido un numero, el caracter introducido no es valido");
			}
		} while (!finTurno);
	}

	// Combinaciones ganadoras que terminan la partida
	public void comprobarCombinaciones(Juego j) {
		if (j.tablero[0].equals(j.tablero[1]) && j.tablero[0].equals(j.tablero[2]) && j.tablero[0].trim() != "") {
			terminado = true;
			ganador = j.tablero[0];
		} else if (j.tablero[0].equals(j.tablero[4]) && j.tablero[0].equals(j.tablero[8])
				&& j.tablero[0].trim() != "") {
			terminado = true;
			ganador = j.tablero[0];
		} else if (j.tablero[0].equals(j.tablero[3]) && j.tablero[0].equals(j.tablero[6])
				&& j.tablero[0].trim() != "") {
			terminado = true;
			ganador = j.tablero[0];
		} else if (j.tablero[1].equals(j.tablero[4]) && j.tablero[1].equals(j.tablero[7])
				&& j.tablero[1].trim() != "") {
			terminado = true;
			ganador = j.tablero[2];
		} else if (j.tablero[2].equals(j.tablero[4]) && j.tablero[2].equals(j.tablero[6])
				&& j.tablero[2].trim() != "") {
			terminado = true;
			ganador = j.tablero[2];
		} else if (j.tablero[2].equals(j.tablero[5]) && j.tablero[2].equals(j.tablero[8])
				&& j.tablero[2].trim() != "") {
			terminado = true;
			ganador = j.tablero[2];
		} else if (j.tablero[3].equals(j.tablero[4]) && j.tablero[3].equals(j.tablero[5])
				&& j.tablero[3].trim() != "") {
			terminado = true;
			ganador = j.tablero[3];
		} else if (j.tablero[6].equals(j.tablero[7]) && j.tablero[6].equals(j.tablero[8])
				&& j.tablero[6].trim() != "") {
			terminado = true;
			ganador = j.tablero[6];
		} else {
			terminado = false;
			ganador = "empate";
		}
	}

}

class Jugador {
	private String nombre;
	private String ficha;

	public Jugador(String nombre, String ficha) {
		this.nombre = nombre;
		this.ficha = ficha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFicha() {
		return ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

}
