package net.claims.express.next2.responses;

import org.springframework.beans.factory.annotation.Value;

public interface NotificationSearchResponse {
    @Value("#{target.NOTIF_ID}")
    String getNotifId ();

    @Value("#{target.COMPANY_LOGO}")
    String getCompanyLogo ();

    @Value("#{target.NOTIFICATIONS}")
    String getNotifications ();

    @Value("#{target.NOTIFICATION_DATE}")
    String getNotificationDate ();

    @Value("#{target.POLICY_CAR_ID}")
    String getPolicyCarId ();

    @Value("#{target.NOTIFICATION_NATURE}")
    String getNotificationNature();

    @Value("#{target.NOTIFICATION_STATUS}")
    String getNotificationStatus();

    @Value("#{target.NOTIFICATIONS_STATUS_CODE}")
    String getNotificationStatusCode();

    @Value("#{target.EXPERT_NAME}")
    String getExpertName();

    @Value("#{target.CARS_COUNT}")
    Long getCarsCount();
}
