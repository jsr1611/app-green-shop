package uz.webbrain.appgreenshop.service.impl;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 6:13 PM
 */

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.webbrain.appgreenshop.dto.request.IncomingDto;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.entity.Incoming;
import uz.webbrain.appgreenshop.entity.Plant;
import uz.webbrain.appgreenshop.repository.IncomingRepository;
import uz.webbrain.appgreenshop.service.IncomingService;
import uz.webbrain.appgreenshop.service.PlantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class IncomingServiceImpl implements IncomingService {

    private final IncomingRepository incomingRepository;
    private final PlantService plantService;

    @Override
    public HttpEntity<?> addIncoming(IncomingDto incomingDto) {
        Response response = new Response();
        Incoming incoming = new Incoming();
        incoming.setPrice(incomingDto.getPrice());
        incoming.setSalePrice(incomingDto.getSalePrice());
        Plant plant = plantService.findById(incomingDto.getPlantId());
        incoming.setPlant(plant);
        incoming.setQuantity(incomingDto.getQuantity());
        incoming.setActive(incomingDto.getActive());
        incoming.setCreatedAt(incomingDto.getCreatedAt());
        response.setSuccess(true);
        incoming = incomingRepository.save(incoming);
        response.setData(incoming);
        response.setMessage("Data was successfully created.");
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> getAll() {
        Response response = new Response();
        List<Incoming> incomingList = incomingRepository.findAll();
        response.setSuccess(true);
        response.setDataList(new ArrayList<>(incomingList));
        if(incomingList.size() == 0){
            response.setMessage("No data was found.");
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> editIncoming(Long incomingId, IncomingDto incomingDto) {
        Response response = new Response();
        Incoming incoming = findById(incomingId);
        if(incoming == null){
            response.setSuccess(false);
            response.setMessage("No data found with id {" + incomingId + "}.");
//            throw new NotFoundException("No data found with id {" + incomingId + "}.");
        }else {
            incoming.setPrice(incomingDto.getPrice());
            incoming.setSalePrice(incomingDto.getSalePrice());
            Plant plant = plantService.findById(incomingDto.getPlantId());
            incoming.setPlant(plant);
            incoming.setQuantity(incomingDto.getQuantity());
            incoming.setActive(incomingDto.getActive());
            incoming.setCreatedAt(incomingDto.getCreatedAt());
            incoming = incomingRepository.save(incoming);
            response.setData(incoming);
            response.setSuccess(true);
        }
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }


    @Override
    public HttpEntity<?> deleteIncoming(Long incomingId) {
        Response response = new Response();
        Incoming incomingToBeDeleted = findById(incomingId);

        if(incomingToBeDeleted == null){
//            throw new NotFoundException("No data was found with id {" + incomingId + "}.");
            response.setSuccess(false);
            response.setMessage("No data was found with id {" + incomingId + "}.");
        } else {
            response.setMessage("Data was successfully deleted with id {" + incomingId + "}.");
            incomingRepository.delete(incomingToBeDeleted);
        }
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @Override
    public Incoming findById(Long id) {
            Incoming incomingFound = null;
            Optional<Incoming> optionalIncoming = incomingRepository.findById(id);
            if(optionalIncoming.isPresent()){
                incomingFound = optionalIncoming.get();
            }
            return incomingFound;
    }

    @Override
    public HttpEntity<?> findAllPageable(Pageable pageable) {
        Response response = new Response();
        Page<Incoming> incomingAll = incomingRepository.findAll(pageable);
        List<Incoming> incomingList = incomingAll.getContent();
        response.setSuccess(true);
        if(incomingList.size() == 0){
            response.setMessage("No data was found");
        }
        else {
            response.setMessage("Incoming list");
        }
//        response = new Response(true, "Incoming List", incomingList);
        response.setDataList(new ArrayList<>(incomingList));
        response.getMap().put("size", incomingAll.getSize());
        response.getMap().put("total_elements", incomingAll.getTotalElements());
        response.getMap().put("total_pages", incomingAll.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> findOneById(Long id) {
        Response response = new Response();
        Incoming incoming = findById(id);
        if(incoming == null){
            response.setSuccess(false);
            response.setMessage("Data was not found with id {" + id +"}" );
        }else {
            response.setSuccess(true);
            response.setData(incoming);
            response.setMessage("Data was successfully retrieved");
        }
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
