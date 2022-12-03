package vehicle.client;

import com.proto.vehicle.VehicleRequest;
import com.proto.vehicle.VehicleResponse;
import com.proto.vehicle.VehicleServiceGrpc;
import io.grpc.*;
import java.lang.invoke.MethodHandles;

public final class VehicleClient {


    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    private static void connect(ManagedChannel channel) {
        System.out.println("Enter connection...");
        VehicleServiceGrpc.VehicleServiceBlockingStub stub = VehicleServiceGrpc.newBlockingStub(channel);
        VehicleResponse response = stub.connect(VehicleRequest.newBuilder().setPayload("Test Payload").build());
         System.out.println("VehicleResponse: " + response.getResponseString());
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Need one argument to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .intercept(new LogInterceptor())
                .usePlaintext()
                .build();

        if ("connect".equals(args[0])) {
            connect(channel);
        } else {
             System.out.println("Keyword Invalid: " + args[0]);
        }

         System.out.println("Shutting Down");
        channel.shutdown();
    }
}
