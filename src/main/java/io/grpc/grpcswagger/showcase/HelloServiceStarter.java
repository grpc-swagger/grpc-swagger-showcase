package io.grpc.grpcswagger.showcase;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.protobuf.services.ProtoReflectionService;

/**
 * @author zhangjikai
 * Created on 2018-12-30
 */
public class HelloServiceStarter {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceStarter.class);
    private static final int SERVER_PORT = 12347;

    public static void main(String[] args) {
        logger.info("Starting grpc server on port: " + SERVER_PORT);
        try {
            Server server = ServerBuilder.forPort(SERVER_PORT)
                    .addService(ServerInterceptors.intercept(new HelloServiceImpl(), new HeaderServerInterceptor()))
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
