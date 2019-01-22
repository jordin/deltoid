package in.jord.deltoid.vector;

import com.google.gson.annotations.SerializedName;
import in.jord.deltoid.utils.MathUtilities;

public class Rotation implements Vector<Rotation> {
    /**
     * A {@link Rotation} with coordinates <b>[0, 0, 0]</b>.
     */
    public static final Rotation ORIGIN = new Rotation(0, 0, 0);

    /**
     * The magnitude of the <b>yaw</b> angle of the {@link Rotation}.
     *
     * @serial
     */
    @SerializedName("yaw")
    public final double rotationYaw;

    /**
     * The magnitude of the <b>pitch</b> angle of the {@link Rotation}.
     *
     * @serial
     */
    @SerializedName("pitch")
    public final double rotationPitch;

    /**
     * The magnitude of the <b>roll</b> angle of the {@link Rotation}.
     *
     * @serial
     */
    @SerializedName("roll")
    public final double rotationRoll;

    private volatile double length = -1;

    /**
     * Constructs a newly allocated {@link Rotation} object.
     *
     * @param rotationYaw   the magnitude of the yaw of the {@link Rotation}.
     * @param rotationPitch the magnitude of the pitch of the {@link Rotation}.
     * @param rotationRoll  the magnitude of the roll of the {@link Rotation}.
     */
    public Rotation(double rotationYaw, double rotationPitch, double rotationRoll) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.rotationRoll = rotationRoll;
    }

    /**
     * Constructs a newly allocated {@link Rotation} object,
     * with a roll of <b>\u03B8=0.0</b>.
     *
     * @param rotationYaw   the magnitude of the yaw of the {@link Rotation}.
     * @param rotationPitch the magnitude of the pitch of the {@link Rotation}.
     */
    public Rotation(double rotationYaw, double rotationPitch) {
        this(rotationYaw, rotationPitch, 0);
    }

    /**
     * Constructs a newly allocated {@link Rotation} object,
     * with a yaw and roll of <b>\u03B8=0.0</b>.
     *
     * @param rotationYaw the magnitude of the yaw of the {@link Rotation}.
     */
    public Rotation(double rotationYaw) {
        this(rotationYaw, 0, 0);
    }

    /**
     * Constructs a newly allocated {@link Rotation} object with coordinates <b>[0, 0, 0]</b>
     */
    public Rotation() {
        this.rotationYaw = 0;
        this.rotationPitch = 0;
        this.rotationRoll = 0;
    }

    /**
     * Returns the magnitude of the {@link Rotation}.
     * <p>
     * One use case for this is to sort \u0394{@link Rotation}s.
     *
     * @return the magnitude of the {@link Rotation}
     */
    @Override
    public double length() {
        if (length == -1) {
            length = Math.sqrt(this.rotationYaw * this.rotationYaw + this.rotationPitch * this.rotationPitch + this.rotationRoll * this.rotationRoll);
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
        return this.rotationYaw + this.rotationPitch + this.rotationRoll;
    }

    /**
     * Breaks this {@link Direction} into its underlying components.
     *
     * @return the components of this {@link Direction}
     */
    @Override
    public double[] components() {
        return new double[]{rotationYaw, rotationPitch, rotationRoll};
    }

    /**
     * Compares this {@link Rotation} to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link Rotation}
     * object that represents the same rotation angles as this {@link Rotation}.
     *
     * @param other the object to compare this {@link Rotation} against
     * @return {@code true} if the given object represents a {@link Rotation}
     * equivalent to this {@link Rotation}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Rotation) &&
                ((Rotation) other).rotationYaw == this.rotationYaw &&
                ((Rotation) other).rotationPitch == this.rotationPitch &&
                ((Rotation) other).rotationRoll == this.rotationRoll;
    }

    /**
     * Returns the unit {@link Rotation} parallel to this {@link Rotation}.
     *
     * @return Nothing
     * @throws UnsupportedOperationException {@link Rotation} {@code normalize} is meaningless.
     */
    public Rotation normalize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of the unit {@link Rotation} parallel to this {@link Rotation}.
     *
     * @param length the desired length of the {@link Rotation}.
     * @return Nothing.
     * @throws UnsupportedOperationException {@link Rotation} {@code normalize} is meaningless.
     */
    @Override
    public Rotation normalize(double length) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of this {@link Rotation}.
     *
     * @param scaleFactor the desired scale factor for the {@link Rotation}.
     * @return The {@link Rotation}.
     */
    @Override
    public Rotation scale(double scaleFactor) {
        return new Rotation(this.rotationYaw * scaleFactor, this.rotationPitch * scaleFactor, this.rotationRoll * scaleFactor);
    }

    /**
     * Returns the {@link Rotation} that is the sum of this {@link Rotation} and {@code addend}.
     *
     * @param addend the {@link Rotation} to be added to this {@link Rotation}.
     * @return the {@link Rotation}
     */
    @Override
    public Rotation add(Rotation addend) {
        return new Rotation(this.rotationYaw + addend.rotationYaw, this.rotationPitch + addend.rotationPitch, this.rotationRoll + addend.rotationRoll);
    }

    /**
     * Returns the {@link Rotation} that is the difference of this {@link Rotation} and {@code subtrahend}.
     *
     * @param subtrahend the {@link Rotation} to be subtracted from this {@link Rotation}.
     * @return the {@link Rotation}
     */
    @Override
    public Rotation subtract(Rotation subtrahend) {
        return new Rotation(this.rotationYaw - subtrahend.rotationYaw, this.rotationPitch - subtrahend.rotationPitch, this.rotationRoll - subtrahend.rotationRoll);
    }

    /**
     * Returns the {@link Rotation} that is this {@link Rotation} with floored coordinates.
     *
     * @return the {@link Rotation}
     */
    @Override
    public Rotation floor() {
        return new Rotation(Math.floor(this.rotationYaw), Math.floor(this.rotationPitch), Math.floor(this.rotationRoll));
    }

    /**
     * Returns the {@link Rotation} that is this {@link Rotation} with ceilinged coordinates.
     *
     * @return the {@link Rotation}
     */
    @Override
    public Rotation ceil() {
        return new Rotation(Math.ceil(this.rotationYaw), Math.ceil(this.rotationPitch), Math.ceil(this.rotationRoll));
    }

    /**
     * Returns the {@link Rotation} that is anti-parallel to this {@link Rotation}.
     *
     * @return the {@link Rotation}
     */
    @Override
    public Rotation reverse() {
        return new Rotation(-this.rotationYaw, -this.rotationPitch, -this.rotationRoll);
    }

    /**
     * Converts a {@link Rotation} with angles in radians to angles in degrees.
     *
     * @return the {@link Rotation}
     */
    public Rotation toDegrees() {
        return new Rotation(Math.toDegrees(this.rotationYaw), Math.toDegrees(this.rotationPitch), Math.toDegrees(this.rotationRoll));
    }

    /**
     * Converts a {@link Rotation} with angles in degrees to angles in radians.
     *
     * @return the {@link Rotation}
     */
    public Rotation toRadians() {
        return new Rotation(Math.toRadians(this.rotationYaw), Math.toRadians(this.rotationPitch), Math.toRadians(this.rotationRoll));
    }

    /**
     * Returns the {@link Rotation} with angles restricted to <b>\u03B8\u2208[-180\u00B0, 180\u00B0)</b>
     *
     * @return the {@link Rotation}
     */
    public Rotation wrapDegrees() {
        return new Rotation(MathUtilities.wrapDegrees(this.rotationYaw), MathUtilities.wrapDegrees(this.rotationPitch), MathUtilities.wrapDegrees(this.rotationRoll));
    }

    /**
     * Returns the {@link Rotation} with angles restricted to <b>\u03B8\u2208[-\u03C0, \u03C0)</b>
     *
     * @return the {@link Rotation}
     */
    public Rotation wrapRadians() {
        return new Rotation(MathUtilities.wrapRadians(this.rotationYaw), MathUtilities.wrapRadians(this.rotationPitch), MathUtilities.wrapRadians(this.rotationRoll));
    }
}
