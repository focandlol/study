package basicRe.basicRe.lifecycle;

public class NetworkClient {
    private String url;
    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect:" + url);
    }

    public void call(String message){
        System.out.println("call:" + url + "message =" + message);
    }

    public void disconnect(){
        System.out.println("close");
    }
}
