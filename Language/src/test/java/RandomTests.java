import types.BooleanType;
import types.FunctionType;
import types.IType;
import types.IntType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTests {

	public static void main(String[] args) throws Exception {

//		boolean x = true;
//		boolean x2 = false;
//		boolean t = x && x2;

		List<IType> ll = new ArrayList<>();
		List<IType> l2 = new ArrayList<>();

		ll.add(BooleanType.value);
		ll.add(IntType.value);
		ll.add(BooleanType.value);

		l2.add(BooleanType.value);
		l2.add(IntType.value);
		l2.add(IntType.value);

		FunctionType fType = new FunctionType(BooleanType.value,ll);
		FunctionType fType2 = new FunctionType(BooleanType.value,l2);

		List<IType> ll1 = new ArrayList<>();
		List<IType> l22 = new ArrayList<>();

		ll1.add(fType);


		FunctionType lfType = new FunctionType(BooleanType.value, ll1);
		l22.add(lfType);
		FunctionType lfType2 = new FunctionType(lfType, l22);

		System.out.printf(lfType2.toString());


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
