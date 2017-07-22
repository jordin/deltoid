package me.jordin.deltoid.geometry;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.vector.Vec3;

/**
 * Created by Jordin on 6/20/2017.
 * Jordin is still best hacker.
 */
public class Line {
    /**
     * A <b>Line</b> with coordinates <b>[0, 0, 0]</b> and length <b>l = 0.0</b>.
     */
    public static final Line ORIGIN = new Line(Vec3.ORIGIN, Vec3.ORIGIN);

    /**
     * The start position of this <b>Line</b>.
     *
     * @serial
     */
    @SerializedName("start")
    public final Vec3 start;

    /**
     * The end position of this <b>Line</b>.
     *
     * @serial
     */
    @SerializedName("end")
    public final Vec3 end;

    /**
     * The difference between the <b>end</b> and <b>start</b> of this <b>Line</b>.
     * Calculated by <b>delta = end - start</b>
     *
     * @serial
     */
    @SerializedName("delta")
    public final Vec3 delta;

    /**
     * The total length of this <b>Line</b>.
     * Calculated by <b>length = ||<i>delta</i>||</b>
     *
     * @serial
     */
    @SerializedName("length")
    public final double length;

    /**
     * Constructs a newly allocated <b>Line</b> object.
     *
     * @param start the beginning position of the <b>Line</b>.
     * @param end   the ending position of the <b>Line</b>.
     */
    public Line(Vec3 start, Vec3 end) {
        this.start = start;
        this.end = end;
        this.delta = this.end.subtract(this.start);
        this.length = this.delta.length();
    }
}
