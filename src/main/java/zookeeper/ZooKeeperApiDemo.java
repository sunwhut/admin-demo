package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class ZooKeeperApiDemo {
    private ZooKeeper zk = null;
    private static final String quorumString = "127.0.0.1:2181";
    private static final int sessionTimeOut = 2000;

    private void getZkClient() throws IOException {
        zk = new ZooKeeper(quorumString, sessionTimeOut, null);
    }

    public void createNode(String node) throws KeeperException, InterruptedException {
        zk.create(node, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void exists(String node) throws KeeperException, InterruptedException {
        Stat stat = zk.exists(node, new DefaultWatcher());
        System.out.println(stat == null ? node + " not exists" : node + " exists");
    }

    public void getChildren(String node) throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(node, new DefaultWatcher());
        for (String child : children) {
            System.out.println(child);
        }
    }

    public void setData(String node, String data) throws KeeperException, InterruptedException {
        zk.setData(node, data.getBytes(), -1);
    }

    public void getData(String node) throws KeeperException, InterruptedException {
        byte[] data = zk.getData(node, new DefaultWatcher(), null);
        System.out.println(node + " data: " + new String(data));
    }

    public void deleteNode(String node) throws KeeperException, InterruptedException {
        zk.delete(node, -1);
    }

    public static void main(String[] args) throws Exception {
        ZooKeeperApiDemo zooKeeperApiDemo = new ZooKeeperApiDemo();
        zooKeeperApiDemo.getZkClient();
//        zooKeeperApiDemo.exists("/servers");
//        zooKeeperApiDemo.createNode("/servers");
        zooKeeperApiDemo.getChildren("/servers");
//        zooKeeperApiDemo.getData("/servers");
//        zooKeeperApiDemo.setData("/servers","hello");
        zooKeeperApiDemo.deleteNode("/servers");
    }
}

class DefaultWatcher implements Watcher{
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected){
            switch (event.getType()){
                case NodeCreated:
                    System.out.println("znode : " + event.getPath() + " created!");
                    break;
                case NodeDeleted:
                    System.out.println("znode : " + event.getPath() + " deleted!");
                    break;
                case NodeDataChanged:
                    System.out.println("znode : " + event.getPath() + " data changed!");
                    break;
            }
        }
    }
}
