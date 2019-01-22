package in.jord.deltoid.vector;

import com.google.gson.annotations.SerializedName;

public class Vec3 implements Vector<Vec3> {
    /**
     * A {@link Vec3} with coordinates <b>[0, 0, 0]</b>.
     */
    public static final Vec3 ORIGIN = new Vec3(0, 0, 0);

    /**
     * A {@link Vec3} with coordinates <b>[1, 1, 1]</b>.
     */
    public static final Vec3 ONE = new Vec3(1, 1, 1);

    /**
     * A unit {@link Vec3} parallel to the <b>x</b>-axis.
     */
    public static final Vec3 X_AXIS = new Vec3(1, 0, 0);

    /**
     * A unit {@link Vec3} parallel to the <b>y</b>-axis.
     */
    public static final Vec3 Y_AXIS = new Vec3(0, 1, 0);

    /**
     * A unit {@link Vec3} parallel to the <b>z</b>-axis.
     */
    public static final Vec3 Z_AXIS = new Vec3(0, 0, 1);

    /**
     * A {@link Vec3} with coordinates <b>[0, 0, 0]</b>.
     */
    public static final Vec3 ZERO = ORIGIN;

    /**
     * A unit {@link Vec3} parallel to the <b>x</b>-axis.
     */
    public static final Vec3 I_HAT = X_AXIS;

    /**
     * A unit {@link Vec3} parallel to the <b>y</b>-axis.
     */
    public static final Vec3 J_HAT = Y_AXIS;

    /**
     * A unit {@link Vec3} parallel to the <b>z</b>-axis.
     */
    public static final Vec3 K_HAT = Z_AXIS;

    /**
     * A {@link Vec3} with coordinates <b>[0.5, 0.5, 0.5]</b>.
     *
     * <p>
     * This represents the position of the midpoint of a <b>1x1x1</b> voxel
     * </p>
     *
     */
    public static final Vec3 CENTRE = new Vec3(0.5, 0.5, 0.5);

    /**
     * A {@link Vec3} with coordinates <b>[0.5, 0, 0.5]</b>.
     *
     * <p>
     * This represents the position of the midpoint of a <b>1x1</b> surface parallel to the <b>x</b>-<b>z</b> plane
     * </p>
     *
     */
    public static final Vec3 HORIZONTAL_CENTRE = new Vec3(0.5, 0, 0.5);

    /**
     * The <b>x</b>-component of the {@link Vec3}.
     *
     * @serial
     */
    @SerializedName("x")
    public final double x;

    /**
     * The <b>y</b>-component of the {@link Vec3}.
     *
     * @serial
     */
    @SerializedName("y")
    public final double y;

    /**
     * The <b>z</b>-component of the {@link Vec3}.
     *
     * @serial
     */
    @SerializedName("z")
    public final double z;

    private volatile double length = -1;

    /**
     * Constructs a newly allocated {@link Vec3} object.
     *
     * @param x the magnitude of the <b>x</b>-component of the {@link Vec3}.
     * @param y the magnitude of the <b>y</b>-component of the {@link Vec3}.
     * @param z the magnitude of the <b>z</b>-component of the {@link Vec3}.
     */
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a newly allocated {@link Vec3} object,
     * with a <b>z</b>-coordinate of <b>0.0</b>.
     *
     * @param x the magnitude of the <b>x</b>-component of the {@link Vec3}.
     * @param y the magnitude of the <b>y</b>-component of the {@link Vec3}.
     */
    public Vec3(double x, double y) {
        this(x, y, 0);
    }

    /**
     * Constructs a newly allocated {@link Vec3} object with coordinates <b>[0, 0, 0]</b>
     */
    public Vec3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    private void calculateLength() {
        if (length == -1) {
            length = Math.sqrt(x * x + y * y + z * z);
        }
    }

    /**
     * Returns the magnitude of the {@link Vec3}.
     *
     * @return the magnitude of the {@link Vec3}
     */
    @Override
    public double length() {
        calculateLength();
        return length;
    }

