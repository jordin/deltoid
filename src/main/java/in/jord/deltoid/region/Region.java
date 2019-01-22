package in.jord.deltoid.region;

import in.jord.deltoid.vector.Vector;

import java.util.List;

public interface Region<R, V> {
    /**
     * Returns the total volume enclosed in this {@link Region}.
     *
     * @return the total volume of this {@link Region}
     */
    double volume();

    /**
     * Returns the total surface area of this {@link Region}.
     *
     * @return the total surface area of this {@link Region}
     */
    double surfaceArea();

    /**
     * Returns {@code true} if the {@link Region} has
     * a non-zero volume, {@code false} otherwise.
     *
     * @return {@code true} if the {@link Region} exists, {@code false} otherwise
     */
    boolean exists();

    /**
     * Returns {@code true} if the {@link Region}
     * contains <b>location</b>  {@code false} otherwise.
     *
     * @param location the {@link Vector} to consider.
     * @return {@code true} if the {@link Region} contains <b>location</b>, {@code false} otherwise
     */
    boolean contains(V location);

    /**
     * Returns a {@link List} of all of the {@link Vector}s enclosed in this {@link Region}.
     *
     * @return the {@link List} of the {@link Vector}s
     */
    List<V> enclosedPoints();

    /**
     * Creates the smallest possible {@link Region}
     * that fully encloses this {@link Region} and <b>region</b>.
     *
     * @param region the other {@link Region} to be considered
     * @return the {@link Region}
     */
    R union(R region);

    /**
     * Returns a new {@link Region} translated by <b>offset</b>.
     *
     * @param offset the distance to translate this {@link Region}
     * @return the new {@link Region}
     */
    R offset(V offset);
}
