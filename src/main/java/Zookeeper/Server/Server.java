package Zookeeper.Server;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.File;
import java.util.concurrent.CountDownLatch;



public class Server {
        private String connectString="192.168.0.10,192.168.0.11,192.168.0.12";
        //private String connectString="123.206.213.12:2183,123.206.213.12:2182,123.206.213.12:2181";
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
//向zookepper注册服务器信息
    public void registServerInfo(String hostName) {

        String root="/Hosts";
        String  path=root.concat(File.separator)+hostName;//拼接节点
        try {
            Stat stat= zooKeeper.exists(root,new registWatch());
                if(stat==null){
                    zooKeeper.create(root,"Hosts".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
                }
            zooKeeper.create(path,("hello,"+hostName).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);//EPHEMERAL 临时节点
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }

    public void startServer() {
        System.out.println("========start server========");
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    //内部watch类
    private class mywWatcher implements Watcher
    {

        public void process(WatchedEvent watchedEvent) {
            //监听连接状态
           if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
               countDownLatch.countDown();
           }
        }
    }

    private class registWatch implements  Watcher{

        public void process(WatchedEvent watchedEvent) {
            System.out.println("xxxx");
        }
    }
}
