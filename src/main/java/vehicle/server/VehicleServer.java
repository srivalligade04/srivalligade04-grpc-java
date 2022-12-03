package vehicle.server;

import io.grpc.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static io.grpc.ServerBuilder.*;

public final class VehicleServer {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        Server server = forPort(port)
                .addService(new VehicleServiceImpl())
                .intercept(new LogInterceptor())
                .build();

        server.start();
        LOG.info("Server Started");
        LOG.info("Listening on port: " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("Received Shutdown Request");
            server.shutdown();
           LOG.info("Server Stopped");
        }));

        server.awaitTermination();
    }
}
