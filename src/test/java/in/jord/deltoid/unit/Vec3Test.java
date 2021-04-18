package in.jord.deltoid.unit;

import in.jord.deltoid.vector.Vec3;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by appledash on 7/17/17.
 * Blackjack is best pony.
 */
public class Vec3Test {
    @Test
    public void testGetters() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Assert.assertEquals(1, vec3.x, 0.0D);
        Assert.assertEquals(2, vec3.y, 0.0D);
        Assert.assertEquals(3, vec3.z, 0.0D);

        Vec3 twoDee = new Vec3(1, 2);
        Assert.assertEquals(1, twoDee.x, 0.0D);
        Assert.assertEquals(2, twoDee.y, 0.0D);
        Assert.assertEquals(0, twoDee.z, 0.0D);
    }

    @Test
    public void testConstants() {
        Assert.assertSame(Vec3.ORIGIN, Vec3.ZERO);
        Assert.assertTrue(Vec3.ORIGIN.x == 0 && Vec3.ORIGIN.y == 0 && Vec3.ORIGIN.z == 0);
        Assert.assertTrue(Vec3.ONE.x == 1 && Vec3.ONE.y == 1 && Vec3.ONE.z == 1);
        Assert.assertTrue(Vec3.X_AXIS.x == 1 && Vec3.X_AXIS.y == 0 && Vec3.X_AXIS.z == 0);
        Assert.assertTrue(Vec3.Y_AXIS.x == 0 && Vec3.Y_AXIS.y == 1 && Vec3.Y_AXIS.z == 0);
        Assert.assertTrue(Vec3.Z_AXIS.x == 0 && Vec3.Z_AXIS.y == 0 && Vec3.Z_AXIS.z == 1);
    }

    @Test
    public void testLength() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Assert.assertEquals(vec3.length(), Math.sqrt(1 * 1 + 2 * 2 + 3 * 3), 0.00000000001);
    }

    @Test
    public void testManhattan() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Assert.assertEquals(6, vec3.manhattan(), 0);
    }

    @Test
    public void testComponents() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Assert.assertArrayEquals(vec3.components(), new double[] { 1, 2, 3 }, 0);
    }

    @Test
    public void testNormalize() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        double length = Math.sqrt(1 * 1 + 2 * 2 + 3 * 3);
        vec3 = vec3.normalize();

        Assert.assertEquals(vec3.length(), 1, 0.00000000001);
        Assert.assertEquals(vec3.x, 1 / length, 0.00000000001);
        Assert.assertEquals(vec3.y, 2 / length, 0.00000000001);
        Assert.assertEquals(vec3.z, 3 / length, 0.00000000001);
    }

    @Test
    public void testScale() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Vec3 scaled = vec3.scale(2);

        Assert.assertEquals(2, scaled.x, 0);
        Assert.assertEquals(4, scaled.y, 0);
        Assert.assertEquals(6, scaled.z, 0);
    }

    @Test
    public void testAdd() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Vec3 added = vec3.add(new Vec3(4, 5, 6));

        Assert.assertEquals(5, added.x, 0);
        Assert.assertEquals(7, added.y, 0);
        Assert.assertEquals(9, added.z, 0);
    }

    @Test
    public void testSubtract() {
        Vec3 vec3 = new Vec3(5, 7, 9);
        Vec3 subtracted = vec3.subtract(new Vec3(1, 2, 3));

        Assert.assertEquals(4, subtracted.x, 0);
        Assert.assertEquals(5, subtracted.y, 0);
        Assert.assertEquals(6, subtracted.z, 0);
    }

    @Test
    public void testFloor() {
        Vec3 vec3 = new Vec3(1.5, 2.5, 3.5);
        Vec3 floored = vec3.floor();

        Assert.assertEquals(1, floored.x, 0);
        Assert.assertEquals(2, floored.y, 0);
        Assert.assertEquals(3, floored.z, 0);
    }

    @Test
    public void testCeil() {
        Vec3 vec3 = new Vec3(1.5, 2.5, 3.5);
        Vec3 ceiled = vec3.ceil();

        Assert.assertEquals(2, ceiled.x, 0);
        Assert.assertEquals(3, ceiled.y, 0);
        Assert.assertEquals(4, ceiled.z, 0);
    }

    @Test
    public void testReverse() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Vec3 reversed = vec3.reverse();

        Assert.assertEquals(-1, reversed.x, 0);
        Assert.assertEquals(-2, reversed.y, 0);
        Assert.assertEquals(-3, reversed.z, 0);
    }

    @Test
    public void testDot() {
        // TODO
    }

    @Test
    public void testCross() {
        // TODO
    }

    @Test
    public void testDirection() {
        // TODO
    }

    @Test
    public void testDown() {
        Vec3 vec3 = new Vec3(1, 2, 3);
        Vec3 downed = vec3.down(1);

        Assert.assertEquals(1, downed.x, 0);
        Assert.assertEquals(1, downed.y, 0);
        Assert.assertEquals(3, downed.z, 0);
    }

    // TODO: I'm doing these in order, anything in Vec3 below down() has not yet been tested
}
