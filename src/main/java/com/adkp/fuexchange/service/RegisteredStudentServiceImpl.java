package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrderPostProductDTO;
import com.adkp.fuexchange.dto.RegisteredStudentDTO;
import com.adkp.fuexchange.mapper.OrderPostProductMapper;
import com.adkp.fuexchange.mapper.RegisteredStudentMapper;
import com.adkp.fuexchange.pojo.RegisteredStudent;
import com.adkp.fuexchange.repository.OrderPostProductRepository;
import com.adkp.fuexchange.repository.RegisteredStudentRepository;
import com.adkp.fuexchange.request.UpdatePasswordRequest;
import com.adkp.fuexchange.response.OrderDetailResponse;
import com.adkp.fuexchange.response.PostProductResponse;
import com.adkp.fuexchange.response.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredStudentServiceImpl implements RegisteredStudentService {

    public final RegisteredStudentRepository registeredStudentRepository;

    public final RegisteredStudentMapper registeredStudentMapper;

    private final PasswordEncoder passwordEncoder;

    private final OrderPostProductRepository orderPostProductRepository;

    private final OrderPostProductMapper orderPostProductMapper;

    @Autowired
    public RegisteredStudentServiceImpl(RegisteredStudentRepository registeredStudentRepository, RegisteredStudentMapper registeredStudentMapper, PasswordEncoder passwordEncoder, OrderPostProductRepository orderPostProductRepository, OrderPostProductMapper orderPostProductMapper) {
        this.registeredStudentRepository = registeredStudentRepository;
        this.registeredStudentMapper = registeredStudentMapper;
        this.passwordEncoder = passwordEncoder;
        this.orderPostProductRepository = orderPostProductRepository;
        this.orderPostProductMapper = orderPostProductMapper;
    }

    @Override
    public RegisteredStudentDTO viewProfile(Integer registeredStudentId) {
        return registeredStudentMapper.toRegisteredStudentDTO(
                registeredStudentRepository.getReferenceById(registeredStudentId)
        );
    }

    @Override
    @Transactional
    public ResponseObject<Object> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        RegisteredStudent registeredStudentUpdate = registeredStudentRepository.getReferenceById(updatePasswordRequest.getIdWantUpdate());
        if (passwordEncoder.matches(updatePasswordRequest.getOldPassword(), registeredStudentUpdate.getPassword())) {
            if (updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getConfirmNewPassword())
            ) {
                registeredStudentUpdate.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
                registeredStudentRepository.save(registeredStudentUpdate);
                return ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message(HttpStatus.OK.name())
                        .content("Thay đổi mật khẩu thành công!")
                        .build();
            }
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Mật khẩu mới và xác nhận mật khẩu không trùng khớp!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content("Mật khẩu cũ không chính xác!")
                .build();

    }

    @Override
    public List<OrderDetailResponse> getOrdersDetailByRegisteredStudentId(Integer registeredStudentId) {

        List<OrderPostProductDTO> orderPostProductList =
                orderPostProductMapper.toOrderPostProductDTOList(orderPostProductRepository.getOrdersDetailByRegisteredStudentId(registeredStudentId));

        if (orderPostProductList.isEmpty()) {
            return null;
        }

        return getPostProductInOrder(orderPostProductList);
    }

    @Override
    public RegisteredStudentDTO updateDeliveryAddress(Integer registeredStudentId, String deliveryAddress) {

        RegisteredStudent registeredStudent = registeredStudentRepository.getReferenceById(registeredStudentId);

        registeredStudent.setDeliveryAddress(deliveryAddress);

        return registeredStudentMapper.toRegisteredStudentDTO(registeredStudentRepository.save(registeredStudent));
    }

    @Override
    public List<RegisteredStudentDTO> filterRegisteredStudent(String studentName) {

        String name = Optional.ofNullable(studentName).map(String::valueOf).orElse("");

        List<RegisteredStudent> registeredStudents = registeredStudentRepository.filterRegisteredStudent(name);

        return registeredStudentMapper.totoRegisteredStudentDTOList(registeredStudents);
    }

    @Override
    public RegisteredStudentDTO updateStatusRegisteredStudent(Integer registeredStudentId, Integer isActive) {
        RegisteredStudent registeredStudent = registeredStudentRepository.getReferenceById(registeredStudentId);

        registeredStudent.setActive(isActive != 0);

        return registeredStudentMapper.toRegisteredStudentDTO(registeredStudentRepository.save(registeredStudent));
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
            postProductResponse.setSellerId(currentOrderProductDTO.getPostProduct().getSellerId());
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
        postProductResponse.setSellerId(currentOrderProductDTO.getPostProduct().getSellerId());
        postProductResponse.setPriceBought(currentOrderProductDTO.getPriceBought());
        postProductResponse.setProductName(currentOrderProductDTO.getPostProduct().getProduct().getDetail().getProductName());
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
