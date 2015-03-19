package com.jiaoyiping.util.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created with Intellij IDEA
 * USER: 焦一平
 * Date: 2015/2/15
 * Time: 13:36
 * To change this template use File | Settings | File Template
 */
public class TestClient {
    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap( new NioClientSocketChannelFactory( Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类
         bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloClientHandler());
            }
        });
        bootstrap.connect(new InetSocketAddress( "127.0.0.1", 8000));


    }
    private static class HelloClientHandler extends SimpleChannelHandler {
        @Override public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("客户端已连接");
        }
    }
}
