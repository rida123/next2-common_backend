package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarsNotification;
import net.claims.express.next2.http.response.PolicySearchResponse;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
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











    @Query(value = " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T ,cars_loss_car c "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND  C.CAR_CLAIM_ID = T.LOSS_TOW_ID "+
            " AND  (C.CAR_DRIVER_MOBILE_PHONE LIKE '%'|| trim( ?1 )||'%' OR CAR_DRIVER_PHONE LIKE '%'||trim( ?1 )||'%' OR CAR_OWNER_MOBILE_PHONE LIKE '%'||trim( ?1 )||'%' OR N.NOTIFICATION_CONTACT_PHONE LIKE '%'||trim( ?1 )||'%') "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+
            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByPHone(String value , String cmp,String admin);

    @Query(value =   " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T ,cars_loss_car c "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " and C.CAR_CLAIM_ID = T.LOSS_TOW_ID "+

            " AND UPPER(C.CAR_OWNER_FIRST_NAME)||' '||UPPER(C.CAR_OWNER_FATHER_NAME) ||' '||UPPER(C.CAR_OWNER_FAMILY_NAME) LIKE '%'||UPPER(trim( ?1 ))||'%' "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+

            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByName(String value , String cmp,String admin);
//
    @Query(value =  " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND  N.notification_visa = trim( ?1 ) "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+

            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByNotification(String value , String cmp,String admin);
//
//
    @Query(value = " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T , cars_loss_car c "+
            " WHERE  T.NOTIFICATION_ID  = N.NOTIFICATION_ID "+
            " AND    C.CAR_CLAIM_ID = T.LOSS_TOW_ID "+
            " AND (UPPER(C.CAR_PLATE_NUM) like '%'||UPPER(trim( ?1 ))||'%' "+
            " OR (DISTRIBUTION_NO_DATA='Y' AND DISTRIBUTION_NO_DATA_PLATE = trim(UPPER(?1)))) "+
            " AND (NOTIFICATION_INSURANCE_ID=? OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','23')) OR ('1' = ?2 AND NVL(?1,'N')='Y')) "+
            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryBySimPlate(String value , String cmp,String admin);




    @Query(value =  " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND  UPPER(T.LOSS_TOW_INS_CLAIM_NUMBER) = UPPER(trim( ?1 )) "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+
            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByClaimNum(String value , String cmp,String admin);











    @Query(value =      " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND  N.NOTIFICATION_ID = "+
            " ( "+
            " SELECT MAX(A.NOTIFICATION_ACTIV_NOTIF_ID) "+
            " FROM   CARS_NOTIFICATION_ACTIVITY A, CARS_NOTIFICATION C "+
            " WHERE  A.NOTIFICATION_ACTIV_NOTIF_ID = C.NOTIFICATION_ID "+
            " AND    A.NOTIFICATION_ACTIV_NUMBER   = '50' "+
            " AND    UPPER(A.NOTIFICATION_ACTIV_USER)     = UPPER(?) "+
            " AND    C.NOTIFICATION_MAT_DAMAGE = '6' "+
            " AND    A.NOTIFICATION_ACTIV_DATE_TIME= (SELECT MAX(B.NOTIFICATION_ACTIV_DATE_TIME) "+

            " FROM   CARS_NOTIFICATION_ACTIVITY B, CARS_NOTIFICATION D "+
            " WHERE  B.NOTIFICATION_ACTIV_NOTIF_ID = D.NOTIFICATION_ID "+
            " AND    B.NOTIFICATION_ACTIV_NUMBER   = '50' "+
            " AND    UPPER(B.NOTIFICATION_ACTIV_USER)     = UPPER(?1) "+
            " AND    D.NOTIFICATION_MAT_DAMAGE ='6') "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+
            " )ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByNewestTow(String value , String cmp,String admin);



    @Query(value = " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T ,CARS_POLICY P, CARS_POLICY_CAR PC "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND    P.POLICY_ID = PC.POLICY_ID "+
            " AND    N.NOTIFICATION_POL_CAR_ID = PC.CAR_ID "+
            " AND    P.POLICY_NUMBER = trim( ?1 ) "+
            " UNION "+
            " SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T  "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND   DISTRIBUTION_NO_DATA='Y' AND UPPER(DISTRIBUTION_NO_DATA_POLICY) = UPPER(trim( ?1)) "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+
            " ORDER BY 4 DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByPolicyNumber(String value , String cmp,String admin);






    @Query(value =" SELECT DISTINCT "+
            " N.NOTIFICATION_ID NOTIF_ID, "+
            " N.NOTIFICATION_INSURANCE_ID COMPANY_LOGO, "+
            " N.NOTIFICATION_VISA NOTIFICATIONS, "+
            " N.NOTIFICATION_REPORTED_DATE NOTIFICATION_DATE, "+
            " N.NOTIFICATION_POL_CAR_ID POLICY_CAR_ID, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'MATDAMAGE' AND D.CODE = N.NOTIFICATION_MAT_DAMAGE )NOTIFICATION_NATURE, "+
            " (SELECT D.DESCRIPTION FROM CORE_DOMAIN_VALUE D WHERE D.CORE_DOMAIN_ID = 'CLAIM' AND D.CODE = N.NOTIFICATIONS_STATUS ) NOTIFICATION_STATUS, "+
            " N.NOTIFICATIONS_STATUS NOTIFICATIONS_STATUS_CODE, "+
            " (SELECT S.SUPPLIER_NAME "+
            " FROM   CARS_SUPPLIER S "+
            " WHERE  S.SUPPLIER_ID = T.LOSS_TOW_EXPERT_ID "+
            " ) EXPERT_NAME, "+
            " ( select count(*) from cars_loss_car c where C.CAR_CLAIM_ID = T.LOSS_TOW_ID) cars_count, "+
            " ( select count(*) from cars_bodily_injury j where J.INJURED_NOTIFICATION_ID = N.NOTIFICATION_ID) injury_count, "+
            " (SELECT TW.TOWN_NAME "+
            " FROM   CARS_TOWN TW "+
            " WHERE  TW.TOWN_ID = NVL(T.DISTRIBUTION_LOSS_TOWN_ID,T.LOSS_TOW_LOSS_TOWN_ID)) ACCIDENT_LOCATION, LOSS_TOW_INS_CLAIM_NUMBER CLAIM_NUMBER ,T.LOSS_TOW_LOSS_DATE lossDate "+
            " ,NVL((select veh.MECHANICAL_TOW_COUNT from V_VEHICLE veh where veh.CAR_ID = N.NOTIFICATION_POL_CAR_ID),0) towCount, N.NOTIFICATION_MAT_DAMAGE "+
            " FROM   CARS_NOTIFICATION N, CARS_LOSS_TOWING T "+
            " WHERE  T.NOTIFICATION_ID      = N.NOTIFICATION_ID "+
            " AND  N.NOTIFICATION_ID = "+
            " ( "+
            " SELECT MAX(A.NOTIFICATION_ACTIV_NOTIF_ID)  "+
            " FROM   CARS_NOTIFICATION_ACTIVITY A, CARS_NOTIFICATION C "+
            " WHERE  A.NOTIFICATION_ACTIV_NOTIF_ID = C.NOTIFICATION_ID "+
            " AND    A.NOTIFICATION_ACTIV_NUMBER   = '50' "+
            " AND    UPPER(A.NOTIFICATION_ACTIV_USER)    = UPPER(?) "+
            " AND    C.NOTIFICATION_MAT_DAMAGE <> '6' "+
            " AND    A.NOTIFICATION_ACTIV_DATE_TIME= (SELECT MAX(B.NOTIFICATION_ACTIV_DATE_TIME) "+

            " FROM   CARS_NOTIFICATION_ACTIVITY B, CARS_NOTIFICATION D "+
            " WHERE  B.NOTIFICATION_ACTIV_NOTIF_ID = D.NOTIFICATION_ID "+
            " AND    B.NOTIFICATION_ACTIV_NUMBER   = '50' "+
            " AND    UPPER(B.NOTIFICATION_ACTIV_USER)     = UPPER(?1) "+
            " AND    D.NOTIFICATION_MAT_DAMAGE <> '6') "+
            " ) "+
            " AND (NOTIFICATION_INSURANCE_ID=?2 OR ('1' = ?2  AND NOTIFICATION_INSURANCE_ID IN ('10','11','13','18','19','23')) OR ('1' = ?2 AND NVL(?3,'N')='Y')) "+

            " ORDER BY N.NOTIFICATION_REPORTED_DATE DESC "
            ,nativeQuery = true)
    List<NotificationSearchResponse> getNotificationSearchQueryByNewestAccident(String value , String cmp,String admin);



    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND POLICY_NUMBER = ?5 AND POLICY_EFFECTIVE_DATE <= ?3 ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc , TRUNC(POLICY_EXPIRY_DATE) desc , POLICY_ISSUE_DATE desc ",

            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewByPolicyNumber(String iInsurance , String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);



    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND POLICY_NUMBER like '%' || ?5 || '%' AND POLICY_EFFECTIVE_DATE <= ?3 ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc , TRUNC(POLICY_EXPIRY_DATE) desc , POLICY_ISSUE_DATE desc ",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewBySimilarPolicyNumber(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);



    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND PLATE_NUM = ?5 AND POLICY_EFFECTIVE_DATE <= ?3  ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc ",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewByPlateNumber(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);



    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND PLATE_NUM like '%' || ?5 || '%' AND POLICY_EFFECTIVE_DATE <= ?3  ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc ",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewBySimilarPlate(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);





    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND PLATE_NUM = regexp_replace(?5, '[^0-9]', '') and upper(regexp_replace(CAR_PLATE , '[^a-zA-Z]', '')) = upper(regexp_replace(?5 , '[^a-zA-Z]', '')) AND POLICY_EFFECTIVE_DATE <= ?3  ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewByExactPlateNumber(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);




    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND UPPER(CLIENT_NAME) like '%' || UPPER(trim(?5)) || '%' AND POLICY_EFFECTIVE_DATE <= ?3  ORDER BY POLICY_INSURANCE_ID ,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewByName(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);

    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND CAR_CHASSIS like '%' || ?5 AND POLICY_EFFECTIVE_DATE <= ?3  ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewByChassisNumber(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);



    @Query(
            value = "select *  from V_VEHICLE where (POLICY_INSURANCE_ID = ?1 OR 'ALL' = ?1)" +
                    " AND ('SHOW_ALL' = ?2 OR POLICY_TYPE = ?2 OR ('ALL_TPL' = ?2 AND POLICY_TYPE IN ('ALL','TPL')))" +
                    " AND POLICY_EXPIRY_DATE >= ?3 " + " AND ('ALL' = ?4 or POLICY_PRODUCTS_ID = ?4 ) " +
                    " AND CLIENT_PHONE = ?5 AND POLICY_EFFECTIVE_DATE <= ?3  ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleViewByPhoneNumber(String iInsurance, String iPolicyType, Date iAsOfDate, String productType, String iSearchValue);


    @Query(
            value = "select *  from V_VEHICLE where 1=2 ORDER BY POLICY_INSURANCE_ID,POLICY_POLSERNO desc, TRUNC(POLICY_EXPIRY_DATE) desc, POLICY_ISSUE_DATE desc ",
            nativeQuery = true)
    List<PolicySearchResponse> getVehicleView();


//     query.setParameter(1, iInsurance);
//        query.setParameter(2, iInsurance);
//        query.setParameter(3, iPolicyType);
//        query.setParameter(4, iPolicyType);
//        query.setParameter(5, iPolicyType);
//        query.setParameter(6, iAsOfDate);
//        if (!StringUtils.isEmpty(productType)) {
//        query.setParameter(7, productType);
//        query.setParameter(8, productType);
//    } else {
//        query.setParameter(7, "ALL");
//        query.setParameter(8, "ALL");
//    }
//        query.setParameter(9, iSearchValue);
//        if (iSearchBy.equals("ExactPlateNumber")) {
//        query.setParameter(10, iSearchValue);
//        query.setParameter(11, iAsOfDate);
//        //            query.setParameter(12, iAsOfDate);
//    } else {
//        query.setParameter(10, iAsOfDate);












}
