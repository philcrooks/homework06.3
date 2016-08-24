public class Observer implements Notifiable {
  private String id;
  private String lastNotifiedChange;

  public Observer(String id) {
    this.id = id;
    lastNotifiedChange = "";
  }

  public void subscribe(Observable... objects) {
    for (Observable object : objects) {
      object.addObserver(this);
    }
  }

  public void unsubscribe(Observable... objects) {
    for (Observable object : objects) {
      object.removeObserver(this);
    }
  }

  public void notifyChange(Observable notifier) {
    // This method is called when the observered class changes
    lastNotifiedChange = notifier.getState();
  }

  public String getId() {
    return id;
  }

  public String getChange() {
    return lastNotifiedChange;
  }
}