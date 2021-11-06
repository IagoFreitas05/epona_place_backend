package main.place.repository;

import main.place.dto.PurchaseOrderByPeriodDTO;
import main.place.entity.EntidadeDominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurcharseOrderByPeriodDTORepository extends JpaRepository<PurchaseOrderByPeriodDTO, Integer> {
    @Query(value = "select  id,count(id) as quantity, DATE_FORMAT(data,'%Y-%m-%d') as data from purchase_order group by DATE_FORMAT(data,'%y-%m-%d')", nativeQuery = true)
    List<PurchaseOrderByPeriodDTO> findPurcharseOrderByPeriod();

    @Query(value = "select  id,count(id) as quantity, DATE_FORMAT(data,'%Y-%m-%d') as data\n" +
            "    from purchase_order\n" +
            "    WHERE DATE_FORMAT(data,'%Y-%m-%d')  > :startPeriod \n" +
            "    AND DATE_FORMAT(data,'%Y-%m-%d')  < :endsPeriod \n" +
                "    group by DATE_FORMAT(data,'%y-%m-%d')", nativeQuery = true)
    List<PurchaseOrderByPeriodDTO> findPurcharseOrderByLimitedPeriod(@Param("startPeriod") String startPeriod, @Param("endsPeriod") String endsPeriod);
}
