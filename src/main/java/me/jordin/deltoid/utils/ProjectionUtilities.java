package me.jordin.deltoid.utils;

import me.jordin.deltoid.geometry.Line;
import me.jordin.deltoid.vector.Rotation;
import me.jordin.deltoid.vector.Vec3;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public class ProjectionUtilities {
    private static final double TAU_OVER_4 = Math.PI / 2.0;

    /**
     * Returns the <b>Rotation</b> required to have a view
     * centred at the <b>origin</b> intersect <b>offset</b>.
     *
     * @param offset the location to face.
     * @return the <b>Rotation</b> required to be facing <b>offset</b>.
     */
    public static Rotation faceOffset(Vec3 offset) {
        double distance = Math.sqrt((offset.x * offset.x) + (offset.z * offset.z));
        double yaw = Math.atan2(offset.z, offset.x) - TAU_OVER_4;
        double pitch = -Math.atan2(offset.y, distance);

        return new Rotation(yaw, pitch);
    }

    /**
     * Returns the <b>Rotation</b> required to have a view
     * centred at the <b>origin</b> intersect <b>offset</b>.
     *
     * @param offset the location to face.
     * @return the <b>Rotation</b> required to be facing <b>offset</b>.
     */
    public static Rotation faceOffsetDeg(Vec3 offset) {
        double distance = Math.sqrt((offset.x * offset.x) + (offset.z * offset.z));
        double yaw = Math.toDegrees(Math.atan2(offset.z, offset.x)) - 90.0;
        double pitch = -Math.toDegrees(Math.atan2(offset.y, distance));

        return new Rotation(yaw, pitch);
    }

    /**
     * Returns the <b>Vec3</b> that is closest to the "crosshair"
     * centred at <b>eyePos</b> with look vector <b>lookVec</b>.
     *
     * @param eyePos      the location of the eye position.
     * @param lookVec     a {@code normalize}d <b>Vec3</b> representing the camera's look vector.
     * @param maxDistance the maximum distance to be considered.
     * @param options     a <b>List</b> of the potential <b>Vec3</b>s to be considered.
     * @return the closest <b>Vec3</b> to an extended <b>lookVec</b>.
     */
    public static Optional<Vec3> closestToLook(Vec3 eyePos, Vec3 lookVec, double maxDistance, List<Vec3> options) {
        Optional<Vec3> best = Optional.empty();
        double closest = maxDistance;

        for (Vec3 potentialVec3 : options) {
            Vec3 offset = potentialVec3.subtract(eyePos);
            double distance = offset.length();

            if (distance > maxDistance) {
                continue;
            }

            Vec3 projected = offset.subtract(lookVec.scale(distance));
            double newDistance = projected.length();

            if (newDistance < closest) {
                best = Optional.of(potentialVec3);
                closest = newDistance;
            }
        }
        return best;
    }

    /**
     * Returns the <b>Line</b> connecting the pair
     * of the closest <b>start</b> and <b>end</b> positions.
     *
     * @param startOptions a <b>List</b> of the potential <b>start</b> locations to be considered.
     * @param endOptions   a <b>List</b> of the potential <b>end</b> locations to be considered.
     * @param maxDistance  the maximum distance to be considered.
     * @return the <b>Line</b> with the smallest length connecting a <b>start</b> location to an <b>end</b> location
     */
    public static Optional<Line> optimalPath(List<Vec3> startOptions, List<Vec3> endOptions, double maxDistance) {
        Optional<Line> best = Optional.empty();
        double bestDistance = maxDistance;
        for (Vec3 potentialStart : startOptions) {
            for (Vec3 potentialEnd : endOptions) {
                double currentDistance = potentialEnd.subtract(potentialStart).length();
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                    best = Optional.of(new Line(potentialStart, potentialEnd));
                }
            }
        }

        return best;
    }
}
