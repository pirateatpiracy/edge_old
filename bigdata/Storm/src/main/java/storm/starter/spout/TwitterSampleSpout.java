/*

package storm.starter.spout;

import org.apache.storm.Config;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterSampleSpout extends BaseRichSpout {
    SpoutOutputCollector _collector;
    LinkedBlockingQueue<Status> queue = null;
    TwitterStream _twitterStream;
    String _username;
    String _pwd;
    
    
    public TwitterSampleSpout(String username, String pwd) {
        _username = username;
        _pwd = pwd;
    }
    
    
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        queue = new LinkedBlockingQueue<Status>(1000);
        _collector = collector;
        StatusListener listener = new StatusListener() {

            
            public void onStatus(Status status) {
                queue.offer(status);
            }

            
            public void onDeletionNotice(StatusDeletionNotice sdn) {
            }

            
            public void onTrackLimitationNotice(int i) {
            }

            
            public void onScrubGeo(long l, long l1) {
            }

            
            public void onException(Exception e) {
            }
            
        };
        TwitterStreamFactory fact = new TwitterStreamFactory(new ConfigurationBuilder().setUser(_username).setPassword(_pwd).build());
        _twitterStream = fact.getInstance();
        _twitterStream.addListener(listener);
        _twitterStream.sample();
    }

    
    public void nextTuple() {
        Status ret = queue.poll();
        if(ret==null) {
            Utils.sleep(50);
        } else {
            _collector.emit(new Values(ret));
        }
    }

    
    public void close() {
        _twitterStream.shutdown();
    }

    
    public Map<String, Object> getComponentConfiguration() {
        Config ret = new Config();
        ret.setMaxTaskParallelism(1);
        return ret;
    }    

    
    public void ack(Object id) {
    }

    
    public void fail(Object id) {
    }

    
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("tweet"));
    }
    
}
*/