package com.cbfacademy.restapiexercise.ious;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ious")
public class IOUController {

    private final IOUService iouService;

    @Autowired
    public IOUController(IOUService iouService) {
        this.iouService = iouService;
    }

    @GetMapping
    public ResponseEntity<List<IOU>> getAllIOUs() {
        List<IOU> ious = iouService.getAllIOUs();
        return ResponseEntity.ok(ious);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IOU> getIOU(@PathVariable UUID id) {
        IOU iou = iouService.getIOU(id);
        return ResponseEntity.ok(iou);
    }

    @PostMapping
    public ResponseEntity<IOU> createIOU(@RequestBody IOU iou) {
        IOU createdIOU = iouService.createIOU(iou);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIOU);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IOU> updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        IOU updated = iouService.updateIOU(id, updatedIOU);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIOU(@PathVariable UUID id) {
        iouService.deleteIOU(id);
        return ResponseEntity.noContent().build();
    }
}