    /**
     * Returns the manhattan (taxicab) length of the {@link Vec3}.
     *
     * @return the manhattan length of the {@link Vec3}
     */
    @Override
    public double manhattan() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    /**
     * Breaks this {@link Vec3} into its underlying components.
     *
     * @return the components of this {@link Vec3}
     */
    @Override
    public double[] components() {
        return new double[]{x, y, z};
    }

    /**
     * Compares this {@link Vec3} to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link Vec3}
     * object that represents the same coordinates as this {@link Vec3}.
     *
     * @param other the object to compare this {@link Vec3} against
     * @return {@code true} if the given object represents a {@link Vec3}
     * equivalent to this {@link Vec3}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Vec3) &&
                ((Vec3) other).x == this.x &&
                ((Vec3) other).y == this.y &&
                ((Vec3) other).z == this.z;
    }

    /**
     * Returns the unit {@link Vec3} parallel to this {@link Vec3}.
     *
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 normalize() {
        calculateLength();
        if (length == 0) {
            return ZERO;
        }
        return new Vec3(x / length, y / length, z / length);
    }

    /**
     * Returns a scalar multiple of the unit {@link Vec3} parallel to this {@link Vec3}.
     *
     * @param length the desired length of the {@link Vec3}.
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 normalize(double length) {
        return normalize().scale(length);
    }

    /**
     * Returns a scalar multiple of this {@link Vec3}.
     *
     * @param scaleFactor the desired scale factor for the {@link Vec3}.
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 scale(double scaleFactor) {
        return new Vec3(x * scaleFactor, y * scaleFactor, z * scaleFactor);
    }

    /**
     * Returns the {@link Vec3} that is the sum of this {@link Vec3} and {@code addend}.
     *
     * @param addend the {@link Vec3} to be added to this {@link Vec3}.
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 add(Vec3 addend) {
        return new Vec3(x + addend.x, y + addend.y, z + addend.z);
    }

    /**
     * Returns the {@link Vec3} that is the difference of this {@link Vec3} and {@code subtrahend}.
     *
     * @param subtrahend the Vector to be subtracted from this {@link Vec3}.
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 subtract(Vec3 subtrahend) {
        return new Vec3(x - subtrahend.x, y - subtrahend.y, z - subtrahend.z);
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} with floored coordinates.
     *
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 floor() {
        return new Vec3(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} with ceilinged coordinates.
     *
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 ceil() {
        return new Vec3(Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    /**
     * Returns the {@link Vec3} that is anti-parallel to this {@link Vec3}.
     *
     * @return the {@link Vec3}
     */
    @Override
    public Vec3 reverse() {
        return new Vec3(-x, -y, -z);
    }


    /**
     * Returns the {@link Vec3} that is the dot product of this {@link Vec3} and {@code vec3}.
     *
     * @param vec3 the {@link Vec3} to be dot multiplied with this {@link Vec3}.
     * @return the {@link Vec3}
     */
    public Vec3 dot(Vec3 vec3) {
        return new Vec3(x * vec3.x, y * vec3.y, z * vec3.z);
    }

    /**
     * Returns the {@link Vec3} that is the cross product of this {@link Vec3} and {@code vec3}.
     *
     * @param vec3 the {@link Vec3} to be crossed with this {@link Vec3}.
     * @return the {@link Vec3}
     */
    public Vec3 cross(Vec3 vec3) {
        return new Vec3(y * vec3.z - z * vec3.y, z * vec3.x - x * vec3.z, x * vec3.y - y * vec3.x);
    }

    /**
     * Returns the {@link Direction} of this {@link Vec3}.
     *
     * @return the {@link Direction}
     */
    public Direction direction() {
        calculateLength();
        double alpha = Math.acos(x / length);
        double beta = Math.acos(y / length);
        double gamma = Math.acos(z / length);
        return new Direction(alpha, beta, gamma);
    }

