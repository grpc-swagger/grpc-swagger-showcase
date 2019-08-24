package io.grpc.grpcswagger.showcase;

import io.grpc.grpcswagger.showcase.TestServiceGrpc.TestServiceImplBase;
import io.grpc.stub.StreamObserver;

/**
 * @author Jikai Zhang
 * @date 2019-08-24
 */
public class TestServiceImpl extends TestServiceImplBase {
    
    @Override
    public void test(TestProto.Request request, StreamObserver<TestProto.Response> responseObserver) {
        TestProto.Response response = TestProto.Response.newBuilder()
                .setMessage("test: " + request.getS())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
