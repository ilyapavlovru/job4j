package ru.job4j.pools.threadpool.socketsandhttpexamples;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://dzone.com/articles/simple-http-server-in-java
 * https://dev-gang.ru/article/prostoi-http-server-na-java-8mjy9xrxmt/
 */

public class InBuiltHttpServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/test", new  MyHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
//        logger.info(" Server started on port 8001");
        threadPoolExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    private static class MyHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            System.out.println("Внутри handle");
            String requestParamValue = null;

            if ("GET".equals(httpExchange.getRequestMethod())) {
                System.out.println("Внутри GET обработчика");
                requestParamValue = handleGetRequest(httpExchange);
                System.out.println("requestParamValue = " + requestParamValue);

                // получатель читает сообщение и удаляет его из очереди

            } else if ("POST".equals(httpExchange.getRequestMethod())) {

                System.out.println("Внутри POST обработчика");

                StringBuilder sb = new StringBuilder();
                InputStream ios = httpExchange.getRequestBody();
                int i;
                while ((i = ios.read()) != -1) {
                    sb.append((char) i);
                }
//                System.out.println("hm: " + sb.toString());
                String message = sb.toString();
                System.out.println(message);



//                requestParamValue = handlePostRequest(httpExchange);
                // здесь нужно прочитать тело сообщения

            }

            handleResponse(httpExchange, requestParamValue);
        }

        private String handleGetRequest(HttpExchange httpExchange) {
            return httpExchange.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1]
                    .split("=")[1];
        }

        private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();

            htmlBuilder.append("").
                    append("").
                    append("<h1>").
                    append("Hello ")
                    .append(requestParamValue)
                    .append("</h1>")
                    .append("")
                    .append("");

            // encode HTML content
            String htmlResponse = htmlBuilder.toString();

            // this line is a must
            httpExchange.sendResponseHeaders(200, htmlResponse.length());

            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
}
