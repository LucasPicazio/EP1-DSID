package server;

import commons.Piece;

public class Test {
	public static void test() {
		String serverName = Server.name;
		Piece ram = new Piece("Memoria RAM","NONONO", serverName);
		Piece processor = new Piece("Processador","NONONO", serverName);
		Piece vga = new Piece("Placa de Video","NONONO", serverName);
		Piece nucleo = new Piece("Nucleo","NONONO", serverName);
		Piece registrador = new Piece("Registrador","NONONO", serverName);
		Piece motherBoard = new Piece("Placa Mae","NONONO", serverName);
		nucleo.addSubcomponent(registrador);
		nucleo.addSubcomponent(registrador);
		processor.addSubcomponent(nucleo);
		processor.addSubcomponent(nucleo);
		processor.addSubcomponent(nucleo);
		processor.addSubcomponent(nucleo);
		motherBoard.addSubcomponent(ram);
		motherBoard.addSubcomponent(ram);
		motherBoard.addSubcomponent(processor);
		motherBoard.addSubcomponent(vga);
		
		System.out.println("Nucleo Subcomponents = " + nucleo.countSubcomponents(0));
		System.out.println("Processador Subcomponents = " + processor.countSubcomponents(0));
		System.out.println("MotherBoard Subcomponents = " + motherBoard.countSubcomponents(0));
		System.out.println("FIM DO TESTE");
	}
}
