package me.jordin.deltoid.vector;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jordin on 6/23/2017.
 * Jordin is still best hacker.
 */
public class Direction implements Vector<Direction> {
    /**
     * A <b>Direction</b> with coordinates <b>[0, 0, 0]</b>.
     */
    public static final Direction ORIGIN = new Direction(0, 0, 0);

    /**
     * The magnitude of the <b>alpha (\u03B1)</b> angle of the <b>Direction</b>.
     * This is the angle relative to the <b>x</b>-axis.
     *
     * @serial
     */
    @SerializedName("alpha")
    public final double alpha;

    /**
     * The magnitude of the <b>beta (\u03B2)</b> angle of the <b>Direction</b>.
     * This is the angle relative to the <b>y</b>-axis.
     *
     * @serial
     */
    @SerializedName("beta")
    public final double beta;

    /**
     * The magnitude of the <b>gamma (\u03B3)</b> angle of the <b>Direction</b>.
     * This is the angle relative to the <b>z</b>-axis.
     *
     * @serial
     */
    @SerializedName("gamma")
    public final double gamma;

    private double length = -1;

    /**
     * Constructs a newly allocated <b>Direction</b> object.
     *
     * @param alpha <b>(\u03B1)</b> the magnitude of the angle relative to the <b>x</b>-axis.
     * @param beta  <b>\u03B2)</b> the magnitude of the angle relative to the <b>y</b>-axis.
     * @param gamma <b>(\u03B3)</b> the magnitude of the angle relative to the <b>z</b>-axis.
     */
    public Direction(double alpha, double beta, double gamma) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    /**
     * Returns the magnitude of the <b>Direction</b>. Although the {@code length}
     * is meaningless on its own, it is useful to know that:
     * <p>
     * <b>cos\u00B2(\u03B1) + cos\u00B2(\u03B2) + cos\u00B2(\u03B3) = 1.0</b>
     * <p>
     * One use case for this is to sort \u0394<b>Direction</b>s.
     *
     * @return the magnitude of the <b>Direction</b>
     */
    @Override
    public double length() {
        if (length == -1) {
            length = Math.sqrt(this.alpha * this.alpha + this.beta * this.beta + this.gamma * this.gamma);
        }
        return length;
    }

    /**
     * Returns the manhattan (taxicab) length of the <b>Direction</b>.
     *
     * @return the manhattan length of the <b>Direction</b>
     */
    @Override
    public double manhattan() {
        return this.alpha + this.beta + this.gamma;
    }

    /**
     * Breaks this <b>Direction</b> into its underlying components.
     *
     * @return the components of this <b>Direction</b>
     */
    @Override
    public double[] components() {
        return new double[]{this.alpha, this.beta, this.gamma};
    }

    /**
     * Compares this <b>Direction</b> to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a <b>Direction</b>
     * object that represents the same coordinate direction angles as this <b>Direction</b>.
     *
     * @param other the object to compare this <b>Direction</b> against
     * @return {@code true} if the given object represents a <b>Direction</b>
     * equivalent to this <b>Direction</b>, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Direction) &&
                ((Direction) other).alpha == this.alpha &&
                ((Direction) other).beta == this.beta &&
                ((Direction) other).gamma == this.gamma;
    }

    /**
     * Returns the unit <b>Direction</b> parallel to this <b>Direction</b>.
     *
     * @return Nothing
     * @throws UnsupportedOperationException <b>Direction</b> {@code normalize} is meaningless.
     */
    @Override
    public Direction normalize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of the unit <b>Direction</b> parallel to this <b>Direction</b>.
     *
     * @param length the desired length of the <b>Direction</b>.
     * @return Nothing.
     * @throws UnsupportedOperationException <b>Direction</b> {@code normalize} is meaningless.
     */
    @Override
    public Direction normalize(double length) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of this <b>Direction</b>.
     *
     * @param scaleFactor the desired scale factor for the <b>Direction</b>.
     * @return Nothing.
     * @throws UnsupportedOperationException <b>Direction</b> {@code scale} is meaningless.
     */
    @Override
    public Direction scale(double scaleFactor) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the <b>Direction</b> that is the sum of this <b>Direction</b> and {@code addend}.
     *
     * @param addend the <b>Direction</b> to be added to this <b>Direction</b>.
     * @return the <b>Direction</b>
     */
    @Override
    public Direction add(Direction addend) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the <b>Direction</b> that is the difference of this <b>Direction</b> and {@code subtrahend}.
     *
     * @param subtrahend the <b>Direction</b> to be subtracted from this <b>Direction</b>.
     * @return the <b>Direction</b>
     */
    @Override
    public Direction subtract(Direction subtrahend) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the <b>Direction</b> that is this <b>Direction</b> with floored coordinates.
     *
     * @return the <b>Direction</b>
     */
    @Override
    public Direction floor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the <b>Direction</b> that is this <b>Direction</b> with ceilinged coordinates.
     *
     * @return the <b>Direction</b>
     */
    @Override
    public Direction ceil() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the <b>Direction</b> that is anti-parallel to this <b>Direction</b>.
     *
     * @return the <b>Direction</b>
     */
    @Override
    public Direction reverse() {
        return new Direction(-this.alpha, -this.beta, -this.gamma);
    }
}
