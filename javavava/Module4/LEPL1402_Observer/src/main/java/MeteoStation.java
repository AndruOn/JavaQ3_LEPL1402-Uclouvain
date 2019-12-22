import java.util.List;

public class MeteoStation extends Observable {

    public MeteoStation(){
    }
    @Override
    public Observer[] getSubscribers() {
        Observer[] subscribersArray = new Observer[this.subscribers.size()];
        for (int i = 0; i < subscribers.size() ; i++) {
            subscribersArray[i] = subscribers.get(i);
        }
        return subscribersArray;
    }

    @Override
    public void broadcast(Pair<String, Integer> pair) {
        for (Observer observer: getSubscribers()){
            if (observer.getZone() == pair.getZone()){
                observer.update(pair.getAlert());
            }
        }
    }

    @Override
    public void addObserver(Observer sub) {
        if (!subscribers.contains(sub)){
            subscribers.add(sub);
        }
    }

    @Override
    public boolean removeObserver(Observer sub) {
        if (subscribers.contains(sub)){
            return subscribers.remove(sub);
        }
        return false;
    }

    @Override
    public void setAlert(String alert, int zone) {
        for (Observer observer  : getSubscribers()){
            if ( observer.getZone() == zone) {
                observer.update(alert);
            }
        }
    }
}
