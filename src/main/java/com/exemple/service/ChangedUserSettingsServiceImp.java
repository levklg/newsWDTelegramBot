package com.exemple.service;

import com.example.grpc.ChangedUserSettingsServiceGrpc;
import com.example.grpc.Empty;
import com.example.grpc.UserName;
import io.grpc.stub.StreamObserver;

public class ChangedUserSettingsServiceImp extends ChangedUserSettingsServiceGrpc.ChangedUserSettingsServiceImplBase {


    @Override
    public void sendChangedUserSettings(com.example.grpc.UserName request,
                                        io.grpc.stub.StreamObserver<com.example.grpc.Empty> responseObserver){


        System.out.println("Send: " + request.getUserName());
        responseObserver.onNext(null);
        responseObserver.onCompleted();

    }


    @Override
    public void reciveChangedUserSettings(com.example.grpc.Empty request,
                                          io.grpc.stub.StreamObserver<com.example.grpc.UserName> responseObserver) {
        UserName response = UserName.newBuilder()

                .build();

        responseObserver.onNext(response);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" Recive: " + response.getUserName());

        responseObserver.onCompleted();

    }

}
