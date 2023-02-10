package net.claims.express.next2.repositories;

import net.claims.express.next2.entities.CarsSupplier;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarsSupplierRepository  extends BaseRepository<CarsSupplier,String>{
    @Query(value=" SELECT SUPPLIER_NAME, SUPPLIER_ID FROM CARS_SUPPLIER WHERE SUPPLIER_INTERM='A' ORDER BY 1 ", nativeQuery = true)
    List<CarsSupplier> getInsuranceCompany();
}
