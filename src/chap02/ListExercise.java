package chap02;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("太郎");
		names.add("ジロウ");
		names.add("SABUROU");
		
		for(String name : names) {
			System.out.println(name);
		}

	}

}
