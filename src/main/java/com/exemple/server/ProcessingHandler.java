package com.exemple.server;

import com.exemple.model.User;
import com.exemple.model.UserSetting;
import com.exemple.service.CustomerServiceImp;
import com.exemple.service.DbServiceUserImpl;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProcessingHandler extends SimpleChannelInboundHandler<String> {
    CustomerServiceImp customerServiceImp = new CustomerServiceImp();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Server connect");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Gson gson = new Gson ();
        if(s.startsWith("/")){
            if(s.startsWith("/findByName")){
                String nameUser = "";
                nameUser =   s.split("\\ ", 2)[1];
                var user = customerServiceImp.findCustomerName(nameUser);
                String gsonUser = gson.toJson(user);
                StringBuilder stringBuilder = new StringBuilder();
                var sendString = stringBuilder.append("/findByName ").append(gsonUser);
                ctx.channel().writeAndFlush(sendString.toString());
            }
            if(s.startsWith("/updateUser")){
                String userGson;
                userGson =   s.split("\\ ", 2)[1];
                User user = gson.fromJson(userGson, User.class);
                System.out.println("update " + user.getUserSetting().isAllnews());
                customerServiceImp.updateUser(user);
            }

        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Client disconnect ");
        ctx.close();
    }

}
