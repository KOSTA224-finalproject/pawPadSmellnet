package org.kosta.pawPadSmell.service;

import java.io.File;
import java.util.UUID;

import org.kosta.pawPadSmell.entity.BoardDTO;
import org.kosta.pawPadSmell.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class pawPadSmellService {

    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(BoardDTO boardDTO, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        boardDTO.setFilename(fileName);
        boardDTO.setFilepath("/files/" + fileName);

        boardRepository.save(boardDTO);
    }

    // 게시글 리스트 처리
    public Page<BoardDTO> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    public Page<BoardDTO> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public BoardDTO boardView(int postId) {
        return boardRepository.findById(postId).get();
    }

}
