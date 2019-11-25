import java.util.Random;

public class RandomTests {

	public static void main(String[] args) throws Exception {

//		boolean x = true;
//		boolean x2 = false;
//		boolean t = x && x2;

		boolean x = getRandomBoolean() && getRandomBoolean();


		System.out.println(x);


//        Environment<Value> scope = new Environment();
//        scope.declareVariable("test", new Int(5));
//
//        for (int i = 0; i < 50; i++) {
//            scope = scope.beginScope();
//        }
//
//        scope.declareVariable("test", new Int(5));
//
//        long startTime;
//        long stopTime;
//        long elapsedTime;
//
//        startTime = System.currentTimeMillis();
//
//        for (int i = 0; i < 10000000; i++) {
//            scope.assignVariable("test", new Int(5));
//        }
//
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("First");
//        System.out.println(elapsedTime);

		//2nd
//
	}

	static boolean getRandomBoolean()
	{
		Random random = new Random();
		if (random.nextBoolean())
			return true;
		if (random.nextBoolean())
			return false;
		return true;
	}
}
