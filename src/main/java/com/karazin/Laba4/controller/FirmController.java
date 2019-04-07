package com.karazin.Laba4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.karazin.Laba4.dao.FirmDao;
import com.karazin.Laba4.model.Firm;

@Controller
public class FirmController {

    @Autowired
    private FirmDao firmDAO;

    @RequestMapping(value = "/")
    public ModelAndView listContact(ModelAndView model) throws IOException {
        List<Firm> firmList = firmDAO.getAll();
        model.addObject("firmList", firmList);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/newFirm", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Firm newFirm = new Firm();
        model.addObject("firm", newFirm);
        model.setViewName("FirmForm");
        return model;
    }

    @RequestMapping(value = "/findByLetter", method = RequestMethod.GET)
    public ModelAndView findByLetter(ModelAndView model,
            HttpServletRequest request) {
        String address = request.getParameter("l");
        List<Firm> firmList = firmDAO.getAllBYLetter(address.charAt(0));
        model.addObject("firmList", firmList);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/createFirm", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Firm firm) {
        firmDAO.create(firm);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteFirm", method = RequestMethod.GET)
    public ModelAndView deleteFirm(HttpServletRequest request) {
        int firmId = Integer.parseInt(request.getParameter("id"));
        firmDAO.delete(firmId);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editFirm", method = RequestMethod.GET)
    public ModelAndView editFirm(HttpServletRequest request) {
        int firmId = Integer.parseInt(request.getParameter("id"));
        Firm firm = firmDAO.get(firmId);
        ModelAndView model = new ModelAndView("editFirm");
        model.addObject("firm", firm);
        return model;
    }

    @RequestMapping(value = "/editFirm", method = RequestMethod.POST)
    public String editFirm(@ModelAttribute("firm") Firm firm) {
        firmDAO.update(firm);
        return "redirect:/";

    }
}
