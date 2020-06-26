import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.protobuf.services.ProtoReflectionService;

/**
 * @author Jikai Zhang
 * @date 2020-06-26
 */
public class NoPackageServiceStarter {
    
    private static final Logger logger = LoggerFactory.getLogger(NoPackageServiceStarter.class);
    private static final int SERVER_PORT = 12348;
    
    public static void main(String[] args) {
        logger.info("Starting grpc server on port: " + SERVER_PORT);
        try {
            Server server = ServerBuilder.forPort(SERVER_PORT)
                    .addService(ServerInterceptors.intercept(new NoPackageServiceImpl()))
                    .addService(ProtoReflectionService.newInstance())
                    .build()
                    .start();
            server.awaitTermination();
        } catch (InterruptedException | IOException e) {
            logger.info("Caught exception, shutting down", e);
            System.exit(0);
        }
    }
    
}
