package Zookeeper.Client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class Client {

    private String connectString="192.168.0.10,192.168.0.11,192.168.0.12";
    private Integer sessionTimeout=6000;
    ZooKeeper zooKeeper=null;
    private String root="/Hosts";

    public static void main(String[] args) {
        Client client=new Client();
        try {
            client.getConnect();
            client.handleBussiness();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getConnect() throws  Exception{
        zooKeeper=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void getServerList() throws  Exception{
       List<String> childrenList= zooKeeper.getChildren(root,true);
        for(String s:childrenList){
            System.out.println(s);
        }

    }

    /**
     * 业务功能
     *
     * @throws InterruptedException
     */
    public void handleBussiness() throws InterruptedException {
        System.out.println("client start working.....");
        Thread.sleep(Long.MAX_VALUE);
    }
}
