package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarsNotification;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CarsNotificationRepository  extends JpaRepository<CarsNotification, String> {
    @Query(value = " SELECT DISTINCT  N.NOTIFICATION_ID NOTIF_ID,  N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO,   " +
            " N.NOTIFICATION_VISA NOTIFICATIONS,  N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE,  " +
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID,  " +
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D  " +
            "     WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' " +
            "  AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, " +
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D " +
            " WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, " +
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, " +
            " (SELECT S.SUPPLIER_NAME  FROM   CARS_SUPPLIER S " +
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID  ) EXPERT_NAME, " +
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count,  " +
            " ( select count(*) from cars_bodily_injury j  " +
            " where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, " +
            " (SELECT TW.TOWN_NAME  FROM   CARS_TOWN TW " +
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, " +
            " LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER, T.LOSS_TOW_LOSS_DATE lossDate   " +
            ",NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh  " +
            "     where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, " +
            "  N.NOTIFICATION_MAT_DAMAGE   " +
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T , cars_loss_car c " +
            " WHERE  T.NOTIFICATION_ID  = N.NOTIFICATION_ID " +
            " AND    C.CAR_CLAIM_ID = T.LOSS_TOW_ID " +
            " AND (UPPER(C.CAR_PLATE_NUM) = UPPER(trim(?1))  OR (DISTRIBUTION_NO_DATA='Y'  " +
            " AND DISTRIBUTION_NO_DATA_PLATE = trim(UPPER(?1))))  " +
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR  " +
            " ('1' = ?2  AND NOTIFICATION_INSURANCE_ID " +
            " IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) " +
            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC  "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByPlate(String value , String cmp,String admin);
}
