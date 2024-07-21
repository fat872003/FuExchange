package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ProductDTO;
import com.adkp.fuexchange.mapper.ProductMapper;
import com.adkp.fuexchange.pojo.*;
import com.adkp.fuexchange.repository.*;
import com.adkp.fuexchange.request.*;
import com.adkp.fuexchange.response.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductDetailRepository productDetailRepository;
    private final VariationDetailRepository variationDetailRepository;
    private final VariationRepository variationRepository;
    private final SellerRepository sellerRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;
    private final PostProductRepository postProductRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductDetailRepository productDetailRepository, VariationDetailRepository variationDetailRepository, VariationRepository variationRepository, SellerRepository sellerRepository, ProductImageRepository productImageRepository, ProductMapper productMapper, PostProductRepository postProductRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productDetailRepository = productDetailRepository;
        this.variationDetailRepository = variationDetailRepository;
        this.variationRepository = variationRepository;
        this.sellerRepository = sellerRepository;
        this.productImageRepository = productImageRepository;
        this.productMapper = productMapper;
        this.postProductRepository = postProductRepository;
    }

    @Override
    public ResponseObject<Object> topProductByUserIdAndName(String studentId, String productName, int current) {
        Pageable currentProduct = PageRequest.of(0, current);
        String newProductName = Optional.ofNullable(productName).map(String::valueOf).orElse("");
        Seller seller = sellerRepository.getInformationSellerByStudentId(studentId);

        List<ProductDTO> productDTO = productMapper.toProductDTOList(productRepository.filterSellerProduct(seller.getSellerId(), newProductName, currentProduct));
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem sản phẩm thành công!")
                .data(productDTO)
                .build();
    }

    @Override
    public ResponseObject<Object> getProductByProductID(int productID) {
        return ResponseObject.builder().status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Đây là thông tin sản phẩm")
                .data(
                        productMapper.toProductDTOList(productRepository.getProductByProductID(productID))
                )
                .build();
    }

    @Override
    @Transactional
    public ResponseObject<Object> createProduct(RegisterProductRequest registerProductRequest) {
        ProductDetail productDetail = new ProductDetail(registerProductRequest.getProductName(), registerProductRequest.getProductDescription());
        productDetailRepository.save(productDetail);

        for (ProductImageRequest productImageRequest : registerProductRequest.getProductImageRequestsList()) {
            productImageRepository.save(new ProductImage(productDetail, productImageRequest.getImageUrl()));
        }

        Seller seller = sellerRepository.getInformationSellerByStudentId(registerProductRequest.getStudentId());

        Product product = new Product(productDetail, sellerRepository.getReferenceById(seller.getSellerId())
                , categoryRepository.getReferenceById(registerProductRequest.getCategoryId()), Long.parseLong(new DecimalFormat("#.###").format(registerProductRequest.getPrice() / 1000))
                , true);
        productRepository.save(product);

        List<RegisterVariationResponse> variationList = new ArrayList<>();
        List<RegisterVariationDetailResponse> variationDetailResponseList = new ArrayList<>();
        for (VariationRequest variationRequest : registerProductRequest.getVariationList()) {
            Variation variation = new Variation(variationRequest.getVariationName(), product);
            variationRepository.save(variation);

            for (VariationDetailRequest variationDetailRequest : variationRequest.getVariationDetailRequestList()) {
                variationDetailRepository.save(new VariationDetail(variation, variationDetailRequest.getDescription()));
                variationDetailResponseList.add(new RegisterVariationDetailResponse(variationDetailRequest.getDescription()));
                variationList.add(new RegisterVariationResponse(variation.getVariationId(), variation.getVariationName(), variationDetailResponseList));
            }

        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Tạo sản phẩm thành công!").data(new RegisterProductRespone(product.getProductId()
                        , product.getSellerId().getSellerId(), product.getCategoryId(), product.getPrice(), product.isProductStatus()
                        , variationList, productDetail))
                .build();
    }


    @Override
    @Transactional
    public ProductDTO updateProductInformation(UpdateInformationProductRequest updateInformationProductRequest) {

        Product product = productRepository.getReferenceById(updateInformationProductRequest.getProductId());

        Category category = categoryRepository.getReferenceById(updateInformationProductRequest.getCategoryId());

        product.setProductDetailId(getProductDetail(updateInformationProductRequest, product));

        product.setCategoryId(category);

        product.setPrice(Long.parseLong(new DecimalFormat("#.###").format(updateInformationProductRequest.getPrice() / 1000)));

        product.setVariationId(getVariation(updateInformationProductRequest, product));

        product.getProductDetailId().setProductImageId(null);

        return productMapper.toProductDTO(productRepository.save(product));
    }

    private ProductDetail getProductDetail(
            UpdateInformationProductRequest updateInformationProductRequest, Product product
    ) {
        ProductDetail productDetail = product.getProductDetailId();

        productDetail.setProductName(updateInformationProductRequest.getProductName());

        productDetail.setDescription(updateInformationProductRequest.getProductDescription());

        return productDetail;
    }

    private List<Variation> getVariation(
            UpdateInformationProductRequest updateInformationProductRequest, Product product
    ) {
        List<VariationDetail> variationDetails = new ArrayList<>();

        List<Variation> variations = new ArrayList<>();

        for (UpdateVariationRequest updateVariationRequest : updateInformationProductRequest.getVariation()) {

            Variation variation = new Variation();

            variation.setVariationId(updateVariationRequest.getVariationId());

            variation.setVariationName(updateVariationRequest.getVariationName());

            variation.setProductId(product);

            for (UpdateVariationDetailRequest updateVariationDetailRequest : updateVariationRequest.getVariationDetail()) {

                VariationDetail variationDetail = new VariationDetail();

                variationDetail.setVariationDetailId(updateVariationDetailRequest.getVariationDetailId());

                variationDetail.setVariationId(variation);

                variationDetail.setDescription(updateVariationDetailRequest.getDescription());

                variationDetails.add(variationDetail);
            }

            variation.setVariationDetailId(variationDetails);

            variations.add(variation);
        }

        return variations;
    }

    @Override
    public ProductResponse getProductByVariationDetailId(List<Integer> variationDetailId) {

        List<VariationDetail> variationDetails = variationDetailRepository.getVariationDetailByVariationId(variationDetailId);

        List<Integer> variationIds = new ArrayList<>();

        List<VariationResponse> variations = getVariation(variationDetails, variationIds);

        ProductDTO product = productMapper.toProductDTO(productRepository.getProductByVariationId(variationIds));

        product.setVariation(null);
        product.setSeller(null);

        return ProductResponse.builder()
                .variation(variations)
                .product(product)
                .build();
    }

    @Override
    public ProductDTO deleteProduct(Integer productId) {
        Product product = productRepository.getReferenceById(productId);

        if (checkActiveProduct(productId)) {
            return null;
        }

        product.setProductStatus(false);

        return productMapper.toProductDTO(productRepository.save(product));
    }

    private List<VariationResponse> getVariation(List<VariationDetail> variationDetails, List<Integer> variationIds) {

        List<VariationResponse> variations = new ArrayList<>();

        for (VariationDetail detail : variationDetails) {
            variationIds.add(detail.getVariationId().getVariationId());

            VariationResponse variation = VariationResponse.builder()
                    .variationId(detail.getVariationId().getVariationId())
                    .variationName(detail.getVariationId().getVariationName())
                    .variationDetail(detail)
                    .build();

            detail.getVariationId().setProductId(null);
            detail.setVariationId(null);

            variations.add(variation);
        }
        return variations;
    }

    private boolean checkActiveProduct(Integer productId) {

        long productInPost = postProductRepository.checkProductInPost(productId);

        return productInPost != 0;
    }
}
