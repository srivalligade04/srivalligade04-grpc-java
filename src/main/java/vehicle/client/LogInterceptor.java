package vehicle.client;

import io.grpc.*;
import java.lang.invoke.MethodHandles;

public final class LogInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void sendMessage(ReqT message) {
                 System.out.println("Sending a message");
                 System.out.println(message);

                 System.out.println("With call options");
                 System.out.println(callOptions);
                 super.sendMessage(message);
            }
        };
    }
}
