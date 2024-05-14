package com.aloha.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.aloha.board.dto.Board;
import com.aloha.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    public BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Board> boarList = boardService.list();
        model.addAttribute("boardList", boarList);
        return "/board/list";
    }
    
    @GetMapping("/read")
    public String read(@RequestParam("no")int no, Model model) throws Exception {
        Board board  = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/read";
    }
    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }
    
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {
        int result = boardService.insert(board);
        if( result > 0){
            return "redirect:/board/list";
        }
        return "redirect:/board/insert?error";
    }

    @GetMapping("/update")
    public String update(@RequestParam("no")int no, Model model) throws Exception {
        Board board  = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/update";
    }
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {
        int result = boardService.update(board);
        if( result > 0){
            return "redirect:/board/list";
        }
        int no = board.getNo();
        return "redirect:/board/update?no=" + no + "&error";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no")int no) throws Exception {
        int result = boardService.delete(no);
        if( result > 0){
            return "redirect:/board/list";
        }
        return "redirect:/board/update?no=" + no + "&error";
    }
    
    
    
}
