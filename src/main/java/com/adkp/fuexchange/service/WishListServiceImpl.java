package com.adkp.fuexchange.service;

import com.adkp.fuexchange.pojo.PostProduct;
import com.adkp.fuexchange.pojo.PostStatus;
import com.adkp.fuexchange.pojo.Product;
import com.adkp.fuexchange.pojo.WishList;
import com.adkp.fuexchange.repository.*;
import com.adkp.fuexchange.request.RegisterWishListRequest;
import com.adkp.fuexchange.response.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;
    private final PostProductRepository postProductRepository;
    private final ProductRepository productRepository;
    private final RegisteredStudentRepository registeredStudentRepository;
    private final PostStatusRepository postStatusRepository;

    @Autowired

    public WishListServiceImpl(WishListRepository wishListRepository, PostProductRepository postProductRepository, ProductRepository productRepository, RegisteredStudentRepository registeredStudentRepository, PostStatusRepository postStatusRepository) {
        this.wishListRepository = wishListRepository;
        this.postProductRepository = postProductRepository;
        this.productRepository = productRepository;
        this.registeredStudentRepository = registeredStudentRepository;
        this.postStatusRepository = postStatusRepository;
    }


    @Override
    @Transactional
    public ResponseObject<Object> createWishList(RegisterWishListRequest registerWishListRequest) {

        PostProduct postProduct = postProductRepository.getReferenceById(registerWishListRequest.getPostProductId());

        if (wishListRepository.checkExistInWishList(
                registerWishListRequest.getPostProductId(), registerWishListRequest.getRegisteredStudentId()) != 0) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Bạn đã gửi yêu cầu tặng cho bài post này rồi!")
                    .build();
        }

        if (registerWishListRequest.getQuantity() > postProduct.getQuantity()) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Vui lòng không nhập quá số lượng bài đăng!")
                    .build();
        }

        WishList wishList = new WishList(postProductRepository.getReferenceById(registerWishListRequest.getPostProductId()),
                registeredStudentRepository.getReferenceById(registerWishListRequest.getRegisteredStudentId()),
                LocalDateTime.now(), registerWishListRequest.getQuantity(), false);
        wishListRepository.save(wishList);


        PostProductResponse postProductResponse = PostProductResponse.builder()
                .postProductId(postProduct.getPostProductId())
                .postTypeId(postProduct.getPostTypeId())
                .campusId(postProduct.getCampusId())
                .postStatusId(postProduct.getPostStatusId())
                .quantity(postProduct.getQuantity())
                .createDate(postProduct.getCreateDate())
                .content(postProduct.getContent())
                .build();

        WishListResponse response = WishListResponse.builder()
                .registeredStudentId(registerWishListRequest.getRegisteredStudentId())
                .postProductResponse(postProductResponse)
                .build();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Đã tạo wishlist thành công!")
                .data(response)
                .build();
    }

    @Override
    public ResponseObject<Object> viewWishListByPostProductID(int postProductID) {

        List<WishList> wishListList = wishListRepository.getWishListByPostProductID(postProductID);
        PostProduct postProduct = postProductRepository.getReferenceById(postProductID);
        Product product = productRepository.getReferenceById(postProduct.getProductId().getProductId());
        List<WishListResponse> wishListResponesList = new ArrayList<>();
        ProductResponse productResponse = ProductResponse.builder().categoryId(product.getCategoryId())
                .price(product.getPrice())
                .productDetailResponse(ProductDetailResponse.builder()
                        .productName(product.getProductDetailId().getProductName())
                        .description(product.getProductDetailId().getDescription())
                        .build())
                .build();
        PostProductResponse postProductResponse = PostProductResponse.builder()
                .postProductId(postProduct.getPostProductId())
                .postTypeId(postProduct.getPostTypeId())
                .campusId(postProduct.getCampusId())
                .postStatusId(postProduct.getPostStatusId())
                .quantity(postProduct.getQuantity())
                .createDate(postProduct.getCreateDate()).
                productResponse(productResponse)
                .content(postProduct.getContent())
                .build();
        for (WishList index : wishListList) {
            wishListResponesList.add(WishListResponse.builder()
                    .wishListId(index.getWishListId())
                    .registeredStudentId(index.getRegisteredStudentId().getRegisteredStudentId())
                    .postProductResponse(postProductResponse)
                    .createTime(index.getCreateTime())
                    .quantity(index.getQuantity())
                    .isActive(index.isActive())
                    .build());
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("xuât danh sách wishlist thành công!")

                .data(wishListResponesList)

                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> UpdateQuantity(int wishlistID, int quantity) {
        WishList wishList = wishListRepository.getReferenceById(wishlistID);
        if (wishList.getPostProductId().getQuantity() < quantity) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Vui lòng không được nhập quá số luọng bài đăng!")
                    .data(WishListResponse.builder().isActive(wishList.isActive()).build())
                    .build();
        }
        wishList.setQuantity(quantity);
        wishListRepository.save(wishList);

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("cập nhật thành công!")
                .data(WishListResponse.builder().quantity(quantity).build())
                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> UpdateActive(int wishListID, int active) {
        WishList wishList = wishListRepository.getReferenceById(wishListID);
        PostProduct postProduct = postProductRepository.getReferenceById(wishList.getPostProductId().getPostProductId());

        PostStatus postStatus = postStatusRepository.getReferenceById(1);
        // xem thu post product dang trang thai chap nhan giao dich ko và phai ve poststatus ==4 moi giao dich dc
        if (active == 1 && (postProduct.getPostStatusId().getPostStatusId() != 4)) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("bài đăng chưa được chấp nhận để giao dịch!")
                    .data(WishListResponse.builder().isActive(wishList.isActive()).build())
                    .build();
        }

        if (active == 1 && postProduct.getQuantity() == 0) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Sản phẩm đã hết!!")
                    .data(WishListResponse.builder().isActive(wishList.isActive()).build())
                    .build();
        }

        if (active == 1 && (postProduct.getQuantity() < wishList.getQuantity())) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("vui lòng cập nhật lại số lượng!")
                    .data(WishListResponse.builder().isActive(wishList.isActive()).build())
                    .build();
        }
        postProduct.setQuantity((active == 0) ? (postProduct.getQuantity() + wishList.getQuantity()) : (postProduct.getQuantity() - wishList.getQuantity()));
        if (postProduct.getQuantity() == 0) {
            postProduct.setPostStatusId(postStatus);
        }
        wishList.setActive((active == 0) ? false : true);
        postProductRepository.save(postProduct);
        wishListRepository.save(wishList);

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("cập nhật thành công!")
                .data(WishListResponse.builder().registeredStudentId(wishList.getRegisteredStudentId().getRegisteredStudentId()).isActive(wishList.isActive()).quantity(wishList.getQuantity()).build())
                .build();
    }

    @Override
    @Transactional
    public void deleteWishList(int wishListID) {
        wishListRepository.deleteById(wishListID);
    }


}
