package gameHandler;

/**
 * <p> 1. File-name: Tuple.java</p>
 * <p> 2. Creation Date: (N/A) </p>
 * <p> 3. Last modification date:</p>
 * <p> 4. Purpose of the program: storing objects of different types together</p>
 *
 * @author Jonny
 */

public class Tuple<T1, T2, T3, T4, T5, T6> {
    private final T1 first;
    private final T2 second;
    private final T3 third;
    private final T4 fourth;
    private final T5 fifth;
    private final T6 sixth;

    /**
     * Constructor.
     * <p> side-effects</p>
     * <p> not referentially transparent</p>
     *
     * @param pos takes all six tuples.
     */

    public Tuple(T1 first, T2 second, T3 third, T4 fourth,
                 T5 fifth, T6 sixth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
    }

    /**
     * method to get the first tuple
     *
     * @return frist tuple
     */

    public T1 getFirst() {
        return first;
    }

    /**
     * method to get the second tuple
     *
     * @return second tuple
     */

    public T2 getSecond() {
        return second;
    }

    /**
     * method to get the third tuple
     *
     * @return third tuple
     */

    public T3 getThird() {
        return third;
    }

    /**
     * method to get the fourth tuple
     *
     * @return fourth tuple
     */

    public T4 getFourth() {
        return fourth;
    }

    /**
     * method to get the fifth tuple
     *
     * @return fifth tuple
     */

    public T5 getFifth() {
        return fifth;
    }

    /**
     * method to get the sixth tuple
     *
     * @return sixth tuple
     */

    public T6 getSixth() {
        return sixth;
    }
}
