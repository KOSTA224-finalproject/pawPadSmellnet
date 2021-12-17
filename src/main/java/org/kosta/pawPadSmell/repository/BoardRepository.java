package org.kosta.pawPadSmell.repository;

import org.kosta.pawPadSmell.entity.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, Integer> {

    Page<BoardDTO> findByTitleContaining(String searchKeyword, Pageable pageable);
}
