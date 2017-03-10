package dds.poi.observer;

import dds.poi.action.search.Search;
import dds.poi.history.SearchsHistory;

public class StoreResultsObserver implements Observer {

    public StoreResultsObserver() {
    }

    @Override
    public void update(Search search) {
        SearchsHistory.getInstance().addSearch(search);
    }

}
