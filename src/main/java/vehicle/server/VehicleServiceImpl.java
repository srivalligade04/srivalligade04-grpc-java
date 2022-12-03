package vehicle.server;


import com.proto.vehicle.VehicleRequest;
import com.proto.vehicle.VehicleResponse;
import com.proto.vehicle.VehicleServiceGrpc;
import io.grpc.stub.StreamObserver;

public final class VehicleServiceImpl extends VehicleServiceGrpc.VehicleServiceImplBase {

   VehicleServiceImpl() {

    }

    @Override
    public void connect(VehicleRequest request, StreamObserver<VehicleResponse> responseObserver) {
        responseObserver.onNext(VehicleResponse.newBuilder().setResponseString("Payload for Vehicle command is: " + request.getPayload()).build());
        responseObserver.onCompleted();
    }

}
