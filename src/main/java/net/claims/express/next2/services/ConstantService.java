package net.claims.express.next2.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.claims.express.next2.entities.*;
import net.claims.express.next2.http.StatusCode;
import net.claims.express.next2.http.response.*;
import net.claims.express.next2.repositories.DB;
import net.claims.express.next2.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ConstantService {
	@Autowired
	DB db;

	public ApiResponse companyInfoResponseList() {
		ApiResponse apiResponse = new ApiResponse();
		CompanyListResponse companyListResponse = new CompanyListResponse();
		List<CompanyInfoResponse> companyList = new ArrayList<CompanyInfoResponse>();
		List<CoreCompany> cLsit = db.companyRepository.findAll();

		cLsit.forEach(c -> {
			CompanyInfoResponse cInfo = new CompanyInfoResponse();

			cInfo.setCompanyId(c.getId());
			cInfo.setCompanyName(c.getName());
			companyList.add(cInfo);
		});
		companyListResponse.setCompanyList(companyList);
		apiResponse.setData(companyListResponse);
		return apiResponse;
	}

//	public GenderListResponse getGendersList() {
//		List<GenderResponse> genderBeanList = new ArrayList<GenderResponse>();
//		GenderResponse genderResponse = new GenderResponse();
//		genderResponse.setGenderId("M");
//		genderResponse.setGenderDescription("MALE");
//		genderBeanList.add(genderResponse);
//		genderResponse = new GenderResponse();
//		genderResponse.setGenderId("F");
//		genderResponse.setGenderDescription("FEMALE");
//		genderBeanList.add(genderResponse);
//
//		GenderListResponse GenderListBean = new GenderListResponse();
//
//		GenderListBean.(genderBeanList);
//		// TODO Auto-generated method stub
//		return GenderListBean;
//	}

	public ApiResponse getCarBrandList() {
		ApiResponse apiResponse = new ApiResponse();
		CarBrandListResponse carBrandListResponse = new CarBrandListResponse();
		List<CarBrand> brandLsit = db.carBrandRepository.findAll();
		List<CarBrandResponse> carBrandList = new ArrayList();
		brandLsit.forEach(c -> {
			CarBrandResponse cr = new CarBrandResponse();
			cr.setCarBrandDescription(c.getCarBrandDescription());
			cr.setCarBrandId(c.getCarBrandId());
			carBrandList.add(cr);
		});
		carBrandListResponse.setCarBrandBeanList(carBrandList);
		apiResponse.setData(carBrandListResponse);
		return apiResponse;
	}

	public ApiResponse getCarTrademarkList(String carBrandId) {
		ApiResponse apiResponse = new ApiResponse();

		CarTrademarkListResponse carTrademarkListResponse = new CarTrademarkListResponse();
		List<CarTrademark> cardTradeMarkList = db.carTrademarkRepository.getTrademarksbyBrandId(carBrandId);
		List<CarTrademarkResponse> carTradeList = new ArrayList<>();
		cardTradeMarkList.forEach(c -> {
			CarTrademarkResponse cr = new CarTrademarkResponse();
			cr.setCarTrademarkDescription(c.getCarTrademarkDescription());
			cr.setCarTrademarkId(c.getCarTrademarkId());
			carTradeList.add(cr);
		});
		carTrademarkListResponse.setCarTrademarkBeanList(carTradeList);
		apiResponse.setData(carTrademarkListResponse);
		return apiResponse;
	}

	public ApiResponse getDoors() {
		ApiResponse apiResponse = new ApiResponse();

		DoorsListResponse doorsListResponse = new DoorsListResponse();
		List<DoorsResponse> doors = new ArrayList<>();
		List<Doors> doorsList = db.doorsRepository.findAll();
		doorsList.forEach(d -> {
			DoorsResponse doorsResponse = new DoorsResponse();
			doorsResponse.setCode(d.getCode());
			doorsResponse.setDescription(d.getDescription());

			doors.add(doorsResponse);
		});

		doorsListResponse.setDoorsList(doors);
		apiResponse.setData(doorsListResponse);
		return apiResponse;
	}

	public ApiResponse getBodyType() {
		ApiResponse apiResponse = new ApiResponse();

		BodyTypeListResponse bodyTypeListResponse = new BodyTypeListResponse();
		List<BodyTypeResponse> bodytypes = new ArrayList<>();
		List<BodyTypeLov> body = db.bodyTypeRepository.findAll();
		body.forEach(b -> {
			BodyTypeResponse bodyTypeResponse = new BodyTypeResponse();
			bodyTypeResponse.setCode(b.getCode());
			bodyTypeResponse.setDescription(b.getDescription());
			bodytypes.add(bodyTypeResponse);

		});
		bodyTypeListResponse.setBodyTypeList(bodytypes);
		apiResponse.setData(bodyTypeListResponse);
		return apiResponse;
	}


	public ApiResponse getTownsList() {
		ApiResponse apiResponse = new ApiResponse();

		TownsListResponse townsListResponse = new TownsListResponse();
		List<TownsResponse> townsResponseList = new ArrayList<>();
		List<CarsTown> carsTowns = db.carsTownRepository.findAll();
		carsTowns.forEach(t -> {
			TownsResponse townsResponse = new TownsResponse();
			townsResponse.setCode(t.getTownCode());
			townsResponse.setDescription(t.getTownGoogleName());
			townsResponseList.add(townsResponse);

		});
		townsListResponse.setTownsResponseList(townsResponseList);
		apiResponse.setData(townsListResponse);
		return apiResponse;
	}

	public ApiResponse  getVehicleSize() {
		ApiResponse apiResponse = new ApiResponse();

		VehicleSizeListResponse vehicleSizeListResponse = new VehicleSizeListResponse();
		List<VehicleSizeResponse> veList = new ArrayList<>();
		List<VehicleSize> vsize = db.vehicleSizeRepository.findAll();
		vsize.forEach(v -> {
			VehicleSizeResponse vehicleSizeResponse = new VehicleSizeResponse();
			vehicleSizeResponse.setCode(v.getCode());
			vehicleSizeResponse.setDescription(v.getDescription());
			veList.add(vehicleSizeResponse);
		});
		// TODO Auto-generated method stub
		vehicleSizeListResponse.setVehicleSizeList(veList);
		apiResponse.setData(vehicleSizeListResponse);
		return apiResponse;
	}

	public ApiResponse getDescriptions() {
		ApiResponse apiResponse = new ApiResponse();

		LitigationDamageLovListResponse litigationDamageLovListResponse = new LitigationDamageLovListResponse();
		List<LitigationDamageLovResponse> damageLovResponses = new ArrayList<>();
		List<Reviews> reviewList = db.litigationRepository.findAll();
		reviewList.forEach(l -> {
			LitigationDamageLovResponse damageLovResponse = new LitigationDamageLovResponse();
			damageLovResponse.setCode(l.getCode());
			damageLovResponse.setDescription(l.getDescription());
			damageLovResponses.add(damageLovResponse);
		});
		
		litigationDamageLovListResponse.setLitigationDamageLovBeanList(damageLovResponses);
		// TODO Auto-generated method stub
		apiResponse.setData(litigationDamageLovListResponse);
		return apiResponse;
	}

	public ApiResponse getPartGroup() {
		ApiResponse apiResponse = new ApiResponse();

		PartGroupListResponse groupListResponse = new PartGroupListResponse();
		List<PartGroupResponse> partGroupResponses = new ArrayList<>();
		List<PartGroup> partGrouplist = db.partGroupRepository.findAll();	
		partGrouplist.stream().forEach(p -> {
			PartGroupResponse groupResponse = new PartGroupResponse()	;
			groupResponse.setCode(p.getCode());
			groupResponse.setDescription(p.getDescription());
			partGroupResponses.add(groupResponse);
		});
		groupListResponse.setPartGroupList(partGroupResponses);
		apiResponse.setData(groupListResponse);
		return apiResponse;
	}

	public ApiResponse getDirections() {
		ApiResponse apiResponse = new ApiResponse();
		DirectionsListResponse directionsListResponse = new DirectionsListResponse();
		List<Direction> directions = db.directionsRepository.findAll();
		List<DirectionResponse> directionResponses = new ArrayList<>();
		directions.forEach(d->{
	
			DirectionResponse directionResponse = new DirectionResponse();
			
			directionResponse.setCode(d.getCode());
			directionResponse.setDescription(d.getDescription());
			directionResponses.add(directionResponse);
		});
		directionsListResponse.setDirectionsListResponse(directionResponses);
		apiResponse.setData(directionsListResponse);
		return apiResponse;
	}

	public ApiResponse getPolicyType() {
		ApiResponse apiResponse = new ApiResponse();

		PolicyTypeListResponse policyTypeListResponse = new PolicyTypeListResponse();
		List<PolicyType> policyTypes = db.policyTypeRepository.findAll();
		List<PolicyTypeResponse> policyTypeResponses = new ArrayList<>();
		policyTypes.forEach(d->{
	
			PolicyTypeResponse policyTypeResponse = new PolicyTypeResponse();
			
			policyTypeResponse.setCode(d.getCode());
			policyTypeResponse.setDescription(d.getDescription());
			policyTypeResponses.add(policyTypeResponse);
		});
		policyTypeListResponse.setPolicyTypeResponseList(policyTypeResponses);
		apiResponse.setData(policyTypeListResponse);
		return apiResponse;
	}

	public ApiResponse getInsuranceCompany() {
		ApiResponse apiResponse = new ApiResponse();

		InsuranceCompanyListResponse insuranceCompanyListResponse = new    InsuranceCompanyListResponse();
		List<CarsSupplier> companies=db.carsSupplierRepository.getInsuranceCompany();
		List<InsuranceCompanyResponse> companyResponses= new ArrayList<>();
		companies.forEach(i->{
			
			InsuranceCompanyResponse insuranceCompanyResponse = new InsuranceCompanyResponse();
			
			insuranceCompanyResponse.setSupplierId(i.getSupplierId());
			insuranceCompanyResponse.setSupplierName(i.getSupplierName());
			companyResponses.add(insuranceCompanyResponse);
		});
		insuranceCompanyListResponse.setInsuranceCompanyResponseList(companyResponses);
		apiResponse.setData(insuranceCompanyListResponse);
		return apiResponse;
	}



	public  ApiResponse getLocalLanguage(String local){
		ApiResponse apiResponse = new ApiResponse();
		Localization localization = new Localization();
List<DecoLocalization> decoLocalizationList = new ArrayList<>();
List<CoreResourceBundle> coreResourceBundleList= db.coreResourceBundleRepository.findByLocale(local);
coreResourceBundleList.forEach(coreResourceBundle -> {
	DecoLocalization decoLocalization = new DecoLocalization();
decoLocalization.setKey(coreResourceBundle.getResourceKey());
decoLocalizationList.add(decoLocalization);

});
localization.setDecoLocalizationList(decoLocalizationList);
apiResponse.setData(localization);
apiResponse.setStatusCode(StatusCode.OK.getCode());
		return  apiResponse;
	}

	public ApiResponse getLanguages() {
		ApiResponse apiResponse = new ApiResponse();
		Set<DecoLocalization> languages = new HashSet<>();
		List<CoreResourceBundle> coreResourceBundleList= db.coreResourceBundleRepository.findAll();

coreResourceBundleList.forEach(coreResourceBundle -> {
	DecoLocalization decoLocalization = new DecoLocalization();
	if(coreResourceBundle.getLocale().equals("en")){
		decoLocalization.setValue("English");
		decoLocalization.setKey(coreResourceBundle.getLocale());

	}
	else if(coreResourceBundle.getLocale().equals("fr")){
		decoLocalization.setValue("French");
		decoLocalization.setKey(coreResourceBundle.getLocale());

	}
	else if(coreResourceBundle.getLocale().equals("ar")){
		decoLocalization.setValue("Arabic");
		decoLocalization.setKey(coreResourceBundle.getLocale());

	}
	else{
		decoLocalization.setValue(coreResourceBundle.getLocale());
		decoLocalization.setKey(coreResourceBundle.getLocale());

	}
	languages.add(decoLocalization);
});
		apiResponse.setStatusCode(StatusCode.OK.getCode());
		apiResponse.setData(languages);
		return  apiResponse;
	}
}
