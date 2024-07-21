package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ReportPostProductDTO;
import com.adkp.fuexchange.dto.ReportProductTypeDTO;
import com.adkp.fuexchange.dto.ReportSellerDTO;
import com.adkp.fuexchange.dto.ReportSellerTypeDTO;
import com.adkp.fuexchange.mapper.ReportPostProductMapper;
import com.adkp.fuexchange.mapper.ReportProductTypeMapper;
import com.adkp.fuexchange.mapper.ReportSellerMapper;
import com.adkp.fuexchange.mapper.ReportSellerTypeMapper;
import com.adkp.fuexchange.pojo.*;
import com.adkp.fuexchange.repository.*;
import com.adkp.fuexchange.request.SendReportPostRequest;
import com.adkp.fuexchange.request.SendReportSellerRequest;
import com.adkp.fuexchange.request.UpdateReportPostRequest;
import com.adkp.fuexchange.request.UpdateReportSellerRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportPostProductRepository reportPostProductRepository;

    private final ReportPostProductMapper reportPostProductMapper;

    private final ReportProductTypeRepository reportProductTypeRepository;

    private final ReportProductTypeMapper reportProductTypeMapper;

    private final PostProductRepository postProductRepository;

    private final RegisteredStudentRepository registeredStudentRepository;

    private final ReportStatusRepository reportStatusRepository;

    private final PostStatusRepository postStatusRepository;

    private final ReportSellerRepository reportSellerRepository;

    private final ReportSellerMapper reportSellerMapper;

    private final ReportSellerTypeRepository reportSellerTypeRepository;

    private final ReportSellerTypeMapper reportSellerTypeMapper;

    private final SellerRepository sellerRepository;

    @Autowired
    public ReportServiceImpl(ReportPostProductRepository reportPostProductRepository, ReportPostProductMapper reportPostProductMapper, ReportProductTypeRepository reportProductTypeRepository, ReportProductTypeMapper reportProductTypeMapper, PostProductRepository postProductRepository, RegisteredStudentRepository registeredStudentRepository, ReportStatusRepository reportStatusRepository, PostStatusRepository postStatusRepository, ReportSellerRepository reportSellerRepository, ReportSellerMapper reportSellerMapper, ReportSellerTypeRepository reportSellerTypeRepository, ReportSellerTypeMapper reportSellerTypeMapper, SellerRepository sellerRepository) {
        this.reportPostProductRepository = reportPostProductRepository;
        this.reportPostProductMapper = reportPostProductMapper;
        this.reportProductTypeRepository = reportProductTypeRepository;
        this.reportProductTypeMapper = reportProductTypeMapper;
        this.postProductRepository = postProductRepository;
        this.registeredStudentRepository = registeredStudentRepository;
        this.reportStatusRepository = reportStatusRepository;
        this.postStatusRepository = postStatusRepository;
        this.reportSellerRepository = reportSellerRepository;
        this.reportSellerMapper = reportSellerMapper;
        this.reportSellerTypeRepository = reportSellerTypeRepository;
        this.reportSellerTypeMapper = reportSellerTypeMapper;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<ReportPostProductDTO> getAllReportPostProduct() {
        List<ReportPostProduct> getAll = reportPostProductRepository.findAll();
        return reportPostProductMapper.toReportPostProductDTOList(getAll);
    }

    @Override
    public List<ReportSellerDTO> getAllReportSeller() {
        List<ReportSeller> getAll = reportSellerRepository.findAll();
        return reportSellerMapper.toReportSellerDTOList(getAll);
    }

    @Override
    public List<ReportProductTypeDTO> allReportPostProductType() {
        List<ReportProductType> allReportPostProductType = reportProductTypeRepository.findAll();
        return reportProductTypeMapper.toReportProductTypeDTOList(allReportPostProductType);
    }

    @Override
    public List<ReportSellerTypeDTO> allReportSellerType() {
        List<ReportSellerType> allReportSellerType = reportSellerTypeRepository.findAll();
        return reportSellerTypeMapper.toReportSellerTypeDTOList(allReportSellerType);
    }

    @Override
    public List<ReportPostProductDTO> filterReportPostProduct(
            String productName, Integer reportProductTypeId, Integer reportStatusId
    ) {

        String name = Optional.ofNullable(productName).map(String::valueOf).orElse("");
        String reportProductType = Optional.ofNullable(reportProductTypeId).map(String::valueOf).orElse("");
        String reportStatus = Optional.ofNullable(reportStatusId).map(String::valueOf).orElse("");

        List<ReportPostProduct> filterPostProduct = reportPostProductRepository.filterReportPostProduct(
                name, reportProductType, reportStatus
        );

        return reportPostProductMapper.toReportPostProductDTOList(filterPostProduct);
    }

    @Override
    public List<ReportSellerDTO> filterReportSeller(String sellerName, Integer reportType, Integer reportStatusId) {

        String name = Optional.ofNullable(sellerName).map(String::valueOf).orElse("");
        String reportSellerType = Optional.ofNullable(reportType).map(String::valueOf).orElse("");
        String reportStatus = Optional.ofNullable(reportStatusId).map(String::valueOf).orElse("");

        List<ReportSeller> filterReportSeller = reportSellerRepository.filterReportSeller(
                name, reportSellerType, reportStatus
        );

        return reportSellerMapper.toReportSellerDTOList(filterReportSeller);
    }

    @Override
    @Transactional
    public ReportPostProductDTO sendReportPostProduct(SendReportPostRequest sendReportPostRequest) {

        RegisteredStudent registeredStudentSend = registeredStudentRepository.getReferenceById(sendReportPostRequest.getRegisteredStudentId());

        PostProduct postProductReport = postProductRepository.getReferenceById(sendReportPostRequest.getPostProductId());

        ReportProductType reportProductType = reportProductTypeRepository.getReferenceById(sendReportPostRequest.getReportProductTypeId());

        ReportStatus reportStatus = reportStatusRepository.getReferenceById(1);

        if (checkReportedInReportPost(registeredStudentSend.getRegisteredStudentId(), postProductReport.getPostProductId())) {
            return null;
        }

        ReportPostProduct reportPostProductSaved = reportPostProductRepository.save(
                ReportPostProduct.builder()
                        .buyerId(registeredStudentSend)
                        .postProductId(postProductReport)
                        .reportProductTypeId(reportProductType)
                        .reportStatusId(reportStatus)
                        .createTime(LocalDateTime.now())
                        .content(sendReportPostRequest.getContent())
                        .build()
        );

        return reportPostProductMapper.toReportPostProductDTO(reportPostProductSaved);
    }

    @Override
    @Transactional
    public List<ReportPostProductDTO> updateStatusReportPostProduct(UpdateReportPostRequest updatePostProductRequest) {

        ReportPostProduct reportPostProduct = reportPostProductRepository.getReferenceById(
                updatePostProductRequest.getReportPostProductId()
        );

        ReportStatus reportStatus = reportStatusRepository.getReferenceById(
                updatePostProductRequest.getReportStatusId()
        );

        PostProduct postProduct =
                reportPostProductRepository.
                        getReferenceById(updatePostProductRequest.getReportPostProductId())
                        .getPostProductId();

        reportPostProductRepository.updateStatusPostProduct(
                reportPostProduct.getReportPostProductId(), reportStatus.getReportStatusId()
        );

        List<ReportPostProduct> reportPostProductUpdated = reportPostProductRepository.getReportByPostId(postProduct.getPostProductId());

        updatePostProductAfterUpdateReport(updatePostProductRequest, postProduct);

        return reportPostProductMapper.toReportPostProductDTOList(reportPostProductUpdated);
    }

    @Override
    @Transactional
    public ReportSellerDTO sendReportSeller(SendReportSellerRequest sendReportSellerRequest) {

        RegisteredStudent registeredStudentSend = registeredStudentRepository.getReferenceById(sendReportSellerRequest.getRegisteredStudentId());

        Seller sellerReport = sellerRepository.getReferenceById(sendReportSellerRequest.getSellerId());

        ReportSellerType reportSellerType = reportSellerTypeRepository.getReferenceById(sendReportSellerRequest.getReportSellerTypeId());

        ReportStatus reportStatus = reportStatusRepository.getReferenceById(1);

        if (checkReportedInReportSeller(registeredStudentSend.getRegisteredStudentId(), sellerReport.getSellerId())) {
            return null;
        }

        ReportSeller reportSellerSaved = reportSellerRepository.save(
                ReportSeller.builder()
                        .buyerId(registeredStudentSend)
                        .sellerId(sellerReport)
                        .reportSellerTypeId(reportSellerType)
                        .reportStatusId(reportStatus)
                        .createTime(LocalDateTime.now())
                        .content(sendReportSellerRequest.getContent())
                        .build()
        );

        return reportSellerMapper.toReportSellerDTO(reportSellerSaved);
    }

    @Override
    @Transactional
    public List<ReportSellerDTO> updateStatusReportSeller(UpdateReportSellerRequest updateReportSellerRequest) {

        ReportSeller reportSeller = reportSellerRepository.getReferenceById(
                updateReportSellerRequest.getReportSellerId()
        );

        ReportStatus reportStatus = reportStatusRepository.getReferenceById(
                updateReportSellerRequest.getReportStatusId()
        );

        Seller seller = reportSeller.getSellerId();

        reportSellerRepository.updateStatusSeller(
                reportSeller.getSellerId().getSellerId(),
                reportStatus.getReportStatusId()
        );

        List<ReportSeller> reportSellerUpdated = reportSellerRepository.getReportBySellerId(seller.getSellerId());

        updateSellerAfterUpdateReport(updateReportSellerRequest, seller);

        return reportSellerMapper.toReportSellerDTOList(reportSellerUpdated);
    }

    private boolean checkReportedInReportPost(Integer buyerId, Integer postProductId) {
        ReportPostProduct reportPostProduct = reportPostProductRepository.checkReportedOfStudentInReportPostProduct(buyerId, postProductId);

        return reportPostProduct != null;
    }

    private void updatePostProductAfterUpdateReport(
            UpdateReportPostRequest updatePostProductRequest,
            PostProduct postProduct
    ) {

        PostStatus postStatus;
        if (updatePostProductRequest.getReportStatusId() == 2) {
            postStatus = postStatusRepository.getReferenceById(6);
        } else {
            postStatus = postStatusRepository.getReferenceById(1);
        }

        postProduct.setPostStatusId(postStatus);

        postProductRepository.save(postProduct);
    }

    private void updateSellerAfterUpdateReport(
            UpdateReportSellerRequest updateReportSellerRequest,
            Seller seller
    ) {

        int isActive;
        if (updateReportSellerRequest.getReportStatusId() == 2) {
            isActive = 0;
        } else {
            isActive = 1;
        }

        seller.setIsActive(isActive);

        sellerRepository.save(seller);
    }

    private boolean checkReportedInReportSeller(Integer buyerId, Integer sellerId) {
        ReportSeller reportSeller = reportSellerRepository.checkReportedOfStudentInReportSeller(buyerId, sellerId);

        return reportSeller != null;
    }
}
