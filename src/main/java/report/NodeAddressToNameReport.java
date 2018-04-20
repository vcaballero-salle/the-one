package report;

import core.Coord;
import core.DTNHost;
import core.MovementListener;
import core.event.Publisher;

import java.util.HashSet;

public class NodeAddressToNameReport extends Report {

  private HashSet<Integer> loggedNodes;

  public NodeAddressToNameReport()
  {
    super();
    loggedNodes = new HashSet<>();
  }

  @Override
  public String topic() {
    return DTNHost.HOST_CREATED_TOPIC;
  }

  @Override
  public void onEvent(Publisher publisher, Object event) {
    DTNHost host = (DTNHost) event;
    write(host.getAddress() + ", " + host.toString());
  }


}