    /**
     * Returns the {@link Vec3} that is {@code distance} above this {@link Vec3}.
     *
     * @param distance the distance to increase the height of this {@link Vec3}.
     * @return the {@link Vec3}
     */
    public Vec3 up(double distance) {
        return new Vec3(x, y + distance, z);
    }

    /**
     * Returns the {@link Vec3} that is {@code distance} below this {@link Vec3}.
     *
     * @param distance the distance to decrease the height of this {@link Vec3}.
     * @return the {@link Vec3}
     */
    public Vec3 down(double distance) {
        return new Vec3(x, y - distance, z);
    }

    /**
     * Returns the {@link Vec3} with elements raised to the {@code exponent} power.
     *
     * @param exponent the exponent to raise each element to.
     * @return the {@link Vec3}
     */
    public Vec3 powElements(double exponent) {
        return new Vec3(Math.pow(x, exponent), Math.pow(y, exponent), Math.pow(z, exponent));
    }

    /**
     * Returns the {@link Vec3} with elements multiplied by {@code multiplier}.
     *
     * @param multiplier the {@link Vec3} to multiply by.
     * @return the {@link Vec3}
     */
    public Vec3 multiplyElements(Vec3 multiplier) {
        return new Vec3(x * multiplier.x, y * multiplier.y, z * multiplier.z);
    }

    /**
     * Returns the {@link Vec3} with elements multiplied by {@code multiplier}.
     *
     * @param multiplier the number to multiply by.
     * @return the {@link Vec3}
     */
    public Vec3 multiplyElements(double multiplier) {
        return new Vec3(x * multiplier, y * multiplier, z * multiplier);
    }

    /**
     * Returns the {@link Vec3} with elements divided by {@code denominator}.
     *
     * @param denominator the {@link Vec3} to divide by.
     * @return the {@link Vec3}
     */
    public Vec3 divideElements(Vec3 denominator) {
        return new Vec3(x / denominator.x, y / denominator.y, z / denominator.z);
    }

    /**
     * Returns the {@link Vec3} with elements divided by {@code denominator}.
     *
     * @param denominator the number to divide by.
     * @return the {@link Vec3}
     */
    public Vec3 divideElements(double denominator) {
        return new Vec3(x / denominator, y / denominator, z / denominator);
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} rotated {@code theta} radians about the z-axis.
     *
     * @param theta the angle to rotate.
     * @return the {@link Vec3}
     */
    public Vec3 rotateAboutX(double theta) {
        double sin = Math.sin(theta);
        double cos = Math.cos(theta);
        return new Vec3(x, y * cos - z * sin, y * sin + z * cos);
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} rotated {@code theta} radians about the y-axis.
     *
     * @param theta the angle to rotate.
     * @return the {@link Vec3}
     */
    public Vec3 rotateAboutY(double theta) {
        double sin = Math.sin(theta);
        double cos = Math.cos(theta);
        return new Vec3(x * cos + z * sin, y, -x * sin + z * cos);
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} rotated {@code theta} radians about the z-axis.
     *
     * @param theta the angle to rotate.
     * @return the {@link Vec3}
     */
    public Vec3 rotateAboutZ(double theta) {
        double sin = Math.sin(theta);
        double cos = Math.cos(theta);
        return new Vec3(x * cos - y * sin, x * sin + y * cos, z);
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} rotated {@code theta} degrees about the x-axis.
     *
     * @param theta the angle to rotate.
     * @return the {@link Vec3}
     */
    public Vec3 rotateAboutXDeg(double theta) {
        return rotateAboutX(Math.toRadians(theta));
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} rotated {@code theta} degrees about the y-axis.
     *
     * @param theta the angle to rotate.
     * @return the {@link Vec3}
     */
    public Vec3 rotateAboutYDeg(double theta) {
        return rotateAboutY(Math.toRadians(theta));
    }

    /**
     * Returns the {@link Vec3} that is this {@link Vec3} rotated {@code theta} degrees about the z-axis.
     *
     * @param theta the angle to rotate.
     * @return the {@link Vec3}
     */
    public Vec3 rotateAboutZDeg(double theta) {
        return rotateAboutZ(Math.toRadians(theta));
    }

