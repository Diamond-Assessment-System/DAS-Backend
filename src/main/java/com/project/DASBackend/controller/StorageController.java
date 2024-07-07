package com.project.DASBackend.controller;

import com.project.DASBackend.service.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class StorageController {

    @Autowired
    private StorageService service;

    //    @PostMapping("/upload")
////    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) throws Exception {
////        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
////    }
        @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "assessmentPaperId") Integer assessmentPaperId) throws Exception {
        return new ResponseEntity<>(service.uploadFile(file, assessmentPaperId), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
//    @GetMapping("/download")
//    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
//        Resource resource=null;
//        try {
//            resource = service.dowloadFile(fileName);
//            String contentType = "application/octet-stream";
//            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}