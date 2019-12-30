package hu.kg.main;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class StringLoadUtil {

	public static String load(String file) throws IOException {
		int len;
		final char[] chr = new char[4096];
		final StringBuffer buffer = new StringBuffer();
		final FileReader reader = new FileReader(file);
		try {
			while ((len = reader.read(chr)) > 0) {
				buffer.append(chr, 0, len);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	public static String loadResource(String filename) {
		InputStream x = Util.class.getClassLoader().getResourceAsStream(filename);
		Scanner in=new Scanner(x,"UTF-8");
		in.useDelimiter("\\A");
		String inputStreamString = in.next();
		in.close();
		return inputStreamString;
	}

}
