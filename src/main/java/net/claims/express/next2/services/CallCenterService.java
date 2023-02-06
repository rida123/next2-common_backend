package net.claims.express.next2.services;

import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.repositories.DB;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallCenterService  {
    @Autowired
    DB db;
public ApiResponse getNotificationSearch(String type ,String value ,String company, String admin) {
    ApiResponse apiResponse = new ApiResponse();

    if (type.equalsIgnoreCase("PLATE")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByPlate(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    } else if (type.equalsIgnoreCase("PHONE")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByPHone(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }

    else if (type.equalsIgnoreCase("SIMPLATE")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryBySimPlate(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }


    else if (type.equalsIgnoreCase("NAME")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByName(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }


    else if (type.equalsIgnoreCase("CLAIM_NUM")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByClaimNum(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }

    else if (type.equalsIgnoreCase("NEWEST_TOWN")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByNewestTow(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }
    else if (type.equalsIgnoreCase("NOTIFICATION")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByNotification(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }
    else if (type.equalsIgnoreCase("POLICY_NUMBER")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByPolicyNumber(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }
    else if (type.equalsIgnoreCase("NEWEST_ACCIDENT")) {
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByNewestAccident(value, company, admin);
        apiResponse.setData(notificationSearchResponseList);
        apiResponse.setStatusCode(StatusCode.OK.getCode());
        return apiResponse;
    }




    else{
        apiResponse.setStatusCode(StatusCode.FAILED.getCode());
        apiResponse.setTitle("INVALID INPUT");
        apiResponse.setMessage("INVALID INPUT");
        return apiResponse;


    }

}


}
