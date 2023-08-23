package com.manenkov;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

public class Actor<T> {

    private final ExecutorService executor;
    private final Consumer<Runnable> async;
    private final T object;

    public Actor(final T object) {
        this.executor = Executors.newSingleThreadExecutor(Thread::new);
        this.async = this.executor::submit;
        this.object = object;
    }

    public T object() {
        return this.object;
    }

    public void tell(final Consumer<T> message) {
        this.executor.submit(() -> message.accept(this.object));
    }

    public <R> void ask(final Function<T, R> message, final Consumer<R> replyTo) {
        this.async.accept(() -> {
            final R result = message.apply(object);
            this.async.accept(() -> replyTo.accept(result));
        });
    }
}
