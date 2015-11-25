package Part1;

import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }
    
    public void testEqualityMismatch(){
    	assertFalse(new Rational(-2,3).equals("-2/3"));
    }
    
    public void testEqualityNull(){
    	assertFalse(new Rational(-2,3).equals(null));
    }
    
    //testNegatives
    public void testNegatives() {
        assertEquals(new Rational(-1,3), new Rational(1,-3));
        assertEquals(new Rational(-1,-3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
        assertFalse(new Rational(-2,3).equals(new Rational(2,3)));
        assertFalse(new Rational(2,-3).equals(new Rational(2,3)));
    }
    
    //Test making fractions with zeroes
    public void testZeros() {
        assertEquals(new Rational(0,3), new Rational(0,-4));
    }
    
    //test Plus
    public void testPlus() {
        assertEquals(new Rational(1,3).plus(new Rational(1,3)), new Rational(2,3));
        assertEquals(new Rational(0,3).plus(new Rational(1,3)), new Rational(1,3));
        assertEquals(new Rational(1,6).plus(new Rational(-1,6)), new Rational(0,3));
        assertEquals(new Rational(1,2).plus(new Rational(1,3)), new Rational(5,6));
        assertEquals(new Rational(-1,2).plus(new Rational(1,3)), new Rational(-1,6));
        assertEquals(new Rational(-1,2).plus(new Rational(-1,3)), new Rational(-5,6));
    }
    
    //test Plus with negative number in denominator of one rational
    public void testPlusNegDem1(){
        assertEquals(new Rational(1,2).plus(new Rational(1,-3)), new Rational(1,6));
    }
    
  //test Plus with negative number in denominator of both rationals
    public void testPlusNegDem2(){
        assertEquals(new Rational(1,-2).plus(new Rational(1,-3)), new Rational(-5,6));
    }
    
    //test minus
    public void testMinus() {
        assertEquals(new Rational(1,3).minus(new Rational(1,3)), new Rational(0,3));
        assertEquals(new Rational(0,3).minus(new Rational(1,3)), new Rational(-1,3));
        assertEquals(new Rational(1,6).minus(new Rational(-1,6)), new Rational(2,6));
        assertEquals(new Rational(1,2).minus(new Rational(1,3)), new Rational(1,6));
        assertEquals(new Rational(-1,2).minus(new Rational(1,3)), new Rational(-5,6));
        assertEquals(new Rational(-1,2).minus(new Rational(-1,3)), new Rational(-1,6));
    }
    
  //test Minus with negative number in denominator of one rational
    public void testMinusNegDem1(){
        assertEquals(new Rational(1,2).minus(new Rational(1,-3)), new Rational(5,6));
    }
    
  //test Minus with negative number in denominator of both rationals
    public void testMinusNegDem2(){
        assertEquals(new Rational(1,-2).minus(new Rational(1,-3)), new Rational(-1,6));
    }
    
    
  //test multiply
    public void testTimes() {
		assertEquals(new Rational(1,3).times(new Rational(1,3)), new Rational(1,9));
        assertEquals(new Rational(0,3).times(new Rational(1,3)), new Rational(0,3));
        assertEquals(new Rational(1,6).times(new Rational(-6,1)), new Rational(-1,1));
        assertEquals(new Rational(1,2).times(new Rational(4,3)), new Rational(4,6));
        assertEquals(new Rational(1,2).times(new Rational(1,-3)), new Rational(1,-6));
        assertEquals(new Rational(-1,2).times(new Rational(1,3)), new Rational(1,-6));
        assertEquals(new Rational(-1,2).times(new Rational(-1,3)), new Rational(1,6));
    }
    
  //test divides
    public void testdivides() {
		assertEquals(new Rational(1,3).divides(new Rational(1,3)), new Rational(1,1));
        assertEquals(new Rational(0,3).divides(new Rational(1,3)), new Rational(0,3));
        assertEquals(new Rational(1,6).divides(new Rational(-6,1)), new Rational(-1,36));
        assertEquals(new Rational(1,2).divides(new Rational(4,3)), new Rational(3,8));
        assertEquals(new Rational(1,2).divides(new Rational(1,-3)), new Rational(-3,2));
        assertEquals(new Rational(-1,2).divides(new Rational(1,3)), new Rational(-3,2));
        assertEquals(new Rational(-1,2).divides(new Rational(-1,3)), new Rational(3,2));
    }
    
    //test set and get Tolerance
    public void testGetAndSetTolerance(){
    	Rational testRat = new Rational(1,3);
    	testRat.setTolerance(new Rational(1,2000));
    	assertEquals(testRat.getTolerance(), new Rational(1,2000));
    }

    //Test lessthan
    public void testLessThan() {
        assertTrue(new Rational(1,3).isLessThan(new Rational(2,3)));
        assertTrue(new Rational(-2,3).isLessThan(new Rational(1,3)));
        assertTrue(new Rational(-2,3).isLessThan(new Rational(-1,3)));
        assertTrue(new Rational(2,-3).isLessThan(new Rational(1,-3)));
        //assertfalse if equal
        assertFalse(new Rational(-2,3).isLessThan(new Rational(2,-3)));
        assertFalse(new Rational(2,3).isLessThan(new Rational(2,3)));
      //assertfalse if greater than
        assertFalse(new Rational(4,3).isLessThan(new Rational(2,3)));
        assertFalse(new Rational(1,2).isLessThan(new Rational(-4,2)));
    }
    
    //Test less than if the denominator of one and only one of the rationals is negative
    public void testLessThanNegDem1() {
    	assertTrue(new Rational(-2,3).isLessThan(new Rational(1,-3)));
    }
    
  //test set and get Tolerance
    public void testAbs(){
    	assertEquals(new Rational(1,3).abs(), new Rational(1,3));
    	assertEquals(new Rational(-1,3).abs(), new Rational(1,3));
    	assertEquals(new Rational(1,-3).abs(), new Rational(1,3));
    	assertEquals(new Rational(-1,-3).abs(), new Rational(1,3));
    }
    
  //test set and get Tolerance
    public void testToString(){
    	assertEquals(new Rational(1,3).toString(), "1/3");
    }
    
    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }
    
    public void testRootNegative() {
        int flag = 0;
    	Rational s = new Rational( -1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
            flag = 1;
            System.out.println(e);
            assertEquals(e.toString(),"Part1.IllegalArgumentToSquareRootException: Illegal argument to square root, should be in range [0,2147483647] got "+s.toString());
        }
       
        assertEquals(flag,1);
    }
    
    public void testRootTolerance() {
    	//set to default tolerance
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}