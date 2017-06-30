package me.jordin.deltoid.region;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.utils.MathUtilities;
import me.jordin.deltoid.utils.UnionUtilities;
import me.jordin.deltoid.vector.Vec2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jordin on 6/29/2017.
 * Jordin is still best hacker.
 */
public class RectangleRegion implements Region<RectangleRegion, Vec2> {
    /**
     * A <b>RectangleRegion</b> with coordinates <b>[0, 0]</b> and area <b>a = 0.0</b>.
     */
    public static final RectangleRegion ORIGIN = new RectangleRegion(Vec2.ORIGIN, Vec2.ORIGIN);

    /**
     * The lower bounds of this <b>RectangleRegion</b>.
     * Calculated by <b>min = Math.min(<i>pos1</i>, <i>pos2</i>)</b>
     *
     * @serial
     */
    @SerializedName("min")
    public final Vec2 min;

    /**
     * The upper bounds of this <b>RectangleRegion</b>.
     * Calculated by <b>max = Math.max(<i>pos1</i>, <i>pos2</i>)</b>.
     *
     * @serial
     */
    @SerializedName("max")
    public final Vec2 max;

    /**
     * The original first position of this <b>RectangleRegion</b>.
     *
     * @serial
     */
    @SerializedName("pos1")
    public final Vec2 pos1;

    /**
     * The original second position of this <b>RectangleRegion</b>.
     *
     * @serial
     */
    @SerializedName("pos2")
    public final Vec2 pos2;

    /**
     * The size of this <b>RectangleRegion</b>.
     * Calculated by <b>dimensions = max - min</b>
     *
     * @serial
     */
    @SerializedName("dimensions")
    public final Vec2 dimensions;

    /**
     * The total surface area of this <b>RectangleRegion</b>.
     *
     * @serial
     */
    @SerializedName("area")
    private final double surfaceArea;

    /**
     * A <b>List</b> of all of the <b>Vec2</b>s enclosed in this <b>RectangleRegion</b>.
     *
     * @serial
     */
    private List<Vec2> enclosedPoints;

    /**
     * Constructs a newly allocated <b>RectangleRegion</b> object.
     *
     * @param pos1 the first position of this <b>RectangleRegion</b>.
     * @param pos2 the second position of this <b>RectangleRegion</b>.
     */
    public RectangleRegion(Vec2 pos1, Vec2 pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;

        this.min = new Vec2(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y));
        this.max = new Vec2(Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y));

        this.dimensions = new Vec2(max.x - min.x, max.y - min.y);

        this.surfaceArea = this.dimensions.x * this.dimensions.y;
    }

    /**
     * Returns the total volume enclosed in this <b>RectangleRegion</b>.
     *
     * @return <b>0.0</b>, Rectangles do not have a volume!
     */
    @Override
    public double volume() {
        return 0;
    }

    /**
     * Returns the total area of this <b>RectangleRegion</b>.
     *
     * @return the total area of this <b>RectangleRegion</b>
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the <b>RectangleRegion</b> has
     * a non-zero area, {@code false} otherwise.
     *
     * @return {@code true} if the <b>RectangleRegion</b> exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.surfaceArea != 0;
    }

    /**
     * Returns {@code true} if the <b>RectangleRegion</b>
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the <b>Vec2</b>to consider.
     * @return {@code true} if the <b>RectangleRegion</b> contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec2 location) {
        return (location.x >= min.x && location.x <= max.x)
                && (location.y >= min.y && location.y <= max.y);
    }

    /**
     * Returns a <b>List</b> of all of the <b>Vec2</b>s enclosed in this <b>RectangleRegion</b>.
     *
     * @return the <b>List</b> of the <b>Vec2</b>s
     */
    @Override
    public List<Vec2> enclosedPoints() {
        if (this.enclosedPoints == null) {
            int startX = MathUtilities.floor(min.x);
            int startY = MathUtilities.floor(min.y);

            int endX = MathUtilities.floor(max.x);
            int endY = MathUtilities.floor(max.y);

            Vec2[] points = new Vec2[(endX - startX + 1) * (endY - startY + 1)];

            int i = 0;
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    points[i] = new Vec2(x, y);
                    i++;
                }
            }

            this.enclosedPoints = Arrays.asList(points);
        }

        return this.enclosedPoints;
    }

    /**
     * Creates the smallest possible <b>RectangleRegion</b>
     * that fully encloses this <b>RectangleRegion</b> and <b>RectangleRegion</b>.
     *
     * @param region the other <b>RectangleRegion</b> to be considered
     * @return the <b>RectangleRegion</b>
     */
    @Override
    public RectangleRegion union(RectangleRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new <b>RectangleRegion</b> translated by <b>offset</b>.
     *
     * @return the new <b>RectangleRegion</b>
     */
    @Override
    public RectangleRegion offset(Vec2 offset) {
        return new RectangleRegion(pos1.add(offset), pos2.add(offset));
    }

    /**
     * Returns a new <b>RectangleRegion</b> expanded by <b>expansion</b>.
     *
     * @return the new <b>RectangleRegion</b>
     */
    public RectangleRegion expand(Vec2 expansion) { // TODO: preserve pos1 and pos2
        Vec2 newMin = new Vec2(min.x - expansion.x, min.y - expansion.y);
        Vec2 newMax = new Vec2(max.x + expansion.x, max.y + expansion.y);

        return new RectangleRegion(newMin, newMax);
    }

    /**
     * Returns a new <b>RectangleRegion</b> expanded in all directions by <b>amount</b>.
     *
     * @return the new <b>RectangleRegion</b>
     */
    public RectangleRegion expand(double amount) { // TODO: preserve pos1 and pos2
        Vec2 newMin = new Vec2(min.x - amount, min.y - amount);
        Vec2 newMax = new Vec2(max.x + amount, max.y + amount);

        return new RectangleRegion(newMin, newMax);
    }
}