    /**
     * Returns the {@link Vec3} of length {@code length} that has
     * coordinate direction angles of {@code Direction}.
     *
     * @param direction the coordinate direction angles (<i>alpha</i>, <i>beta</i>, <i>gamma</i>).
     * @param length    the length of the resultant {@link Vec3}.
     * @return the {@link Vec3}
     */
    public static Vec3 fromDirection(Direction direction, double length) {
        double x = length * Math.cos(direction.alpha);
        double y = length * Math.cos(direction.beta);
        double z = length * Math.cos(direction.gamma);

        return new Vec3(x, y, z);
    }

    /**
     * Returns the unit {@link Vec3} that is parallel to {@code yaw} and {@code pitch} (radians).
     *
     * @param yaw   the angle about the y-axis.
     * @param pitch the angle above the horizon.
     * @return the {@link Vec3}
     */
    public static Vec3 fromAngles(double yaw, double pitch) {
        return fromAngles(1, yaw, pitch);
    }

    /**
     * Returns the unit {@link Vec3} that is parallel to {@code yaw} and {@code pitch} (degrees).
     *
     * @param yaw   the angle about the y-axis.
     * @param pitch the angle above the horizon.
     * @return the {@link Vec3}
     */
    public static Vec3 fromAnglesDeg(double yaw, double pitch) {
        return fromAnglesDeg(1, yaw, pitch);
    }

    /**
     * Returns a {@link Vec3} (magnitude: {@code length}) that is parallel to {@code yaw} and {@code pitch} (degrees).
     *
     * @param length the desired length of the resultant {@link Vec3}.
     * @param yaw    the angle about the y-axis.
     * @param pitch  the angle above the horizon.
     * @return the {@link Vec3}
     */
    public static Vec3 fromAnglesDeg(double length, double yaw, double pitch) {
        return fromAngles(length, Math.toRadians(yaw), Math.toRadians(pitch));
    }

    /**
     * Returns a {@link Vec3} (magnitude: {@code length}) that is parallel to {@code yaw} and {@code pitch} (radians).
     *
     * @param length the desired length of the resultant {@link Vec3}.
     * @param yaw    the angle about the y-axis.
     * @param pitch  the angle above the horizon.
     * @return the {@link Vec3}
     */
    public static Vec3 fromAngles(double length, double yaw, double pitch) {
        double x = -Math.sin(yaw);
        double z = Math.cos(yaw);

        double hScale = Math.cos(pitch);

        return new Vec3(x * hScale * length, Math.sin(-pitch) * length, z * hScale * length);
    }

    @Override
    public String toString() {
        return String.format("Vec3(x=%f, y=%f, z=%f)", this.x, this.y, this.z);
    }

    public String toSimpleString() {
        return String.format("%.2f %.2f %.2f", this.x, this.y, this.z);
    }

    /**
     * Construct and return a newly-allocated Vec3 object.
     *
     * @param x the magnitude of the <b>x</b>-component of the {@link Vec3}.
     * @param y the magnitude of the <b>y</b>-component of the {@link Vec3}.
     * @param z the magnitude of the <b>z</b>-component of the {@link Vec3}.
     * @return new Vec3 instance
     * @see Vec3#Vec3(double, double, double)
     */
    public static Vec3 of(double x, double y, double z) {
        return new Vec3(x, y, z);
    }

    /**
     * Construct and return a newly-allocated Vec3 object,
     *
     * with a <b>z</b>-coordinate of <b>0.0</b>.
     * @param x the magnitude of the <b>x</b>-component of the {@link Vec3}.
     * @param y the magnitude of the <b>y</b>-component of the {@link Vec3}.
     * @return new Vec3 instance
     * @see Vec3#Vec3(double, double)
     */
    public static Vec3 of(double x, double y) {
        return new Vec3(x, y);
    }
}
