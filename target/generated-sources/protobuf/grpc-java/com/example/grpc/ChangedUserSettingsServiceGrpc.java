package com.example.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: ChangedUserSettingsService.proto")
public final class ChangedUserSettingsServiceGrpc {

  private ChangedUserSettingsServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.ChangedUserSettingsService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.UserName,
      com.example.grpc.Empty> METHOD_SEND_CHANGED_USER_SETTINGS =
      io.grpc.MethodDescriptor.<com.example.grpc.UserName, com.example.grpc.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.grpc.ChangedUserSettingsService", "sendChangedUserSettings"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.UserName.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.Empty,
      com.example.grpc.UserName> METHOD_RECIVE_CHANGED_USER_SETTINGS =
      io.grpc.MethodDescriptor.<com.example.grpc.Empty, com.example.grpc.UserName>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.grpc.ChangedUserSettingsService", "reciveChangedUserSettings"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.Empty.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.UserName.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChangedUserSettingsServiceStub newStub(io.grpc.Channel channel) {
    return new ChangedUserSettingsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChangedUserSettingsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChangedUserSettingsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChangedUserSettingsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChangedUserSettingsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChangedUserSettingsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendChangedUserSettings(com.example.grpc.UserName request,
        io.grpc.stub.StreamObserver<com.example.grpc.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEND_CHANGED_USER_SETTINGS, responseObserver);
    }

    /**
     */
    public void reciveChangedUserSettings(com.example.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.UserName> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECIVE_CHANGED_USER_SETTINGS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SEND_CHANGED_USER_SETTINGS,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.UserName,
                com.example.grpc.Empty>(
                  this, METHODID_SEND_CHANGED_USER_SETTINGS)))
          .addMethod(
            METHOD_RECIVE_CHANGED_USER_SETTINGS,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.Empty,
                com.example.grpc.UserName>(
                  this, METHODID_RECIVE_CHANGED_USER_SETTINGS)))
          .build();
    }
  }

  /**
   */
  public static final class ChangedUserSettingsServiceStub extends io.grpc.stub.AbstractStub<ChangedUserSettingsServiceStub> {
    private ChangedUserSettingsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChangedUserSettingsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChangedUserSettingsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChangedUserSettingsServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendChangedUserSettings(com.example.grpc.UserName request,
        io.grpc.stub.StreamObserver<com.example.grpc.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND_CHANGED_USER_SETTINGS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reciveChangedUserSettings(com.example.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.example.grpc.UserName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECIVE_CHANGED_USER_SETTINGS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChangedUserSettingsServiceBlockingStub extends io.grpc.stub.AbstractStub<ChangedUserSettingsServiceBlockingStub> {
    private ChangedUserSettingsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChangedUserSettingsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChangedUserSettingsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChangedUserSettingsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.Empty sendChangedUserSettings(com.example.grpc.UserName request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEND_CHANGED_USER_SETTINGS, getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.UserName reciveChangedUserSettings(com.example.grpc.Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECIVE_CHANGED_USER_SETTINGS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChangedUserSettingsServiceFutureStub extends io.grpc.stub.AbstractStub<ChangedUserSettingsServiceFutureStub> {
    private ChangedUserSettingsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChangedUserSettingsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChangedUserSettingsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChangedUserSettingsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.Empty> sendChangedUserSettings(
        com.example.grpc.UserName request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND_CHANGED_USER_SETTINGS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.UserName> reciveChangedUserSettings(
        com.example.grpc.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECIVE_CHANGED_USER_SETTINGS, getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_CHANGED_USER_SETTINGS = 0;
  private static final int METHODID_RECIVE_CHANGED_USER_SETTINGS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChangedUserSettingsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChangedUserSettingsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_CHANGED_USER_SETTINGS:
          serviceImpl.sendChangedUserSettings((com.example.grpc.UserName) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.Empty>) responseObserver);
          break;
        case METHODID_RECIVE_CHANGED_USER_SETTINGS:
          serviceImpl.reciveChangedUserSettings((com.example.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.UserName>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ChangedUserSettingsServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.ChangedUserSettingsServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChangedUserSettingsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChangedUserSettingsServiceDescriptorSupplier())
              .addMethod(METHOD_SEND_CHANGED_USER_SETTINGS)
              .addMethod(METHOD_RECIVE_CHANGED_USER_SETTINGS)
              .build();
        }
      }
    }
    return result;
  }
}
