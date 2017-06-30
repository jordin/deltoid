package me.jordin.deltoid.vector;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.utils.MathUtilities;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public class Rotation implements Vector<Rotation> {
    /**
     * A <b>Rotation</b> with coordinates <b>[0, 0, 0]</b>.
     */
    public static final Rotation ORIGIN = new Rotation(0, 0, 0);

    /**
     * The magnitude of the <b>yaw</b> angle of the <b>Rotation</b>.
     *
     * @serial
     */
    @SerializedName("yaw")
    public final double rotationYaw;

    /**
     * The magnitude of the <b>pitch</b> angle of the <b>Rotation</b>.
     *
     * @serial
     */
    @SerializedName("pitch")
    public final double rotationPitch;

    /**
     * The magnitude of the <b>roll</b> angle of the <b>Rotation</b>.
     *
     * @serial
     */
    @SerializedName("roll")
    public final double rotationRoll;

    private double length = -1;

    /**
     * Constructs a newly allocated <b>Rotation</b> object.
     *
     * @param rotationYaw   the magnitude of the yaw of the <b>Rotation</b>.
     * @param rotationPitch the magnitude of the pitch of the <b>Rotation</b>.
     * @param rotationRoll  the magnitude of the roll of the <b>Rotation</b>.
     */
    public Rotation(double rotationYaw, double rotationPitch, double rotationRoll) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.rotationRoll = rotationRoll;
    }

    /**
     * Constructs a newly allocated <b>Rotation</b> object,
     * with a roll of <b>\u03B8=0.0</b>.
     *
     * @param rotationYaw   the magnitude of the yaw of the <b>Rotation</b>.
     * @param rotationPitch the magnitude of the pitch of the <b>Rotation</b>.
     */
    public Rotation(double rotationYaw, double rotationPitch) {
        this(rotationYaw, rotationPitch, 0);
    }

    /**
     * Constructs a newly allocated <b>Rotation</b> object,
     * with a yaw and roll of <b>\u03B8=0.0</b>.
     *
     * @param rotationYaw the magnitude of the yaw of the <b>Rotation</b>.
     */
    public Rotation(double rotationYaw) {
        this(rotationYaw, 0, 0);
    }

    /**
     * Returns the magnitude of the <b>Rotation</b>.
     * <p>
     * One use case for this is to sort \u0394<b>Rotation</b>s.
     *
     * @return the magnitude of the <b>Rotation</b>
     */
    @Override
    public double length() {
        if (length == -1) {
            length = Math.sqrt(this.rotationYaw * this.rotationYaw + this.rotationPitch * this.rotationPitch + this.rotationRoll * this.rotationRoll);
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
        return this.rotationYaw + this.rotationPitch + this.rotationRoll;
    }

    /**
     * Breaks this <b>Direction</b> into its underlying components.
     *
     * @return the components of this <b>Direction</b>
     */
    @Override
    public double[] components() {
        return new double[]{rotationYaw, rotationPitch, rotationRoll};
    }

    /**
     * Compares this <b>Rotation</b> to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a <b>Rotation</b>
     * object that represents the same rotation angles as this <b>Rotation</b>.
     *
     * @param other the object to compare this <b>Rotation</b> against
     * @return {@code true} if the given object represents a <b>Rotation</b>
     * equivalent to this <b>Rotation</b>, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Rotation) &&
                ((Rotation) other).rotationYaw == this.rotationYaw &&
                ((Rotation) other).rotationPitch == this.rotationPitch &&
                ((Rotation) other).rotationRoll == this.rotationRoll;
    }

    /**
     * Returns the unit <b>Rotation</b> parallel to this <b>Rotation</b>.
     *
     * @return Nothing.
     * @throws UnsupportedOperationException <b>Rotation</b> {@code normalize} is meaningless.
     */
    public Rotation normalize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of the unit <b>Rotation</b> parallel to this <b>Rotation</b>.
     *
     * @param length the desired length of the <b>Rotation</b>.
     * @return Nothing.
     * @throws UnsupportedOperationException <b>Rotation</b> {@code normalize} is meaningless.
     */
    @Override
    public Rotation normalize(double length) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a scalar multiple of this <b>Vector</b>.
     *
     * @param scaleFactor the desired scale factor for the <b>Vector</b>.
     * @return Nothing.
     * @throws UnsupportedOperationException <b>Rotation</b> {@code scale} is meaningless.
     */
    @Override
    public Rotation scale(double scaleFactor) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the <b>Rotation</b> that is the sum of this <b>Rotation</b> and {@code addend}.
     *
     * @param addend the <b>Rotation</b> to be added to this <b>Rotation</b>.
     * @return the <b>Rotation</b>
     */
    @Override
    public Rotation add(Rotation addend) {
        return new Rotation(this.rotationYaw + addend.rotationYaw, this.rotationPitch + addend.rotationPitch, this.rotationRoll + addend.rotationRoll);
    }

    /**
     * Returns the <b>Rotation</b> that is the difference of this <b>Rotation</b> and {@code subtrahend}.
     *
     * @param subtrahend the <b>Rotation</b> to be subtracted from this <b>Rotation</b>.
     * @return the <b>Rotation</b>
     */
    @Override
    public Rotation subtract(Rotation subtrahend) {
        return new Rotation(this.rotationYaw - subtrahend.rotationYaw, this.rotationPitch - subtrahend.rotationPitch, this.rotationRoll - subtrahend.rotationRoll);
    }

    /**
     * Returns the <b>Rotation</b> that is this <b>Rotation</b> with floored coordinates.
     *
     * @return the <b>Rotation</b>
     */
    @Override
    public Rotation floor() {
        return new Rotation(Math.floor(this.rotationYaw), Math.floor(this.rotationPitch), Math.floor(this.rotationRoll));
    }

    /**
     * Returns the <b>Rotation</b> that is this <b>Rotation</b> with ceilinged coordinates.
     *
     * @return the <b>Rotation</b>
     */
    @Override
    public Rotation ceil() {
        return new Rotation(Math.ceil(this.rotationYaw), Math.ceil(this.rotationPitch), Math.ceil(this.rotationRoll));
    }

    /**
     * Returns the <b>Rotation</b> that is anti-parallel to this <b>Rotation</b>.
     *
     * @return the <b>Rotation</b>
     */
    @Override
    public Rotation reverse() {
        return new Rotation(-this.rotationYaw, -this.rotationPitch, -this.rotationRoll);
    }

    /**
     * Converts a <b>Rotation</b> with angles in radians to angles in degrees.
     *
     * @return the <b>Rotation</b>
     */
    public Rotation toDegrees() {
        return new Rotation(Math.toDegrees(this.rotationYaw), Math.toDegrees(this.rotationPitch), Math.toDegrees(this.rotationRoll));
    }

    /**
     * Converts a <b>Rotation</b> with angles in degrees to angles in radians.
     *
     * @return the <b>Rotation</b>
     */
    public Rotation toRadians() {
        return new Rotation(Math.toRadians(this.rotationYaw), Math.toRadians(this.rotationPitch), Math.toRadians(this.rotationRoll));
    }

    /**
     * Returns the <b>Rotation</b> with angles restricted to <b>\u03B8\u2208[-180\u00B0, 180\u00B0)</b>
     *
     * @return the <b>Rotation</b>
     */
    public Rotation wrapDegrees() {
        return new Rotation(MathUtilities.wrapDegrees(this.rotationYaw), MathUtilities.wrapDegrees(this.rotationPitch), MathUtilities.wrapDegrees(this.rotationRoll));
    }

    /**
     * Returns the <b>Rotation</b> with angles restricted to <b>\u03B8\u2208[-\u03C0, \u03C0)</b>
     *
     * @return the <b>Rotation</b>
     */
    public Rotation wrapRadians() {
        return new Rotation(MathUtilities.wrapRadians(this.rotationYaw), MathUtilities.wrapRadians(this.rotationPitch), MathUtilities.wrapRadians(this.rotationRoll));
    }
}
