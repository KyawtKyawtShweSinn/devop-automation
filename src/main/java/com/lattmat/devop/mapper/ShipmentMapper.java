package com.lattmat.devop.mapper;

import com.lattmat.devop.dto.ShipmentEventDto;
import com.lattmat.devop.entity.Shipments;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ShipmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ShipmentEventDto convertShipmentDto(Shipments shipment) {
        return modelMapper.map(shipment, ShipmentEventDto.class);
    }

    public Shipments convertShipment(ShipmentEventDto shipmentEventDto) {
        return modelMapper.map(shipmentEventDto, Shipments.class);
    }
}