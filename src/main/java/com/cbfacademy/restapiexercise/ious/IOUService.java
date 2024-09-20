package com.cbfacademy.restapiexercise.ious;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class IOUService {

    private final IOURepository iouRepository;

    @Autowired
    public IOUService(IOURepository iouRepository) {
        this.iouRepository = iouRepository;
    }

    public List<IOU> getAllIOUs() {
        return iouRepository.findAll();
    }

    public IOU getIOU(UUID id) throws NoSuchElementException {
        return iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("IOU not found"));
    }

    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
        if (iou.getAmount() == null || iou.getBorrower() == null || iou.getLender() == null) {
            throw new IllegalArgumentException("Invalid IOU data");
        }
        return iouRepository.save(iou);
    }

    public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
        if (!iouRepository.existsById(id)) {
            throw new NoSuchElementException("IOU not found");
        }
        updatedIOU.setId(id);
        return iouRepository.save(updatedIOU);
    }

    public void deleteIOU(UUID id) {
        iouRepository.deleteById(id);
    }
}
