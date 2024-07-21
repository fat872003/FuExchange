package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ReportPostProductDTO;
import com.adkp.fuexchange.dto.ReportProductTypeDTO;
import com.adkp.fuexchange.dto.ReportSellerDTO;
import com.adkp.fuexchange.dto.ReportSellerTypeDTO;
import com.adkp.fuexchange.request.SendReportPostRequest;
import com.adkp.fuexchange.request.SendReportSellerRequest;
import com.adkp.fuexchange.request.UpdateReportPostRequest;
import com.adkp.fuexchange.request.UpdateReportSellerRequest;

import java.util.List;

public interface ReportService {

    List<ReportPostProductDTO> getAllReportPostProduct();

    List<ReportSellerDTO> getAllReportSeller();

    List<ReportProductTypeDTO> allReportPostProductType();

    List<ReportSellerTypeDTO> allReportSellerType();

    List<ReportPostProductDTO> filterReportPostProduct(
            String productName, Integer reportProductTypeId, Integer reportStatusId
    );

    List<ReportSellerDTO> filterReportSeller(
            String sellerName, Integer reportSellerType, Integer reportStatusId
    );

    ReportPostProductDTO sendReportPostProduct(SendReportPostRequest sendReportPostRequest);

    List<ReportPostProductDTO> updateStatusReportPostProduct(UpdateReportPostRequest updatePostProductRequest);

    ReportSellerDTO sendReportSeller(SendReportSellerRequest sendReportSellerRequest);

    List<ReportSellerDTO> updateStatusReportSeller(UpdateReportSellerRequest updateReportSellerRequest);
}
