import java.util.*;
import java.io.*;

public class Sum{

	public static void main(String[] args) throws Exception{

		Scanner input = new Scanner(new File("sum_sample.in"));

		int lines = input.nextInt();

		int x = 0, y = 0;
		for(int i = 0; i < lines; i++){
	
			x = input.nextInt();
			y = input.nextInt();

			System.out.println("Case #" + (i + 1));
			System.out.println(x + y);
		}


	}

}
