package main.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDataAnalysisDTO {
    private List<PurchaseOrderByPeriodDTO> purchaseOrderByPeriodDTO;
    private List<ProductsByPeriodDTO> productsByPeriodDTO;
    private List<SalesByCategoryDTO> salesByCategoryDTO;
}
