package app.nytimes.promobi.com.nytimesapp.eventsbus;

import app.nytimes.promobi.com.nytimesapp.models.Results;

public class Events {

    // Event used to send message from fragment to activity.
    public static class FragmentActivityMessage {
        private String url;

        public FragmentActivityMessage(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    // Event used to send message from activity to fragment.
    public static class ActivityFragmentMessage {
        private String url;

        public ActivityFragmentMessage(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

}
