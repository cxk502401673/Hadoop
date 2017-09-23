package Zookeeper.Server;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;



public class Server {
        private String connectString="192.168.0.10,192.168.0.11,192.168.0.12";
        private Integer sessionTimeout=6000;
        ZooKeeper zooKeeper=null;
        private static CountDownLatch countDownLatch=new CountDownLatch(1);

    public Server(){
        try {
            zooKeeper =  new ZooKeeper(connectString,sessionTimeout,new mywWatcher());
            System.out.println("状态："+zooKeeper.getState());
            countDownLatch.await();
            System.out.println("状态："+zooKeeper.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class mywWatcher implements Watcher
    {

        public void process(WatchedEvent watchedEvent) {
            //监听连接状态
           if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
               countDownLatch.countDown();
           }
        }
    }
}
