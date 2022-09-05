package com.company.design.aop;

import com.company.design.proxy.Html;
import com.company.design.proxy.IBrowser;

public class AopBrowser implements IBrowser {
    private String url;
    private Html html;
    private Runnable before;
    private Runnable after;

    public AopBrowser(String url, Runnable before, Runnable after) {
        this.url = url;
        this.before = before;
        this.after = after;
    }

    @Override
    public Html show() {
        before.run();
        if (html == null) {
            this.html = new Html(url);
            System.out.println("AopBrowser html loading from : " + url); //메소드가 실행되는 시간 관측 위해 run() 앞뒤로
            try {
                Thread.sleep(1500); //인위적으로 html 로딩에 1.5초 걸리게 만듦
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        after.run();
        System.out.println("AopBrowser html cache: " + url);
        return html;
    }
}
