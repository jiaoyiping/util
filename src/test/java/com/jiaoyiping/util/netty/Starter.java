package com.jiaoyiping.util.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created with Intellij IDEA
 * USER: 焦一平
 * Date: 2015/2/15
 * Time: 13:16
 * To change this template use File | Settings | File Template
 */
public class Starter {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap(
                                               new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        serverBootstrap .setPipelineFactory(new ChannelPipelineFactory() { @Override public ChannelPipeline getPipeline() throws Exception { return Channels.pipeline(new HelloServerHandler()); } });
        serverBootstrap.bind(new InetSocketAddress(8000));
    }
    private static class HelloServerHandler extends SimpleChannelHandler {
        @Override
        public void channelConnected( ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("Hello world, I'm server.");
        }

    }
}
