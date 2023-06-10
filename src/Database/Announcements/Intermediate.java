package Database.Announcements;

public class Intermediate implements Announcement{
    Announcements globalAnnouncementsObject;

    public Intermediate() {
        globalAnnouncementsObject = new Announcements();
    }
    @Override
    public void addAnnouncement(String to, String subject, String body, String timestamp, String uniqueID) throws Exception {
        globalAnnouncementsObject.addAnnouncement(to, subject, body, timestamp, uniqueID);
    }

    @Override
    public Announcements[] getAllAnnouncements(String filterTo) {
        return globalAnnouncementsObject.getAllAnnouncements(filterTo);
    }
}
