package io.grpc.grpcswagger.showcase;

import static java.util.Collections.singletonMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.grpcswagger.showcase.HelloProto.HelloRequest;
import io.grpc.grpcswagger.showcase.HelloProto.HelloResponse;
import io.grpc.grpcswagger.showcase.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;

/**
 * @author zhangjikai
 * Created on 2018-12-30
 */
public class HelloServiceImpl extends HelloServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Receive message: {}", request.getRecipient());
        Map<String, Long> map = singletonMap("hello", 123L);
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Reply: " + request.getRecipient())
                .putAllMap(map)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
