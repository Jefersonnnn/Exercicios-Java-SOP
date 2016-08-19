package br.com.nimit.appmv;
import java.io.PrintStream;

public class Matriz {
	private double[][] a;
	private int lins, cols;
	
	public double[][] getA() {
		return a;
	}

	public int getLins() {
		return lins;
	}

	public int getCols() {
		return cols;
	}

	public Matriz(int lins, int cols)
	{
		this.lins = lins;
		this.cols = cols;
		this.a = new double[lins][cols];
	}
	
	public Matriz mult(Matriz outra)
	{
		Matriz m = new Matriz(this.lins,outra.cols);
		int i;
		for(i = 0; i < m.lins; i++) {
			mult_linha(m, outra, i);
		}
		return m;
	}
	
	public Matriz mult_theads(final Matriz outra) {
		final Matriz m = new Matriz(this.lins, outra.cols);
		class ParamThread implements Runnable {
			private int linha;
			public ParamThread(int linha) {
				this.linha = linha;
			}
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mult_linha(m, outra, linha);
			}
		}
		int i;
		Thread[] ts = new Thread[m.lins];
		for (i = 0; i < m.lins; i++) {
			Runnable r = new ParamThread(i);
			ts[i] = new Thread(r);
			ts[i].start();
		}
		try {
			for (i = 0; i < m.lins; i++) {
				ts[i].join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	
	private void mult_linha(Matriz m, Matriz outra, int i)
	{
		int j, k;
		for(j = 0; j < m.cols; j++) {
			for(k = 0; k < outra.lins; ++k) {
				m.a[i][j] += (this.a[i][k] * outra.a[k][j]);
			}
		}
	}
	
	public void print(PrintStream out)
	{
		out.printf("%dx%d\n", lins, cols);
		int i, j;
		for(i = 0; i < lins; ++i) { 
			for(j = 0; j < cols; ++j) {
				out.printf("%6.2f ", a[i][j]);
			}
			out.println();
		}	
	}
}
