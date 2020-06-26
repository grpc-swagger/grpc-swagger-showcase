import io.grpc.stub.StreamObserver;

/**
 * @author Jikai Zhang
 * @date 2020-06-26
 */
public class NoPackageServiceImpl extends NoPackageServiceGrpc.NoPackageServiceImplBase {
    
    @Override
    public void test(NoPackageProto.Request request, StreamObserver<NoPackageProto.Response> responseObserver) {
        NoPackageProto.Response response = NoPackageProto.Response.newBuilder()
                .setMessage("test")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
