package com.dasiolmapserver.dasiolmap.bookmark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.bookmark.domain.dto.BookmarkRequestDTO;
import com.dasiolmapserver.dasiolmap.bookmark.domain.dto.BookmarkResponseDTO;
import com.dasiolmapserver.dasiolmap.bookmark.domain.entity.BookmarkEntity;
import com.dasiolmapserver.dasiolmap.bookmark.repository.BookmarkRepository;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.dto.DasiolReviewResponseDTO;
import com.dasiolmapserver.dasiolmap.dasiolreview.domain.entity.DasiolReviewEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.domain.entity.DasiolStoreEntity;
import com.dasiolmapserver.dasiolmap.dasiolstore.repository.DasiolStoreRepository;
import com.dasiolmapserver.dasiolmap.user.domain.entity.UserEntity;
import com.dasiolmapserver.dasiolmap.user.repository.UserRepository;

@Service
public class BookmarkService {

        @Autowired
        private BookmarkRepository bookmarkRepository;

        @Autowired
        private DasiolStoreRepository dasiolStoreRepository;

        @Autowired
        private UserRepository userRepository;

        public List<BookmarkResponseDTO> select() {
                System.out.println("[debug] >>> store service select ");
                List<BookmarkEntity> list = bookmarkRepository.findAll();

                return list.stream()
                                .map(entity -> BookmarkResponseDTO.builder()
                                                .storeId(entity.getStore().getStoreId())
                                                .userEmail(entity.getUser().getEmail())
                                                .build())
                                .toList();
        }

        public List<BookmarkResponseDTO> insert(BookmarkRequestDTO request) {
                System.out.println("[debug] >>> store service insert ");

                // 스토어 엔티티 조회
                DasiolStoreEntity store = dasiolStoreRepository.findById(request.getStoreId())
                                .orElseThrow(() -> new RuntimeException("스토어 없음"));

                // 유저 엔티티 조회
                UserEntity user = userRepository.findByEmail(request.getUserEmail())
                                .orElseThrow(() -> new RuntimeException("유저 없음"));

                BookmarkEntity bookmark = BookmarkEntity.builder()
                                .store(store)
                                .user(user)
                                .build();
                if (bookmarkRepository.findByUserAndStore(user, store).isPresent()) {
                        throw new RuntimeException("이미 북마크된 가게입니다.");
                }
                user.getBookmarks().add(bookmark);
                bookmarkRepository.save(bookmark);
                Optional<BookmarkEntity> allBookmarks = bookmarkRepository.findByUser_Email(request.getUserEmail());
                return allBookmarks.stream()
                                .map(e -> BookmarkResponseDTO.fromEntity(e))
                                .toList();
        }

        public void delete(String email, Integer bookmarkId) {

                System.out.println("[debug] >>> storePhoto service delete storePhoto ");
                BookmarkEntity bookmark = bookmarkRepository.findById(bookmarkId)
                                .orElseThrow(() -> new RuntimeException("북마크가 존재하지 않습니다. ID=" + bookmarkId));

                if (!bookmark.getUser().getEmail().equals(email)) {
                        throw new RuntimeException("잘못된 요청입니다.");
                }

                bookmarkRepository.delete(bookmark);

        }
}
