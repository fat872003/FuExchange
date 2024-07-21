package com.adkp.fuexchange.service;

import com.adkp.fuexchange.pojo.OrderPostProduct;
import com.adkp.fuexchange.pojo.Product;
import com.adkp.fuexchange.repository.OrderPostProductRepository;
import com.adkp.fuexchange.repository.ProductRepository;
import com.adkp.fuexchange.response.DashBoardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPostProductServiceImpl implements OrderPostProductService {

    private final OrderPostProductRepository orderPostProductRepository;

    private final ProductRepository productRepository;

    public OrderPostProductServiceImpl(OrderPostProductRepository orderPostProductRepository, ProductRepository productRepository) {
        this.orderPostProductRepository = orderPostProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<DashBoardResponse> dashboardEachPost(Integer sellerId) {
        List<Product> getProduct = productRepository.getProductBySellerId(sellerId);

        List<OrderPostProduct> orderPostProducts
                = orderPostProductRepository.getAllOrdersByProduct(getProduct);

        return List.of();
    }

}
