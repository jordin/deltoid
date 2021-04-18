package in.jord.deltoid.geometry;

import in.jord.deltoid.vector.Vec3;

public class Line {
    /**
     * A {@link Line} with coordinates <b>[0, 0, 0]</b> and length <b>l = 0.0</b>.
     */
    public static final Line ORIGIN = new Line(Vec3.ORIGIN, Vec3.ORIGIN);

    /**
     * The start position of this {@link Line}.
     *
     * @serial
     */
    public final Vec3 start;

    /**
     * The end position of this {@link Line}.
     *
     * @serial
     */
    public final Vec3 end;

    /**
     * The difference between the <b>end</b> and <b>start</b> of this {@link Line}.
     *
     * <p>
     * Calculated by <b>delta = end - start</b>
     * </p>
     *
     * @serial
     */
    public final Vec3 delta;

    /**
     * The total length of this {@link Line}.
     *
     * <p>
     * Calculated by <b>length = ||<i>delta</i>||</b>
     * </p>
     *
     * @serial
     */
    public final double length;

    /**
     * Constructs a newly allocated {@link Line} object.
     *
     * @param start the beginning position of the {@link Line}.
     * @param end   the ending position of the {@link Line}.
     */
    public Line(Vec3 start, Vec3 end) {
        this.start = start;
        this.end = end;
        this.delta = this.end.subtract(this.start);
        this.length = this.delta.length();
    }
}
