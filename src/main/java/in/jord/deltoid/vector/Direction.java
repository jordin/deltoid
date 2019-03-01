package in.jord.deltoid.vector;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Direction implements Vector<Direction> {
    /**
     * A {@link Direction} with all coordinates being {@link Double#NaN}.
     * This is used to represent an invalid or "null" value being returned from a function or similar.
     */
    public static final Direction INVALID = new Direction();

    /**
     * A {@link Direction} with coordinates <b>[0, 0, 0]</b>.
     */
    public static final Direction ORIGIN = new Direction(0, 0, 0);

    /**
     * The magnitude of the <b>alpha (\u03B1)</b> angle of the {@link Direction}.
     * This is the angle relative to the <b>x</b>-axis.
     *
     * @serial
     */
    @SerializedName("alpha")
    public final double alpha;

    /**
     * The magnitude of the <b>beta (\u03B2)</b> angle of the {@link Direction}.
     * This is the angle relative to the <b>y</b>-axis.
     *
     * @serial
     */
    @SerializedName("beta")
    public final double beta;

    /**
     * The magnitude of the <b>gamma (\u03B3)</b> angle of the {@link Direction}.
     * This is the angle relative to the <b>z</b>-axis.
     *
     * @serial
     */
    @SerializedName("gamma")
    public final double gamma;

    private volatile double length = -1;

    /**
     * Constructs a newly allocated {@link Direction} object.
     *
     * @param alpha <b>(\u03B1)</b> the magnitude of the angle relative to the <b>x</b>-axis.
     * @param beta  <b>(\u03B2)</b> the magnitude of the angle relative to the <b>y</b>-axis.
     * @param gamma <b>(\u03B3)</b> the magnitude of the angle relative to the <b>z</b>-axis.
     */
    public Direction(double alpha, double beta, double gamma) {
        if (Double.isNaN(alpha))
            throw new IllegalArgumentException("alpha shall not be NaN!");
        if (Double.isNaN(beta))
            throw new IllegalArgumentException("beta shall not be NaN!");
        if (Double.isNaN(gamma))
            throw new IllegalArgumentException("gamma shall not be NaN!");
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    /**
     * Constructs an invalid {@link Direction} instance.
     *
     * @implNote Generally only one invalid instance, {@link #INVALID},
     * should exist per VM.
     */
    private Direction() {
        this.alpha = Double.NaN;
        this.beta = Double.NaN;
        this.gamma = Double.NaN;
    }

    /**
     * Returns the magnitude of the {@link Direction}. Although the {@code length}
     * is meaningless on its own, it is useful to know that:
     * <p>
     * <b>cos\u00B2(\u03B1) + cos\u00B2(\u03B2) + cos\u00B2(\u03B3) = 1.0</b>
     * <p>
     * One use case for this is to sort \u0394{@link Direction}s.
     *
     * @return the magnitude of the {@link Direction}
     */
    @Override
    public double length() {
        if (length == -1) {
            length = Math.sqrt(this.alpha * this.alpha + this.beta * this.beta + this.gamma * this.gamma);
        }
        return length;
    }

    /**
     * Returns the manhattan (taxicab) length of the {@link Direction}.
     *
     * @return the manhattan length of the {@link Direction}
     */
    @Override
    public double manhattan() {
        return this.alpha + this.beta + this.gamma;
    }

    /**
     * Breaks this {@link Direction} into its underlying components.
     *
     * @return the components of this {@link Direction}
     */
    @Override
    public double[] components() {
        return new double[]{this.alpha, this.beta, this.gamma};
    }

    /**
     * Compares this {@link Direction} to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link Direction}
     * object that represents the same coordinate direction angles as this {@link Direction}.
     *
     * @param other the object to compare this {@link Direction} against
     * @return {@code true} if the given object represents a {@link Direction}
     * equivalent to this {@link Direction}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Direction direction = (Direction) other;
        return Double.compare(direction.alpha, alpha) == 0 &&
                Double.compare(direction.beta, beta) == 0 &&
                Double.compare(direction.gamma, gamma) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alpha, beta, gamma);
    }

    /**
     * Returns the unit {@link Direction} parallel to this {@link Direction}.
     *
     * @return Nothing
     * @throws UnsupportedOperationException {@link Direction} {@code normalize} is meaningless.
     */
    @Override
    public Direction normalize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of the unit {@link Direction} parallel to this {@link Direction}.
     *
     * @param length the desired length of the {@link Direction}.
     * @return Nothing.
     * @throws UnsupportedOperationException {@link Direction} {@code normalize} is meaningless.
     */
    @Override
    public Direction normalize(double length) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of this {@link Direction}.
     *
     * @param scaleFactor the desired scale factor for the {@link Direction}.
     * @return Nothing.
     * @throws UnsupportedOperationException {@link Direction} {@code scale} is meaningless.
     */
    @Override
    public Direction scale(double scaleFactor) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link Direction} that is the sum of this {@link Direction} and {@code addend}.
     *
     * @param addend the {@link Direction} to be added to this {@link Direction}.
     * @return the {@link Direction}
     */
    @Override
    public Direction add(Direction addend) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link Direction} that is the difference of this {@link Direction} and {@code subtrahend}.
     *
     * @param subtrahend the {@link Direction} to be subtracted from this {@link Direction}.
     * @return the {@link Direction}
     */
    @Override
    public Direction subtract(Direction subtrahend) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link Direction} that is this {@link Direction} with floored coordinates.
     *
     * @return the {@link Direction}
     */
    @Override
    public Direction floor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link Direction} that is this {@link Direction} with ceilinged coordinates.
     *
     * @return the {@link Direction}
     */
    @Override
    public Direction ceil() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link Direction} that is anti-parallel to this {@link Direction}.
     *
     * @return the {@link Direction}
     */
    @Override
    public Direction reverse() {
        return new Direction(-this.alpha, -this.beta, -this.gamma);
    }

    /**
     * Returns {@code true} IFF this {@link Direction} is
     * considered to be valid, with each {@code component ∈ ℝ}.
     *
     * @return {@code true} if the {@link Direction} is valid, {@code false} otherwise
     */
    @Override
    public boolean isValid() {
        return this != INVALID;
    }
}
