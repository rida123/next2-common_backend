package net.claims.express.next2.services;

import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.http.response.PolicySearchResponse;
import net.claims.express.next2.repositories.DB;
import net.claims.express.next2.security.services.responses.NotificationSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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







    public ApiResponse getPolicySearch(String iSearchBy, String iSearchValue, String iPolicyType, Date iAsOfDate,
                                             String iInsurance, String productType)  {
    if(productType.isEmpty()){
        productType="ALL";
    }
    ApiResponse apiResponse = new ApiResponse();
    List<PolicySearchResponse> policySearchResponseList;
        if (iSearchBy.isEmpty()) {
            apiResponse.setStatusCode(StatusCode.FAILED.getCode());
            return apiResponse;
        }
        if (iSearchBy.equals("PolicyNumber")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewByPolicyNumber(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);
        } else if (iSearchBy.equals("SimilarPolicyNumber")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewBySimilarPolicyNumber(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);

        } else if (iSearchBy.equals("PlateNumber")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewByPlateNumber(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);

        } else if (iSearchBy.equals("SimilarPlate")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewBySimilarPlate(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);

        } else if (iSearchBy.equals("ExactPlateNumber")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewByExactPlateNumber(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);

        } else if (iSearchBy.equals("Name")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewByName(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);
        } else if (iSearchBy.equals("ChassisNumber")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewByChassisNumber(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);
        } else if (iSearchBy.equals("PhoneNumber")) {
            policySearchResponseList= db.carsNotificationRepository.getVehicleViewByPhoneNumber(iInsurance,iPolicyType,iAsOfDate,productType,iSearchValue);
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);

        } else {
            policySearchResponseList= db.carsNotificationRepository.getVehicleView();
            apiResponse.setMessage("success");
            apiResponse.setStatusCode(StatusCode.OK.getCode());
            apiResponse.setData(policySearchResponseList);
        }

        return apiResponse;
    }













}
