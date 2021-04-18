package in.jord.deltoid.utils;

import in.jord.deltoid.vector.Rotation;
import in.jord.deltoid.vector.Vec2;
import in.jord.deltoid.vector.Vec3;

public class MathUtilities {
    private static final double TAU = 2 * Math.PI;

    /**
     * Returns a {@link Vec2} of the weighted average of
     * <b>previous</b> and <b>current</b>, using a weight of <b>ratio</b>.
     *
     * @param previous the first {@link Vec2} to be considered.
     * @param current  the second {@link Vec2} to be considered.
     * @param ratio    the weight for the weighted average.
     * @return the interpolated {@link Vec2}
     */
    public static Vec2 interpolate(Vec2 previous, Vec2 current, double ratio) {
        if (current == null && previous == null) {
            return Vec2.ORIGIN;
        }
        if (previous == null) {
            return current;
        }
        if (current == null) {
            return previous;
        }

        return previous.add(current.subtract(previous).scale(ratio));
    }


    /**
     * Returns a {@link Vec3} of the weighted average of
     * <b>previous</b> and <b>current</b>, using a weight of <b>ratio</b>.
     *
     * @param previous the first {@link Vec3} to be considered.
     * @param current  the second {@link Vec3} to be considered.
     * @param ratio    the weight for the weighted average.
     * @return the interpolated {@link Vec3}
     */
    public static Vec3 interpolate(Vec3 previous, Vec3 current, double ratio) {
        if (current == null && previous == null) {
            return Vec3.ORIGIN;
        }
        if (previous == null) {
            return current;
        }
        if (current == null) {
            return previous;
        }

        return previous.add(current.subtract(previous).scale(ratio));
    }

    /**
     * Returns a {@link Rotation} of the weighted average of
     * <b>previous</b> and <b>current</b>, using a weight of <b>ratio</b>.
     *
     * @param previous the first {@link Rotation} to be considered.
     * @param current  the second {@link Rotation} to be considered.
     * @param ratio    the weight for the weighted average.
     * @return the interpolated {@link Rotation}
     */
    public static Rotation interpolate(Rotation previous, Rotation current, double ratio) {
        if (current == null && previous == null) {
            return Rotation.ORIGIN;
        }
        if (previous == null) {
            return current;
        }
        if (current == null) {
            return previous;
        }

        return new Rotation(interpolateRotation(previous.rotationYaw, current.rotationYaw, ratio),
                interpolateRotation(previous.rotationPitch, current.rotationPitch, ratio),
                interpolateRotation(previous.rotationRoll, current.rotationRoll, ratio));
    }

    /**
     * Returns a {@link Rotation} of the weighted average of
     * <b>previous</b> and <b>current</b>, using a weight of <b>ratio</b>.
     *
     * @param previous the first {@link Rotation} to be considered.
     * @param current  the second {@link Rotation} to be considered.
     * @param ratio    the weight for the weighted average.
     * @return the interpolated {@link Rotation}
     */
    public static Rotation interpolateDeg(Rotation previous, Rotation current, double ratio) {
        if (current == null && previous == null) {
            return Rotation.ORIGIN;
        }
        if (previous == null) {
            return current;
        }
        if (current == null) {
            return previous;
        }

        return new Rotation(interpolateRotationDeg(previous.rotationYaw, current.rotationYaw, ratio),
                interpolateRotationDeg(previous.rotationPitch, current.rotationPitch, ratio),
                interpolateRotationDeg(previous.rotationRoll, current.rotationRoll, ratio));
    }

    /**
     * Returns a weighted average of <b>previous</b> and <b>current</b>,
     * using a weight of <b>ratio</b>.
     *
     * @param previous the first value to be considered.
     * @param current  the second value to be considered.
     * @param ratio    the weight for the weighted average.
     * @return the interpolated value
     */
    public static double interpolate(double previous, double current, double ratio) {
        return previous + ((current - previous) * ratio);
    }

    /**
     * Returns a weighted average of <b>previousRotation</b>
     * and {@link Rotation}, using a weight of <b>ratio</b>.
     *
     * @param previousRotation the first rotation to be considered.
     * @param rotation         the second rotation to be considered.
     * @param ratio            the weight for the weighted average.
     * @return the interpolated rotation
     */
    public static double interpolateRotation(double previousRotation, double rotation, double ratio) {
        double delta = MathUtilities.wrapRadians(rotation - previousRotation);

        return previousRotation + (delta * ratio);
    }


    /**
     * Returns a weighted average of <b>previousRotation</b>
     * and {@link Rotation}, using a weight of <b>ratio</b>.
     *
     * @param previousRotation the first rotation to be considered.
     * @param rotation         the second rotation to be considered.
     * @param ratio            the weight for the weighted average.
     * @return the interpolated rotation
     */
    public static double interpolateRotationDeg(double previousRotation, double rotation, double ratio) {
        double delta = MathUtilities.wrapDegrees(rotation - previousRotation);

        return previousRotation + (delta * ratio);
    }


    /**
     * Returns the <b>angle</b> restricted to:
     * <p>
     * <b>θ ∈ [-180°, 180°)</b>
     *
     * @param angle to be restricted.
     * @return the wrapped <b>angle</b>
     */
    public static double wrapDegrees(double angle) {
        angle = angle % 360.0D;
        if (angle >= 180.0D) {
            angle -= 360.0D;
        }

        if (angle < -180.0D) {
            angle += 360.0D;
        }
        return angle;
    }

    /**
     * Returns the <b>angle</b> restricted to:
     * <p>
     * <b>θ ∈ [-π, π)</b>
     *
     * @param angle to be restricted.
     * @return the wrapped <b>angle</b>
     */
    public static double wrapRadians(double angle) {
        angle = angle % TAU;
        if (angle >= Math.PI) {
            angle -= TAU;
        }

        if (angle < -Math.PI) {
            angle += TAU;
        }
        return angle;
    }

    /**
     * Returns the result of <b>floor(value)</b> as an integer.
     *
     * @param value the value to floor
     * @return the result of floor(value) as an int primitive.
     */
    public static int floor(double value) {
        return (int) Math.floor(value);
    }
}
