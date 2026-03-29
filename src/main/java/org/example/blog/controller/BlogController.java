package org.example.blog.controller;

import org.example.blog.service.BlogService;
import org.example.blog.vo.BlogEditRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate() {
        return "blog/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@RequestParam Map<String, Object> map) {
        int blogContSeq = this.blogService.create(map);
        return "redirect:/read/" + blogContSeq;
    }

    @GetMapping(value = "/read/{blogContSeq}")
    public String getRead(@PathVariable("blogContSeq") int blogContSeq, Model model) {
        Map<String, Object> blogCont = this.blogService.read(blogContSeq);
        model.addAttribute("blogCont", blogCont);
        return "blog/read";
    }

    @GetMapping(value = "/edit/{blogContSeq}")
    public ModelAndView getEdit(@PathVariable("blogContSeq") int blogContSeq) {
        ModelAndView mav = new ModelAndView("/blog/edit");
        Map<String, Object> blogCont = this.blogService.read(blogContSeq);

        if (blogCont == null) {
            mav.setViewName("redirect:/list");
            return mav;
        }

        mav.addObject("blogCont", blogCont);
        return mav;
    }

    @PutMapping(value = "/edit/{blgContSeq}")
    public String putEdit(BlogEditRequestVO blogEditRequestVO) {
        boolean isSuccessEdit = this.blogService.edit(blogEditRequestVO);

        if (isSuccessEdit) {
            return "redirect:/edit/" + blogEditRequestVO.getBlgContSeq();
        }

        return "redirect:/list";
    }

    @DeleteMapping(value = "/delete")
    public String delete(int blogContSeq) {
        this.blogService.delete(blogContSeq);
        return "redirect:/list";
    }
}
