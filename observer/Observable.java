public interface Observable {
  public boolean addObserver(Notifiable observer);
  public boolean removeObserver(Notifiable observer);
  public String getState();
}