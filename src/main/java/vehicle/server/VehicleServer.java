package vehicle.server;

import io.grpc.Server;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static io.grpc.ServerBuilder.*;

public final class VehicleServer {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        Server server = forPort(port)
                .addService(new VehicleServiceImpl())
                .intercept(new LogInterceptor())
                .build();

        server.start();
        System.out.println("Server Started");
         System.out.println("Listening on port: " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Server Stopped");
        }));

        server.awaitTermination();
    }
}
