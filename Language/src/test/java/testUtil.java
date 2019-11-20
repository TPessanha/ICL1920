import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testUtil {
	public static List<TypedResult> getResultCheck(InputStream in) {
		Scanner reader = new Scanner(in);
		ArrayList<TypedResult> results = new ArrayList<>();
		String[] check = reader.nextLine().substring(2).split(",");
		for (String r : check) {
			results.add(new TypedResult(r));
		}
		return results;
	}
}
