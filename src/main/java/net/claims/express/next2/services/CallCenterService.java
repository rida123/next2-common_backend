package net.claims.express.next2.services;

import net.claims.express.next2.repositories.DB;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallCenterService {
    @Autowired
    DB db;
public List<NotificationSearchResponse> getNotificationSearch(String type ,String value ,String company, String admin){
    if (type.equalsIgnoreCase("PLATE")){
        List<NotificationSearchResponse> notificationSearchResponseList = db.carsNotificationRepository.getNotificationSearchQueryByPlate(value,company,admin);
        return notificationSearchResponseList;
    }
    else {
        return null;
    }


}
}
