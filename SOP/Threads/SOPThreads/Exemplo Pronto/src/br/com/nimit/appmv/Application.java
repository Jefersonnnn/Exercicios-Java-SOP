package br.com.nimit.appmv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s;
		// TODO Auto-generated method stub
		if (args.length < 1) {
			s = new Scanner(System.in);
			
		} else {
			Path p = Paths.get(args[0]);
			if (Files.exists(p, LinkOption.NOFOLLOW_LINKS)) {
				try {
					s = new Scanner(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.err.println("Arquivo nao pode ser aberto.\n");
					return;
				}
			}
			else {
				System.err.println("Arquivo nao encontrado.\n");
				return;
			}
		}
		if(s.hasNext()) {
			execute_mv(s);
		}
	}

	private static void execute_mv(Scanner scan) {
		scan.useLocale(Locale.ENGLISH);
		boolean usar_threads = scan.nextBoolean();
		Matriz a = getMatriz(scan);
		Matriz b = getMatriz(scan);
		if (a.getCols() != b.getLins()) {
			System.err
					.println("A quantidade de colunas da primeira matriz deve ser igual a quantidade de linhas da segunda matriz\n");
		} else {
			long start = System.currentTimeMillis();
			Matriz c = usar_threads ? a.mult_theads(b) : a.mult(b);
			long end = System.currentTimeMillis();
			c.print(System.out);
			System.out.printf("Tempo gasto: %d milisec.\n",end-start);
		}
		scan.close();
	}

	private static Matriz getMatriz(Scanner scan) {
		Matriz m;
		int lins, cols, i, j;
		lins = scan.nextInt();
		cols = scan.nextInt();
		m = new Matriz(lins, cols);
		for (i = 0; i < lins; ++i) {
			for (j = 0; j < cols; ++j) {
				m.getA()[i][j] = scan.nextDouble();
			}
		}
		return m;
	}
}
