package controller;

import java.util.concurrent.Semaphore;

public class CompraController extends Thread {

	private Semaphore semaphore;
	private int idComprador;
	private static int qtdIngressos = 100;
	private int qtdIngressosSolicitados = 0;

	public CompraController(int idComprador, Semaphore semaphore) {
		this.idComprador = idComprador;
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		if (logar() && escolherIngresso()) {
			try {
				semaphore.acquire();
				comprarIngresso();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally  {
				semaphore.release();
			}
		}
	}

	private boolean logar() {
		int tempoDeLogon = (int) ((Math.random() * 2000) + 50);

		try {
			sleep(tempoDeLogon);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (tempoDeLogon > 1000) {
			System.err.println("Usuário #" + idComprador + " teve problemas no login: Time Out");
			return false;

		}

		return true;
	}

	private boolean escolherIngresso() {
		int tempoDeEscolha = (int) ((Math.random() * 3000) + 1000);

		try {
			sleep(tempoDeEscolha);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (tempoDeEscolha > 2500) {
			System.err.println("Usuário #" + idComprador + " teve problemas com a compra: Session Expired");
			return false;
		}

		return true;

	}

	private boolean comprarIngresso() {
		qtdIngressosSolicitados = (int) ((Math.random() * 4) + 1);

		if (qtdIngressosSolicitados > qtdIngressos) {
			System.err.println("Caro usuário #" + idComprador + ", a quantidade de ingressos solicitadas ("
					+ qtdIngressosSolicitados + ") não estão disponíveis para compra.");
			return false;
		}

		qtdIngressos -= qtdIngressosSolicitados;
		System.out.println("Usuário #" + idComprador + " comprou " + qtdIngressosSolicitados + ". [" + qtdIngressos
				+ " ingressos disponíveis].");

		return true;
	}

}
