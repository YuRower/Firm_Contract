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

import com.karazin.Laba4.dao.ContractDao;
import com.karazin.Laba4.model.Contract;

@Controller
public class ContractController {

    @Autowired
    private ContractDao contractDAO;

    @RequestMapping(value = "/newContract", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Contract contract = new Contract();
        model.addObject("contract", contract);
        model.setViewName("newContract");
        return model;
    }

    @RequestMapping(value = "/getAll")
    public ModelAndView listContact(ModelAndView model) throws IOException {
        List<Contract> contractList = contractDAO.getAll();
        model.addObject("contractList", contractList);
        model.setViewName("firmDogovor");
        return model;
    }

    @RequestMapping(value = "/createContract", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Contract contract) {
        contractDAO.create(contract);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteContract", method = RequestMethod.GET)
    public ModelAndView deleteContract(HttpServletRequest request) {
        int contractId = Integer.parseInt(request.getParameter("id"));
        contractDAO.delete(contractId);
        return new ModelAndView("redirect:/getAll");
    }

    @RequestMapping(value = "/allContracts", method = RequestMethod.GET)
    public ModelAndView editContract(ModelAndView model,
            HttpServletRequest request) {
        int contractId = Integer.parseInt(request.getParameter("id"));
        List<Contract> contractList = contractDAO.get(contractId);
        model.addObject("contractList", contractList);
        model.setViewName("firmDogovor");
        return model;
    }

    @RequestMapping(value = "/editContract", method = RequestMethod.GET)
    public ModelAndView editContract(HttpServletRequest request) {
        int contractId = Integer.parseInt(request.getParameter("id"));
        Contract contract = contractDAO.getContractById(contractId);
        ModelAndView model = new ModelAndView("editContract");
        model.addObject("contract", contract);
        return model;
    }

    @RequestMapping(value = "/findByDate", method = RequestMethod.GET)
    public ModelAndView editFirm(HttpServletRequest request) {
        String dayStart = request.getParameter("dayS");
        String dayEnd = request.getParameter("dayE");
        int idFirm = Integer.parseInt(request.getParameter("id"));
        List<Contract> contracts = contractDAO.getAllByRangeDate(dayStart,
                dayEnd, idFirm);
        ModelAndView model = new ModelAndView("firmDogovor");
        model.addObject("contractList", contracts);
        return model;
    }

    @RequestMapping(value = "/editContract", method = RequestMethod.POST)
    public String editContract(@ModelAttribute("contract") Contract contract) {
        contractDAO.update(contract);
        return "redirect:/";

    }

}
