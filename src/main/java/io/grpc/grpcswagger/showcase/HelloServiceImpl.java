package io.grpc.grpcswagger.showcase;

import static java.util.Collections.singletonMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

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

    @Override
    public void getUser(HelloProto.GetUserRequest request, StreamObserver<HelloProto.GetUserResponse> responseObserver) {
        long userId = request.getUserId();
        List<HouseProto.House> houses = new ArrayList<>();
        houses.add(HouseProto.House.newBuilder().setCity("北京").setId(1).build());
        houses.add(HouseProto.House.newBuilder().setCity("海南").setId(2)
                .setPrice(1.2f).build());
        HelloProto.User user = HelloProto.User.newBuilder()
                .setUserId(userId)
                .setAge(0)
                .setScore(1.1)
                .setUserName("user test")
                .putAllProperties(ImmutableMap.of("key", 1L))
                .addAllAlias(Lists.newArrayList("zhangsan", "lisi"))
                .addAllHouses(houses)
                .build();
        HelloProto.GetUserResponse userResponse = HelloProto.GetUserResponse.newBuilder()
                .setStatus(HelloProto.GetUserResponse.Status.OK)
                .setUser(user)
                .build();
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }
    
    
}
