import java.util.*;

public class Notifier implements Observable {
  private ArrayList<Notifiable> observers;
  private String instanceState;
  private String id;

  public Notifier (String id) {
    // Create an empty Observer List
    observers = new ArrayList<Notifiable>();
    instanceState = "";
    this.id = id;
  }

  public boolean addObserver(Notifiable observer) {
    if (observers.indexOf(observer) < 0) {

      return observers.add(observer);
    }
    return true;  // Already on the list  
  }

  public boolean removeObserver(Notifiable observer) {
    return observers.remove(observer);
  }

  public String getState() {
    return id + ": " + instanceState;
  }

  public String getId() {
    return id;
  }

  private void notifyObservers() {
    for (Notifiable observer : observers) {
      observer.notifyChange(this);
    }
  }

  public void updateState(String newState) {
    instanceState = newState;
    notifyObservers();
  }

  public Notifiable getObserver(int observerIndex) {
    return observers.get(observerIndex);
  }

  public int getObserverCount() {
    return observers.size();
  }
}