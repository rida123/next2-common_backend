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
    else
        return  null ;
}


}
