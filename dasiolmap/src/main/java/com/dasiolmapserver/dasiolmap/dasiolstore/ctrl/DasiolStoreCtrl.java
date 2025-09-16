package com.dasiolmapserver.dasiolmap.dasiolstore.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreRequestDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.dto.DasiolStoreResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.service.DasiolStoreService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/store")
public class DasiolStoreCtrl {

    @Autowired
    private DasiolStoreService storeService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/stores")
    public ResponseEntity<List<DasiolStoreResponseDTO>> stores(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("[debug] >>> store ctrl path : /stores ");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<DasiolStoreResponseDTO> list = storeService.select();

        return new ResponseEntity<List<DasiolStoreResponseDTO>>(list, HttpStatus.OK);

    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody DasiolStoreRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println("[debug] >>> store ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);

        DasiolStoreResponseDTO result = storeService.insert(request);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/read/{storeId}")
    public ResponseEntity<DasiolStoreResponseDTO> read(@PathVariable("storeId") Integer storeId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("[debug] >>>> store ctrl path GET : /read");
        System.out.println("[debug] params store id = " + storeId);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        DasiolStoreResponseDTO response = storeService.findStore(storeId);
        System.out.println("[debug] result = " + response);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{storeId}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Integer storeId,
            @RequestBody DasiolStoreRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("[debug] >>> Store ctrl path PUT : /update ");
        System.out.println("[debug] >>> param is = " + storeId);
        System.out.println("[debug] >>> param dto = " + request);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        DasiolStoreEntity result = storeService.update(storeId, request);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
