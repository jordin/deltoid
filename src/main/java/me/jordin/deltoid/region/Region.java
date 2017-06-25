package me.jordin.deltoid.region;

import java.util.List;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public interface Region<R, V> {
    /**
     * Returns the total volume enclosed in this <b>Region</b>.
     *
     * @return the total volume of this <b>Region</b>
     */
    double volume();

    /**
     * Returns the total surface area of this <b>Region</b>.
     *
     * @return the total surface area of this <b>Region</b>
     */
    double surfaceArea();

    /**
     * Returns {@code true} if the <b>Region</b> has
     * a non-zero volume, {@code false} otherwise.
     *
     * @return {@code true} if the <b>Region</b> exists, {@code false} otherwise
     */
    boolean exists();

    /**
     * Returns {@code true} if the <b>Region</b>
     * contains <b>location</b>  {@code false} otherwise.
     *
     * @param location the <b>Vector</b> to consider.
     * @return {@code true} if the <b>Region</b> contains <b>location</b>, {@code false} otherwise
     */
    boolean contains(V location);

    /**
     * Returns a <b>List</b> of all of the <b>Vector</b>s enclosed in this <b>Region</b>.
     *
     * @return the <b>List</b> of the <b>Vector</b>s
     */
    List<V> enclosedPoints();

    /**
     * Creates the smallest possible <b>Region</b>
     * that fully encloses this <b>Region</b> and <b>region</b>.
     *
     * @param region the other <b>Region</b> to be considered
     * @return the <b>Region</b>
     */
    R union(R region);

    /**
     * Returns a new <b>Region</b> translated by <b>offset</b>.
     *
     * @param offset the distance to translate this <b>Region</b>
     * @return the new <b>Region</b>
     */
    R offset(V offset);
}
