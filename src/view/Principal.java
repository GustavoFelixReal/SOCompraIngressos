package view;

import java.util.concurrent.Semaphore;

import controller.CompraController;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		
		for (int idComprador = 1; idComprador <= 300; idComprador++) {
			CompraController compraController = new CompraController(idComprador, semaphore);
			compraController.start();
		}
		

	}

}
