package core.event;

public interface Subscriber {

  public void onEvent(Publisher publisher, Object event);

  public String topic();

}
