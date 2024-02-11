
public class A4Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;
	
	public static void main(String[] args) {
		testStackOperations();
		testStackIsGeneric();
		testStackedCorrectly();
		testInsertPlate();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void testStackOperations() {
		System.out.println("Stack Operations Tests:");
		A4Stack<Integer> testStack = new A4Stack<Integer>();
		int result = 0;
		
		displayResults(testStack.isEmpty(), "stack initially empty");
				
		testStack.push(2);
		result = testStack.top();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result==2, "top works after initial push");
		
		testStack.push(3);
		result = testStack.top();
		displayResults(result==3, "test with 3");
				
		System.out.println();
	}
	
	public static void testStackIsGeneric() {
		System.out.println("Stack Generics Tests:");
		A4Stack<Integer> s1 = new A4Stack<Integer>();
		A4Stack<String> s2 = new A4Stack<String>();
		A4Stack<Double> s3 = new A4Stack<Double>();
		
		int result1;
		String result2;
		double result3;
		
		s1.push(3);
		s1.push(8);
		s2.push("CSC");
		s2.push("ENGR");
		s3.push(5.5);
		s3.push(9.1);
		
		result1 = s1.pop();
		result2 = s2.pop();
		result3 = s3.pop();
		
		displayResults(result1==8, "Integer Stack");
		displayResults(result2.equals("ENGR"), "String Stack");
		displayResults(result3==9.1, "Double Stack");		
		
		result1 = s1.top();
		result2 = s2.top();
		result3 = s3.top();
		
		displayResults(result1==3, "Integer Stack");
		displayResults(result2.equals("CSC"), "String Stack");
		displayResults(result3==5.5, "Double Stack");
		
		//TODO: add more tests here
		
		System.out.println();
	}

	public static void testStackedCorrectly() {
		System.out.println("Testing stackedCorrectly");
		Plate p1 = new Plate(5);
		Plate p2 = new Plate(12);
		Plate p3 = new Plate(1);
		Plate p4 = new Plate(8);
		Plate p5 = new Plate(3);
		
		A4Stack<Plate> s1 = new A4Stack<Plate>();
		A4Stack<Plate> s2 = new A4Stack<Plate>();
		A4Stack<Plate> s3 = new A4Stack<Plate>();
		
		s1.push(p2);
		s1.push(p4);
		s1.push(p1);
		s1.push(p5);
		s1.push(p3);
		
		s2.push(p1);
		s2.push(p4);

		s3.push(p1);
		s3.push(p5);
		s3.push(p3);
		s3.push(p2);
		s3.push(p4);
		
		boolean result = false;
		System.out.println(s1.pop());
		result = A4Exercises.stackedCorrectly(s1);
		displayResults(result, "s1 stacked correctly "+result);
		
		result = A4Exercises.stackedCorrectly(s2);
		displayResults(!result, "s2 stacked correctly");
		
		//TODO: add more tests here
		result = A4Exercises.stackedCorrectly(s3); //do we have to add tests after changing the s3 using pop()
		displayResults(!result, "s3 stacked correctly "+result);

		System.out.println();
	}

	public static void testInsertPlate() {
		System.out.println("Testing insertPlate");
		Plate p1 = new Plate(5);
		Plate p2 = new Plate(12);
		Plate p3 = new Plate(1);
		Plate p4 = new Plate(8);
		Plate p5 = new Plate(3);
		Plate p6 = new Plate(13);
		
		Plate insert1 = new Plate(6);
		Plate insert2 = new Plate(11);
		Plate insert3 = new Plate(14);
		Plate insert4 = new Plate(9);
		Plate insert5 = new Plate(4);
		
		A4Stack<Plate> s1 = new A4Stack<Plate>();
		A4Stack<Plate> s2 = new A4Stack<Plate>();
		
		s1.push(p2);
		s1.push(p4);
		s1.push(p1);
		s1.push(p5);
		s1.push(p3);

		s2.push(p1);
		s2.push(p5);
		s2.push(p3);

		
		A4Exercises.insertPlate(s1, insert1);
		displayResults(A4Exercises.stackedCorrectly(s1), "insert into empty stack");//how is this empty?

		A4Exercises.insertPlate(s1, insert2);
		A4Exercises.insertPlate(s1, insert3);
		displayResults(A4Exercises.stackedCorrectly(s1), "test with s1");//ask for test cases

		A4Exercises.insertPlate(s2, insert5);
		displayResults(A4Exercises.stackedCorrectly(s2), "test with s2"+ s2.pop()+s2.pop()+s2.pop());//what if p is equal

		
		//TODO: add more tests here
	}

	public static void displayResults (boolean passed, String testName) {
       /* There is some magic going on here getting the line number
        * Borrowed from:
        * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
        */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
	
}