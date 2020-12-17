package room;

import android.os.Handler;
import android.os.Looper;

import androidx.core.util.Consumer;
import androidx.core.util.Supplier;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.http2.Http2Reader;

public class TaskRunner {
        private final Executor threadPoolExecutor = new ThreadPoolExecutor(
                5, 128, 1,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>()
        );

        private final Handler handler = new Handler(Looper.getMainLooper());

        public <R> void executeAsync(Supplier<R> callable, Consumer<R> callback) {
            threadPoolExecutor.execute(() -> {
                R result = callable.get();
                handler.post(()-> {
                    callback.accept(result);
                });
            });
        }
    }
