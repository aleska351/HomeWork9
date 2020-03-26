package com.company;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *The class Optional<T> - a container object which may or may not contain a non-null value. If a value is present, isPresent() returns true.
 * If no value is present, the object is considered empty and isPresent() returns false.
 * Additional methods that depend on the presence or absence of a contained value are provided, such as orElse()
 * (returns a default value if no value is present) and ifPresent() (performs an action if a value is present).
 *
 * This is a value-based class; use of identity-sensitive operations (including reference equality (==), identity hash code, or synchronization) on instances of Optional may have unpredictable results and should be avoided.
 * @param <T> T - the type of value
 */
public class Optional<T> {
    private T data;

    private Optional(T data) {
        this.data = data;
    }

    /**
     * If a value is present, returns true, otherwise false.
     *
     * @return true if a value is present, otherwise false.
     */
    public boolean isPresent() {
        boolean isPres = false;
        if (data != null) {
            isPres = true;
        }
        return isPres;
    }

    /**
     * If a value is present, returns the value, otherwise throws NoSuchElementException.
     *
     * @return the non-null value described by this Optional.
     * @throws NoSuchElementException
     */
    public T getData() throws NoSuchElementException {
        if (data == null) {
            throw new NoSuchElementException();
        }
        return data;

    }

    /**
     * This method returns an Optional describing the given non-null value.
     *
     * @param d   - value - the value to describe, which must be non-null
     * @param <U> - the type of the value
     * @return an Optional with the value present
     * @throws NullPointerException if value is null
     */
    public static <U> Optional<U> of(U d) throws NullPointerException {
        if (d == null) {
            throw new NullPointerException();
        }
        return new Optional<U>(d);
    }

    /**
     * This method returns an Optional describing the given value, if non-null, otherwise returns an empty Optional.
     *
     * @param d   - the possibly-null value to describe.
     * @param <U> - the type of the value.
     * @return an Optional with a present value if the specified value is non-null, otherwise an empty Optional.
     */
    public static <U> Optional<U> ofNullable(U d) {

        if (d == null) {
            return new Optional<>(null);
        }
        return new Optional<U>(d);
    }

    /**
     * This method returns an empty Optional instance. No value is present for this Optional.
     *
     * @param <U>- The type of the non-existent value.
     * @return an empty Optional.
     */
    public static <U> Optional<U> empty() {
        return new Optional<>(null);

    }

    /**
     * If a value is present, returns the value, otherwise returns other.
     *
     * @param other - the value to be returned, if no value is present. May be null.
     * @return the value, if present, otherwise other
     */
    public T orElse(T other) {
        if (data == null) {
            return other;
        }
        return data;
    }

    /**
     * Indicates whether some other object is "equal to" this Optional. The other object is considered equal if:
     * it is also an Optional and;
     * both instances have no value present or;
     * the present values are "equal to" each other via equals().
     *
     * @param other - an object to be tested for equality.
     * @return true if the other object is "equal to" this object otherwise false.
     * @Overrides: equals in class Object.
     */

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Optional)) {
            return false;
        }
        Optional<?> d = (Optional<?>) other;
        return Objects.equals(d.data, this.data);

    }

    /**
     * This method returns the hash code of the value, if present, otherwise 0 (zero) if no value is present.
     *
     * @return hash code value of the present value or 0 if no value is present.
     */
    public int hashCode() {
        if (!(isPresent())) {
            return 0;
        }
        return Objects.hash(data);
    }

    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action - the action to be performed, if a value is present.
     * @throws NullPointerException
     */
    public void ifPresent​(Consumer<? super T> action) throws NullPointerException {
        if (data != null) {
            action.accept(data);
        } else
            throw new NullPointerException();
    }

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier - the supplying function that produces a value to be returned.
     * @return the value, if present, otherwise the result produced by the supplying function.
     * @throws NullPointerException
     */
    public T orElseGet​(Supplier<? extends T> supplier) throws NullPointerException {
        return data != null ? data : supplier.get();
    }

    /**
     * If a value is present, returns the value, otherwise throws NoSuchElementException.
     *
     * @return the non-null value described by this Optional.
     * @throws NoSuchElementException
     */
    public T orElseThrow() throws NoSuchElementException {
        if (!(isPresent())) {
            throw new NoSuchElementException();
        }
        return data;
    }

    /**
     * If a value is present, and the value matches the given predicate, returns an Optional describing the value, otherwise returns an empty Optional.
     *
     * @param predicate - the predicate to apply to a value, if present.
     * @return an Optional describing the value of this Optional, if a value is present and the value matches the given predicate, otherwise an empty Optional.
     * @throws NullPointerException
     */
    public Optional<T> filter​(Predicate<? super T> predicate) throws NullPointerException, NoSuchElementException {
        if (predicate == null) {
            throw new NullPointerException();
        } else if (data != null && predicate.test(data) == true) {
            return this;
        } else return empty();
    }

    /**
     * f a value is present, returns an Optional describing (as if by ofNullable(T)) the result of applying the given mapping function to the value, otherwise returns an empty Optional.
     * If the mapping function returns a null result then this method returns an empty Optional.
     *
     * @param mapper - the mapping function to apply to a value, if present.
     * @param <U>    - The type of the value returned from the mapping function.
     * @return an Optional describing the result of applying a mapping function to the value of this Optional, if a value is present, otherwise an empty Optional.
     * @throws NullPointerException - if the mapping function is null.
     */
    public <U> Optional<U> map​(Function<? super T, ? extends U> mapper) throws NullPointerException {
        if (data == null) {
            return empty();
        } else {
            return Optional.ofNullable(mapper.apply(data));
        }
    }

    /**
     * If a value is present, returns the result of applying the given Optional-bearing mapping function to the value, otherwise returns an empty Optional.
     * This method is similar to map(Function), but the mapping function is one whose result is already an Optional, and if invoked, flatMap does not wrap it within an additional Optional.
     *
     * @param mapper - the mapping function to apply to a value, if present.
     * @param <U>    - The type of value of the Optional returned by the mapping function.
     * @return the result of applying an Optional-bearing mapping function to the value of this Optional, if a value is present, otherwise an empty Optional.
     * @throws NullPointerException - if the mapping function is null or returns a null result
     */
    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) throws NullPointerException {
        if (!isPresent()) {
            return empty();
        } else {
            Optional<U> data1 = (Optional<U>) mapper.apply(data);
            return data1;
        }
    }
}


