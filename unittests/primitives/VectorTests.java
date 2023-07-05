package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTests {
    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
    @Test
    void testConstructor1(){
        assertThrows(IllegalArgumentException.class,() ->new Vector(0, 0, 0),"ERROR: zero vector does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Vector#Vector(Double3)}.
     */
    @Test
    void testConstructor2(){
        Double3 d = new Double3(0,0,0);
        assertThrows(IllegalArgumentException.class,() ->new Vector(d),"ERROR: zero vector does not throw an exception");
    }

    Vector v1 = new Vector(1,2,3);
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {

        // =============== Boundary Values Tests ==================
        //ensure that Vector+ -itself throws exception
        Vector v2 = new Vector(-1,-2,-3);
        assertThrows(IllegalArgumentException.class,() ->v1.add(v2),"ERROR: Vector + -itself does not throw an exception");
        // ============ Equivalence Partitions Tests ==============
        //ensure that Vector+ another Vector returns the correct answer
        Vector v3 = new Vector(3,4,5);
        assertTrue(v1.add(v3).equals(new Vector(4,6,8)),"\"ERROR: Vector + Vector does not work correctly\"");
    }
    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract(){
        // =============== Boundary Values Tests ==================
        //ensure that Vector - itself throws exception
        assertThrows(IllegalArgumentException.class,() ->v1.subtract(v1),"ERROR: Vector + -itself does not throw an exception");
        // ============ Equivalence Partitions Tests ==============
        //ensure that Vector - another Vector returns the correct answer
        Vector v2 = new Vector(3,4,5);
        assertTrue(v2.subtract(v1).equals(new Vector(2,2,2)),"\"ERROR: Vector - Vector does not work correctly\"");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // =============== Boundary Values Tests ==================
        //ensure that Vector multiplied by 0 throws exception
        assertThrows(IllegalArgumentException.class,() ->v1.scale(0),"ERROR: Vector * 0 does not throw an exception");
        // ============ Equivalence Partitions Tests ==============
        //ensure that Vector * scalar returns the correct answer
        double scl = 10;
        assertTrue(v1.scale(scl).equals(new Vector(10,20,30)),"\"ERROR: Vector * scalar does not work correctly\"");

    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // =============== Equivalence Partitions Tests ==================
        //checks weather the dotProduct function for orthogonal vector works proper (returns 0)
        Vector v2 = new Vector(2, -1, 0);
        assertEquals(v1.dotProduct(v2),0,"ERROR: dotProduct() for orthogonal vectors is not zero");
        // ============ Equivalence Partitions Tests ==============
        //checks weather the dotProduct function between 2 vectors works proper
        Vector v3 = new Vector(2,3,4);
        assertEquals(v1.dotProduct(v3),20,"ERROR: dotProduct() wrong value");

    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector}.
     */
    @Test
    void testCrossProduct() {
        // =============== Boundary Values Tests ==================
        //ensure that crossProduct for parallel vectors throws exception
        Vector v2 = new Vector (2,4,6);
        assertThrows(IllegalArgumentException.class,() ->v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");
        // ============ Equivalence Partitions Tests ==============
        // checks weather the crossProduct function between 2 vectors works proper
        Vector v3 = new Vector(2,-1,0);
        Vector v4 = v1.crossProduct(v3);
        assertEquals(v4.length() - v1.length() * v3.length(),0,"ERROR: crossProduct() wrong result length");
        // ============ Equivalence Partitions Tests ==============
        // checks weather the crossProduct function between 2 vectors gives vector that is orthogonal to each of them.
        assertEquals(v4.dotProduct(v1),0,"ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(v4.dotProduct(v3),0,"ERROR: crossProduct() result is not orthogonal to its operands");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //checks weather the lengthSquared function works proper
        assertEquals(v1.lengthSquared(),14,"ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        //checks weather the length function works proper
        assertEquals(v1.length(),Math.sqrt(14),"ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v2 = v1.normalize();
        // ============ Equivalence Partitions Tests ==============
        //checks weather the normalize function works proper
        assertEquals(v2.length(),1,"ERROR: the normalized vector is not a unit vector");
        // ============ Equivalence Partitions Tests ==============
        //checks weather the normalize function works proper
        assertThrows(IllegalArgumentException.class,() ->v2.crossProduct(v1),"ERROR: the normalized vector is not parallel to the original one");
        // ============ Equivalence Partitions Tests ==============
        //checks weather the normalize function works proper
        //assertEquals(v1.dotProduct(v2),);//------------------
    }
}