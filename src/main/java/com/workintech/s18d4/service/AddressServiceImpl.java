package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }


    @Override
    public Address find(long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            return addressOptional.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address delete(long id) {
        Address address = find(id);
        addressRepository.delete(address);
        return address;
    }
}
