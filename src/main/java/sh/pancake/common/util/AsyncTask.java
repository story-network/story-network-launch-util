/*
 * Created on Sat Sep 26 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util;

import java.util.concurrent.*;

public class AsyncTask<T> {

    private final static ExecutorService executor;

    static {
        executor = Executors.newCachedThreadPool();
    }

    public static <S>AsyncTask<S[]> all(AsyncTask<S>[] list) {
        Object[] resList = new Object[list.length];

        int length = list.length;
        for (int i = 0; i < length; i++) {
            int index = i;
            AsyncTask<S> task = list[i];

            task.then((S res) -> { resList[index] = res; });
        }
        
        return new AsyncTask<>(() -> {

            for (AsyncTask<S> task : list) {
                if (!task.internalTask.isDone()) task.internalTask.join();
            }

            return (S[]) resList;
        });
    }

    private AsyncFunction<T> func;
    private CompletableFuture<T> internalTask;

    private AsyncNext<T> onDone;
    private AsyncExcept onExcept;

    private volatile T result;
    private volatile Throwable throwable;

    public AsyncTask(AsyncFunction<T> func) {
        this.func = func;

        this.onDone = null;
        this.onExcept = null;

        this.internalTask = CompletableFuture.supplyAsync(this::invokeTask, executor);
    }

    private T invokeTask() {
        result = null;
        throwable = null;

        try {
            result = func.run();
        } catch (Throwable t) {
            throwable = t;
        }

        if (result != null && onDone != null) {
            onDone.then(result);
            result = null;
        }
        
        if (throwable != null) {
            onExcept.except(throwable);
            throwable = null;
        }

        return result;
    }

    public AsyncTask<T> then(AsyncNext<T> onComplete) {
        this.onDone = onComplete;

        if (result != null) {
            onDone.then(result);
            result = null;
        }

        return this;
    }

    public AsyncTask<T> except(AsyncExcept onExcept) {
        this.onExcept = onExcept;

        if (throwable != null) {
            onExcept.except(throwable);
            throwable = null;
        }

        return this;
    }

    @FunctionalInterface
    public static interface AsyncFunction<T> {

        T run() throws Throwable;

        public default <A>A await(AsyncTask<A> task) throws Throwable {
            return task.internalTask.join();
        }

    }

    @FunctionalInterface
    public interface AsyncNext<T> {
        void then(T result);
    }

    @FunctionalInterface
    public interface AsyncExcept {
        void except(Throwable throwable);
    }

}
