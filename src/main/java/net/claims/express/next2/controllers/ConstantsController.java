package net.claims.express.next2.controllers;

import net.claims.express.next2.http.response.ApiResponse;
import net.claims.express.next2.services.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin("*")
@RestController

@RequestMapping(value="/api/constant")
public class ConstantsController   {
	@Autowired 
	private ConstantService constantService ;
@GetMapping("/companiesList")
private ApiResponse getCompanies (){
	ApiResponse  response= constantService.companyInfoResponseList();

	return response;
}
//@GetMapping("/genderList")
//private ApiResponse  getGenders (){
//	ApiResponse  response= constantService.getDoors();
//
//	return response;
//}
@GetMapping("/carBrandList")
private ApiResponse  getCarBrandList (){
	ApiResponse  response= constantService.getCarBrandList();

	return response;
}
@GetMapping("/carTrademarkList")
private ApiResponse getCarTrademarkList (@RequestParam(name="carBrandId") String carBrandId){
	ApiResponse  response= constantService.getCarTrademarkList(carBrandId);

	return response;
}


	@GetMapping("/getLabelByLocal")
	private ApiResponse getLabelByLocal (@RequestParam(name="local") String local){
		ApiResponse  response= constantService.getLocalLanguage(local);

		return response;
	}










@GetMapping("/getDoors")
private ApiResponse getDoors (){
	ApiResponse  response= constantService.getDoors();

	return response;
}

@GetMapping("/getBodyType")
private ApiResponse getBodyType (){
	ApiResponse  response= constantService.getBodyType();
	return  response;
}
@GetMapping("/getVehicleSize")
private ApiResponse getVehicleSize (){
	ApiResponse  response= constantService.getVehicleSize();

	return response;
}
@GetMapping("/getDescriptions")
private ApiResponse  getDescriptions (){
	ApiResponse  response= constantService.getDescriptions();
	return response ;
}

	@GetMapping("/getTownsList")
	private ApiResponse  getTownsList (){
		ApiResponse  response= constantService.getTownsList();
		return response ;
	}


@GetMapping("/getPartGroup")
private ApiResponse getPartGroup (){
	ApiResponse  response= constantService.getPartGroup();
	return response ;
}
@GetMapping("/getDirections")
private ApiResponse getDirections (){
	ApiResponse  response= constantService.getDirections();
	return  response ;
}
@GetMapping("/getPolicyType")
private ApiResponse getPolicyType (){
	ApiResponse  response= constantService.getPolicyType();
	return response;
}
@GetMapping("/getInsuranceCompany")
private ApiResponse  getInsuranceCompany (){
	ApiResponse  response= constantService.getInsuranceCompany();
	return   response ;



}
	@GetMapping("/getLanguages")
	private ApiResponse  getLanguages (){
		ApiResponse  response= constantService.getLanguages();
		return   response ;
	}



}
