package com.bong.jpaquerydsl.common.util;

import java.util.function.Function;

/**
 * The type Function exception wrapper.
 */
public class FunctionExceptionWrapper {
    /**
     * The interface Function with exception.
     *
     * @param <T>          input
     * @param <R>          output
     * @param <E>          exception
     */
    @FunctionalInterface
    public interface FunctionWithException<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    /**
     * 기존 CheckedException 을 포함한 Exception 을 RuntimeException 으로 변환.
     *
     * @param <T>          input
     * @param <R>          output
     * @param <E>          exception
     * @param function     대상 Function
     * @return the function
     */
    public static <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> function) {
        return arg -> {
            try {
                return function.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * 기존 CheckedException 을 포함한 Exception 을 무시하고, Exception 발생 시 null을 리턴.
     *
     * @param <T>          input
     * @param <R>          output
     * @param <E>          exception
     * @param function     대상 Function
     * @return the function
     */
    public static <T, R, E extends Exception> Function<T, R> wrapperIgnore(FunctionWithException<T, R, E> function) {
        return wrapperIgnore(function, null);
    }

    /**
     * 기존 CheckedException 을 포함한 Exception 을 무시하고, Exception 발생 시 에러 핸들러를 실행.
     *
     * @param <T>          input
     * @param <R>          output
     * @param <E>          exception
     * @param function     대상 Function
     * @param errorHandler 예외 처리를 위한 에러 핸들러 Function
     * @return the function
     */
    public static <T, R, E extends Exception> Function<T, R> wrapperIgnore(FunctionWithException<T, R, E> function, Function<Exception, R> errorHandler) {
        return arg -> {
            try {
                return function.apply(arg);
            } catch (Exception e) {
                if (errorHandler != null) return errorHandler.apply(e);

                System.err.printf("%s - %s%n", e.getClass(), e.getMessage());
                return null;
            }
        };
    }
}
