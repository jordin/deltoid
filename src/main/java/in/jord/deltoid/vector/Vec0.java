package in.jord.deltoid.vector;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Vec0 implements Vector<Vec0> {
    /**
     * This is used to represent an invalid or "null" value being returned from a function or similar.
     */
    public static final Vec0 INVALID = new Vec0(false);

    public static final Vec0 VALID = new Vec0(true);

    private static final double[] COMPONENTS = { 0 };

    /**
     * The <b>x</b>-component of the {@link Vec0}.
     *
     * @serial
     */
    @SerializedName("valid")
    public final boolean valid;

    /**
     * Constructs a newly allocated {@link Vec0} object.
     *
     * @param valid weather or not the {@link Vec0} is valid.
     */
    public Vec0(boolean valid) {
        this.valid = valid;
    }

    /**
     * Returns the magnitude of the {@link Vec0}.
     *
     * @return the magnitude of the {@link Vec0}
     */
    @Override
    public double length() {
        return 0;
    }

    /**
     * Returns the manhattan (taxicab) length of the {@link Vec0}.
     *
     * @return the manhattan length of the {@link Vec0}
     */
    @Override
    public double manhattan() {
        return 0;
    }

    /**
     * Breaks this {@link Vec0} into its underlying components.
     *
     * @return the components of this {@link Vec0}
     */
    @Override
    public double[] components() {
        return COMPONENTS;
    }

    /**
     * Compares this {@link Vec0} to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link Vec0}
     * object that represents the same coordinates as this {@link Vec0}.
     *
     * @param other the object to compare this {@link Vec0} against
     * @return {@code true} if the given object represents a {@link Vec0}
     * equivalent to this {@link Vec0}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Vec0 vec0 = (Vec0) other;
        return valid == vec0.valid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valid);
    }

    /**
     * Returns the unit {@link Vec0} parallel to this {@link Vec0}.
     *
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 normalize() {
        throw new IllegalStateException("Normalizing a Vec0 is illegal, since it's length = 0.0.");
    }

    /**
     * Returns a scalar multiple of the unit {@link Vec0} parallel to this {@link Vec0}.
     *
     * @param length the desired length of the {@link Vec0}.
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 normalize(double length) {
        throw new IllegalStateException("Normalizing a Vec0 is illegal, since it's length = 0.0.");
    }

    /**
     * Returns a scalar multiple of this {@link Vec0}.
     *
     * @param scaleFactor the desired scale factor for the {@link Vec0}.
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 scale(double scaleFactor) {
        throw new IllegalStateException("Scaling a Vec0 is illegal, since it's length = 0.0.");
    }

    /**
     * Returns the {@link Vec0} that is the sum of this {@link Vec0} and {@code addend}.
     *
     * @param addend the {@link Vec0} to be added to this {@link Vec0}.
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 add(Vec0 addend) {
        throw new IllegalStateException("Adding two Vec0s is illegal.");
    }

    /**
     * Returns the {@link Vec0} that is the difference of this {@link Vec0} and {@code subtrahend}.
     *
     * @param subtrahend the Vector to be subtracted from this {@link Vec0}.
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 subtract(Vec0 subtrahend) {
        throw new IllegalStateException("Subtracting two Vec0s is illegal.");
    }

    /**
     * Returns the {@link Vec0} that is this {@link Vec0} with floored coordinates.
     *
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 floor() {
        return this;
    }

    /**
     * Returns the {@link Vec0} that is this {@link Vec0} with ceilinged coordinates.
     *
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 ceil() {
        return this;
    }

    /**
     * Returns the {@link Vec0} that is anti-parallel to this {@link Vec0}.
     *
     * @return the {@link Vec0}
     */
    @Override
    public Vec0 reverse() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("Vec0(valid=%b)", this.valid);
    }

    /**
     * Returns {@code true} IFF this {@link Vec0} is
     * considered to be valid.
     *
     * @return {@code true} if the {@link Vec0} is valid, {@code false} otherwise
     */
    @Override
    public boolean isValid() {
        return this.valid;
    }
}
