package Database.Announcements;

public interface Announcement {
    public void addAnnouncement(String to, String subject, String body, String timestamp, String uniqueID) throws Exception;
    public Announcements[] getAllAnnouncements(String filterTo);
}
