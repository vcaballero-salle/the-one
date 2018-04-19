package core.event;

import util.Tuple;

import java.util.*;

public class EventManager {

  private Map<String, Set<Subscriber>> subscribers;

  private Queue<Tuple<String, Object>> eventQueue;

  private boolean isStarted;

  private static EventManager em;

  private EventManager() {
    subscribers = new HashMap<>();
    eventQueue = new ArrayDeque<>(16);
    isStarted = false;
  }

  public static EventManager instance() {
    if (em != null) {
      return em;
    }
    em = new EventManager();
    return em;
  }

  public void subscribe(Subscriber subscriber, String topic) {
    if (subscribers.containsKey(topic)) {
      subscribers.get(topic).add(subscriber);
    } else {
      HashSet<Subscriber> subscriberSet = new HashSet<>();
      subscriberSet.add(subscriber);
      subscribers.put(topic, subscriberSet);
    }
  }

  public void publish(Publisher publisher, String topic, Object message) {
    if (!isStarted) {
      eventQueue.add(new Tuple<>(topic, message));
    } else {
      publishEventToSubscribers(publisher, topic, message);
    }
  }


  public void start() {

    isStarted = true;
    eventQueue.forEach(e -> publishEventToSubscribers(null, e.getKey(), e.getValue()));
    eventQueue = null;

  }

  private void publishEventToSubscribers(Publisher publisher, String topic, Object message) {
    if (subscribers.containsKey(topic)) {
      subscribers.get(topic).forEach(s -> s.onEvent(publisher, message));
    }
  }

}
