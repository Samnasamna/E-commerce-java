package com.shamna.Aesthetic.Shop.Services;

import com.shamna.Aesthetic.Shop.Entities.Address;
import com.shamna.Aesthetic.Shop.Entities.OrderData;
import com.shamna.Aesthetic.Shop.Repositories.AddressRepository;
import com.shamna.Aesthetic.Shop.Repositories.OrderDataRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {
    private final AddressRepository addressRepository;
    private final HttpServletRequest request;
    private final JwtService jwtService;
    private final OrderDataRepository orderDataRepository;

    public OrderService(AddressRepository addressRepository, HttpServletRequest request, JwtService jwtService, OrderDataRepository orderDataRepository) {
        this.addressRepository = addressRepository;
        this.request = request;
        this.jwtService = jwtService;

        this.orderDataRepository = orderDataRepository;
    }

    @Transactional
    public Optional<Address> updateAddress(Address address) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        if(addressRepository.findByUserId(username).isEmpty()){
            address.setUserId(username);
            addressRepository.save(address);

        }
        else{
            addressRepository.findByUserId(username).map(newaddress -> {
                newaddress.setAddress1(address.getAddress1());
                newaddress.setAddress2(address.getAddress2());
                newaddress.setDistrict(address.getDistrict());
                newaddress.setState(address.getState());
                return addressRepository.save(newaddress);
            } );
        }
        return addressRepository.findByUserId(username);

    }

    public Optional<Address> getAddress() {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return addressRepository.findByUserId(username);
    }


    public Optional<OrderData> getOrderDetails() {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return orderDataRepository.findByUserId(username);
    }

    public Optional<OrderData> addOrderData(OrderData data) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        data.setUserId(username);
        return Optional.of(orderDataRepository.save(data));
    }
}
