package Database.Announcements;

import Database.InvalidPermissionsException;

public class Basic implements Announcement {
    Announcements globalAnnouncementsObject;

    public Basic() {
        globalAnnouncementsObject = new Announcements();
    }
    @Override
    public void addAnnouncement(String to, String subject, String body, String timestamp, String uniqueID) throws Exception {
        throw new InvalidPermissionsException();
    }

    @Override
    public Announcements[] getAllAnnouncements(String filterTo) {
        return globalAnnouncementsObject.getAllAnnouncements(filterTo);
    }
}
