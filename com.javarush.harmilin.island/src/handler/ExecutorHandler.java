package handler;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Data
public class ExecutorHandler {
    @Getter
    private ExecutorService executorService;
    @Getter
    private ScheduledExecutorService scheduler;
    private List<ExecutorService> executors;

    public ExecutorHandler() {
        executorService = Executors.newFixedThreadPool(3);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        executors = List.of(executorService, scheduler);
    }

    public void shutdownAllExecutors(){
        for (ExecutorService executorService1 : executors){
            executorService1.shutdown();
        }
    }

    public void restartExecutors(){
        executorService = Executors.newFixedThreadPool(3);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        executors = List.of(executorService, scheduler);
    }
}
