import static org.junit.Assert.*;
import org.junit.*;

public class TestObserver {
  Notifier observed1;
  Notifier observed2;
  Observer observerA;
  Observer observerB;

  @Before
  public void before() {
    observed1 = new Notifier("Observed #1");
    observed2 = new Notifier("Observed #2");
    observerA = new Observer("Observer A");
    observerB = new Observer("Observer B");
  }

  @Test
  public void oneObserverSubscribesToOneNotifier() {
    observerA.subscribe(observed1);
    assertEquals("Observer A", ((Observer)observed1.getObserver(0)).getId());
  }

  @Test
  public void oneObserverUnubscribesFromOneNotifier() {
    observerA.subscribe(observed1);
    observerA.unsubscribe(observed1);
    assertEquals(0, observed1.getObserverCount());
  }

  @Test
  public void oneObserverAndOneStateChange() {
    observerA.subscribe(observed1);
    observed1.updateState("Something Cool Happened!");
    assertEquals("Observed #1: Something Cool Happened!", observerA.getChange());
  }

  @Test
  public void twoObserversAndOneStateChange() {
    observerA.subscribe(observed1);
    observerB.subscribe(observed1);
    assertEquals(2, observed1.getObserverCount());
    observed1.updateState("Another Cool Thing Happened!");
    assertEquals("Observed #1: Another Cool Thing Happened!", observerA.getChange());
    assertEquals("Observed #1: Another Cool Thing Happened!", observerB.getChange());
  }

  @Test
  public void oneObserverAndTwoSeparateStateChanges() {
    observerA.subscribe(observed1, observed2);
    observed1.updateState("That cool thing happened, again!");
    assertEquals("Observed #1: That cool thing happened, again!", observerA.getChange());
    observed2.updateState("First time for me!");
    assertEquals("Observed #2: First time for me!", observerA.getChange());
  }

}