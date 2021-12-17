package org.kosta.pawPadSmell.controller;

import org.kosta.pawPadSmell.entity.BoardDTO;
import org.kosta.pawPadSmell.service.pawPadSmellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class BoardController {


    @Autowired
    private pawPadSmellService boardService;
    @RequestMapping("/board/write")
    //@GetMapping("/board/write") //localhost:8090/board/write
    public String boardWriteForm() {

        return "boardwrite.html";
    }

    @PostMapping(value="/board/writepro")
    public String boardWritePro(BoardDTO boardDTO, Model model, MultipartFile file) throws Exception{

        boardService.write(boardDTO, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @RequestMapping("board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "postId", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<BoardDTO> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        System.out.println("zz");
        return "boardlist";
    }

    @GetMapping(value="/board/view") // localhost:8080/board/view?postId=1
    public String boardView(Model model, int postId) {

        model.addAttribute("boardDTO", boardService.boardView(postId));
        return "boardview";
    }

    @GetMapping(value="/board/modify/{postId}")
    public String boardModify(@PathVariable("postId") int postId,
                              Model model) {

        model.addAttribute("boardDTO", boardService.boardView(postId));

        return "boardmodify";
    }

    @PostMapping(value="/board/update/{postId}")
    public String boardUpdate(@PathVariable("postId") int postId, BoardDTO board, MultipartFile file) throws Exception{

        BoardDTO boardTemp = boardService.boardView(postId);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        return "redirect:/board/list";

    }
}
