package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrderPostProductDTO;
import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.dto.SellerDTO;
import com.adkp.fuexchange.mapper.OrderPostProductMapper;
import com.adkp.fuexchange.mapper.OrdersMapper;
import com.adkp.fuexchange.mapper.SellerMapper;
import com.adkp.fuexchange.pojo.*;
import com.adkp.fuexchange.repository.*;
import com.adkp.fuexchange.request.RegisterToSellerRequest;
import com.adkp.fuexchange.request.UpdateInformationSellerRequest;
import com.adkp.fuexchange.request.UpdateStatusRequest;
import com.adkp.fuexchange.response.OrderDetailResponse;
import com.adkp.fuexchange.response.PostProductResponse;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.response.SellerInformationResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    private final SellerMapper sellerMapper;

    private final RegisteredStudentRepository registeredStudentRepository;

    private final PasswordEncoder passwordEncoder;

    private final OrderPostProductRepository orderPostProductRepository;

    private final OrdersMapper ordersMapper;

    private final OrderPostProductMapper orderPostProductMapper;

    private final RoleRepository roleRepository;

    private final OrdersRepository ordersRepository;

    private final PaymentRepository paymentRepository;

    private final TransactionsRepository transactionsRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, SellerMapper sellerMapper, RegisteredStudentRepository registeredStudentRepository, PasswordEncoder passwordEncoder, OrderPostProductRepository orderPostProductRepository, OrdersMapper ordersMapper, OrderPostProductMapper orderPostProductMapper, RoleRepository roleRepository, OrdersRepository ordersRepository, PaymentRepository paymentRepository, TransactionsRepository transactionsRepository) {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.registeredStudentRepository = registeredStudentRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderPostProductRepository = orderPostProductRepository;
        this.ordersMapper = ordersMapper;
        this.orderPostProductMapper = orderPostProductMapper;
        this.roleRepository = roleRepository;
        this.ordersRepository = ordersRepository;
        this.paymentRepository = paymentRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public ResponseObject<Object> viewInformationSellerById(int sellerId) {
        Seller seller = sellerRepository.getReferenceById(sellerId);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(SellerInformationResponse.builder()
                        .sellerTO(sellerMapper.toSellerDTO(seller))
                        .registeredStudentId(seller.getRegisteredStudentId().getRegisteredStudentId())
                        .build())
                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> registerToSeller(RegisterToSellerRequest registerToSellerRequest) {

        String content = "Mật khẩu không chính xác!";

        int status = HttpStatus.BAD_REQUEST.value();

        String message = HttpStatus.BAD_REQUEST.name();

        Object data = null;

        RegisteredStudent registeredStudent = registeredStudentRepository.getReferenceById(registerToSellerRequest.getRegisteredStudentId());


        if (passwordEncoder.matches(
                registerToSellerRequest.getPassword(),
                registeredStudent.getPassword()
        )) {

            Seller seller = sellerRepository.getInformationSellerByStudentId(registeredStudent.getStudentId().getStudentId());

            if (seller != null && seller.getIsActive() == 2) {
                content = "Tài khoản của bạn đã đăng ký trở thành người bán nhưng chưa được xác nhận!";
            } else if (seller == null) {

                registeredStudent.setRoleId(roleRepository.getReferenceById(2));

                Seller sellerSaved = sellerRepository.save(
                        Seller.builder()
                                .registeredStudentId(registeredStudent)
                                .bankingName(registerToSellerRequest.getBankingName())
                                .bankingNumber(registerToSellerRequest.getBankingNumber())
                                .isActive(2)
                                .build()
                );
                status = HttpStatus.OK.value();

                message = HttpStatus.OK.name();

                content = "Đăng ký thành công. Xin chờ xác nhận!";

                data = sellerMapper.toSellerDTO(sellerSaved);
            } else {
                content = "Tài khoản của bạn đã đăng ký trở thành người bán!";
            }
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(data)
                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> updateInformationSeller(UpdateInformationSellerRequest updateInformationSellerRequest) {
        if (sellerRepository.existsById(updateInformationSellerRequest.getSellerId())) {
            Seller seller = sellerRepository.getReferenceById(updateInformationSellerRequest.getSellerId());
            seller.setBankingNumber(updateInformationSellerRequest.getBankingNumber());
            seller.setBankingName(updateInformationSellerRequest.getBankingName());
            sellerRepository.save(seller);
            return ResponseObject.builder()
                    .status(HttpStatus.OK.value())
                    .message(HttpStatus.OK.name())
                    .content("Cập nhật thông tin thành công!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content("Thông tin người dùng không chính xác!")
                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> updateStatusSeller(UpdateStatusRequest updateStatusRequest) {

        Seller seller = sellerRepository.getReferenceById(updateStatusRequest.getSellerId());

        seller.setIsActive(updateStatusRequest.getIsActive());

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Cập nhật thông tin thành công!")
                .build();

    }

    @Override
    public SellerDTO checkSellerByStudentID(String studentId) {
        return sellerMapper.toSellerDTO(sellerRepository.getInformationSellerByStudentId(studentId));
    }

    @Override
    public ResponseObject<Object> getInformationSellerByStudentId(String studentId) {
        Seller seller = sellerRepository.getInformationSellerByStudentId(studentId);

        RegisteredStudent registeredStudent = registeredStudentRepository.getReferenceById(seller.getRegisteredStudentId().getRegisteredStudentId());
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("thông tin người bán hàng!")
                .data(SellerInformationResponse.builder().sellerTO(sellerMapper.toSellerDTO(seller))
                        .deliveryAddress(registeredStudent.getDeliveryAddress())
                        .build())
                .build();
    }

    @Override
    public void deleteSellerByID(int sellerID) {
        sellerRepository.deleteById(sellerID);
    }

    @Override
    public List<OrdersDTO> getOrderBySellerId(Integer sellerId) {
        return ordersMapper.toOrdersDTOList(orderPostProductRepository.getOrdersBySellerId(sellerId));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailBySellerIdAndOrderId(Integer sellerId, Integer orderId) {

        List<OrderPostProductDTO> orderPostProductList =
                orderPostProductMapper.toOrderPostProductDTOList(orderPostProductRepository.getOrdersDetailBySellerIdAndOrderId(sellerId, orderId));

        if (orderPostProductList.isEmpty()) {
            return null;
        }

        return getPostProductInOrder(orderPostProductList);
    }

    @Override
    public List<SellerDTO> getAllSeller() {
        List<Seller> sellers = sellerRepository.findAll();

        return sellerMapper.toSellerDTOList(sellers);
    }

    private long totalPriceInOrder(Integer orderId) {

        Orders orders = ordersRepository.getReferenceById(orderId);

        Payment payment = paymentRepository.getReferenceById(orders.getPaymentId().getPaymentId());

        Transactions transactions = transactionsRepository.getReferenceById(payment.getTransactionId().getTransactionsId());

        return transactions.getTotalPrice() * 1000;
    }

    private List<OrderDetailResponse> getPostProductInOrder(List<OrderPostProductDTO> orderPostProductList) {

        List<OrderDetailResponse> orderResponse = new ArrayList<>();

        List<PostProductResponse> postProductInOrder = new ArrayList<>();

        OrderPostProductDTO previousOrderProductDTO = null;

        PostProductResponse postProductResponse = new PostProductResponse();

        List<PostProductResponse> previousPostProductInOrder;

        OrderPostProductDTO last = orderPostProductList.get(orderPostProductList.size() - 1);

        for (OrderPostProductDTO currentOrderProductDTO : orderPostProductList) {

            currentOrderProductDTO.getPostProduct().setPriceBought(currentOrderProductDTO.getPriceBought() * 1000);

            if (previousOrderProductDTO != null) {

                postProductResponse = setInformationForSecond(currentOrderProductDTO, previousOrderProductDTO, postProductResponse);

                previousPostProductInOrder = postProductInOrder;

                postProductInOrder = postProductCanAdd(currentOrderProductDTO, previousOrderProductDTO, postProductInOrder, postProductResponse);

                if (currentOrderProductDTO.getPostProduct().getSellerId() != previousOrderProductDTO.getPostProduct().getSellerId()) {
                    orderResponse.add(
                            OrderDetailResponse.builder()
                                    .order(previousOrderProductDTO.getOrder())
                                    .postProductInOrder(previousPostProductInOrder)
                                    .build());
                }
                PostProductResponse lastPost = postProductInOrder.get(postProductInOrder.size() - 1);
                if (
                        last.getPostProduct().getPostProductId() == lastPost.getPostProductId() &&
                                last.getOrder().getOrderId() == currentOrderProductDTO.getOrder().getOrderId()
                                && last.getVariationDetail().getVariationDetailId() == currentOrderProductDTO.getVariationDetail().getVariationDetailId()
                ) {

                    orderResponse.add(
                            OrderDetailResponse.builder()
                                    .order(currentOrderProductDTO.getOrder())
                                    .postProductInOrder(postProductInOrder)
                                    .build());
                }

            } else {
                setInformationForFirst(currentOrderProductDTO, postProductResponse, postProductInOrder);
                if (orderPostProductList.size() == 1) {
                    orderResponse.add(
                            OrderDetailResponse.builder()
                                    .order(currentOrderProductDTO.getOrder())
                                    .postProductInOrder(postProductInOrder)
                                    .build());
                }
            }

            currentOrderProductDTO.getPostProduct().getProduct().getDetail().setProductImage(null);
            previousOrderProductDTO = currentOrderProductDTO;
        }

        return orderResponse;
    }

    private PostProductResponse setInformationForSecond(
            OrderPostProductDTO currentOrderProductDTO, OrderPostProductDTO previousOrderProductDTO,
            PostProductResponse postProductResponse
    ) {
        if (currentOrderProductDTO.getPostProduct().getPostProductId() == previousOrderProductDTO.getPostProduct().getPostProductId()
                && currentOrderProductDTO.getSttOrder() == previousOrderProductDTO.getSttOrder()
                && currentOrderProductDTO.getOrder().getOrderId() == previousOrderProductDTO.getOrder().getOrderId()
        ) {
            postProductResponse.setSecondVariation(
                    currentOrderProductDTO.getVariationDetail().getVariation().getVariationName() + ": "
                            + currentOrderProductDTO.getVariationDetail().getDescription()
            );
            return postProductResponse;
        } else {
            postProductResponse = new PostProductResponse();
            postProductResponse.setPostProductId(currentOrderProductDTO.getPostProduct().getPostProductId());
            postProductResponse.setPostStatusDTO(currentOrderProductDTO.getPostProduct().getPostStatus());
            postProductResponse.setFirstVariation(
                    currentOrderProductDTO.getVariationDetail().getVariation().getVariationName() + ": "
                            + currentOrderProductDTO.getVariationDetail().getDescription()
            );
            postProductResponse.setProductName(currentOrderProductDTO.getPostProduct().getProduct().getDetail().getProductName());
            postProductResponse.setPriceBought(currentOrderProductDTO.getPriceBought());
            postProductResponse.setQuantity(currentOrderProductDTO.getQuantity());
            postProductResponse.setImageUrlProduct(
                    currentOrderProductDTO.getPostProduct().getProduct().getDetail().getProductImage().get(0).getImageUrl()
            );
        }

        return postProductResponse;
    }

    private void setInformationForFirst(
            OrderPostProductDTO currentOrderProductDTO, PostProductResponse postProductResponse, List<PostProductResponse> postProductInOrder
    ) {
        postProductResponse.setPostProductId(currentOrderProductDTO.getPostProduct().getPostProductId());
        postProductResponse.setPostStatusDTO(currentOrderProductDTO.getPostProduct().getPostStatus());
        postProductResponse.setFirstVariation(
                currentOrderProductDTO.getVariationDetail().getVariation().getVariationName() + ": "
                        + currentOrderProductDTO.getVariationDetail().getDescription()
        );
        postProductResponse.setQuantity(currentOrderProductDTO.getQuantity());
        postProductResponse.setImageUrlProduct(
                currentOrderProductDTO.getPostProduct().getProduct().getDetail().getProductImage().get(0).getImageUrl()
        );
        postProductInOrder.add(postProductResponse);
    }

    private List<PostProductResponse> postProductCanAdd(
            OrderPostProductDTO currentOrderPostProductDTO, OrderPostProductDTO previousOrderPostProductDTO,
            List<PostProductResponse> postProductInOrder, PostProductResponse postProductResponse
    ) {
        if (currentOrderPostProductDTO.getOrder().getOrderId() == previousOrderPostProductDTO.getOrder().getOrderId()
                && currentOrderPostProductDTO.getPostProduct().getSellerId() == previousOrderPostProductDTO.getPostProduct().getSellerId()
        ) {
            if (currentOrderPostProductDTO.getPostProduct().getPostProductId() == previousOrderPostProductDTO.getPostProduct().getPostProductId()) {
                postProductInOrder.remove(postProductInOrder.size() - 1);
                postProductInOrder.add(postProductResponse);
            } else {
                postProductInOrder.add(postProductResponse);
            }
        } else {
            postProductInOrder = new ArrayList<>();
            postProductInOrder.add(postProductResponse);
        }
        return postProductInOrder;
    }
}
