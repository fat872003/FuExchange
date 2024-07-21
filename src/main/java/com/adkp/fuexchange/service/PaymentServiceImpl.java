package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrderPostProductDTO;
import com.adkp.fuexchange.mapper.OrderPostProductMapper;
import com.adkp.fuexchange.pojo.*;
import com.adkp.fuexchange.repository.*;
import com.adkp.fuexchange.request.OrdersRequest;
import com.adkp.fuexchange.request.PostProductRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final OrdersRepository ordersRepository;

    private final PaymentRepository paymentRepository;

    private final TransactionsRepository transactionsRepository;

    private final RegisteredStudentRepository registeredStudentRepository;

    private final OrdersStatusRepository ordersStatusRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    private final OrderPostProductRepository orderPostProductRepository;

    private final PostProductRepository postProductRepository;

    private final VariationDetailRepository variationDetailRepository;

    private final TransactionsStatusRepository transactionsStatusRepository;

    private final PostStatusRepository postStatusRepository;

    private final OrderPostProductMapper orderPostProductMapper;

    private final CartPostRepository cartPostRepository;

    @Autowired
    public PaymentServiceImpl(OrdersRepository ordersRepository, PaymentRepository paymentRepository, TransactionsRepository transactionsRepository, RegisteredStudentRepository registeredStudentRepository, OrdersStatusRepository ordersStatusRepository, PaymentMethodRepository paymentMethodRepository, OrderPostProductRepository orderPostProductRepository, PostProductRepository postProductRepository, VariationDetailRepository variationDetailRepository, TransactionsStatusRepository transactionsStatusRepository, PostStatusRepository postStatusRepository, OrderPostProductMapper orderPostProductMapper, CartPostRepository cartPostRepository) {
        this.ordersRepository = ordersRepository;
        this.paymentRepository = paymentRepository;
        this.transactionsRepository = transactionsRepository;
        this.registeredStudentRepository = registeredStudentRepository;
        this.ordersStatusRepository = ordersStatusRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.orderPostProductRepository = orderPostProductRepository;
        this.postProductRepository = postProductRepository;
        this.variationDetailRepository = variationDetailRepository;
        this.transactionsStatusRepository = transactionsStatusRepository;
        this.postStatusRepository = postStatusRepository;
        this.orderPostProductMapper = orderPostProductMapper;
        this.cartPostRepository = cartPostRepository;
    }

    @Override
    @Transactional(rollbackOn = {DataIntegrityViolationException.class})
    public List<OrderPostProductDTO> payOrders(OrdersRequest ordersRequest) {

        long totalPrice = totalPrice(ordersRequest.getPostProductToBuyRequests());

        Payment paymentSaved = savePayment(ordersRequest.getPaymentMethodId());

        saveTransactions(paymentSaved, totalPrice);

        List<OrderPostProduct> orderSaved = saveOrderPostProduct(
                ordersRequest.getPostProductToBuyRequests(),
                ordersRequest.getRegisteredStudentId(),
                paymentSaved,
                ordersRequest
        );

        if (getPostProductInOrder(ordersRequest.getPostProductToBuyRequests()).size() >= 2) {
            removeAfterOrder(ordersRequest);
        }

        return orderPostProductMapper.toOrderPostProductDTOList(orderSaved);
    }

    private Payment savePayment(int getPaymentMethod) {
        boolean paymentStatus = getPaymentMethod != 1;

        PaymentMethod paymentMethod = paymentMethodRepository.getReferenceById(getPaymentMethod);

        return paymentRepository.save(
                new Payment(
                        paymentMethod,
                        paymentStatus,
                        LocalDateTime.now()
                )
        );

    }

    private void saveTransactions(Payment paymentSaved, double totalPrice) {
        String formatted = new DecimalFormat("#.###").format(totalPrice / 1000);
        double parsedValue = Double.parseDouble(formatted.replace(",", "."));
        long longValue = (long) parsedValue;
        transactionsRepository.save(
                new Transactions(
                        paymentSaved,
                        transactionsStatusRepository.getReferenceById(1),
                        longValue,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(3)
                )
        );
    }

    private long totalPrice(List<PostProductRequest> postProductRequestList) {
        long totalPrice = 0;

        Map<Integer, Integer> quantityEachPost = new HashMap<>();

        PostProductRequest previousProduct = null;
        for (PostProductRequest currentProduct : postProductRequestList) {
            if (previousProduct != null &&
                    currentProduct.getSttOrder() == previousProduct.getSttOrder()
            ) {
                if (currentProduct.getPostProductId() != previousProduct.getPostProductId()) {
                    quantityEachPost.merge(currentProduct.getPostProductId(), currentProduct.getQuantity(), Integer::sum);
                }
            } else {
                quantityEachPost.merge(currentProduct.getPostProductId(), currentProduct.getQuantity(), Integer::sum);
                totalPrice += currentProduct.getPrice() * currentProduct.getQuantity();
            }
            previousProduct = currentProduct;
        }
        calcQuantityPostProduct(quantityEachPost);
        return totalPrice;
    }

    private void calcQuantityPostProduct(Map<Integer, Integer> quantityEachPost) {
        List<Integer> postProductId = new ArrayList<>();
        quantityEachPost.forEach((key, value) -> {
            postProductId.add(key);
        });

        List<PostProduct> postProductList = postProductRepository.findAllById(postProductId);

        for (PostProduct postProduct : postProductList) {
            int quantity = quantityEachPost.get(postProduct.getPostProductId());

            if ((postProduct.getQuantity() - quantity) < 0) {
                throw new DataIntegrityViolationException("Số lượng mà bạn muốn mua đã vượt quá giới hạn mà sản phẩm hiện có!");
            }

            if (postProduct.getQuantity() - quantity == 0) {
                postProduct.setPostStatusId(postStatusRepository.getReferenceById(1));
            }

            postProduct.setQuantity(postProduct.getQuantity() - quantity);
        }

        postProductRepository.saveAll(postProductList);
    }

    private List<PostProduct> getPostProductInOrder(List<PostProductRequest> postProductRequests) {

        List<Integer> postProductId = new ArrayList<>();

        PostProductRequest previous = null;

        for (PostProductRequest current : postProductRequests) {
            if (previous != null) {
                if (current.getPostProductId() != previous.getPostProductId()) {
                    postProductId.add(current.getPostProductId());
                }
            } else {
                postProductId.add(current.getPostProductId());
            }
            previous = current;
        }

        return postProductRepository.findAllById(postProductId);
    }

    private List<OrderPostProduct> saveOrderPostProduct(
            List<PostProductRequest> postProductRequests,
            Integer registeredStudentId,
            Payment payment,
            OrdersRequest ordersRequest
    ) {
        PostProduct previousPostProduct = null;

        List<OrderPostProduct> orderPostProducts = new ArrayList<>();
        Orders orders = null;

        postProductRequests = sort(postProductRequests);

        for (PostProductRequest postProductRequest : postProductRequests) {
            PostProduct curentPostProduct = postProductRepository.getReferenceById(postProductRequest.getPostProductId());

            if (previousPostProduct != null) {
                if (curentPostProduct.getProductId().getSellerId().getSellerId() !=
                        previousPostProduct.getProductId().getSellerId().getSellerId()
                ) {
                    orders = saveOrder(registeredStudentId, payment, ordersRequest);
                }
            } else {
                orders = saveOrder(registeredStudentId, payment, ordersRequest);
            }

            orderPostProducts.add(
                    OrderPostProduct.builder()
                            .sttOrder(postProductRequest.getSttOrder())
                            .orderId(orders)
                            .postProductId(curentPostProduct)
                            .variationDetailId(variationDetailRepository.getReferenceById(postProductRequest.getVariationDetailId()))
                            .quantity(postProductRequest.getQuantity())
                            .priceBought(Long.parseLong(new DecimalFormat("#.###").format(postProductRequest.getPrice() / 1000)))
                            .build()
            );
            previousPostProduct = curentPostProduct;
        }

        return orderPostProductRepository.saveAll(orderPostProducts);
    }

    private Orders saveOrder(
            Integer registeredStudentId,
            Payment payment,
            OrdersRequest ordersRequest
    ) {

        OrderStatus orderStatus = ordersStatusRepository.getReferenceById(1);

        RegisteredStudent registeredStudent = registeredStudentRepository.getReferenceById(registeredStudentId);

        return ordersRepository.save(
                Orders.builder()
                        .registeredStudentId(registeredStudent)
                        .paymentId(payment)
                        .orderStatusId(orderStatus)
                        .createDate(LocalDateTime.now())
                        .completeDate(LocalDateTime.now().plusMonths(3))
                        .description(ordersRequest.getDescription())
                        .build()
        );
    }

    private void removeAfterOrder(
            OrdersRequest ordersRequest
    ) {

        RegisteredStudent registeredStudent = registeredStudentRepository.getReferenceById(ordersRequest.getRegisteredStudentId());

        Cart cart = registeredStudent.getCartId();

        List<Integer> variationDetailId = new ArrayList<>();

        List<Integer> postProductId = new ArrayList<>();
        for (PostProductRequest postProductToBuyRequest : ordersRequest.getPostProductToBuyRequests()) {
            variationDetailId.add(
                    postProductToBuyRequest.getVariationDetailId()
            );
            postProductId.add(
                    postProductToBuyRequest.getPostProductId()
            );
        }

        List<CartPost> cartPostsSaved = cartPostRepository.getCartPostByAllListId(
                cart.getCartId(),
                postProductId,
                variationDetailId
        );

        removeAlgorithm(cartPostsSaved);
    }

    private void removeAlgorithm(List<CartPost> cartPostsSaved) {

        if (cartPostsSaved.isEmpty()) {
            return;
        }

        for (CartPost currentCartPost : cartPostsSaved) {

            cartPostRepository.delete(currentCartPost);

        }
    }

    private List<PostProductRequest> sort(List<PostProductRequest> postProductRequests) {
        postProductRequests.sort(new Comparator<PostProductRequest>() {
            @Override
            public int compare(PostProductRequest p1, PostProductRequest p2) {
                return Integer.compare(p1.getSellerId(), p2.getSellerId());
            }
        });
        return postProductRequests;
    }
}
