/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.netty4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.junit.Test;

public class NettySSLConsumerClientModeTest extends BaseNettyTest {
    private MyServer server;
    
    public void startNettyServer() throws Exception {
        server = new MyServer(getPort());
        server.start();
    }
   
    public void shutdownServer() {
        if (server != null) {
            server.shutdown();
        }
    }

    @Override
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry registry = super.createRegistry();
        registry.bind("ksf", new File("src/test/resources/keystore.jks"));
        registry.bind("tsf", new File("src/test/resources/keystore.jks"));
        return registry;
    }
    
    @Test
    public void testNettyRoute() throws Exception {
        try {
            startNettyServer();
            MockEndpoint receive = context.getEndpoint("mock:receive", MockEndpoint.class);
            receive.expectedBodiesReceived("Bye Willem");
            context.getRouteController().startRoute("sslclient");
            receive.assertIsSatisfied();
        } finally {
            shutdownServer();
        }
        
    }
      
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("netty4:tcp://localhost:{{port}}?textline=true&clientMode=true&ssl=true&passphrase=changeit&keyStoreResource=#ksf&trustStoreResource=#tsf").id("sslclient")
                .process(new Processor() {
                    public void process(final Exchange exchange) {
                        String body = exchange.getIn().getBody(String.class);
                        exchange.getOut().setBody("Bye " + body);
                    }
                }).to("mock:receive").noAutoStartup();
            }
        };
    }
    
    private static class MyServer {
        private int port;
        private ServerBootstrap bootstrap;
        private Channel channel;
        private EventLoopGroup bossGroup;
        private EventLoopGroup workerGroup;
        
        MyServer(int port) {
            this.port = port;
        }

        public void start() throws Exception {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();

            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());

            ChannelFuture cf = bootstrap.bind(port).sync();
            channel = cf.channel();

        }
        
        public void shutdown() {
            channel.disconnect();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        
    }
    
    private static class ServerHandler extends SimpleChannelInboundHandler<String> {
        
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.write("Willem\r\n");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            // Do nothing here
        }
        
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }
    
    private static class ServerInitializer extends ChannelInitializer<SocketChannel> {
        private static final StringDecoder DECODER = new StringDecoder();
        private static final StringEncoder ENCODER = new StringEncoder();
        private static final ServerHandler SERVERHANDLER = new ServerHandler();

        private SSLContext sslContext;

        ServerInitializer() {
            super();
            try {
                // create the SSLContext that will be used to create SSLEngine instances
                char[] pass = "changeit".toCharArray();
                
                KeyManagerFactory kmf;
                kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                
                KeyStore ks = KeyStore.getInstance("JKS");
                try (InputStream ksStream = new FileInputStream(new File("src/test/resources/keystore.jks"))) {
                    ks.load(ksStream, pass);
                }
                kmf.init(ks, pass);
                tmf.init(ks);

                sslContext = SSLContext.getInstance("TLS");

                sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            } catch (NoSuchAlgorithmException | KeyStoreException | CertificateException | IOException | UnrecoverableKeyException | KeyManagementException e) {
                e.printStackTrace();
            }
        }
       
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            // create a new SslHandler to add at the start of the pipeline
            SSLEngine engine = sslContext.createSSLEngine();
            engine.setUseClientMode(false);
            engine.setNeedClientAuth(true);
            pipeline.addLast("ssl", new SslHandler(engine));
            
            // Add the text line codec combination,
            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                    8192, Delimiters.lineDelimiter()));
            // the encoder and decoder are static as these are sharable
            pipeline.addLast("decoder", DECODER);
            pipeline.addLast("encoder", ENCODER);

            // and then business logic.
            pipeline.addLast("handler", SERVERHANDLER);
        }
    }
    
}
