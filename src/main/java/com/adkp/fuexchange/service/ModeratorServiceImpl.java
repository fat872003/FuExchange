package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.dto.PostProductDTO;
import com.adkp.fuexchange.dto.SellerDTO;
import com.adkp.fuexchange.mapper.OrdersMapper;
import com.adkp.fuexchange.mapper.PostProductMapper;
import com.adkp.fuexchange.mapper.SellerMapper;
import com.adkp.fuexchange.pojo.Orders;
import com.adkp.fuexchange.pojo.PostProduct;
import com.adkp.fuexchange.pojo.Seller;
import com.adkp.fuexchange.repository.OrdersRepository;
import com.adkp.fuexchange.repository.PostProductRepository;
import com.adkp.fuexchange.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    private final SellerRepository sellerRepository;

    private final SellerMapper sellerMapper;

    private final OrdersMapper ordersMapper;

    private final OrdersRepository ordersRepository;

    private final PostProductMapper postProductMapper;

    private final PostProductRepository postProductRepository;

    @Autowired
    public ModeratorServiceImpl(SellerRepository sellerRepository, SellerMapper sellerMapper, OrdersMapper ordersMapper, OrdersRepository ordersRepository, PostProductMapper postProductMapper, PostProductRepository postProductRepository) {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.ordersMapper = ordersMapper;
        this.ordersRepository = ordersRepository;
        this.postProductMapper = postProductMapper;
        this.postProductRepository = postProductRepository;
    }

    @Override
    public List<SellerDTO> viewRegisterToSellerRequest() {
        List<Seller> sellers = sellerRepository.viewRegisterToSellerRequest();
        return sellerMapper.toSellerDTOList(sellers);
    }

    @Override
    public List<OrdersDTO> viewCreateOrderRequest() {

        List<Orders> orders = ordersRepository.viewCreateOrderRequest();

        return ordersMapper.toOrdersDTOList(orders);
    }

    @Override
    public List<PostProductDTO> viewCreatePostProductRequest() {

        List<PostProduct> postProducts = postProductRepository.viewCreateOrderRequest();

        return postProductMapper.toPostProductDTOList(postProducts);
    }
}
