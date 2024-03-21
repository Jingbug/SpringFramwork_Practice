package com.example.practice.lifecycle;

public class NetworkClient /*implements InitializingBean, DisposableBean */{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 ,url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    public void disconnect() {
        System.out.println("close = " + url);
    }

    public void destroy() {
        disconnect();
    }

    public void init(){
        connect();
        call("초기화 연결 메세지");
    }
}
