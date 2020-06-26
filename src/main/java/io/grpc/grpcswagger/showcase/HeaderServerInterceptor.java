package io.grpc.grpcswagger.showcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

/**
 * @author Jikai Zhang
 * @date 2020-06-26
 */
public class HeaderServerInterceptor implements ServerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(HeaderServerInterceptor.class);
    
    private static final Metadata.Key<String> CUSTOM_SERVER_HEADER_KEY =
            Metadata.Key.of("custom_server_header_key", Metadata.ASCII_STRING_MARSHALLER);
    
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            final Metadata requestHeaders,
            ServerCallHandler<ReqT, RespT> next) {
        logger.info("header received from client:" + requestHeaders);
        return next.startCall(call, requestHeaders);
    }
}
