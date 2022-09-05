package com.company.design.facade;

public class SftpClient {

    private Ftp ftp;
    private Reader reader;
    private Writer writer;

    public SftpClient(Ftp ftp, Reader reader, Writer writer) {//객체를 한번 감싸서, 의존성을 SftpClient가 다 가져감
        this.ftp = ftp;
        this.reader = reader;
        this.writer = writer;
    }

    public SftpClient(String host, int port, String path, String fileName) {
        this.ftp = new Ftp(host, port, path);
        this.reader = new Reader(fileName);
        this.writer = new Writer(fileName);
    }
    //String host,int port, String path,String fileName 4가지 값만 받아도
    //SftpClient 객체를 사용할 수 있도록 오버로딩

    public void connect() { //새로운 인터페이스 제공
        ftp.connect();
        ftp.moveDirectory();
        writer.fileConnect();
        reader.fileRead();
    }

    public void disConnect() {//새로운 인터페이스 제공
        writer.fileDisconnect();
        reader.fileDisconnect();
        ftp.disConnect();
    }

    public void read() {//새로운 인터페이스 제공
        reader.fileRead();
    }

    public void write() {//새로운 인터페이스 제공
        writer.write();
    }

}
