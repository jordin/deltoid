package in.jord.deltoid.vector;

import com.google.gson.annotations.SerializedName;

public class Vec1 implements Vector<Vec1> {
    /**
     * A {@link Vec1} with coordinates <b>[0]</b>.
     */
    public static final Vec1 ORIGIN = new Vec1(0);

    /**
     * A {@link Vec1} with coordinates <b>[1]</b>.
     */
    public static final Vec1 ONE = new Vec1(1);

    /**
     * A unit {@link Vec1} parallel to the <b>x</b>-axis.
     */
    public static final Vec1 X_AXIS = new Vec1(1);

    /**
     * A {@link Vec1} with coordinates <b>[0]</b>.
     */
    public static final Vec1 ZERO = ORIGIN;

    /**
     * A unit {@link Vec1} parallel to the <b>x</b>-axis.
     */
    public static final Vec1 I_HAT = X_AXIS;

    /**
     * A {@link Vec1} with coordinates <b>[0.5]</b>.
     *
     * <p>
     * This represents the position of the midpoint of a <b>1x1</b> surface parallel to the <b>x</b> plane
     * </p>
     *
     */
    public static final Vec1 CENTRE = new Vec1(0.5);

    /**
     * The <b>x</b>-component of the {@link Vec1}.
     *
     * @serial
     */
    @SerializedName("x")
    public final double x;

    /**
     * Constructs a newly allocated {@link Vec1} object.
     *
     * @param x the magnitude of the <b>x</b>-component of the {@link Vec1}.
     */
    public Vec1(double x) {
        this.x = x;
    }

    /**
     * Constructs a newly allocated {@link Vec1} object with coordinates <b>[0]</b>
     */
    public Vec1() {
        this.x = 0;
    }


    /**
     * Returns the magnitude of the {@link Vec1}.
     *
     * @return the magnitude of the {@link Vec1}
     */
    @Override
    public double length() {
        return x;
    }

    /**
     * Returns the manhattan (taxicab) length of the {@link Vec1}.
     *
     * @return the manhattan length of the {@link Vec1}
     */
    @Override
    public double manhattan() {
        return Math.abs(this.x);
    }

    /**
     * Breaks this {@link Vec1} into its underlying components.
     *
     * @return the components of this {@link Vec1}
     */
    @Override
    public double[] components() {
        return new double[]{this.x};
    }

    /**
     * Compares this {@link Vec1} to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link Vec1}
     * object that represents the same coordinates as this {@link Vec1}.
     *
     * @param other the object to compare this {@link Vec1} against
     * @return {@code true} if the given object represents a {@link Vec1}
     * equivalent to this {@link Vec1}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Vec1) &&
                ((Vec1) other).x == this.x;
    }

    /**
     * Returns the unit {@link Vec1} parallel to this {@link Vec1}.
     *
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 normalize() {
        return new Vec1(x / this.length());
    }

    /**
     * Returns a scalar multiple of the unit {@link Vec1} parallel to this {@link Vec1}.
     *
     * @param length the desired length of the {@link Vec1}.
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 normalize(double length) {
        return this.normalize().scale(length);
    }

    /**
     * Returns a scalar multiple of this {@link Vec1}.
     *
     * @param scaleFactor the desired scale factor for the {@link Vec1}.
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 scale(double scaleFactor) {
        return new Vec1(x * scaleFactor);
    }

    /**
     * Returns the {@link Vec1} that is the sum of this {@link Vec1} and {@code addend}.
     *
     * @param addend the {@link Vec1} to be added to this {@link Vec1}.
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 add(Vec1 addend) {
        return new Vec1(x + addend.x);
    }

    /**
     * Returns the {@link Vec1} that is the difference of this {@link Vec1} and {@code subtrahend}.
     *
     * @param subtrahend the Vector to be subtracted from this {@link Vec1}.
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 subtract(Vec1 subtrahend) {
        return new Vec1(x - subtrahend.x);
    }

    /**
     * Returns the {@link Vec1} that is this {@link Vec1} with floored coordinates.
     *
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 floor() {
        return new Vec1(Math.floor(x));
    }

    /**
     * Returns the {@link Vec1} that is this {@link Vec1} with ceilinged coordinates.
     *
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 ceil() {
        return new Vec1(Math.ceil(x));
    }

    /**
     * Returns the {@link Vec1} that is anti-parallel to this {@link Vec1}.
     *
     * @return the {@link Vec1}
     */
    @Override
    public Vec1 reverse() {
        return new Vec1(-x);
    }

    @Override
    public String toString() {
        return String.format("Vec1(x=%f)", this.x);
    }

    public String toSimpleString() {
        return String.format("%.2f", this.x);
    }

    /**
     * Construct and return a newly-allocated Vec1 object.
     *
     * @param x the magnitude of the <b>x</b>-component of the {@link Vec1}
     * @return new Vec1 instance
     * @see Vec1#Vec1(double)
     */
    public static Vec1 of(double x, double y) {
        return new Vec1(x);
    }
}
