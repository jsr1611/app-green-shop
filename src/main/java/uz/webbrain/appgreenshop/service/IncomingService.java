package uz.webbrain.appgreenshop.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import uz.webbrain.appgreenshop.dto.request.IncomingDto;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.entity.Incoming;

import java.util.List;

public interface IncomingService {

    HttpEntity<?> addIncoming(IncomingDto incomingDto);

    HttpEntity<?> findAllPageable(Pageable pageable);

    HttpEntity<?> getAll();

    HttpEntity<?> findOneById(Long id);

    HttpEntity<?> editIncoming(Long incomingId, IncomingDto incomingDto);

    HttpEntity<?> deleteIncoming(Long incomingId);

    Incoming findById(Long id);

}
