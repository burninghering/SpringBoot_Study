package com.example.server.dto;

public class Req<T> { //바디의 내용이 자꾸 바뀔 것이므로 제네릭 타입으로 받자

    private Header header;
    private T rBody;

    public static class Header {
        private String responseCode;

        public String getResponseCode() { //Getter
            return responseCode;
        }

        public void setResponseCode(String responseCode) { //Setter
            this.responseCode = responseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getrBody() {
        return rBody;
    }

    public void setrBody(T rBody) {
        this.rBody = rBody;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + rBody +
                '}';
    }
}
