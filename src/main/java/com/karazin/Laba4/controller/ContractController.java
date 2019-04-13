package com.karazin.Laba4.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.karazin.Laba4.dao.ContractDao;
import com.karazin.Laba4.dao.FirmDao;
import com.karazin.Laba4.model.Contract;
import com.karazin.Laba4.model.Firm;

@Controller
public class ContractController {

    @Autowired
    private ContractDao contractDAO;

    @Autowired
    private FirmDao firmDAO;

    @RequestMapping(value = "/newContract", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Contract contract = new Contract();
        List<Firm> firmList = firmDAO.getAll();
        Map<Integer, String> firmMap = new LinkedHashMap<>();
        for (Firm firm : firmList) {
            firmMap.put(firm.getId(), firm.getName());
        }
        model.addObject("firmList", firmMap);
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
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/allContracts", method = RequestMethod.GET)
    public ModelAndView editContract(ModelAndView model,
            HttpServletRequest request) {
        int contractId = Integer.parseInt(request.getParameter("id"));
        List<Contract> contractList = contractDAO.get(contractId);
        Firm firmNam = firmDAO.get(contractId);
        model.addObject("firmName", firmNam.getName());
        model.addObject("contractList", contractList);
        model.setViewName("firmDogovor");
        return model;
    }

    @RequestMapping(value = "/editContract", method = RequestMethod.GET)
    public ModelAndView editContract(HttpServletRequest request) {
        int contractId = Integer.parseInt(request.getParameter("id"));
        Contract contract = contractDAO.getContractById(contractId);
        List<Firm> firmList = firmDAO.getAll();
        ModelAndView model = new ModelAndView("editContract");
        model.addObject("contract", contract);
        Map<Integer, String> firmMap = new LinkedHashMap<>();
        for (Firm firm : firmList) {
            firmMap.put(firm.getId(), firm.getName());
        }
        model.addObject("firmList", firmMap);

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
        System.out.println(contract);
        contractDAO.update(contract);
        return "redirect:/";

    }

}
