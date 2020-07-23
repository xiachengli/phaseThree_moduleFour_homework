package com.xcl.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *防暴刷
 */
public class IPFilter implements GlobalFilter, Ordered {

    private static Map<String, List<Long>> ipCache = new HashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();

        if (path.startsWith("/api/user/register")) {
            // 从request对象中获取客户端ip
            String clientIp = request.getRemoteAddress().getHostString();

            if (ipCache.containsKey(clientIp)) {
                List<Long> total = ipCache.get(clientIp);
                Long nowTime = System.currentTimeMillis();
                int tempTime = 1 * 60 * 1000;
                Long limitTime = nowTime - tempTime;

                int count = 0;
                for (int i = 0; i < total.size(); i++) {
                    Long costTime = total.get(i);
                    if (limitTime <= costTime) {
                        count++;
                        if (count >= 3) {
                            // 决绝访问
                            response.setStatusCode(HttpStatus.FORBIDDEN); // 状态码
                            String data = "操作次数已达上限，访问拒绝";
                            DataBuffer wrap = null;
                            try {
                                wrap = response.bufferFactory().wrap(data.getBytes("UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            return response.writeWith(Mono.just(wrap));
                        }
                    }
                }
                total.add(nowTime);
            } else {
                //第一次访问
                List<Long> list = new ArrayList<>();
                list.add(System.currentTimeMillis());
                ipCache.put(clientIp, list);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
