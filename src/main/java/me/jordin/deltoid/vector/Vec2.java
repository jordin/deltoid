package me.jordin.deltoid.vector;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jordin on 6/29/2017.
 * Jordin is still best hacker.
 */
public class Vec2 implements Vector<Vec2> {
    /**
     * A <b>Vec2</b> with coordinates <b>[0, 0]</b>.
     */
    public static final Vec2 ORIGIN = new Vec2(0, 0);

    /**
     * A <b>Vec2</b> with coordinates <b>[1, 1]</b>.
     */
    public static final Vec2 ONE = new Vec2(1, 1);

    /**
     * A unit <b>Vec2</b> parallel to the <b>x</b>-axis.
     */
    public static final Vec2 X_AXIS = new Vec2(1, 0);

    /**
     * A unit <b>Vec2</b> parallel to the <b>y</b>-axis.
     */
    public static final Vec2 Y_AXIS = new Vec2(0, 1);

    /**
     * A <b>Vec2</b> with coordinates <b>[0, 0]</b>.
     */
    public static final Vec2 ZERO = ORIGIN;

    /**
     * A unit <b>Vec2</b> parallel to the <b>x</b>-axis.
     */
    public static final Vec2 I_HAT = X_AXIS;

    /**
     * A unit <b>Vec2</b> parallel to the <b>y</b>-axis.
     */
    public static final Vec2 J_HAT = Y_AXIS;

    /**
     * The <b>x</b>-component of the <b>Vec2</b>.
     *
     * @serial
     */
    @SerializedName("x")
    public final double x;

    /**
     * The <b>y</b>-component of the <b>Vec2</b>.
     *
     * @serial
     */
    @SerializedName("y")
    public final double y;

    private volatile double length = -1;

    /**
     * Constructs a newly allocated <b>Vec2</b> object.
     *
     * @param x the magnitude of the <b>x</b>-component of the <b>Vec2</b>.
     * @param y the magnitude of the <b>y</b>-component of the <b>Vec2</b>.
     */
    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private void calculateLength() {
        if (length == -1) {
            length = Math.sqrt(x * x + y * y);
        }
    }

    /**
     * Returns the magnitude of the <b>Vec2</b>.
     *
     * @return the magnitude of the <b>Vec2</b>
     */
    @Override
    public double length() {
        calculateLength();
        return length;
    }

    /**
     * Returns the manhattan (taxicab) length of the <b>Vec2</b>.
     *
     * @return the manhattan length of the <b>Vec2</b>
     */
    @Override
    public double manhattan() {
        return this.x + this.y;
    }

    /**
     * Breaks this <b>Vec2</b> into its underlying components.
     *
     * @return the components of this <b>Vec2</b>
     */
    @Override
    public double[] components() {
        return new double[]{this.x, this.y};
    }

    /**
     * Compares this <b>Vec2</b> to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a <b>Vec2</b>
     * object that represents the same coordinates as this <b>Vec2</b>.
     *
     * @param other the object to compare this <b>Vec2</b> against
     * @return {@code true} if the given object represents a <b>Vec2</b>
     * equivalent to this <b>Vec2</b>, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Vec2) &&
                ((Vec2) other).x == this.x &&
                ((Vec2) other).y == this.y;
    }

    /**
     * Returns the unit <b>Vec2</b> parallel to this <b>Vec2</b>.
     *
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 normalize() {
        calculateLength();
        if (length == 0) {
            return ZERO;
        }
        return new Vec2(x / length, y / length);
    }

    /**
     * Returns a scalar multiple of the unit <b>Vec2</b> parallel to this <b>Vec2</b>.
     *
     * @param length the desired length of the <b>Vec2</b>.
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 normalize(double length) {
        return this.normalize().scale(length);
    }

    /**
     * Returns a scalar multiple of this <b>Vec2</b>.
     *
     * @param scaleFactor the desired scale factor for the <b>Vec2</b>.
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 scale(double scaleFactor) {
        return new Vec2(x * scaleFactor, y * scaleFactor);
    }

    /**
     * Returns the <b>Vec2</b> that is the sum of this <b>Vec2</b> and {@code addend}.
     *
     * @param addend the <b>Vec2</b> to be added to this <b>Vec2</b>.
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 add(Vec2 addend) {
        return new Vec2(x + addend.x, y + addend.y);
    }

    /**
     * Returns the <b>Vec2</b> that is the difference of this <b>Vec2</b> and {@code subtrahend}.
     *
     * @param subtrahend the Vector to be subtracted from this <b>Vec2</b>.
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 subtract(Vec2 subtrahend) {
        return new Vec2(x - subtrahend.x, y - subtrahend.y);
    }

    /**
     * Returns the <b>Vec2</b> that is this <b>Vec2</b> with floored coordinates.
     *
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 floor() {
        return new Vec2(Math.floor(x), Math.floor(y));
    }

    /**
     * Returns the <b>Vec2</b> that is this <b>Vec2</b> with ceilinged coordinates.
     *
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 ceil() {
        return new Vec2(Math.ceil(x), Math.ceil(y));
    }

    /**
     * Returns the <b>Vec2</b> that is anti-parallel to this <b>Vec2</b>.
     *
     * @return the <b>Vec2</b>
     */
    @Override
    public Vec2 reverse() {
        return new Vec2(-x, -y);
    }
}
