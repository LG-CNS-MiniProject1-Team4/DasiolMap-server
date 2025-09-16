package com.dasiolmapserver.dasiolmap.bookmark.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasiolmapserver.dasiolmap.bookmark.domain.dto.BookmarkRequestDTO;
import com.dasiolmapserver.dasiolmap.bookmark.domain.dto.BookmarkResponseDTO;
import com.dasiolmapserver.dasiolmap.bookmark.service.BookmarkService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/auth/api/v2/dasiolmap/user/bookmark")
public class BookmarkCtrl {

    @Autowired
    private BookmarkService bookmarkService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<List<BookmarkResponseDTO>> register(
            @RequestBody BookmarkRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("[debug] >>> store review ctrl path POST : /register ");
        System.out.println("[debug] param dto = " + request);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<BookmarkResponseDTO> bookmarks = bookmarkService.insert(request);
        if (bookmarks.size() != 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookmarks);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/delete/{userId}/{bookmarkId}")
    public ResponseEntity<Void> delete(
            @PathVariable("userEmail") String userEmail,
            @PathVariable("bookmarkId") Integer bookmarkId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("[debug] >>> Store review ctrl path DELETE : /delete ");
        System.out.println("[debug] param userEmail = " + userEmail);
        System.out.println("[debug] param bookmarkId = " + bookmarkId);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        bookmarkService.delete(userEmail, bookmarkId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
