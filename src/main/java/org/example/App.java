package org.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9088), 0);
        server.createContext("/", exchange -> {
            URI requestURI = exchange.getRequestURI();
            System.out.println("requestURI = " + requestURI);
            try (BufferedReader r = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                while (r.ready()) {
                    System.out.println(r.readLine());
                }
            }

            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-type", "text/plain");
            String body = "hello world!";
            byte[] bytes = body.getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(bytes);
            }
        });
        server.start();
    }


}
